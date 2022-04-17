package net.darmo_creations.half_life_mod.block_entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Dummy block entity used for render only.
 */
public class KillTriggerBlockEntity extends BlockEntity {
  public KillTriggerBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntities.KILL_TRIGGER.get(), pos, state);
  }
}
