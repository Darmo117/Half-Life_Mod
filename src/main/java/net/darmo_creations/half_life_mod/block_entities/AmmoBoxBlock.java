package net.darmo_creations.half_life_mod.block_entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.lang.reflect.InvocationTargetException;

/**
 * Base class for blocks that contain ammo/weapons that can be picked up by players that collide with it.
 *
 * @param <T> Type of underlying block entity.
 */
public abstract class AmmoBoxBlock<T extends BlockEntity> extends Block implements BlockEntityType.BlockEntitySupplier<T> {
  private final Class<T> blockEntityClass;

  protected AmmoBoxBlock(Properties properties, final Class<T> blockEntityClass) {
    super(properties);
    this.blockEntityClass = blockEntityClass;
  }

  @Override
  public T create(BlockPos pos, BlockState state) {
    try {
      return this.blockEntityClass.getConstructor(BlockPos.class, BlockState.class).newInstance(pos, state);
    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}
