package net.darmo_creations.half_life_mod.items;

public enum FirearmAmmoType {
  GLOCK_ROUND(17, 8),
  COLT_ROUND(6, 40),
  SMG_ROUND(50, 5),
  SMG_GRENADE(1, 100),
  SHOTGUN_SHELL(8, 5),
  RPG_ROCKET(1, 100);

  private final int magazineCapacity;
  private final int baseDamage;

  FirearmAmmoType(final int magazineCapacity, int baseDamage) {
    this.magazineCapacity = magazineCapacity;
    this.baseDamage = baseDamage;
  }

  public int getMagazineCapacity() {
    return this.magazineCapacity;
  }

  public int getBaseDamage() {
    return this.baseDamage;
  }
}
