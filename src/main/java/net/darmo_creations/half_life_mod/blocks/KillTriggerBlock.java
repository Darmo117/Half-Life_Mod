package net.darmo_creations.half_life_mod.blocks;

import net.darmo_creations.half_life_mod.block_entities.KillTriggerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * A block that kills any player that collides with it.
 */
public class KillTriggerBlock extends Block implements EntityBlock {
  public KillTriggerBlock() {
    super(Properties.of(Material.AIR).air().noCollission().noDrops());
  }

  @SuppressWarnings("deprecation")
  @Override
  public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
    return Shapes.empty();
  }

  @SuppressWarnings("deprecation")
  @Override
  public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
    if (entity instanceof ServerPlayer player) {
      if (!player.isCreative() && !player.isSpectator()) {
        player.kill();
      }
    }
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new KillTriggerBlockEntity(pos, state);
  }

  @SuppressWarnings("deprecation")
  @Override
  public RenderShape getRenderShape(BlockState state) {
    return RenderShape.ENTITYBLOCK_ANIMATED;
  }
}
