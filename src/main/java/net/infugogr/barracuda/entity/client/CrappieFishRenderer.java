import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;

public class CrappieFishRenderer extends EntityRenderer<CustomFishEntity> implements GeoEntityRenderer<CustomFishEntity> {
    public CrappieFishRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new GeoModel<>() {
            @Override
            public Identifier getModelResource(CustomFishEntity entity) {
                return new Identifier("barracuda", "geo/crappie_fish.geo.json");
            }
            
            @Override
            public Identifier getTextureResource(CustomFishEntity entity) {
                return new Identifier("barracuda", "textures/entity/crappie_fish.png");
            }
            
            @Override
            public Identifier getAnimationResource(CustomFishEntity entity) {
                return new Identifier("barracuda", "animations/crappie_fish.animation.json");
            }
        });
    }
}