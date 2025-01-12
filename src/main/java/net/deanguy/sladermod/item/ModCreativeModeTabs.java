package net.deanguy.sladermod.item;

import net.deanguy.sladermod.SladerMod;
import net.deanguy.sladermod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SladerMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> STEEL_TAB = CREATIVE_MODE_TABS.register("steel_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STEEL_INGOT.get()))
                    .title(Component.translatable("creativetab.sladermod.steel"))
                    .displayItems((itemsDisplayParameters, output) -> {
                        output.accept(ModItems.STEEL_INGOT.get());
                        output.accept(ModBlocks.STEEL_BlOCK.get());
                        output.accept(ModItems.CHISEL.get());

                    }).build());

    public static final RegistryObject<CreativeModeTab> BLOOD_TAB = CREATIVE_MODE_TABS.register("blood_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BLOOD_BlOCK.get()))
                    .withTabsBefore(STEEL_TAB.getId())
                    .title(Component.translatable("creativetab.sladermod.blood"))
                    .displayItems((itemsDisplayParameters, output) -> {
                        output.accept(ModItems.BLOODBALL.get());
                        output.accept(ModBlocks.BLOOD_BlOCK.get());
                        output.accept(ModBlocks.EXPERIENCE_BlOCK.get());

            }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
