package net.infugogr.barracuda.rod;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;

import java.util.Optional;

public record HookType(Identifier itemId, float power, int size, Optional<Boolean> weighted, Optional<Boolean> fireproof, Identifier category) {
    public static final Codec<HookType> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("item").forGetter(HookType::itemId),
            Codec.FLOAT.fieldOf("power").forGetter(HookType::power),
            Codec.INT.fieldOf("size").forGetter(HookType::size),
            Codec.BOOL.optionalFieldOf("weighted").forGetter(HookType::weighted),
            Codec.BOOL.optionalFieldOf("fireproof").forGetter(HookType::weighted),
            Identifier.CODEC.fieldOf("category").forGetter(HookType::category)
    ).apply(instance, HookType::new));
}
