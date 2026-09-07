package me.hippofatale.fantasy1e196ecore.statuseffect;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import me.hippofatale.fantasy1e196ecore.attribute.ModAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class DamageReductionEffect extends StatusEffect {
    public DamageReductionEffect(String effectName, double value) {
        super(MobEffectCategory.BENEFICIAL);

        double reductionValue = -Math.abs(value);

        this.addAttributeModifier(
                ModAttributes.DAMAGE_TAKEN,
                ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "effect." + effectName),
                reductionValue,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }
}
