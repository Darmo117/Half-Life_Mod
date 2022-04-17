package net.darmo_creations.half_life_mod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.darmo_creations.half_life_mod.HalfLife;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

/**
 * A custom HUD that displays the amount of rupees, bombs and arrows of the player.
 * Also removes the food and XP bars.
 */
public class HUD extends GuiComponent {
  public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(HalfLife.MODID, "textures/gui/hud.png");
  public static final int TEXT_COLOR = 0x0010ff;
  public static final int BARS_X_OFFSET = 10;

  private final Minecraft minecraft;

  public HUD(Minecraft minecraft) {
    this.minecraft = minecraft;
  }

  public void render(PoseStack matrixStack) {
    Player player = Minecraft.getInstance().player;
    if (player == null || player.isCreative() || player.isSpectator()) {
      return;
    }
    RenderSystem.enableBlend();
    RenderSystem.defaultBlendFunc();
    int y = this.minecraft.getWindow().getGuiScaledHeight() - 20;
    this.renderHealth(matrixStack, player, y);
    this.renderArmor(matrixStack, player, y);
  }

  private void renderHealth(PoseStack matrixStack, Player player, int y) {
    RenderSystem.setShaderColor(1, 1, 1, 1);
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderTexture(0, TEXTURE_LOCATION);
    blit(matrixStack, BARS_X_OFFSET, y, 0, 0, 24, 24, 64, 64);
    int yOffset = (16 - this.minecraft.font.lineHeight) / 2;
    String text = "" + (int) (player.getHealth() * 5);
    drawString(matrixStack, this.minecraft.font, text, BARS_X_OFFSET + 20, y + yOffset, TEXT_COLOR);
  }

  private void renderArmor(PoseStack matrixStack, Player player, int y) {
    RenderSystem.setShaderColor(1, 1, 1, 1);
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderTexture(0, TEXTURE_LOCATION);
    int x = BARS_X_OFFSET + 50;
    blit(matrixStack, x, y, 0, 0, 24, 24, 64, 64);
    int yOffset = (16 - this.minecraft.font.lineHeight) / 2;
    String text = "" + player.getArmorValue(); // TODO scale to [0, 100]
    drawString(matrixStack, this.minecraft.font, text, x + 20, y + yOffset, TEXT_COLOR);
  }
}
