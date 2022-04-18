package net.darmo_creations.half_life_mod;

import net.darmo_creations.half_life_mod.blocks.ModBlocks;
import net.darmo_creations.half_life_mod.blocks.NoGeneratedBlockItem;
import net.darmo_creations.half_life_mod.gui.HUD;
import net.darmo_creations.half_life_mod.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HalfLife.MODID)
public class HalfLife {
  public static final String MODID = "half_life";

  public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeTab();
  public static final HUD MAIN_HUD = new HUD(Minecraft.getInstance());

  public HalfLife() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    modEventBus.addListener(this::commonSetup);
    modEventBus.addListener(this::clientSetup);
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {
  }

  private void clientSetup(final FMLClientSetupEvent event) {
    // Custom key bindings
    ClientRegistry.registerKeyBinding(ModKeyBindings.WEAPON_SECONDARY_FIRE);
  }

  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
      event.registerBlockEntityRenderer(ModBlockEntities.KILL_TRIGGER.get(), KillTriggerBlockEntityRenderer::new);
      event.registerBlockEntityRenderer(ModBlockEntities.SPAWNPOINT_SETTER.get(), SpawnPointSetterBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
      blockRegistryEvent.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
      itemRegistryEvent.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
      // Generate BlockItems
      itemRegistryEvent.getRegistry().registerAll(ModBlocks.BLOCKS.stream()
          .filter(block -> !(block instanceof NoGeneratedBlockItem))
          .map(block -> {
            BlockItem blockItem = new BlockItem(block, new Item.Properties().tab(CREATIVE_MODE_TAB));
            //noinspection ConstantConditions
            blockItem.setRegistryName(block.getRegistryName());
            ModItems.ITEM_BLOCKS.put(block, blockItem);
            return blockItem;
          })
          .toArray(Item[]::new)
      );
    }
  }
}
