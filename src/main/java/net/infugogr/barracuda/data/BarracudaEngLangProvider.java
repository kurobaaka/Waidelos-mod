package net.infugogr.barracuda.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.block.ModBlocks;
import net.infugogr.barracuda.item.ModItemGroups;
import net.infugogr.barracuda.item.ModItems;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.jetbrains.annotations.NotNull;

public class BarracudaEngLangProvider extends FabricLanguageProvider {
    public BarracudaEngLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    private static void addText(@NotNull TranslationBuilder builder, @NotNull Text text, @NotNull String value) {
        if (text.getContent() instanceof TranslatableTextContent translatableTextContent) {
            builder.add(translatableTextContent.getKey(), value);
        } else {
            Barracuda.LOGGER.warn("Failed to add translation for text: {}", text.getString());
        }
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.AQUATIC_DUST, "Aquatic Dust");
        translationBuilder.add(ModBlocks.AQUATIC_SAND, "Aquatic Sand");
        translationBuilder.add(ModBlocks.CHARGED_REDSTONEIUM_BLOCK, "Charged Redstoneium Block");
        translationBuilder.add(ModBlocks.CURSED_GOLD_BLOCK, "Cursed Block");
        //translationBuilder.add(ModItems.CURSED_KNIFE, "Cursed Knife");
        translationBuilder.add(ModItems.CHARGED_REDSTONEIUM_INGOT, "Charged Redstoneium Ingot");
        translationBuilder.add(ModItems.CURSED_GOLD_INGOT, "Cursed Gold Ingot");
        translationBuilder.add(ModBlocks.DISCHARGED_URANIUM_BLOCK, "Discharged Uranium Block");
        translationBuilder.add(ModBlocks.DEEPSLATE_URANIUM_ORE, "Deepslate Uranium Block");
        translationBuilder.add(ModItems.DISCHARGED_URANIUM_INGOT, "Discharged Uranium Ingot");
        translationBuilder.add(ModItems.DISCHARGED_URANIUM_NUGGET, "Discharged Uranium Nugget");
        //translationBuilder.add(ModBlocks.FUEL_GENERATOR, "Fuel Generator");
        translationBuilder.add(ModItems.FUEL_GENERATOR_PLATE, "Fuel Generator Plate");
        translationBuilder.add(ModBlocks.MINERAL_BLOCK, "Mineral Block");
        translationBuilder.add(ModBlocks.MINERAL_CLUSTER, "Mineral Cluster");
        translationBuilder.add(ModBlocks.MACHINE_FRAME, "Machine Frame");
        translationBuilder.add(ModBlocks.REDSTONEIUM_BLOCK, "Redstoneium Block");
        translationBuilder.add(ModItems.REDSTONEIUM_INGOT, "Redstoneium Ingot");
        translationBuilder.add(ModItems.SEA_INGOT, "Sea Ingot");
        translationBuilder.add(ModItems.SEA_MINERAL, "Sea Mineral");
        translationBuilder.add(ModBlocks.LVCABLE, "LV Cable");
        //translationBuilder.add(ModBlocks.SMES, "SMES");
        translationBuilder.add(ModItems.SMES_PLATE, "SMES Plate");
        translationBuilder.add(ModItems.STONE_MORTAR, "Stone Mortar");
        translationBuilder.add(ModItems.TORMENTED_SOUL, "Tormented Soul");
        translationBuilder.add(ModBlocks.URANIUM_BLOCK, "Uranium Block");
        translationBuilder.add(ModItems.URANIUM_235, "Uranium 235");
        translationBuilder.add(ModItems.URANIUM_238, "Uranium 238");
        translationBuilder.add(ModItems.URANIUM_DUST, "Uranium Dust");
        translationBuilder.add(ModItems.URANIUM_INGOT, "Uranium Ingot");
        translationBuilder.add(ModItems.URANIUM_NUGGET, "Uranium Nugget");
        translationBuilder.add(ModBlocks.URANIUM_ORE, "Uranium Ore");
        //addText(translationBuilder, SMESblockEntity.TITLE, "SMES");
        //addText(translationBuilder, SMESblockEntity.CHARGE_MODE_BUTTON_TOOLTIP_TEXT, "Charge/Discharge");

        addText(translationBuilder, ModItemGroups.TITLE1, "Barracuda Mod Aquatic");
        addText(translationBuilder, ModItemGroups.TITLE2, "Barracuda Mod Cult");
        addText(translationBuilder, ModItemGroups.TITLE3, "Barracuda Mod Redstoneium");
        addText(translationBuilder, ModItemGroups.TITLE4, "Barracuda Mod Uranium");
        //addText(translationBuilder, FuelGeneratorBlockEntity.TITLE, "Fuel Generator");

        translationBuilder.add("smesplate1", "§7Requires:§r");
        translationBuilder.add("smesplate2", "§71x capacitor§r");
        translationBuilder.add("smesplate3", "§710x HV cable§r");
        translationBuilder.add("smesplate4", "§74x small-capacity power cell§r");

        translationBuilder.add("fgplate1", "§7Requires:§r");
        translationBuilder.add("fgplate2", "§71x capacitor§r");
        translationBuilder.add("fgplate3", "§710x LV cable§r");

    }
}
