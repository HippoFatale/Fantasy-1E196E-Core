package me.hippofatale.fantasy1e196ecore.entity;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import me.hippofatale.fantasy1e196ecore.entity.projectile.BulletProjectile;
import me.hippofatale.fantasy1e196ecore.entity.projectile.RailgunProjectile;
import me.hippofatale.fantasy1e196ecore.entity.summon.DroneSummonEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Fantasy1E196ECore.MOD_ID);

    //summons
    public static final Supplier<EntityType<DroneSummonEntity>> MECHANIC_DRONE = ENTITIES.register("mechanic_drone",
            () -> EntityType.Builder.<DroneSummonEntity>of(DroneSummonEntity::new, MobCategory.MISC)
                    .sized(1.0F, 1.0F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("mechanic_drone"));

    //projectiles
    public static final Supplier<EntityType<BulletProjectile>> GUN_BULLET = ENTITIES.register("gun_bullet",
            () -> EntityType.Builder.<BulletProjectile>of(BulletProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("gun_bullet"));

    public static final Supplier<EntityType<RailgunProjectile>> RAILGUN_PROJECTILE = ENTITIES.register("railgun_projectile",
            () -> EntityType.Builder.<RailgunProjectile>of(RailgunProjectile::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("railgun_projectile"));
}
