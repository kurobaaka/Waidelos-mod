package net.infugogr.barracuda.item;

import net.infugogr.barracuda.Barracuda;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    RUBY("ruby", 25, new int[] { 2, 6, 5, 2 }, 19,
        SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(ModItems.RUBY)),

    ENGINEER("engineer", 25, new int[] { 2, 2, 2, 2 }, 19,    // ENGINEER - это рабочее название для брони эллектрика
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(ModItems.RUBY)),

    SAPPHIRE("sapphire", 25, new int[] { 2, 6, 5, 2 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(ModItems.SAPPHIRE)),

    SHROOM("shroom", 25, new int[] { 1, 2, 2, 1 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(ModItems.SAPPHIRE)),

    DRUID_WOOD("druid", 25, new int[] { 3, 6, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(ModItems.RUBY)), // потом материал на друидскую древесину заменить не забудь

    ENERGY("energy", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0.1f, () -> Ingredient.ofItems(ModItems.RUBY));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 15, 13 };

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return Barracuda.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}