package net.darmo_creations.half_life_mod.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class WeaponColtItem extends RangedWeaponItem {
  public WeaponColtItem() {
    // TODO reload time
    super(new WeaponProperties(1, FirearmAmmoType.COLT_ROUND, 36, 80));
  }

  @Override
  protected SoundEvent getPrimaryFireSound() {
    return null; // TODO
  }

  @Override
  protected SoundEvent getOutOfAmmoSound() {
    return null; // TODO
  }

  @Override
  protected void onPrimaryFire(Level level, AmmoManager ammoManager) {
    // TODO
  }
}
