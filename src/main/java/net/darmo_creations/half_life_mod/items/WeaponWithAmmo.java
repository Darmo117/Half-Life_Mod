package net.darmo_creations.half_life_mod.items;

import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public interface WeaponWithAmmo {
  AmmoType getPrimaryAmmoType();

  default @Nullable AmmoType getSecondaryAmmoType() {
    return null;
  }

  int getLoadedPrimaryAmmoAmount(ItemStack stack);

  int getReservePrimaryAmmoAmount(ItemStack stack);

  default int getSecondaryAmmoAmount(ItemStack stack) {
    return 0;
  }
}
