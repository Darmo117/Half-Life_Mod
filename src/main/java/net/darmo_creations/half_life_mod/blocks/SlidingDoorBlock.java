package net.darmo_creations.half_life_mod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

// TODO change sounds?
public class SlidingDoorBlock extends DoorBlock {
  protected static final VoxelShape SOUTH_RIGHT_OPEN_AABB = Block.box(0, 0, 0, 1, 16, 3);
  protected static final VoxelShape NORTH_RIGHT_OPEN_AABB = Block.box(15, 0, 13, 16, 16, 16);
  protected static final VoxelShape WEST_RIGHT_OPEN_AABB = Block.box(13, 0, 0, 16, 16, 1);
  protected static final VoxelShape EAST_RIGHT_OPEN_AABB = Block.box(0, 0, 15, 3, 16, 16);

  protected static final VoxelShape SOUTH_LEFT_OPEN_AABB = Block.box(15, 0, 0, 16, 16, 3);
  protected static final VoxelShape NORTH_LEFT_OPEN_AABB = Block.box(0, 0, 13, 1, 16, 16);
  protected static final VoxelShape WEST_LEFT_OPEN_AABB = Block.box(13, 0, 15, 16, 16, 16);
  protected static final VoxelShape EAST_LEFT_OPEN_AABB = Block.box(0, 0, 0, 3, 16, 1);

  public SlidingDoorBlock() {
    super(Properties.of(Material.METAL).sound(SoundType.METAL));
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
    Direction direction = state.getValue(FACING);
    boolean closed = !state.getValue(OPEN);
    boolean rightHinge = state.getValue(HINGE) == DoorHingeSide.RIGHT;
    //noinspection EnhancedSwitchMigration
    switch (direction) {
      case EAST:
      default:
        return closed ? EAST_AABB : (rightHinge ? EAST_RIGHT_OPEN_AABB : EAST_LEFT_OPEN_AABB);
      case SOUTH:
        return closed ? SOUTH_AABB : (rightHinge ? SOUTH_RIGHT_OPEN_AABB : SOUTH_LEFT_OPEN_AABB);
      case WEST:
        return closed ? WEST_AABB : (rightHinge ? WEST_RIGHT_OPEN_AABB : WEST_LEFT_OPEN_AABB);
      case NORTH:
        return closed ? NORTH_AABB : (rightHinge ? NORTH_RIGHT_OPEN_AABB : NORTH_LEFT_OPEN_AABB);
    }
  }
}
