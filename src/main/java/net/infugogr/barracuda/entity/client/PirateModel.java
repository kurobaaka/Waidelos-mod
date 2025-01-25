// package net.infugogr.barracuda.entity.client;

// import javax.swing.text.html.parser.Entity;

// import net.minecraft.client.model.Dilation;
// import net.minecraft.client.model.ModelData;
// import net.minecraft.client.model.ModelPart;
// import net.minecraft.client.model.ModelPartBuilder;
// import net.minecraft.client.model.ModelPartData;
// import net.minecraft.client.model.ModelTransform;
// import net.minecraft.client.model.TexturedModelData;
// import net.minecraft.client.render.VertexConsumer;
// import net.minecraft.client.render.entity.model.EntityModel;
// import net.minecraft.client.render.entity.model.SinglePartEntityModel;
// import net.minecraft.client.util.math.MatrixStack;
// import net.infugogr.barracuda.entity.custom.*;
// import net.infugogr.barracuda.entity.client.ModModelLayers;

// public class PirateModel<T extends PirateEntity> extends SinglePartEntityModel<T> {
// 	private final ModelPart head;
// 	private final ModelPart bone;
// 	private final ModelPart group;
// 	private final ModelPart hat;
// 	private final ModelPart nose;
// 	private final ModelPart body;
// 	private final ModelPart arms;
// 	private final ModelPart arms_flipped;
// 	private final ModelPart right_leg;
// 	private final ModelPart left_leg;
// 	private final ModelPart right_arm;
// 	private final ModelPart left_arm;
// 	public PirateModel(ModelPart root) {
// 		this.head = root.getChild("head");
// 		this.bone = root.getChild("bone");
// 		this.group = root.getChild("group");
// 		this.hat = root.getChild("hat");
// 		this.nose = root.getChild("nose");
// 		this.body = root.getChild("body");
// 		this.arms = root.getChild("arms");
// 		this.arms_flipped = root.getChild("arms_flipped");
// 		this.right_leg = root.getChild("right_leg");
// 		this.left_leg = root.getChild("left_leg");
// 		this.right_arm = root.getChild("right_arm");
// 		this.left_arm = root.getChild("left_arm");
// 	}
// 	public static TexturedModelData getTexturedModelData() {
// 		ModelData modelData = new ModelData();
// 		ModelPartData modelPartData = modelData.getRoot();
// 		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.0F))
// 		.uv(64, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
// 		.uv(24, 0).cuboid(-1.0F, -3.0F, -6.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
// 		.uv(32, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.5F))
// 		.uv(96, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

// 		ModelPartData bone = head.addChild("bone", ModelPartBuilder.create().uv(102, 65).cuboid(7.4F, 2.0F, -8.0F, 0.0F, 6.0F, 1.0F, new Dilation(0.0F))
// 		.uv(102, 65).cuboid(16.6F, 2.0F, -8.0F, 0.0F, 6.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-12.0F, -6.6434F, 5.8128F));

// 		ModelPartData group = head.addChild("group", ModelPartBuilder.create().uv(72, 75).cuboid(-6.0F, 9.9F, -8.0F, 14.0F, -1.0F, 14.0F, new Dilation(0.5F))
// 		.uv(96, 64).cuboid(-3.0F, 6.0F, -5.0F, 8.0F, 3.0F, 8.0F, new Dilation(0.5F)), ModelTransform.of(-1.0F, -14.4056F, -0.5792F, 0.1745F, 0.0F, 0.0F));

// 		ModelPartData hat = modelPartData.addChild("hat", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

// 		ModelPartData nose = modelPartData.addChild("nose", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

// 		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(80, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
// 		.uv(80, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.2F))
// 		.uv(16, 20).cuboid(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new Dilation(0.0F))
// 		.uv(0, 38).cuboid(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

// 		ModelPartData arms = modelPartData.addChild("arms", ModelPartBuilder.create().uv(40, 38).cuboid(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.0F))
// 		.uv(44, 22).cuboid(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, 0.0F));

// 		ModelPartData arms_flipped = arms.addChild("arms_flipped", ModelPartBuilder.create().uv(44, 22).mirrored().cuboid(4.0F, -24.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 22.0F, 0.0F));

// 		ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 22).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
// 		.uv(28, 46).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

// 		ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(0, 22).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
// 		.uv(28, 46).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.2F)).mirrored(false), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

// 		ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(44, 46).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
// 		.uv(104, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

// 		ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(44, 30).mirrored().cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
// 		.uv(112, 48).mirrored().cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.2F)).mirrored(false), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
// 		return TexturedModelData.of(modelData, 128, 128);
// 	}
// 	@Override
// 	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
// 	}
// 	@Override
// 	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
// 		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
// 		hat.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
// 		nose.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
// 		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
// 		arms.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
// 		right_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
// 		left_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
// 		right_arm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
// 		left_arm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
// 	}
// 	@Override
// 	public ModelPart getPart() {
// 		return pirate;
// 	}
// }