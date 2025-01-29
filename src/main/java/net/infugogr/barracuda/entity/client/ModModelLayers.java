package net.infugogr.barracuda.entity.client;

import net.infugogr.barracuda.Barracuda;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
        public static final EntityModelLayer PORCUPINE = 
        new EntityModelLayer(new Identifier(Barracuda.MOD_ID, "porcupine"), "main");
    
    public static final EntityModelLayer BASS_FISH = 
        new EntityModelLayer(new Identifier(Barracuda.MOD_ID, "bass_fish"), "main");

    public static final EntityModelLayer AZURE_SERPENT = 
        new EntityModelLayer(new Identifier(Barracuda.MOD_ID, "azure_serpent"), "main");

    public static final EntityModelLayer BARRACUDA = 
        new EntityModelLayer(new Identifier(Barracuda.MOD_ID, "barracuda"), "main");
}
