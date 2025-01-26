package net.infugogr.barracuda.entity.client;

import net.infugogr.barracuda.Barracuda;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer PORCUPINE =
            new EntityModelLayer(new Identifier(Barracuda.MOD_ID, "porcupine"), "main");
}
