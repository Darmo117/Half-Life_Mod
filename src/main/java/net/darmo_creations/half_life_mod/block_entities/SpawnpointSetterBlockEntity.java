package net.darmo_creations.half_life_mod.block_entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Dummy block entity used for render only.
 */
public class SpawnpointSetterBlockEntity extends BlockEntity {
  public SpawnpointSetterBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntities.SPAWNPOINT_SETTER.get(), pos, state);
  }
}
