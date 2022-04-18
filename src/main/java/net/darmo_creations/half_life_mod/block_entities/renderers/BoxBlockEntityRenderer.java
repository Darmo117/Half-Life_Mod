package net.darmo_creations.half_life_mod.block_entities.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.stream.Stream;

/**
 * Renders a red box at the location of a tile entity when the player holds a specific item.
 */
public abstract class BoxBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
  @Override
  public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
    LocalPlayer player = Minecraft.getInstance().player;
    if (player != null && Stream.of(InteractionHand.values()).anyMatch(hand -> player.getItemInHand(hand).getItem() == this.getItem())) {
      VertexConsumer vertexBuilder = bufferSource.getBuffer(RenderType.lines());
      float boxSize = this.getBoxSize();
      int color = this.getColor();
      float red = ((color >> 16) & 0xff) / 255f;
      float green = ((color >> 8) & 0xff) / 255f;
      float blue = (color & 0xff) / 255f;
      float alpha = 1 - ((color >> 24) & 0xff) / 255f;
      float start = 0.5f - boxSize / 2;
      float end = 0.5f + boxSize / 2;
      LevelRenderer.renderLineBox(
          poseStack, vertexBuilder,
          start, start, start,
          end, end, end,
          red, green, blue, alpha
      );
    }
  }

  protected float getBoxSize() {
    return 0.5f;
  }

  protected abstract Item getItem();

  protected abstract int getColor();
}
