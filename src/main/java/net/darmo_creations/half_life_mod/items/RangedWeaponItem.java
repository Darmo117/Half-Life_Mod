package net.darmo_creations.half_life_mod.items;

import net.darmo_creations.half_life_mod.HalfLife;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Objects;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = HalfLife.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class RangedWeaponItem extends HalfLifeItem implements WeaponWithAmmo {
  private final WeaponProperties weaponProperties;

  public RangedWeaponItem(final WeaponProperties weaponProperties) {
    super(new Properties().stacksTo(1));
    this.weaponProperties = weaponProperties;
  }

  @Override
  public FirearmAmmoType getPrimaryAmmoType() {
    return this.weaponProperties.primaryAmmoType;
  }

  public int getPrimaryAmmoCapacity() {
    return this.weaponProperties.primaryAmmoCapacity;
  }

  @Override
  public int getLoadedPrimaryAmmoAmount(ItemStack stack) {
    return this.getAmmoManager(stack).getLoadedPrimaryAmmoAmount();
  }

  @Override
  public int getReservePrimaryAmmoAmount(ItemStack stack) {
    return this.getAmmoManager(stack).getReservePrimaryAmmoAmmount();
  }

  @Override
  public FirearmAmmoType getSecondaryAmmoType() {
    return this.weaponProperties.secondaryAmmoType;
  }

  public int getSecondaryAmmoCapacity() {
    return this.weaponProperties.secondaryAmmoCapacity;
  }

  @Override
  public int getSecondaryAmmoAmount(ItemStack stack) {
    return this.getAmmoManager(stack).getSecondaryAmmoAmount();
  }

  public AmmoManager getAmmoManager(final ItemStack stack) {
    return new AmmoManager(stack);
  }

  @Override
  public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
    AmmoManager ammoManager = this.getAmmoManager(stack);
    long gameTime = context.getLevel().getGameTime();
    long timeSinceLastUse = gameTime - ammoManager.getLastUseTick();
    if (this.canFirePrimary(timeSinceLastUse)) {
      return InteractionResult.SUCCESS;
    }
    if (!context.getLevel().isClientSide() && context.getPlayer() != null) {
      this.playOutOfAmmoSound(context.getLevel(), context.getPlayer());
    }
    return InteractionResult.FAIL;
  }

  @Override
  public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int remainingTicks) {
    if (level.isClientSide()) {
      return;
    }
    AmmoManager ammoManager = this.getAmmoManager(itemStack);
    long gameTime = level.getGameTime();
    long timeSinceLastUse = gameTime - ammoManager.getLastUseTick();
    if (this.canFirePrimary(timeSinceLastUse)) {
      if (ammoManager.getLoadedPrimaryAmmoAmount() > 0) {
        this.onPrimaryFire(level, ammoManager);
        this.playPrimaryFireSound(level, livingEntity);
      } else {
        this.playOutOfAmmoSound(level, livingEntity);
      }
      ammoManager.setLastUseTick(gameTime);
    }
  }

  private void playPrimaryFireSound(Level level, LivingEntity entity) {
    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), this.getPrimaryFireSound(), SoundSource.HOSTILE, 3, 1);
  }

  private void playSecondaryFireSound(Level level, LivingEntity entity) {
    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), this.getSecondaryFireSound(), SoundSource.HOSTILE, 3, 1);
  }

  private void playOutOfAmmoSound(Level level, LivingEntity entity) {
    level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), this.getOutOfAmmoSound(), SoundSource.HOSTILE, 1, 1);
  }

  protected abstract SoundEvent getPrimaryFireSound();

  protected SoundEvent getSecondaryFireSound() {
    return this.getPrimaryFireSound();
  }

  protected abstract SoundEvent getOutOfAmmoSound();

  public boolean canFirePrimary(final long ticksSinceLastUse) {
    return ticksSinceLastUse >= this.getFireTickDelay(this.weaponProperties.primaryFireRate);
  }

  public boolean canFireSecondary(final long ticksSinceLastUse) {
    return ticksSinceLastUse >= this.getFireTickDelay(this.weaponProperties.secondaryFireRate);
  }

  private int getFireTickDelay(int fireRate) {
    return 60 * 20 / fireRate;
  }

  protected abstract void onPrimaryFire(Level level, AmmoManager ammoManager);

  protected void onSecondaryFire(Level level, AmmoManager ammoManager) {
  }

  public boolean onPrimaryAmmoPickup(ItemStack ammoStack, ItemStack playerWeapon, AmmoItem item) {
    AmmoManager ammoManager = this.getAmmoManager(playerWeapon);
    int remaining = ammoManager.onPrimaryAmmoPickup(item.getAmount(ammoStack));
    item.setAmount(ammoStack, remaining);
    return remaining != 0;
  }

  public boolean onSecondaryAmmoPickup(ItemStack ammoStack, ItemStack playerWeapon, AmmoItem item) {
    AmmoManager ammoManager = this.getAmmoManager(playerWeapon);
    int remaining = ammoManager.onSecondaryAmmoPickup(item.getAmount(ammoStack));
    item.setAmount(ammoStack, remaining);
    return remaining != 0;
  }

  public boolean onWeaponPickup(ItemStack pickedStack, ItemStack playerWeapon) {
    AmmoManager thisAmmoManager = this.getAmmoManager(playerWeapon);
    AmmoManager otherAmmoManager = this.getAmmoManager(pickedStack);
    int totalPrimaryAmmo = otherAmmoManager.getReservePrimaryAmmoAmmount() + otherAmmoManager.getLoadedPrimaryAmmoAmount();
    int primaryRemaining = thisAmmoManager.onPrimaryAmmoPickup(totalPrimaryAmmo);
    int secondaryRemaining = thisAmmoManager.onSecondaryAmmoPickup(otherAmmoManager.getSecondaryAmmoAmount());
    Pair<Integer, Integer> splitAmmo = AmmoManager.splitAmmo(totalPrimaryAmmo, 0, otherAmmoManager.getReservePrimaryAmmoAmmount());
    otherAmmoManager.reservePrimaryAmmoAmmount = splitAmmo.getLeft();
    otherAmmoManager.loadedPrimaryAmmoAmount = splitAmmo.getRight();
    otherAmmoManager.secondaryAmmoAmount = secondaryRemaining;
    return primaryRemaining + secondaryRemaining != 0;
  }

  @SubscribeEvent
  public static void onPickup(PlayerEvent.ItemPickupEvent event) {
    ItemStack pickedStack = event.getStack();
    Item pickedItem = pickedStack.getItem();
    if (pickedItem instanceof RangedWeaponItem pickedWeapon) {
      Optional<ItemStack> optPlayerWeapon = event.getPlayer().getInventory().items.stream()
          .filter(stack -> stack.getItem() == pickedWeapon)
          .findFirst();
      if (optPlayerWeapon.isPresent()) {
        ItemStack playerWeaponStack = optPlayerWeapon.get();
        RangedWeaponItem weapon = (RangedWeaponItem) playerWeaponStack.getItem();
        if (weapon.onWeaponPickup(pickedStack, playerWeaponStack)) {
          // TODO spawn a new entity with remaining amount
        }
        // Prevent item from appearing in player’s inventory
        event.setCanceled(true);
      }
    }
  }

  @Override
  public int getUseDuration(ItemStack stack) {
    return 72000;
  }

  public static class WeaponProperties {
    private final FirearmAmmoType primaryAmmoType;
    private final int primaryAmmoCapacity;
    private FirearmAmmoType secondaryAmmoType;
    private int secondaryAmmoCapacity;
    private int primaryFireRate;
    private int secondaryFireRate = -1;
    private int reloadTime;

    public WeaponProperties(int reloadTime, FirearmAmmoType primaryAmmoType, int primaryAmmoCapacity, int primaryFireRate) {
      if (reloadTime <= 0) {
        throw new IllegalArgumentException("reload time must be > 0");
      }
      this.reloadTime = reloadTime;
      this.primaryAmmoType = Objects.requireNonNull(primaryAmmoType);
      if (primaryAmmoCapacity <= 0) {
        throw new IllegalArgumentException("primary ammo capacity must be > 0");
      }
      if (primaryFireRate <= 0) {
        throw new IllegalArgumentException("primary fire rate must be > 0");
      }
      this.primaryAmmoCapacity = primaryAmmoCapacity;
      this.primaryFireRate = primaryFireRate;
    }

    public WeaponProperties secondaryAmmo(FirearmAmmoType secondaryAmmoType, int secondaryAmmoCapacity, int secondaryFireRate) {
      this.secondaryAmmoType = Objects.requireNonNull(secondaryAmmoType);
      if (secondaryAmmoCapacity <= 0) {
        throw new IllegalArgumentException("secondary ammo capacity must be > 0");
      }
      this.secondaryAmmoCapacity = secondaryAmmoCapacity;
      this.secondaryFireRate(secondaryFireRate);
      return this;
    }

    public WeaponProperties secondaryFireRate(int secondaryFireRate) {
      if (secondaryFireRate <= 0) {
        throw new IllegalArgumentException("secondary fire rate must be > 0");
      }
      this.secondaryFireRate = secondaryFireRate;
      return this;
    }
  }

  public final class AmmoManager {
    private static final String LOADED_PRIMARY_AMOUNT_KEY = "LoadedPrimaryAmmoAmount";
    private static final String RESERVE_PRIMARY_AMOUNT_KEY = "ReservePrimaryAmmoAmount";
    private static final String SECONDARY_AMOUNT_KEY = "SecondaryAmmoAmount";
    private static final String LAST_USE_TICK_KEY = "LastUseTick";

    private final ItemStack stack;
    private int loadedPrimaryAmmoAmount;
    private int reservePrimaryAmmoAmmount;
    private int secondaryAmmoAmount;
    private long lastUseTick;

    public AmmoManager(ItemStack stack) {
      if (stack.getItem() != RangedWeaponItem.this) {
        throw new IllegalArgumentException("expected item %s, got %s".formatted(RangedWeaponItem.this, stack.getItem()));
      }
      this.stack = stack;
      CompoundTag tag = Optional.ofNullable(stack.getTag()).orElseGet(CompoundTag::new);
      this.loadedPrimaryAmmoAmount = Math.min(RangedWeaponItem.this.getPrimaryAmmoType().getMagazineCapacity(), tag.getInt(LOADED_PRIMARY_AMOUNT_KEY));
      this.reservePrimaryAmmoAmmount = Math.min(RangedWeaponItem.this.getPrimaryAmmoCapacity(), tag.getInt(RESERVE_PRIMARY_AMOUNT_KEY));
      this.secondaryAmmoAmount = Math.min(RangedWeaponItem.this.getSecondaryAmmoCapacity(), tag.getInt(SECONDARY_AMOUNT_KEY));
      this.lastUseTick = tag.getLong(LAST_USE_TICK_KEY);
    }

    public long getLastUseTick() {
      return this.lastUseTick;
    }

    public void setLastUseTick(long lastUseTick) {
      this.lastUseTick = lastUseTick;
      this.updateStackData();
    }

    public int getReservePrimaryAmmoAmmount() {
      return this.reservePrimaryAmmoAmmount;
    }

    public int getLoadedPrimaryAmmoAmount() {
      return this.loadedPrimaryAmmoAmount;
    }

    public void consumePrimaryAmmo(int amount) {
      this.loadedPrimaryAmmoAmount = Math.min(0, this.loadedPrimaryAmmoAmount - amount);
      this.updateStackData();
    }

    public boolean reload() {
      int diff = RangedWeaponItem.this.getPrimaryAmmoCapacity() - this.loadedPrimaryAmmoAmount;
      if (this.reservePrimaryAmmoAmmount >= diff) {
        this.reservePrimaryAmmoAmmount -= diff;
        this.loadedPrimaryAmmoAmount += diff;
        this.updateStackData();
        return true;
      } else {
        this.loadedPrimaryAmmoAmount = this.reservePrimaryAmmoAmmount;
        this.reservePrimaryAmmoAmmount = 0;
        this.updateStackData();
        return this.loadedPrimaryAmmoAmount != 0;
      }
    }

    public int onPrimaryAmmoPickup(int amount) {
      Pair<Integer, Integer> splitAmmo = splitAmmo(this.reservePrimaryAmmoAmmount, amount, RangedWeaponItem.this.getPrimaryAmmoCapacity());
      this.reservePrimaryAmmoAmmount = splitAmmo.getLeft();
      this.updateStackData();
      return splitAmmo.getRight();
    }

    public int getSecondaryAmmoAmount() {
      return this.secondaryAmmoAmount;
    }

    public void consumeSecondaryAmmo(int amount) {
      this.secondaryAmmoAmount = Math.min(0, this.secondaryAmmoAmount - amount);
      this.updateStackData();
    }

    public int onSecondaryAmmoPickup(int amount) {
      Pair<Integer, Integer> splitAmmo = splitAmmo(this.secondaryAmmoAmount, amount, RangedWeaponItem.this.getSecondaryAmmoCapacity());
      this.secondaryAmmoAmount = splitAmmo.getLeft();
      this.updateStackData();
      return splitAmmo.getRight();
    }

    private void updateStackData() {
      CompoundTag tag = new CompoundTag();
      tag.putInt(LOADED_PRIMARY_AMOUNT_KEY, this.loadedPrimaryAmmoAmount);
      tag.putInt(RESERVE_PRIMARY_AMOUNT_KEY, this.reservePrimaryAmmoAmmount);
      tag.putInt(SECONDARY_AMOUNT_KEY, this.secondaryAmmoAmount);
      tag.putLong(LAST_USE_TICK_KEY, this.lastUseTick);
      this.stack.setTag(tag);
    }

    public static Pair<Integer, Integer> splitAmmo(int currentAmount, int amountToAdd, int capacity) {
      int total = currentAmount + amountToAdd;
      int overflow = total - capacity;
      return new ImmutablePair<>(total - overflow, overflow);
    }
  }
}
