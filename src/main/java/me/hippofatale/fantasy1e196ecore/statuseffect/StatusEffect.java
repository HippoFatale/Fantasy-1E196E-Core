package me.hippofatale.fantasy1e196ecore.statuseffect;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class StatusEffect extends MobEffect {
    private final boolean isRemovable;
    private final boolean isStackable;

    protected StatusEffect(MobEffectCategory category) {
        super(category, 0);
        this.isRemovable = true;
        this.isStackable = false;
    }

    protected StatusEffect(MobEffectCategory category, boolean isRemovable, boolean isStackable) {
        super(category, 0);
        this.isRemovable = isRemovable;
        this.isStackable = isStackable;
    }

    protected StatusEffect(MobEffectCategory category, boolean isRemovable, boolean isStackable, int color) {
        super(category, color);
        this.isRemovable = isRemovable;
        this.isStackable = isStackable;
    }

    protected StatusEffect(MobEffectCategory category, boolean isRemovable, boolean isStackable, int color, ParticleOptions particle) {
        super(category, color, particle);
        this.isRemovable = isRemovable;
        this.isStackable = isStackable;
    }

    public boolean isRemovable() {
        return this.isRemovable;
    }

    public boolean isStackable() {
        return isStackable;
    }

    public boolean isBuff() {
        return this.getCategory() == MobEffectCategory.BENEFICIAL;
    }

    public boolean isDebuff() {
        return this.getCategory() == MobEffectCategory.HARMFUL;
    }

}
