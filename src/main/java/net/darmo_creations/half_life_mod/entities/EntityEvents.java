package net.darmo_creations.half_life_mod.entities;

import net.darmo_creations.half_life_mod.HalfLife;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HalfLife.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class EntityEvents {
  /**
   * Keeps players’ food level at its maximum value.
   */
  @SubscribeEvent
  public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
    event.player.getFoodData().setFoodLevel(20);
    event.player.getFoodData().setSaturation(5);
  }

  private EntityEvents() {
  }
}
