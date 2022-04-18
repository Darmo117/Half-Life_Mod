package net.darmo_creations.half_life_mod.block_entities.renderers;

import net.darmo_creations.half_life_mod.block_entities.SpawnpointSetterBlockEntity;
import net.darmo_creations.half_life_mod.blocks.ModBlocks;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.Item;

/**
 * Renders a blue box at the location of a {@link SpawnpointSetterBlockEntity}
 * when the player is holding a {@link ModBlocks#SPAWNPOINT_SETTER} item.
 */
public class SpawnPointSetterBlockEntityRenderer extends BoxBlockEntityRenderer<SpawnpointSetterBlockEntity> {
  public SpawnPointSetterBlockEntityRenderer(BlockEntityRendererProvider.Context ignored) {
  }

  @Override
  protected Item getItem() {
    return ModBlocks.SPAWNPOINT_SETTER.asItem();
  }

  @Override
  protected int getColor() {
    return 0x0080ff;
  }
}
