package net.darmo_creations.half_life_mod.items;

import net.darmo_creations.half_life_mod.Utils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public final class ModItems {
  // Weapons
  public static final Item CROWBAR = new CrowbarItem().setRegistryName("weapon_crowbar");
  public static final Item GLOCK_17 = new WeaponGlock17Item().setRegistryName("weapon_glock_17");
  public static final Item COLT = new WeaponColtItem().setRegistryName("weapon_colt");
  public static final Item SMG = new WeaponSMGItem().setRegistryName("weapon_smg");
  public static final Item SHOTGUN = new WeaponShotgunItem().setRegistryName("weapon_shotgun");
  public static final Item RPG = new WeaponRPGItem().setRegistryName("weapon_rpg");

  /**
   * The list of all declared items for this mod.
   */
  public static final List<Item> ITEMS;
  /**
   * The list of all generated items for this mod’s blocks.
   */
  public static final Map<Block, BlockItem> ITEM_BLOCKS = new HashMap<>();

  static {
    ITEMS = Utils.gatherEntries(ModItems.class, Item.class);
  }

  private ModItems() {
  }
}
