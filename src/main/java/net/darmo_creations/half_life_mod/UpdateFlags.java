package net.darmo_creations.half_life_mod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

/**
 * This class defines constant that correspond to flags used by the
 * {@link Level#setBlock(BlockPos, BlockState, int)} and
 * {@link Level#markAndNotifyBlock(BlockPos, LevelChunk, BlockState, BlockState, int, int)} methods.
 */
@SuppressWarnings("unused")
public final class UpdateFlags {
  public static final int UPDATE_BLOCK = 1;
  public static final int SEND_TO_CLIENT = 2;
  public static final int PREVENT_RERENDER = 4;
  public static final int RERENDER_ON_MAIN_THREAD = 8;
  public static final int PREVENT_NEIGHBOR_REACTIONS = 16;
  public static final int PREVENT_NEIGHBOR_DROPS = 32;
  public static final int BLOCK_MOVED = 64;

  private UpdateFlags() {
  }
}
