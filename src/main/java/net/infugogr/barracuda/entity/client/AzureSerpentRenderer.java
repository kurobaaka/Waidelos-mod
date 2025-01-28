package net.infugogr.barracuda.entity.client;

import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.entity.custom.AzureSerpentEntity;
import net.infugogr.barracuda.entity.custom.BassFishEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class AzureSerpentRenderer extends MobEntityRenderer<AzureSerpentEntity, AzureSerpentModel<AzureSerpentEntity>> {
    private static final Identifier TEXTURE = new Identifier(Barracuda.MOD_ID, "textures/entity/azure_serpent.png");

    public AzureSerpentRenderer(EntityRendererFactory.Context context) {
        super(context, new AzureSerpentModel<>(context.getPart(ModModelLayers.AZURE_SERPENT)), 0.6f);
    }

    @Override
    public Identifier getTexture(AzureSerpentEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(AzureSerpentEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}