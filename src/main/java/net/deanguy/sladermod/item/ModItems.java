package net.deanguy.sladermod.item;

import net.deanguy.sladermod.SladerMod;
import net.deanguy.sladermod.block.ModBlocks;
import net.deanguy.sladermod.item.custom.FuelBlockItem;
import net.deanguy.sladermod.item.custom.ChiselItem;
import net.deanguy.sladermod.item.custom.FuelItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SladerMod.MOD_ID);


    public static final RegistryObject<Item> STEEL_DUST = ITEMS.register("steel_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOODBALL = ITEMS.register("bloodball",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(1000)));

    public static final RegistryObject<Item> GOLDEN_FILET_MIGNON = ITEMS.register("golden_filet_mignon",
            () -> new Item(new Item.Properties().food(ModFoodProperties.GOLDEN_FILET_MIGNON)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.sladermod.golden_filet_mignon"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final RegistryObject<Item> GOLDEN_KOBE_STEAK = ITEMS.register("golden_kobe_steak",
            () -> new Item(new Item.Properties().food(ModFoodProperties.GOLDEN_KOBE_STEAK)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.sladermod.golden_kobe_steak"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final RegistryObject<Item> GOLDEN_WAYGU_STEAK = ITEMS.register("golden_wagyu_steak",
            () -> new Item(new Item.Properties().food(ModFoodProperties.GOLDEN_WAGYU_STEAK)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.sladermod.golden_wagyu_steak"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final RegistryObject<Item> COMPRESSED_BIOMASS = ITEMS.register("compressed_biomass",
            () -> new FuelItem(new Item.Properties(), 3200){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.sladermod.compressed_biomass.tooltip"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final RegistryObject<Item> COMPRESSED_BIOMASS_BLOCK_ITEM = ITEMS.register("compressed_biomass_block_item",
            () -> new FuelBlockItem(new Item.Properties(), 32000, ModBlocks.COMPRESSED_BIOMASS_BLOCK.get()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
