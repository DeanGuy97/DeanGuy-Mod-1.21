package net.deanguy.sladermod;

import com.mojang.logging.LogUtils;
import net.deanguy.sladermod.block.ModBlocks;
import net.deanguy.sladermod.item.ModCreativeModeTabs;
import net.deanguy.sladermod.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SladerMod.MOD_ID)
public class SladerMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "sladermod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public SladerMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.STEEL_DUST);
            event.accept(ModItems.STEEL_NUGGET);
            event.accept(ModItems.STEEL_INGOT);
            event.accept(ModItems.BLOODBALL);
            event.accept(ModItems.COMPRESSED_BIOMASS);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.BLOOD_BlOCK);
            event.accept(ModBlocks.STEEL_BlOCK);
            event.accept(ModBlocks.EXPERIENCE_BlOCK);
            event.accept(ModBlocks.COMPRESSED_BIOMASS_BLOCK);
        }

        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.CHISEL);
        }

        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.HEALING_BlOCK);
            event.accept(ModBlocks.RECYCLER_BlOCK);
        }

        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.GOLDEN_FILET_MIGNON);
            event.accept(ModItems.GOLDEN_KOBE_STEAK);
            event.accept(ModItems.GOLDEN_WAYGU_STEAK);
        }
    }

    @SubscribeEvent
    public void onFuelRegistry(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == ModItems.COMPRESSED_BIOMASS_BLOCK_ITEM.get()) {
            event.setBurnTime(16000); // Set burn time for Compressed Biomass Block
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}