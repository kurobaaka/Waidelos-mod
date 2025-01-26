package net.infugogr.barracuda.entity.client;


import net.infugogr.barracuda.entity.animation.ModAnimations;
import net.infugogr.barracuda.entity.custom.BassFishEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
   
public class BassFishModel extends EntityModel<Entity> {
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart head;
	public BassFishModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tail = root.getChild("tail");
		this.head = root.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -2.5F, -3.0F, 2.0F, 5.0F, 13.0F, new Dilation(0.0F))
		.uv(0, 7).cuboid(1.0F, -6.5F, -2.0F, 0.0F, 4.0F, 11.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.0F, 2.5F, 5.5F, 0.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 21.5F, -3.0F));

		ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(17, 6).mirrored().cuboid(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.0F, 0.0F, -1.25F, 0.0F, 0.5236F, 0.0F));

		ModelPartData body_r2 = body.addChild("body_r2", ModelPartBuilder.create().uv(17, 6).cuboid(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -1.25F, 0.0F, -0.5236F, 0.0F));

		ModelPartData body_r3 = body.addChild("body_r3", ModelPartBuilder.create().uv(0, 19).mirrored().cuboid(0.0F, 0.0F, -4.0F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, 2.5F, 3.0F, 0.0F, 0.0F, -0.3054F));

		ModelPartData body_r4 = body.addChild("body_r4", ModelPartBuilder.create().uv(0, 19).cuboid(0.0F, 0.0F, -4.0F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 2.5F, 3.0F, 0.0F, 0.0F, 0.3054F));

		ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 0.0F, 10.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(17, 0).cuboid(0.0F, -1.5F, -4.0F, 2.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -3.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}