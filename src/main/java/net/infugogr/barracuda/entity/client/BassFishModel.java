package net.infugogr.barracuda.entity.client;

import net.infugogr.barracuda.entity.custom.BassFishEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

// public class BassFishModel<T extends BassFishEntity> extends SinglePartEntityModel<T>
// public class BassFishModel<B extends AnimalEntity> extends EntityModel<Entity>
public class BassFishModel<T extends BassFishEntity> extends SinglePartEntityModel<T> {
	private final ModelPart body;
    private final ModelPart head;

    public BassFishModel(ModelPart body) {
		this.body = body.getChild("body");
		this.head = body.getChild("body").getChild("head");
        ModelPart tail = body.getChild("body").getChild("tail");
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
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(headYaw, headPitch);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public ModelPart getPart() {
		return body;
	}
}
