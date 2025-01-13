package net.deanguy.sladermod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties GOLDEN_WAGYU_STEAK = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(1.3F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 400, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3000, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3000, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 1), 1.0F)
            .alwaysEdible()
            .build();

    public static final FoodProperties GOLDEN_KOBE_STEAK = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(1.2F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0), 1.0F)
            .alwaysEdible()
            .build();

    public static final FoodProperties GOLDEN_FILET_MIGNON = new FoodProperties.Builder().nutrition(8).saturationModifier(1.2F).build();
}
