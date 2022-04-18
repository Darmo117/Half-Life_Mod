package net.darmo_creations.half_life_mod.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.function.TriFunction;

public enum FirearmAmmoType implements AmmoType {
  GLOCK_ROUND(17, 8, null, null),
  COLT_ROUND(6, 40, null, null),
  SMG_ROUND(50, 5, null, null),
  SMG_GRENADE(1, 100, null, null),
  SHOTGUN_SHELL(8, 5, null, null),
  RPG_ROCKET(1, 100, null, null);

  private final int magazineCapacity;
  private final int baseDamage;
  private final ResourceLocation hudTextureLocation; // TODO define for each type
  private final TriFunction<Level, LivingEntity, Float, Entity> onFire; // TODO implement for each type

  FirearmAmmoType(final int magazineCapacity, final int baseDamage, final ResourceLocation hudTextureLocation,
                  final TriFunction<Level, LivingEntity, Float, Entity> onFire) {
    this.magazineCapacity = magazineCapacity;
    this.baseDamage = baseDamage;
    this.hudTextureLocation = hudTextureLocation;
    this.onFire = onFire;
  }

  @Override
  public Entity fire(Level level, LivingEntity entity, float inaccuracy) {
    return this.onFire.apply(level, entity, inaccuracy);
  }

  public int getMagazineCapacity() {
    return this.magazineCapacity;
  }

  public int getBaseDamage() {
    return this.baseDamage;
  }

  @Override
  public ResourceLocation getHUDTextureLocation() {
    return this.hudTextureLocation;
  }
}
