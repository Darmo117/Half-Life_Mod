package net.darmo_creations.half_life_mod.block_entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class AmmoBoxBlockEntity extends SynchronizedBlockEntity {
  public AmmoBoxBlockEntity(BlockPos pos, BlockState state) {
    super(ModBlockEntities.AMMO_BOX.get(), pos, state);
  }

  // TODO container
}
