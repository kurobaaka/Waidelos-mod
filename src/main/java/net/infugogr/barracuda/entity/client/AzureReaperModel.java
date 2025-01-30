package net.infugogr.barracuda.entity.client;

import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.entity.custom.AzureReaperEntity;
import software.bernie.geckolib.model.GeoModel;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.data.EntityModelData;

public class AzureReaperModel extends GeoModel<AzureReaperEntity> {
    @Override
    public Identifier getModelResource(AzureReaperEntity animatable) {
        return new Identifier(Barracuda.MOD_ID, "geo/azure_reaper.geo.json");
    }

    @Override
    public Identifier getTextureResource(AzureReaperEntity animatable) {
        return new Identifier(Barracuda.MOD_ID, "textures/entity/azure_reaper.png");
    }

    @Override
    public Identifier getAnimationResource(AzureReaperEntity animatable) {
        return new Identifier(Barracuda.MOD_ID, "animations/azure_reaper.animation.json");
    }

    @Override
    public void setCustomAnimations(AzureReaperEntity animatable, long instanceId, AnimationState<AzureReaperEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
