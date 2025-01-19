package net.infugogr.barracuda.item;

import net.infugogr.barracuda.Barracuda;
import net.minecraft.item.Item;
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
