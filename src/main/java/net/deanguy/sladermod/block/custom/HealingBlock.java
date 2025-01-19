package net.deanguy.sladermod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class HealingBlock extends Block {
    private static final int HEAL_INTERVAL_TICKS = 25; // Now heals every 1.25 seconds (instead of 2.5 seconds)
    private static final float HEAL_AMOUNT = 1.0F; // Half a heart = 1 health point

    // Track the last time each entity was healed (to prevent healing multiple times in quick succession)
    private Map<LivingEntity, Integer> lastHealedTicks = new HashMap<>();

    public HealingBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Define the bounding box around the block to detect entities standing directly on it
        AABB area = new AABB(pos).inflate(0.5); // Expands the box slightly to ensure entities in close proximity are detected

        // Get all LivingEntities (players, animals, villagers) in the area
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area);

        for (LivingEntity entity : entities) {
            // Skip if entity was healed recently
            if (lastHealedTicks.containsKey(entity)) {
                int lastHealTick = lastHealedTicks.get(entity);
                if (level.getGameTime() - lastHealTick < HEAL_INTERVAL_TICKS) {
                    continue; // Skip healing if within cooldown period
                }
            }

            // Heal players and passive mobs if they need healing
            if (entity instanceof Player || entity instanceof Animal || entity instanceof Villager) {
                if (entity.getHealth() < entity.getMaxHealth()) {
                    entity.heal(HEAL_AMOUNT); // Heal 1 health point (0.5 hearts)
                    lastHealedTicks.put(entity, (int) level.getGameTime()); // Update last healed tick time
                }
            }
        }

        // Schedule the next healing tick
        level.scheduleTick(pos, this, HEAL_INTERVAL_TICKS);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            // Schedule the first healing tick when the block is placed
            level.scheduleTick(pos, this, HEAL_INTERVAL_TICKS);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, net.minecraft.world.entity.Entity entity) {
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.sladermod.healing_block.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
