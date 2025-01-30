package net.infugogr.barracuda.entity.client;

import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.entity.custom.BarracudaEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BarracudaRenderer extends GeoEntityRenderer<BarracudaEntity> {
    public BarracudaRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BarracudaModel());
    }

    @Override
    public Identifier getTextureLocation(BarracudaEntity animatable) {
        return new Identifier(Barracuda.MOD_ID, "textures/entity/barracuda.png");
    }

    @Override
    public void render(BarracudaEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}