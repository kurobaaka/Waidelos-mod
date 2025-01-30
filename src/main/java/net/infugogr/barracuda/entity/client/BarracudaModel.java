package net.infugogr.barracuda.entity.client;

import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.entity.custom.BarracudaEntity;
import software.bernie.geckolib.model.GeoModel;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.data.EntityModelData;

public class BarracudaModel extends GeoModel<BarracudaEntity> {
    @Override
    public Identifier getModelResource(BarracudaEntity animatable) {
        return new Identifier(Barracuda.MOD_ID, "geo/barracuda.geo.json");
    }

    @Override
    public Identifier getTextureResource(BarracudaEntity animatable) {
        return new Identifier(Barracuda.MOD_ID, "textures/entity/barracuda.png");
    }

    @Override
    public Identifier getAnimationResource(BarracudaEntity animatable) {
        return new Identifier(Barracuda.MOD_ID, "animations/barracuda.animation.json");
    }

    @Override
    public void setCustomAnimations(BarracudaEntity animatable, long instanceId, AnimationState<BarracudaEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
} //https://www.youtube.com/watch?v=pmoAtMbQYog
