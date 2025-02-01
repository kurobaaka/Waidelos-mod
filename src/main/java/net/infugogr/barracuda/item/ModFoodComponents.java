package net.infugogr.barracuda.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    // Мясо
    public static final FoodComponent PORK = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build();
    public static final FoodComponent PORK_COOKED = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f).build();
    public static final FoodComponent CHICKEN_WING = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent CHICKEN_WING_COOKED = new FoodComponent.Builder().hunger(6).saturationModifier(0.6f).build();

    // Рыба (сырая)
    public static final FoodComponent FISH_EEL = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent FISH_BLUEFISH = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent FISH_BREAM = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent FISH_TILAPIA = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent FISH_FLOUNDER = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent FISH_GLASS_CATFISH = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent FISH_DOLPHINFISH = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build();
    public static final FoodComponent FISH_PIKE = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build();
    public static final FoodComponent FISH_HERRING = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent FISH_CARP = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build();
    public static final FoodComponent FISH_CATFISH = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build();
    public static final FoodComponent FISH_SHORT_COD = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent FISH_SALMON = new FoodComponent.Builder().hunger(4).saturationModifier(0.4f).build();
    public static final FoodComponent FISH_OCTOPUS = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build();
    public static final FoodComponent FISH_SANDFISH = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent FISH_ANCHOVY = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent FISH_SARDINE = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent FISH_BLACK_SEA_BASS = new FoodComponent.Builder().hunger(4).saturationModifier(0.4f).build();
    public static final FoodComponent FISH_SEA_CUCUMBER = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent FISH_ROCKFISH = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build();
    public static final FoodComponent FISH_STURGEON = new FoodComponent.Builder().hunger(5).saturationModifier(0.5f).build();
    public static final FoodComponent FISH_TUNA = new FoodComponent.Builder().hunger(5).saturationModifier(0.5f).build();
    public static final FoodComponent FISH_SQUID = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).build();

    // Рыба (приготовленная)
    public static final FoodComponent COOKED_FISH_EEL = new FoodComponent.Builder().hunger(6).saturationModifier(0.7f).build();
    public static final FoodComponent COOKED_FISH_BLUEFISH = new FoodComponent.Builder().hunger(6).saturationModifier(0.7f).build();
    public static final FoodComponent COOKED_FISH_BREAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.7f).build();
    public static final FoodComponent COOKED_FISH_TILAPIA = new FoodComponent.Builder().hunger(6).saturationModifier(0.7f).build();
    public static final FoodComponent COOKED_FISH_FLOUNDER = new FoodComponent.Builder().hunger(6).saturationModifier(0.7f).build();
    public static final FoodComponent COOKED_FISH_GLASS_CATFISH = new FoodComponent.Builder().hunger(6).saturationModifier(0.7f).build();
    public static final FoodComponent COOKED_FISH_DOLPHINFISH = new FoodComponent.Builder().hunger(7).saturationModifier(0.8f).build();
    public static final FoodComponent COOKED_FISH_PIKE = new FoodComponent.Builder().hunger(7).saturationModifier(0.8f).build();
    public static final FoodComponent COOKED_FISH_HERRING = new FoodComponent.Builder().hunger(5).saturationModifier(0.6f).build();
    public static final FoodComponent COOKED_FISH_CARP = new FoodComponent.Builder().hunger(7).saturationModifier(0.8f).build();
    public static final FoodComponent COOKED_FISH_CATFISH = new FoodComponent.Builder().hunger(7).saturationModifier(0.8f).build();
    public static final FoodComponent COOKED_FISH_SHORT_COD = new FoodComponent.Builder().hunger(5).saturationModifier(0.6f).build();
    public static final FoodComponent COOKED_FISH_SALMON = new FoodComponent.Builder().hunger(9).saturationModifier(0.9f).build();
    public static final FoodComponent COOKED_FISH_OCTOPUS = new FoodComponent.Builder().hunger(7).saturationModifier(0.8f).build();
    public static final FoodComponent COOKED_FISH_SANDFISH = new FoodComponent.Builder().hunger(5).saturationModifier(0.6f).build();
    public static final FoodComponent COOKED_FISH_ANCHOVY = new FoodComponent.Builder().hunger(3).saturationModifier(0.4f).build();
    public static final FoodComponent COOKED_FISH_SARDINE = new FoodComponent.Builder().hunger(3).saturationModifier(0.4f).build();
    public static final FoodComponent COOKED_FISH_BLACK_SEA_BASS = new FoodComponent.Builder().hunger(8).saturationModifier(0.9f).build();
    public static final FoodComponent COOKED_FISH_SEA_CUCUMBER = new FoodComponent.Builder().hunger(3).saturationModifier(0.4f).build();
    public static final FoodComponent COOKED_FISH_ROCKFISH = new FoodComponent.Builder().hunger(7).saturationModifier(0.8f).build();
    public static final FoodComponent COOKED_FISH_STURGEON = new FoodComponent.Builder().hunger(10).saturationModifier(1.0f).build();
    public static final FoodComponent COOKED_FISH_TUNA = new FoodComponent.Builder().hunger(10).saturationModifier(1.0f).build();
    public static final FoodComponent COOKED_FISH_SQUID = new FoodComponent.Builder().hunger(7).saturationModifier(0.8f).build();
}