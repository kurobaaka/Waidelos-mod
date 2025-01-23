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
    public static final Text TITLE5 = Text.translatable("itemGroup." + Barracuda.MOD_ID + ".barracuda_druid_items");

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
                        entries.add(ModItems.SAPPHIRE);
                        entries.add(ModItems.RAW_SAPPHIRE);
                        entries.add(ModItems.SAPPHIRE_HELMET);
                        entries.add(ModItems.SAPPHIRE_CHESTPLATE);
                        entries.add(ModItems.SAPPHIRE_LEGGINGS);
                        entries.add(ModItems.SAPPHIRE_BOOTS);
                        entries.add(ModItems.SAPPHIRE_SWORD);
                        entries.add(ModItems.SAPPHIRE_SHOVEL);
                        entries.add(ModItems.SAPPHIRE_AXE);
                        entries.add(ModItems.SAPPHIRE_HOE);
                        entries.add(ModItems.SAPPHIRE_PICKAXE);
                        entries.add(ModItems.AQUATIC_SWORD);
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
                        entries.add(ModItems.RUBY_DUST);
                        entries.add(ModItems.RUBY_PICKAXE);
                        entries.add(ModItems.RUBY_AXE);
                        entries.add(ModItems.RUBY_SHOVEL);
                        entries.add(ModItems.RUBY_SWORD);
                        entries.add(ModItems.RUBY_HOE);
                        entries.add(ModItems.HEART_OF_RED_HARBOR);

                        entries.add(ModItems.BRONZE_BLADE);
                        entries.add(ModItems.BRONZE_INGOT);
                        entries.add(ModItems.BRONZE_PLATE);
                        entries.add(ModItems.BRONZE_SHARDS);
                        entries.add(ModItems.METAL_BLADE);
                        entries.add(ModItems.METAL_PLATE);
                        entries.add(ModItems.METAL_SHARDS);
                        entries.add(ModItems.SILVER_INGOT);
                        entries.add(ModItems.STEEL_BLADE);
                        entries.add(ModItems.STEEL_INGOT);
                        entries.add(ModItems.STEEL_PLATE);
                        entries.add(ModItems.STEEL_SHARDS);

                        entries.add(ModItems.ILLUSIONER_CHESTPLATE);
                        entries.add(ModItems.ILLUSIONER_BOOTS);

                        entries.add(ModItems.EVOKER_CHESTPLATE);
                        entries.add(ModItems.EVOKER_BOOTS);

                        entries.add(ModItems.EXECUTION_SWORD);
                        entries.add(ModItems.EXECUTION_AXE);
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
                        //entries.add(ModBlocks.TELEPORTER);
                        entries.add(ModItems.ENGINEER_HELMET);
                        entries.add(ModItems.ENGINEER_CHESTPLATE);
                        entries.add(ModItems.ENGINEER_LEGGINGS);
                        entries.add(ModItems.ENGINEER_BOOTS);

                        entries.add(ModItems.ENERGY_HELMET);
                        entries.add(ModItems.ENERGY_CHESTPLATE);
                        entries.add(ModItems.ENERGY_LEGGINGS);
                        entries.add(ModItems.ENERGY_BOOTS);
                        entries.add(ModItems.ENERGY_PICKAXE);
                        entries.add(ModItems.ENERGY_AXE);
                        entries.add(ModItems.ENERGY_SHOVEL);
                        entries.add(ModItems.ENERGY_SWORD);
                        entries.add(ModItems.ENERGY_HOE);
                    }).build());


                    public static final ItemGroup BARRACUDA_MOD_ITEMS_GROUP_5 = Registry.register(Registries.ITEM_GROUP,
                    Identifier.of(Barracuda.MOD_ID, "barracuda_druid_items"),
                    FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.MUD)) // URANIUM_INGOT потом заменить на друидский прикол
                     .displayName(TITLE5)
                     .entries((displayContext, entries) -> {
                        entries.add(ModItems.SHROOM_HELMET);
                        entries.add(ModItems.SHROOM_CHESTPLATE);
                        entries.add(ModItems.SHROOM_LEGGINGS);
                        entries.add(ModItems.SHROOM_BOOTS);
                        entries.add(ModItems.DRUID_HELMET);
                        entries.add(ModItems.DRUID_CHESTPLATE);
                        entries.add(ModItems.DRUID_LEGGINGS);
                        entries.add(ModItems.DRUID_BOOTS);
                        entries.add(ModItems.MOSS_HELMET);
                        entries.add(ModItems.MOSS_CHESTPLATE);
                        entries.add(ModItems.MUD);
                        entries.add(ModItems.FIR_CONE);
                        entries.add(ModItems.PINE_CONE);
                     }).build());



    public static void registerItemGroups() {
        Barracuda.LOGGER.info("Registering Item Groups for " + Barracuda.MOD_ID);
    }
}

