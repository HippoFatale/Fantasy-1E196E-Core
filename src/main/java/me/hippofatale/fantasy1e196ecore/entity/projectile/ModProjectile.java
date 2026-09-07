package me.hippofatale.fantasy1e196ecore.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public abstract class ModProjectile extends Projectile {
    protected final double range;
    protected final float speed;
    private static final double defaultRange = 40.0;
    private static final float defaultSpeed = 2.0F;
    protected Vec3 startPos;

    public ModProjectile(EntityType<? extends ModProjectile> entityType, Level level) {
        super(entityType, level);
        this.range = defaultRange;
        this.speed = defaultSpeed;
    }

    public ModProjectile(EntityType<? extends ModProjectile> entityType, Level level, LivingEntity owner){
        this(entityType, level, owner, defaultRange, defaultSpeed);
    }

    public ModProjectile(EntityType<? extends ModProjectile> entityType, Level level, LivingEntity owner, double range, float speed){
        super(entityType, level);
        this.setOwner(owner);
        this.range = range;
        this.speed = speed;
        this.setPos(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
        this.startPos = this.position();
    }

    public double getRange() {
        return range;
    }

    public float getSpeed() {
        return speed;
    }

    protected boolean shouldDiscardOnHit() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.startPos == null) {
            this.startPos = this.position();
        }
        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this,
                entity -> this.canHitEntity(entity) && entity instanceof Enemy
        );

        //max range or blocked
        if (this.position().distanceTo(this.startPos) >= this.range || hitResult.getType() == HitResult.Type.BLOCK) {
            this.onMaxRangeOrBlocked();
            return;
        }

        //on hit
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            this.handleCollision(hitResult);
            if (this.shouldDiscardOnHit()) {
                this.discard();
            }
            return;
        }

        //move projectile
        Vec3 movement = this.getDeltaMovement();
        this.setPos(this.getX() + movement.x, this.getY() + movement.y, this.getZ() + movement.z);
        ProjectileUtil.rotateTowardsMovement(this, 0.5F);
    }

    protected void onMaxRangeOrBlocked() {
        this.discard();
    }

    protected void handleCollision(HitResult result) {
        this.onProjectileHit(result);
    }

    protected abstract void onProjectileHit(HitResult result);
}
