package me.hippofatale.fantasy1e196ecore.statuseffect;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModStatusEffects {
    public static final DeferredRegister<MobEffect> STATUS_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Fantasy1E196ECore.MOD_ID);

    public static final Supplier<MobEffect> ENERGY_FIELD = STATUS_EFFECTS.register("energy_field",
            () -> new DamageReductionEffect("energy_field", 0.10D));
}
