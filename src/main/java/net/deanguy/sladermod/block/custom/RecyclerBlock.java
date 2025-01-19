package net.deanguy.sladermod.block.custom;

import net.deanguy.sladermod.block.ModBlocks;
import net.deanguy.sladermod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class RecyclerBlock extends Block {
    public RecyclerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof ItemEntity itemEntity) {

            // Handle steel dust
            if (itemEntity.getItem().getItem() == ModItems.STEEL_DUST.get()) {
                itemEntity.setItem(new ItemStack(Items.IRON_INGOT, itemEntity.getItem().getCount()));
            }

            // Handle steel ingot
            if (itemEntity.getItem().getItem() == ModItems.STEEL_INGOT.get()) {
                itemEntity.setItem(new ItemStack(Items.IRON_INGOT, itemEntity.getItem().getCount()));
            }

            // Handle steel block
            if (itemEntity.getItem().getItem() == ModBlocks.STEEL_BlOCK.get().asItem()) {
                itemEntity.setItem(new ItemStack(Items.IRON_BLOCK, itemEntity.getItem().getCount()));
            }

            // Handle steel nugget
            if (itemEntity.getItem().getItem() == ModItems.STEEL_NUGGET.get()) {
                itemEntity.setItem(new ItemStack(Items.IRON_NUGGET, itemEntity.getItem().getCount()));
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.sladermod.recycler_block.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
