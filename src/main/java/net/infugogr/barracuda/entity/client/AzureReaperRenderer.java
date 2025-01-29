package net.infugogr.barracuda.entity.client;

import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.entity.custom.AzureReaperEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AzureReaperRenderer extends GeoEntityRenderer<AzureReaperEntity> {
    public AzureReaperRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new AzureReaperModel());
    }

    @Override
    public Identifier getTextureLocation(AzureReaperEntity animatable) {
        return new Identifier(Barracuda.MOD_ID, "textures/entity/azure_reaper.png");
    }

    @Override
    public void render(AzureReaperEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}