package me.hippofatale.fantasy1e196ecore.item.weapon;

import me.hippofatale.fantasy1e196ecore.attachment.ModAttachments;
import me.hippofatale.fantasy1e196ecore.attribute.ModAttributes;
import me.hippofatale.fantasy1e196ecore.entity.projectile.BulletProjectile;
import me.hippofatale.fantasy1e196ecore.util.PlayerClass;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class GunItem extends MainWeaponItem {
    public GunItem(Properties properties) {
        super(properties, ModAttributes.DEXTERITY);
    }

    @Override
    protected void specialAttack(Level level, Player player) {
        if (level.isClientSide()) {
            return;
        }

        List<BulletProjectile.BulletType> currentCylinder = player.getData(ModAttachments.GUN_CYLINDER.get());
        if (currentCylinder.isEmpty()) {
            PlayerClass playerClass = player.getData(ModAttachments.CURRENT_CLASS.get());

            List<BulletProjectile.BulletType> reloadCylinder = new ArrayList<>();
            switch (playerClass) {
                case MECHANIC -> {
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.BATTERY);
                }
                case DOCTOR -> {
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.SYRINGE);
                }
                default -> {
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                    reloadCylinder.add(BulletProjectile.BulletType.NORMAL);
                }
            }
            player.setData(ModAttachments.GUN_CYLINDER.get(), reloadCylinder);

            return;
        }

        List<BulletProjectile.BulletType> mutableCylinder = new ArrayList<>(currentCylinder);
        BulletProjectile.BulletType firedBullet = mutableCylinder.removeFirst();
        player.setData(ModAttachments.GUN_CYLINDER.get(), mutableCylinder);
        BulletProjectile bullet = new BulletProjectile(level, player, firedBullet);

        bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, bullet.getSpeed(), 1.0F);
        level.addFreshEntity(bullet);

        player.getCooldowns().addCooldown(this, 4);
    }
}
