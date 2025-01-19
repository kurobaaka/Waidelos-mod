package net.infugogr.barracuda.entity.effect;

import net.infugogr.barracuda.Barracuda;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModStatusEffects extends StatusEffects {
    public static final RegistryEntry<StatusEffect> CURSE = register(
            "curse", new CurseStatusEffect(StatusEffectCategory.HARMFUL, 2039587)
    );
    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Barracuda.MOD_ID, id), statusEffect);
    }

    public static void registerEffects(){
        Barracuda.LOGGER.info("Registering Mod Effects for" + Barracuda.MOD_ID);
    }
}


