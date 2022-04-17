package net.darmo_creations.half_life_mod;

import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Defines various utility functions.
 */
public final class Utils {
  /**
   * Return the slot index of the first item that has the given class.
   *
   * @param player    The player whose inventory is to be scanned.
   * @param itemClass Item’s class.
   * @return The slot index or -1 if no item matched.
   */
  private static int getInventorySlot(Player player, Class<? extends Item> itemClass) {
    NonNullList<ItemStack> mainInventory = player.getInventory().items;
    for (int i = 0; i < mainInventory.size(); i++) {
      ItemStack itemStack = mainInventory.get(i);
      if (itemStack.getItem().getClass() == itemClass) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Play the item pickup sound to the specified player’s position.
   *
   * @param player The player to use the position of.
   */
  public static void playItemPickupSound(final Player player) {
    player.level.playSound(null, player.getX(), player.getY() + 0.5, player.getZ(),
        SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F,
        ((player.level.random.nextFloat() - player.level.random.nextFloat()) * 0.7F + 1) * 2);
  }

  /**
   * Gathers into a list the values of all static fields from the given class
   * whose types are assignable to the other given class.
   *
   * @param entriesClass Class to gather
   * @param entryClass   Class that fields should be assignable to.
   * @param <T>          Type of each entry.
   * @return A list containing the values of all gathered fields.
   */
  public static <T> List<T> gatherEntries(final Class<?> entriesClass, final Class<T> entryClass) {
    List<T> entries = new LinkedList<>();
    Arrays.stream(entriesClass.getDeclaredFields())
        .filter(field -> entryClass.isAssignableFrom(field.getType()))
        .map(field -> {
          T item;
          try {
            //noinspection unchecked
            item = (T) field.get(null);
          } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
          }
          return item;
        })
        .forEach(entries::add);
    return entries;
  }

  /**
   * Handy method that mimics the behavior of Python’s print function.
   *
   * @param objects The values to print to the console.
   */
  public static void print(Object... objects) {
    System.out.println(Arrays.stream(objects).map(Objects::toString).collect(Collectors.joining(" ")));
  }

  private Utils() {
  }
}
