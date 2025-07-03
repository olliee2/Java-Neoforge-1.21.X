package net.olliee2.kricketotmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties KRICKETOTIUM_LOAF = new FoodProperties.Builder().nutrition(5).saturationModifier(0.25f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 2), 1f).build();
}
