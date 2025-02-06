package net.infugogr.barracuda.rod;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;

public record RodType(Identifier itemId, Identifier rodPattern, float stability, float maxFishWeight) {
    public static final Codec<RodType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("item").forGetter(RodType::itemId),
            Identifier.CODEC.fieldOf("rod_pattern").forGetter(RodType::rodPattern),
            Codec.FLOAT.fieldOf("stability").forGetter(RodType::stability),
            Codec.FLOAT.fieldOf("max_fish_weight").forGetter(RodType::maxFishWeight)
    ).apply(instance, RodType::new));
}
