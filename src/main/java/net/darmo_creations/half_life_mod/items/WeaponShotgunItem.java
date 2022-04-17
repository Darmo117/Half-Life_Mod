package net.darmo_creations.half_life_mod.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class WeaponShotgunItem extends RangedWeaponItem {
  public WeaponShotgunItem() {
    // TODO reload time
    super(new WeaponProperties(1, FirearmAmmoType.SHOTGUN_SHELL, 125, 80)
        .secondaryFireRate(40));
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

  @Override
  protected void onSecondaryFire(Level level, AmmoManager ammoManager) {
    // TODO
  }
}
