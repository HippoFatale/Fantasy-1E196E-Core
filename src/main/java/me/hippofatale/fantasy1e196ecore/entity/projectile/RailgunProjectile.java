package me.hippofatale.fantasy1e196ecore.entity.projectile;

import me.hippofatale.fantasy1e196ecore.attachment.ModAttachments;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.network.PacketDistributor;

public class RailgunProjectile extends PiercingProjectile {
    public RailgunProjectile(EntityType<? extends ModProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void onProjectileHit(HitResult result) {
        if (this.level().isClientSide()) {
            return;
        }

        if (this.hitEntityIds.size() == 1 && this.getOwner() instanceof Player player) {
            int currentBattery = player.getData(ModAttachments.MECHANIC_BATTERY.get());
            player.setData(ModAttachments.MECHANIC_BATTERY.get(), currentBattery + 20);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }
}
