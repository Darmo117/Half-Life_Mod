package net.darmo_creations.half_life_mod.items;

import net.darmo_creations.half_life_mod.HalfLife;
import net.minecraft.world.item.Item;

public class HalfLifeItem extends Item {
  public HalfLifeItem(Properties properties) {
    super(properties.fireResistant().tab(HalfLife.CREATIVE_MODE_TAB));
  }
}
