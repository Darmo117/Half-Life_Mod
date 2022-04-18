package net.darmo_creations.half_life_mod.block_entities.renderers;

import net.darmo_creations.half_life_mod.block_entities.KillTriggerBlockEntity;
import net.darmo_creations.half_life_mod.blocks.ModBlocks;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.Item;

/**
 * Renders a red box at the location of a {@link KillTriggerBlockEntity}
 * when the player is holding a {@link ModBlocks#KILL_TRIGGER} item.
 */
public class KillTriggerBlockEntityRenderer extends BoxBlockEntityRenderer<KillTriggerBlockEntity> {
  public KillTriggerBlockEntityRenderer(BlockEntityRendererProvider.Context ignored) {
  }

  @Override
  protected Item getItem() {
    return ModBlocks.KILL_TRIGGER.asItem();
  }

  @Override
  protected int getColor() {
    return 0xff0000;
  }
}
