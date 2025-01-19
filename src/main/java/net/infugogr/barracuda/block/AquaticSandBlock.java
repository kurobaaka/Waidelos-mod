package net.infugogr.barracuda.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.FallingBlock;

public class AquaticSandBlock extends FallingBlock {
    public AquaticSandBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return null;
    }
}
