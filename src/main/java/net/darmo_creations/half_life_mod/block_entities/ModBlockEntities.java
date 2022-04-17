package net.darmo_creations.half_life_mod.block_entities;

import net.darmo_creations.half_life_mod.HalfLife;
import net.darmo_creations.half_life_mod.blocks.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Declares all block entity types added by this mod.
 */
public class ModBlockEntities {
  /**
   * Mod’s block entity registry.
   */
  public static final DeferredRegister<BlockEntityType<?>> REGISTER =
      DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, HalfLife.MODID);

  public static final RegistryObject<BlockEntityType<SpawnpointSetterBlockEntity>> SPAWNPOINT_SETTER = register(
      "spawnpoint_setter",
      SpawnpointSetterBlockEntity::new,
      ModBlocks.SPAWNPOINT_SETTER
  );
  public static final RegistryObject<BlockEntityType<KillTriggerBlockEntity>> KILL_TRIGGER = register(
      "kill_trigger",
      KillTriggerBlockEntity::new,
      ModBlocks.KILL_TRIGGER
  );
  public static final RegistryObject<BlockEntityType<AmmoBoxBlockEntity>> AMMO_BOX = register(
      "ammo_box",
      AmmoBoxBlockEntity::new,
      ModBlocks.AMMO_BOX // TEMP
  );

  /**
   * Register a new block entity type.
   *
   * @param name     Block entity’s name.
   * @param supplier A reference to the block entity’s constructor.
   * @param blocks   List of blocks this block entity is associated to.
   * @return The resulting registry object.
   */
  private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(
      String name, BlockEntityType.BlockEntitySupplier<T> supplier, Block... blocks) {
    //noinspection ConstantConditions
    return REGISTER.register(name, () -> BlockEntityType.Builder.of(supplier, blocks).build(null));
  }
}
