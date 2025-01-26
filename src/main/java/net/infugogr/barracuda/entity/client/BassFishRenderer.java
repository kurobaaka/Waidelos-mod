package net.infugogr.barracuda.entity.client;

import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.entity.custom.BassFishEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BassFishRenderer extends MobEntityRenderer<BassFishEntity, BassFishModel<BassFishEntity>> {
    private static final Identifier TEXTURE = new Identifier(Barracuda.MOD_ID, "textures/entity/bass_fish.png");

    public BassFishRenderer(EntityRendererFactory.Context context) {
        super(context, new BassFishModel<>(context.getPart(ModModelLayers.BASS_FISH)), 0.6f);
    }

    @Override
    public Identifier getTexture(BassFishEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BassFishEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}