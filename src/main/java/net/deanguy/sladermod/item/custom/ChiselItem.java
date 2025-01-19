package net.deanguy.sladermod.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP = new HashMap<>();

    static {
        // Forward mappings
        CHISEL_MAP.put(Blocks.STONE, Blocks.STONE_BRICKS);
        CHISEL_MAP.put(Blocks.END_STONE, Blocks.END_STONE_BRICKS);
        CHISEL_MAP.put(Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS);
        CHISEL_MAP.put(Blocks.BLACKSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS);
        CHISEL_MAP.put(Blocks.MUD, Blocks.MUD_BRICKS);
        CHISEL_MAP.put(Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BRICKS);
        CHISEL_MAP.put(Blocks.NETHERRACK, Blocks.NETHER_BRICKS);
        CHISEL_MAP.put(Blocks.CLAY, Blocks.BRICKS);

        // Reverse mappings
        CHISEL_MAP.put(Blocks.STONE_BRICKS, Blocks.STONE);
        CHISEL_MAP.put(Blocks.END_STONE_BRICKS, Blocks.END_STONE);
        CHISEL_MAP.put(Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE);
        CHISEL_MAP.put(Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.BLACKSTONE);
        CHISEL_MAP.put(Blocks.MUD_BRICKS, Blocks.MUD);
        CHISEL_MAP.put(Blocks.QUARTZ_BRICKS, Blocks.QUARTZ_BLOCK);
        CHISEL_MAP.put(Blocks.NETHER_BRICKS, Blocks.NETHERRACK);
        CHISEL_MAP.put(Blocks.BRICKS, Blocks.CLAY);
    }

    public ChiselItem(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(pContext.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, pContext.getClickedPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS);

            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.sladermod.chisel.shift_down"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.sladermod.chisel"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
