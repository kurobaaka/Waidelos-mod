package net.infugogr.barracuda.entity.client;

import net.infugogr.barracuda.entity.custom.AzureSerpentEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class AzureSerpentModel<T extends AzureSerpentEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;
	private final ModelPart head;

	public AzureSerpentModel(ModelPart body) {
		this.body = body.getChild("body");
		this.head = body.getChild("body").getChild("head");
		ModelPart tail = body.getChild("body").getChild("tail");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 66).cuboid(-7.0F, -7.0F, -32.0F, 14.0F, 12.0F, 14.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-8.0F, -8.0F, -18.0F, 16.0F, 14.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 18.0F, 8.0F));

		ModelPartData Spine3 = body.addChild("Spine3", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.75F, -11.0F, -0.8727F, 0.0F, 0.0F));

		ModelPartData LeftFin = body.addChild("LeftFin", ModelPartBuilder.create().uv(0, 114).cuboid(-8.0F, -1.0F, 0.0F, 8.0F, 2.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, 0.0F, -15.0F, 0.0F, 0.7854F, 0.0F));

		ModelPartData RightFin = body.addChild("RightFin", ModelPartBuilder.create().uv(0, 114).mirrored().cuboid(0.0F, -1.0F, 0.0F, 8.0F, 2.0F, 24.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-8.0F, 0.0F, -15.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(0, 32).cuboid(-8.0F, -7.0F, 0.0F, 16.0F, 13.0F, 18.0F, new Dilation(0.0F))
				.uv(50, 50).cuboid(-7.0F, -6.0F, 18.0F, 14.0F, 12.0F, 18.0F, new Dilation(0.0F))
				.uv(52, 16).cuboid(-7.0F, -5.0F, 36.0F, 14.0F, 10.0F, 16.0F, new Dilation(0.0F))
				.uv(83, 85).cuboid(-5.0F, -4.0F, 52.0F, 10.0F, 8.0F, 15.0F, new Dilation(0.0F))
				.uv(0, 92).cuboid(-3.0F, -3.0F, 67.0F, 6.0F, 6.0F, 16.0F, new Dilation(0.0F))
				.uv(44, 84).cuboid(0.0F, -7.0F, 69.0F, 0.0F, 14.0F, 16.0F, new Dilation(0.0F))
				.uv(35, 0).cuboid(-7.0F, -0.5F, 69.0F, 14.0F, 0.0F, 15.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Spine4 = body.addChild("Spine4", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.75F, 7.0F, -0.8727F, 0.0F, 0.0F));

		ModelPartData LeftFin2 = body.addChild("LeftFin2", ModelPartBuilder.create().uv(0, 114).cuboid(-8.0F, -1.0F, -5.6569F, 8.0F, 2.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 2.0F, 0.0F, 0.7854F, 0.0F));

		ModelPartData RightFin2 = body.addChild("RightFin2", ModelPartBuilder.create().uv(0, 114).mirrored().cuboid(0.0F, -1.0F, -5.6569F, 8.0F, 2.0F, 24.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, 0.0F, 2.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(42, 80).cuboid(-7.0F, -6.0F, -14.0F, 14.0F, 6.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -32.0F));

		ModelPartData Spine1 = head.addChild("Spine1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.75F, -9.0F, -0.8727F, 0.0F, 0.0F));

		ModelPartData LeftJaw = head.addChild("LeftJaw", ModelPartBuilder.create().uv(96, 0).cuboid(-3.0F, -3.0F, -12.0F, 6.0F, 6.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 3.0F, 0.0F, 0.1745F, -0.3054F, 0.0F));

		ModelPartData RightJaw = head.addChild("RightJaw", ModelPartBuilder.create().uv(96, 42).cuboid(-3.0F, -3.0F, -12.0F, 6.0F, 6.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 3.0F, 0.0F, 0.1745F, 0.2618F, 0.0F));

		ModelPartData Spine7 = body.addChild("Spine7", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.75F, 57.0F, -0.8727F, 0.0F, 0.0F));

		ModelPartData Spine6 = body.addChild("Spine6", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.75F, 42.0F, -0.8727F, 0.0F, 0.0F));

		ModelPartData Spine5 = body.addChild("Spine5", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.75F, 25.0F, -0.8727F, 0.0F, 0.0F));

		ModelPartData LeftFin3 = body.addChild("LeftFin3", ModelPartBuilder.create().uv(0, 114).cuboid(-8.0F, -1.0F, -11.3137F, 8.0F, 2.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 23.0F, 0.0F, 0.7854F, 0.0F));

		ModelPartData RightFin3 = body.addChild("RightFin3", ModelPartBuilder.create().uv(0, 114).mirrored().cuboid(0.0F, -1.0F, -11.3137F, 8.0F, 2.0F, 24.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-7.0F, 0.0F, 23.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData Spine2 = body.addChild("Spine2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -8.0F, 0.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.75F, -28.0F, -0.8727F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 256, 256);
	}

		@Override
		public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		}

		@Override
		public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
			body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		}


		@Override
		public ModelPart getPart() {
			return body;
		}
	}

