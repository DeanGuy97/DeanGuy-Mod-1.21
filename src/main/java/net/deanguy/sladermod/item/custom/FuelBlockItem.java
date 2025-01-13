package net.deanguy.sladermod.item.custom;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class FuelBlockItem extends BlockItem {
    private final int burnTime;

    // Constructor that accepts properties, burn time, and the block associated with the item
    public FuelBlockItem(Item.Properties properties, int burnTime, Block block) {
        super(block, properties);
        this.burnTime = burnTime;
    }

    // Override getBurnTime to return the specified burn time
    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTime;
    }
}