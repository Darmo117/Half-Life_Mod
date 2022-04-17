package net.darmo_creations.half_life_mod.gui;

import net.darmo_creations.half_life_mod.HalfLife;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Handles all render-related events for this mod.
 */
@Mod.EventBusSubscriber(modid = HalfLife.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class RenderEvents {
  /**
   * Removes all bars above the hotbar.
   */
  @SubscribeEvent
  public static void onGameOverlayRenderPre(final RenderGameOverlayEvent.PreLayer event) {
    System.out.println(OverlayRegistry.orderedEntries()); // DEBUG
  }

  /**
   * Renders the custom HUD.
   */
  @SubscribeEvent
  public static void onGameOverlayRenderPost(final RenderGameOverlayEvent.Post event) {
    if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
      HalfLife.MAIN_HUD.render(event.getMatrixStack());
    }
  }

  private RenderEvents() {
  }
}
