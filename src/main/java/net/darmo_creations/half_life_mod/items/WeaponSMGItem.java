package net.darmo_creations.half_life_mod.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class WeaponSMGItem extends RangedWeaponItem {
  public WeaponSMGItem() {
    // TODO reload time
    super(new WeaponProperties(1, FirearmAmmoType.SMG_ROUND, 250, 600)
        .secondaryAmmo(FirearmAmmoType.SMG_GRENADE, 10, 60));
  }

  @Override
  protected SoundEvent getPrimaryFireSound() {
    return null; // TODO
  }

  @Override
  protected SoundEvent getSecondaryFireSound() {
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
