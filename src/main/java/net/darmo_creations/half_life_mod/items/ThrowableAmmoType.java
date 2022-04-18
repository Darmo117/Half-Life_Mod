package net.darmo_creations.half_life_mod.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public enum ThrowableAmmoType implements AmmoType {
  GRENADE(null, null),
  SATCHEL_CHARGE(null, null),
  SNARK(null, null);

  private final Class<? extends Entity> entityClass; // TODO define for each type
  private final ResourceLocation hudTextureLocation; // TODO define for each type

  ThrowableAmmoType(final Class<? extends Entity> entityClass, final ResourceLocation hudTextureLocation) {
    this.entityClass = entityClass;
    this.hudTextureLocation = hudTextureLocation;
  }

  @Override
  public Entity fire(Level level, LivingEntity entity, float inaccuracy) {
    // TODO
    return null;
  }

  @Override
  public ResourceLocation getHUDTextureLocation() {
    return this.hudTextureLocation;
  }
}
