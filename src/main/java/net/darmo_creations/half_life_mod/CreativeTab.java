package net.darmo_creations.half_life_mod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

/**
 * Creative mode tab for this mod.
 */
public class CreativeTab extends CreativeModeTab {
  public CreativeTab() {
    super(HalfLife.MODID);
  }

  @Override
  public ItemStack makeIcon() {
    return null; // TODO
  }
}
