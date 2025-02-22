package net.infugogr.barracuda.block.entity.client;

import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.block.entity.TeleporterBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class TeleporterBlockModel extends GeoModel<TeleporterBlockEntity> {
    @Override
    public Identifier getModelResource(TeleporterBlockEntity animatable) {
        return Barracuda.id("geo/teleporter.geo.json");
    }

    @Override
    public Identifier getTextureResource(TeleporterBlockEntity animatable) {
        return Barracuda.id("textures/block/teleporter.png");
    }

    @Override
    public Identifier getAnimationResource(TeleporterBlockEntity animatable) {
        return Barracuda.id("animations/teleporter.animation.json");
    }
}
