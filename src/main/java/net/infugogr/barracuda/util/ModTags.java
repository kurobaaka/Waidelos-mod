package net.infugogr.barracuda.util;

import net.infugogr.barracuda.Barracuda;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Barracuda.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> PARTS = createTag("parts");
        public static final TagKey<Item> PLATES = createTag("plates");
        public static final TagKey<Item> CABELS = createTag("cabels");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Barracuda.MOD_ID, name));
        }
    }
}
