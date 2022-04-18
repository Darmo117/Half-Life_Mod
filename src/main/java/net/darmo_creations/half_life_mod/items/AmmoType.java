package net.darmo_creations.half_life_mod.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public interface AmmoType {
  ResourceLocation getHUDTextureLocation();

  @Nullable
  Entity fire(Level level, LivingEntity entity, float inaccuracy);
}
