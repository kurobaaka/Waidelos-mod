// package net.infugogr.barracuda.entity.client;

// import net.infugogr.barracuda.Barracuda;
// import net.infugogr.barracuda.entity.custom.PirateEntity;
// import net.minecraft.client.render.VertexConsumerProvider;
// import net.minecraft.client.render.entity.EntityRendererFactory;
// import net.minecraft.client.render.entity.MobEntityRenderer;
// import net.minecraft.client.util.math.MatrixStack;
// import net.minecraft.util.Identifier;

// public class PirateRenderer extends MobEntityRenderer<PirateEntity, PirateModel<PirateEntity>> {
//     private static final Identifier TEXTURE = new Identifier(Barracuda.MOD_ID, "textures/entity/pirate.png");

//     public PirateRenderer(EntityRendererFactory.Context context) {
//         super(context, new PirateMФodel<>(context.getPart(ModModelLayers.PIRATE)), 0.6f);
//     }

//     @Override
//     public Identifier getTexture(PirateEntity entity) {
//         return TEXTURE;
//     }

//     @Override
//     public void render(PirateEntity mobEntity, float f, float g, MatrixStack matrixStack,
//                        VertexConsumerProvider vertexConsumerProvider, int i) {
//         if(mobEntity.isBaby()) {
//             matrixStack.scale(0.5f, 0.5f, 0.5f);
//         } else {
//             matrixStack.scale(1f, 1f, 1f);
//         }

//         super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
//     }
// }
