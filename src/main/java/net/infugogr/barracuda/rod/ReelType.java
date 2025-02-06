package net.infugogr.barracuda.rod;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;

public record ReelType(Identifier itemId, float strength) {
    public static final Codec<ReelType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("item").forGetter(ReelType::itemId),
            Codec.FLOAT.fieldOf("strength").forGetter(ReelType::strength)
    ).apply(instance, ReelType::new));
}
