package net.infugogr.barracuda.entity.client;

import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.entity.custom.BassFishEntity;
import net.infugogr.barracuda.entity.client.BassFishModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class BassFishRenderer extends MobEntityRenderer<BassFishEntity, BassFishModel<BassFishEntity>> {
    public BassFishRenderer(EntityRendererFactory.Context context) {
        super(context, new BassFishModel<>(context.getPart(ModModelLayers.BASS_FISH)), 0.6f);
    }

    @Override
    public Identifier getTexture(BassFishEntity entity) {
        return new Identifier(Barracuda.MOD_ID, "textures/entity/bass_fish.png");
    }
}