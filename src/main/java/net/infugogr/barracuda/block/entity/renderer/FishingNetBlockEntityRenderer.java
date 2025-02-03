// package net.infugogr.barracuda.block.entity.renderer;

// import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
// import net.infugogr.barracuda.block.ModBlocks;
// import net.infugogr.barracuda.block.entity.FishingNetBlockEntity;
// import net.minecraft.client.MinecraftClient;
// import net.minecraft.client.render.RenderLayer;
// import net.minecraft.client.render.VertexConsumerProvider;
// import net.minecraft.client.render.block.entity.BlockEntityRenderer;
// import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
// import net.minecraft.client.render.item.ItemRenderer;
// import net.minecraft.client.util.math.MatrixStack;
// import net.minecraft.item.ItemStack;
// import net.minecraft.util.math.RotationAxis;

// public class FishingNetBlockEntityRenderer implements BlockEntityRenderer<FishingNetBlockEntity> {
//     public FishingNetBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

//     }

//     @Override
//     public void render(FishingNetBlockEntity entity, float tickDelta, MatrixStack matrices,
//                        VertexConsumerProvider vertexConsumers, int light, int overlay) {
//         ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
//         BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FISHING_NET, RenderLayer.getCutout());
//         matrices.push();
//         matrices.translate(0.5f, 0.75f, 0.5f);
//         matrices.scale(0.35f, 0.35f, 0.35f);
//         matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
//     }
    
// }