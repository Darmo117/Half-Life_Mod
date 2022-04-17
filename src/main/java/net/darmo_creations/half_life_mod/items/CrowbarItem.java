package net.darmo_creations.half_life_mod.items;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

/**
 * The crowbar has the exact same attack properties as the regular iron sword.
 */
public class CrowbarItem extends SwordItem {
  public CrowbarItem() {
    super(Tiers.IRON, 1, -2.4f, new Properties().stacksTo(1));
  }
}
