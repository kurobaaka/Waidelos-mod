package net.infugogr.barracuda.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {
    public static final Text TITLE1 = Text.translatable("itemGroup." + Barracuda.MOD_ID + ".barracuda_aquatic_items");
    public static final Text TITLE2 = Text.translatable("itemGroup." + Barracuda.MOD_ID + ".barracuda_cult_items");
    public static final Text TITLE3 = Text.translatable("itemGroup." + Barracuda.MOD_ID + ".barracuda_redstoneium_items");
    public static final Text TITLE4 = Text.translatable("itemGroup." + Barracuda.MOD_ID + ".barracuda_uranium_items");

    public static final ItemGroup BARRACUDA_MOD_ITEMS_GROUP_1 = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Barracuda.MOD_ID, "barracuda_aquatic_items"),
            FabricItemGroup
                    .builder()
                    .icon(() -> new ItemStack(ModItems.SEA_INGOT))
                    .displayName(TITLE1)
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.AQUATIC_DUST);
                        entries.add(ModItems.SEA_MINERAL);
                        entries.add(ModItems.SEA_INGOT);
                        entries.add(ModBlocks.MINERAL_BLOCK);
                        entries.add(ModBlocks.AQUATIC_SAND);
                        entries.add(ModBlocks.MINERAL_CLUSTER);
                        entries.add(ModItems.ANCHOR);
                        entries.add(ModItems.LOST_ANCHOR);
                    }).build());

    public static final ItemGroup BARRACUDA_MOD_ITEMS_GROUP_2 = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Barracuda.MOD_ID, "barracuda_cult_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.CURSED_GOLD_INGOT))
                    .displayName(TITLE2)
                    .entries((displayContext, entries) -> {
                        //entries.add(ModItems.CURSED_KNIFE);
                        entries.add(ModItems.CURSED_GOLD_INGOT);
                        entries.add(ModBlocks.CURSED_GOLD_BLOCK);
                        entries.add(ModItems.TORMENTED_SOUL);
                        entries.add(ModItems.RUBY);
                        entries.add(ModItems.HEART_OF_RED_HARBOR);
                    }).build());

    public static final ItemGroup BARRACUDA_MOD_ITEMS_GROUP_3 = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Barracuda.MOD_ID, "barracuda_redstoneium_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.REDSTONEIUM_INGOT))
                    .displayName(TITLE3)
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.REDSTONEIUM_INGOT);
                        entries.add(ModItems.CHARGED_REDSTONEIUM_INGOT);
                        entries.add(ModBlocks.REDSTONEIUM_BLOCK);
                        entries.add(ModBlocks.CHARGED_REDSTONEIUM_BLOCK);
                    }).build());

    public static final ItemGroup BARRACUDA_MOD_ITEMS_GROUP_4 = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Barracuda.MOD_ID, "barracuda_uranium_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.URANIUM_INGOT))
                    .displayName(TITLE4)
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.URANIUM_238);
                        entries.add(ModItems.URANIUM_235);
                        entries.add(ModItems.URANIUM_INGOT);
                        entries.add(ModItems.DISCHARGED_URANIUM_INGOT);
                        entries.add(ModItems.DISCHARGED_URANIUM_NUGGET);
                        entries.add(ModItems.URANIUM_DUST);
                        entries.add(ModItems.URANIUM_NUGGET);
                        entries.add(ModBlocks.URANIUM_ORE);
                        entries.add(ModBlocks.DEEPSLATE_URANIUM_ORE);
                        entries.add(ModBlocks.URANIUM_BLOCK);
                        entries.add(ModBlocks.DISCHARGED_URANIUM_BLOCK);
                        entries.add(ModBlocks.FUEL_GENERATOR);
                        entries.add(ModBlocks.LVCABLE);
                        entries.add(ModBlocks.SMES);
                        entries.add(ModBlocks.MACHINE_FRAME);
                        entries.add(ModItems.FUEL_GENERATOR_PLATE);
                        entries.add(ModItems.SMES_PLATE);
                        entries.add(ModItems.CAPACITOR);
                        entries.add(ModBlocks.HVCABLE);
                        entries.add(ModItems.SCREWDRIVER);
                        entries.add(ModItems.HAMMER);
                        entries.add(ModBlocks.WALL_BLOCK);
                        entries.add(ModBlocks.TELEPORTER);
                    }).build());



    public static void registerItemGroups() {
        Barracuda.LOGGER.info("Registering Item Groups for " + Barracuda.MOD_ID);
    }
}

