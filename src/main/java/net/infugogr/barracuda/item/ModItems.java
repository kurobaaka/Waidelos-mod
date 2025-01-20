package net.infugogr.barracuda.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.infugogr.barracuda.Barracuda;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public class ModItems {

    public static final Item AQUATIC_DUST = register("aquatic_dust");
    public static final Item SEA_MINERAL = register("sea_mineral");
    public static final Item SEA_INGOT = register("sea_ingot");
    public static final Item CURSED_GOLD_INGOT = register("cursed_gold_ingot");
    public static final TormentedSoul TORMENTED_SOUL = register("tormented_soul",
            TormentedSoul::new, settings -> settings.maxCount(16));
    public static final Item REDSTONEIUM_INGOT = register("redstoneium_ingot");
    public static final Item CHARGED_REDSTONEIUM_INGOT = register("charged_redstoneium_ingot");
    public static final Item HEART_OF_RED_HARBOR = register("heart_of_red_harbor");

    //public static final SwordItem CURSED_KNIFE = registerItem("cursed_knife",
    //        new SwordItem(ToolMaterial.GOLD, 3.0F, -2.4F, new Item.Settings()));
    public static final Item STONE_MORTAR = register("stone_mortar");
    public static final Item URANIUM_DUST = register("uranium_dust");
    public static final Item URANIUM_INGOT = register("uranium_ingot");
    public static final Item URANIUM_238 = register("uranium_238");
    public static final Item URANIUM_235 = register("uranium_235");
    public static final Item DISCHARGED_URANIUM_INGOT = register("discharged_uranium_ingot");
    public static final Item URANIUM_NUGGET = register("uranium_nugget");
    public static final Item DISCHARGED_URANIUM_NUGGET = register("discharged_uranium_nugget");
    public static final Item FUEL_GENERATOR_PLATE= register("fuel_generator_plate",
            Item::new, settings -> settings.maxCount(16));
    public static final Item SMES_PLATE= register("smes_plate",
            Item::new, settings -> settings.maxCount(16));
    public static final Item CAPACITOR = register("capacitor");
    public static final Item SCREWDRIVER = register("screwdriver");
    public static final Item HAMMER = register("hammer");

    public static final Item LOST_ANCHOR = registerItem("lost_anchor",
    new SwordItem(ModToolMaterial.RUBY, 5, 3f, new FabricItemSettings()));
    public static final Item ANCHOR = registerItem("anchor",
    new SwordItem(ModToolMaterial.RUBY, 5, 3f, new FabricItemSettings()));

        // Weapons stuff

    public static final Item RUBY = register("ruby");
    public static final Item RAW_RUBY = registerItem("raw_ruby",
            new Item(new FabricItemSettings()));
    public static final Item RUBY_PICKAXE = registerItem("ruby_pickaxe",
            new PickaxeItem(ModToolMaterial.RUBY, 2, 2f, new FabricItemSettings()));
    public static final Item RUBY_AXE = registerItem("ruby_axe",
            new AxeItem(ModToolMaterial.RUBY, 3, 1f, new FabricItemSettings()));
    public static final Item RUBY_SHOVEL = registerItem("ruby_shovel",
            new ShovelItem(ModToolMaterial.RUBY, 0, 0f, new FabricItemSettings()));
    public static final Item RUBY_SWORD = registerItem("ruby_sword",
            new SwordItem(ModToolMaterial.RUBY, 5, 3f, new FabricItemSettings()));
    public static final Item RUBY_HOE = registerItem("ruby_hoe",
            new HoeItem(ModToolMaterial.RUBY, 0, 0f, new FabricItemSettings()));

    public static final Item SAPPHIRE = register("sapphire");
    public static final Item RAW_SAPPHIRE = registerItem("raw_sapphire",
            new Item(new FabricItemSettings()));
    public static final Item SAPPHIRE_PICKAXE = registerItem("sapphire_pickaxe",
            new PickaxeItem(ModToolMaterial.SAPPHIRE, 2, 2f, new FabricItemSettings()));
    public static final Item SAPPHIRE_AXE = registerItem("sapphire_axe",
            new AxeItem(ModToolMaterial.SAPPHIRE, 3, 1f, new FabricItemSettings()));
    public static final Item SAPPHIRE_SHOVEL = registerItem("sapphire_shovel",
            new ShovelItem(ModToolMaterial.SAPPHIRE, 0, 0f, new FabricItemSettings()));
    public static final Item SAPPHIRE_SWORD = registerItem("sapphire_sword",
            new SwordItem(ModToolMaterial.SAPPHIRE, 5, 3f, new FabricItemSettings()));
    public static final Item SAPPHIRE_HOE = registerItem("sapphire_hoe",
            new HoeItem(ModToolMaterial.SAPPHIRE, 0, 0f, new FabricItemSettings()));

        // Armor stuff

    public static final Item ENGINEER_HELMET = registerItem("engineer_helmet",
            new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ENGINEER_CHESTPLATE = registerItem("engineer_chestplate",
            new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ENGINEER_LEGGINGS = registerItem("engineer_leggings",
            new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ENGINEER_BOOTS = registerItem("engineer_boots",
            new ArmorItem(ModArmorMaterials.RUBY, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item SAPPHIRE_HELMET = registerItem("sapphire_helmet",
            new ArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item SAPPHIRE_CHESTPLATE = registerItem("sapphire_chestplate",
            new ArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item SAPPHIRE_LEGGINGS = registerItem("sapphire_leggings",
            new ArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item SAPPHIRE_BOOTS = registerItem("sapphire_boots",
            new ArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.BOOTS, new FabricItemSettings()));



    public static final IronPlate IRON_PLATE = register("iron_plate",
            IronPlate::new, settings -> settings.maxCount(16));

    private static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(Registries.ITEM, Barracuda.id(name), item);
    }

    public static Item register(String name) {
        return registerItem(name, new Item(new Item.Settings()));
    }

    public static <T extends Item> T register(String name, Function<Item.Settings, T> constructor, Function<Item.Settings, Item.Settings> settingsApplier) {
        return registerItem(name, constructor.apply(
                settingsApplier.apply(new Item.Settings())));
    }


    public static void registerModItems() {
        Barracuda.LOGGER.info("Registering Mod Items for " + Barracuda.MOD_ID);
    }
}
