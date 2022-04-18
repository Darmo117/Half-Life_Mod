package net.darmo_creations.half_life_mod.blocks;

import net.darmo_creations.half_life_mod.block_entities.SpawnpointSetterBlockEntity;
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
 * A block that sets the spawnpoint of any survival/adventure player that collides with it.
 * The spawnpoint is set to the position right below that of the collided block.
 */
public class SpawnpointSetterBlock extends Block implements EntityBlock {
  public SpawnpointSetterBlock() {
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
        // Set the spawn point of the player if it is not already within 2 blocks
        BlockPos currentSpawnPoint = player.getRespawnPosition();
        if (currentSpawnPoint == null || pos.distSqr(currentSpawnPoint) > 4) {
          player.setRespawnPosition(level.dimension(), pos.below(), player.getYRot(), true, false);
        }
      }
    }
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new SpawnpointSetterBlockEntity(pos, state);
  }

  @SuppressWarnings("deprecation")
  @Override
  public RenderShape getRenderShape(BlockState state) {
    return RenderShape.ENTITYBLOCK_ANIMATED;
  }
}
