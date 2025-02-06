package net.infugogr.barracuda.block.entity.client;

import net.infugogr.barracuda.block.entity.TeleporterBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class TeleporterBlockRenderer extends GeoBlockRenderer<TeleporterBlockEntity> {
    public TeleporterBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new TeleporterBlockModel());
    }
}
