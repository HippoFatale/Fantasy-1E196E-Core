package me.hippofatale.fantasy1e196ecore.statuseffect;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class DotEffect extends StatusEffect {
    private final String effectName;
    private final int dotDamage;

    protected DotEffect(String effectName, UUID casterUUID, int dotDamage) {
        super(MobEffectCategory.HARMFUL);
        this.effectName = effectName;
        this.dotDamage = dotDamage;

        ResourceLocation uniqueKey = ResourceLocation.fromNamespaceAndPath(
                Fantasy1E196ECore.MOD_ID,
                "effect." + effectName + "_" + casterUUID.toString()
        );

        this.addAttributeModifier(
                Attributes.MAX_HEALTH,
                uniqueKey,
                0.0D,
                AttributeModifier.Operation.ADD_VALUE
        );
    }

    public static MobEffectInstance createCasterSpecificInstance(String effectName, UUID casterUUID, int dotDamage, int duration) {
        DotEffect personalEffect = new DotEffect(effectName, casterUUID, dotDamage);

        Holder<net.minecraft.world.effect.MobEffect> holder = Holder.direct(personalEffect);

        return new MobEffectInstance(holder, duration, 0, false, true);
    }
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide()) {
            livingEntity.hurt(livingEntity.damageSources().genericKill(), dotDamage);
        }
        return true;
    }

    @Override
    public String getDescriptionId() {
        return "effect." + Fantasy1E196ECore.MOD_ID + "." + this.effectName;
    }
}
