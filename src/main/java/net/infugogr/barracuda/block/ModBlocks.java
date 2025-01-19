package net.infugogr.barracuda.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.infugogr.barracuda.Barracuda;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block AQUATIC_SAND = registerWithItemCopy("aquatic_sand",
            new AquaticSandBlock(FabricBlockSettings.copyOf(Blocks.SAND)));
    public static final Block MINERAL_BLOCK = registerWithItemCopy("mineral_block",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    public static final Block MINERAL_CLUSTER = registerWithItemCopy("mineral_cluster",
            new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)));
    public static final Block CURSED_GOLD_BLOCK = registerWithItemCopy("cursed_gold_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block REDSTONEIUM_BLOCK = registerWithItemCopy("redstoneium_block",
            new RedstoneiumBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).ticksRandomly()));
    public static final Block CHARGED_REDSTONEIUM_BLOCK = registerWithItemCopy("charged_redstoneium_block",
            new ChargedRedstoneiumBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).ticksRandomly()));
    public static final Block URANIUM_ORE = registerWithItemCopy("uranium_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block DEEPSLATE_URANIUM_ORE = registerWithItemCopy("deepslate_uranium_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block URANIUM_BLOCK = registerWithItemCopy("uranium_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block DISCHARGED_URANIUM_BLOCK = registerWithItemCopy("discharged_uranium_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block FUEL_GENERATOR = registerWithItemCopy("fuel_generator",
            new FuelGeneratorBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block LVCABLE = registerWithItemCopy("lv_cable",
            new LVcableBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block SMES = registerWithItemCopy("smes",
            new SMESBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block MACHINE_FRAME = registerWithItemCopy("machine_frame",
            new MachineFrameBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block TELEPORTER = registerWithItemCopy("teleporter",
            new TeleporterBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).ticksRandomly()));
    public static final Block HVCABLE = registerWithItemCopy("hv_cable",
            new HVcableBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block WALL_BLOCK = registerWithItemCopy("wall_block",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    

    private static Block registerWithItemCopy(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Barracuda.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(Barracuda.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Barracuda.LOGGER.info("Registering Mod Blocks for " + Barracuda.MOD_ID);
    }
}
