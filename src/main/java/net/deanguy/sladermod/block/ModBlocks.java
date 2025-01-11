package net.deanguy.sladermod.block;

import net.deanguy.sladermod.SladerMod;
import net.deanguy.sladermod.item.ModItems;
import net.minecraft.client.resources.model.Material;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

//testing commits

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SladerMod.MOD_ID);


    public static final RegistryObject<Block> BLOOD_BlOCK = registerBlock("blood_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5f).sound(SoundType.HONEY_BLOCK)));

    public static final RegistryObject<Block> EXPERIENCE_BlOCK = registerBlock("experience_block",
            () -> new DropExperienceBlock(UniformInt.of(81,297),BlockBehaviour.Properties.of()
                    .strength(0.5f).sound(SoundType.HONEY_BLOCK)));

    public static final RegistryObject<Block> STEEL_BlOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
