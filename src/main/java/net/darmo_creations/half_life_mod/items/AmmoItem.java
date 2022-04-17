package net.darmo_creations.half_life_mod.items;

import net.darmo_creations.half_life_mod.HalfLife;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = HalfLife.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AmmoItem extends HalfLifeItem {
  private final FirearmAmmoType ammoType;

  public AmmoItem(final FirearmAmmoType ammoType) {
    super(new Properties().defaultDurability(ammoType.getMagazineCapacity()));
    this.ammoType = ammoType;
  }

  public FirearmAmmoType getAmmoType() {
    return this.ammoType;
  }

  public int getMagazineCapacity() {
    return this.ammoType.getMagazineCapacity();
  }

  public int getAmount(final ItemStack stack) {
    return this.getMagazineCapacity() - this.getDamage(stack);
  }

  public void setAmount(ItemStack stack, int amount) {
    stack.setDamageValue(this.getMagazineCapacity() - amount);
  }

  @SubscribeEvent
  public static void onPickup(PlayerEvent.ItemPickupEvent event) {
    ItemStack pickedStack = event.getStack();
    Item pickedItem = pickedStack.getItem();
    if (pickedItem instanceof AmmoItem pickedAmmo) {
      FirearmAmmoType ammoType = pickedAmmo.getAmmoType();
      Optional<ItemStack> optWeapon = event.getPlayer().getInventory().items.stream()
          .filter(stack -> (stack.getItem() instanceof RangedWeaponItem weapon)
              && (weapon.getPrimaryAmmoType() == ammoType
              || weapon.getSecondaryAmmoType() == ammoType))
          .findFirst();
      if (optWeapon.isPresent()) {
        ItemStack weaponStack = optWeapon.get();
        RangedWeaponItem weapon = (RangedWeaponItem) weaponStack.getItem();
        boolean someRemains;
        if (ammoType == weapon.getPrimaryAmmoType()) {
          someRemains = weapon.onPrimaryAmmoPickup(pickedStack, weaponStack, pickedAmmo);
        } else {
          someRemains = weapon.onSecondaryAmmoPickup(pickedStack, weaponStack, pickedAmmo);
        }
        if (someRemains) {
          // TODO spawn a new entity with remaining amount
        }
        // Prevent item from appearing in player’s inventory
        event.setCanceled(true);
      }
    }
  }
}
