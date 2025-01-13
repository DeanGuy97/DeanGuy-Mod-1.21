package net.deanguy.sladermod.item;

import net.deanguy.sladermod.SladerMod;
import net.deanguy.sladermod.item.custom.ChiselItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SladerMod.MOD_ID);


    public static final RegistryObject<Item> STEEL_DUST = ITEMS.register("steel_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOODBALL = ITEMS.register("bloodball",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(1000)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
