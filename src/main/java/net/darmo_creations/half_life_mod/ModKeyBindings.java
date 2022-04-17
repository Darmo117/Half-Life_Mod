package net.darmo_creations.half_life_mod;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

/**
 * List of all key bindings added by this mod.
 */
public class ModKeyBindings {
  public static final KeyMapping WEAPON_SECONDARY_FIRE = new KeyMapping("key.half_life.secondary_fire", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_8, "key.categories.half_life");
}
