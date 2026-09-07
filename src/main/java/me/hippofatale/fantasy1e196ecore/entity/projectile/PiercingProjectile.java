package me.hippofatale.fantasy1e196ecore.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public abstract class PiercingProjectile extends ModProjectile {
    protected final List<Integer> hitEntityIds = new ArrayList<>();

    public PiercingProjectile(EntityType<? extends ModProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public PiercingProjectile(EntityType<? extends ModProjectile> entityType, Level level, LivingEntity owner, double range, float speed) {
        super(entityType, level, owner, range, speed);
    }

    @Override
    protected boolean shouldDiscardOnHit() {
        return false;
    }

    @Override
    protected void handleCollision(HitResult result) {
        if (!this.level().isClientSide()) {
            if (result instanceof EntityHitResult) {
                if (((EntityHitResult) result).getEntity() instanceof LivingEntity target) {
                    if (!this.hitEntityIds.contains(target.getId())) {
                        this.hitEntityIds.add(target.getId());
                        this.onProjectileHit(result);
                    }
                }
            }
        }
    }
}
