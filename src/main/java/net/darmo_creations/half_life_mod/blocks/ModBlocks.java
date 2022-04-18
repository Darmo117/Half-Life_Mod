package net.darmo_creations.half_life_mod.blocks;

import net.darmo_creations.half_life_mod.Utils;
import net.darmo_creations.half_life_mod.block_entities.AmmoBoxBlock;
import net.darmo_creations.half_life_mod.block_entities.AmmoBoxBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.List;

@SuppressWarnings("unused")
public final class ModBlocks {
  public static final Block SPAWNPOINT_SETTER = new SpawnpointSetterBlock().setRegistryName("spawnpoint_setter");
  public static final Block KILL_TRIGGER = new KillTriggerBlock().setRegistryName("kill_trigger");

  public static final Block SLIDING_DOOR = new SlidingDoorBlock().setRegistryName("sliding_door");
  public static final Block GLASS_SLIDING_DOOR = new SlidingDoorBlock().setRegistryName("glass_sliding_door");

  // TEMP
  public static final Block AMMO_BOX = (new AmmoBoxBlock<>(BlockBehaviour.Properties.of(Material.WOOD), AmmoBoxBlockEntity.class) {
  }).setRegistryName("ammo_box");

  /**
   * The list of all declared blocks for this mod.
   */
  public static final List<Block> BLOCKS;

  static {
    BLOCKS = Utils.gatherEntries(ModBlocks.class, Block.class);
  }

  private ModBlocks() {
  }
}
