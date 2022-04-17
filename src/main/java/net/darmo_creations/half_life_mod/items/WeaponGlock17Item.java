package net.darmo_creations.half_life_mod.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class WeaponGlock17Item extends RangedWeaponItem {
  public WeaponGlock17Item() {
    // TODO reload time
    super(new WeaponProperties(1, FirearmAmmoType.GLOCK_ROUND, 250, 200)
        .secondaryFireRate(300));
  }

  @Override
  protected void onPrimaryFire(Level level, AmmoManager ammoManager) {
    // TODO fire primary ammo
  }

  @Override
  protected void onSecondaryFire(Level level, AmmoManager ammoManager) {
    // TODO fire primary ammo
  }

  @Override
  protected SoundEvent getPrimaryFireSound() {
    return null; // TODO
  }

  @Override
  protected SoundEvent getOutOfAmmoSound() {
    return null; // TODO
  }
}
