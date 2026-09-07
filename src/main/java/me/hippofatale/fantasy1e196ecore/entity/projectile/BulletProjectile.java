package me.hippofatale.fantasy1e196ecore.entity.projectile;

import com.mojang.serialization.Codec;
import me.hippofatale.fantasy1e196ecore.attachment.ModAttachments;
import me.hippofatale.fantasy1e196ecore.entity.ModEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.network.PacketDistributor;

public class BulletProjectile extends ModProjectile {
    public enum BulletType implements StringRepresentable {
        NORMAL("normal"),
        BATTERY("battery"),
        SYRINGE("syringe"),
        FREIKUGEL("freikugel");

        public static final Codec<BulletType> CODEC = StringRepresentable.fromEnum(BulletType::values);

        private final String name;
        BulletType(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }

    private static final EntityDataAccessor<Integer> BULLET_TYPE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.INT);

    public BulletProjectile(EntityType<? extends ModProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public BulletProjectile(Level level, LivingEntity owner, BulletType type) {
        super(ModEntities.GUN_BULLET.get(), level, owner);
        this.entityData.set(BULLET_TYPE, type.ordinal());
    }

    public BulletType getBulletType() {
        int id = this.entityData.get(BULLET_TYPE);
        return BulletType.values()[id];
    }

    @Override
    protected void onProjectileHit(HitResult result) {
        if (this.level().isClientSide()) {
            return;
        }

        if (result instanceof EntityHitResult entityHitResult && this.getOwner() instanceof Player player) {
            LivingEntity target = (LivingEntity) entityHitResult.getEntity();
            switch (this.getBulletType()) {
                case NORMAL -> {
                    //deal damage based on DEX
                }
                case BATTERY -> {
                    //TODO battery is increased every tick until projectile discards

                    int currentBattery = player.getData(ModAttachments.MECHANIC_BATTERY.get());
                    player.setData(ModAttachments.MECHANIC_BATTERY.get(), currentBattery + 10);
                }
                case SYRINGE -> {
                    //heal nearby players
                }
                case FREIKUGEL -> {
                    //double damage
                }
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(BULLET_TYPE, BulletType.NORMAL.ordinal());
    }
}
