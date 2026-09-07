package me.hippofatale.fantasy1e196ecore.ability;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import me.hippofatale.fantasy1e196ecore.attachment.ModAttachments;
import me.hippofatale.fantasy1e196ecore.entity.ModEntities;
import me.hippofatale.fantasy1e196ecore.entity.projectile.RailgunProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class RailgunAbility extends ModAbility {
    public RailgunAbility() {
        super(
                ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "railgun_ability"),
                "ability.fantasy1e196ecore.railgun",
                "ability.fantasy1e196ecore.railgun.tooltip"
        );
    }

    @Override
    public void execute(ServerPlayer player) {
        int currentBattery = player.getData(ModAttachments.MECHANIC_BATTERY.get());
        if (currentBattery < 10) {
            return;
        }

        player.setData(ModAttachments.MECHANIC_BATTERY.get(), currentBattery - 10);

        RailgunProjectile projectile = new RailgunProjectile(ModEntities.RAILGUN_PROJECTILE.get(), player.level());
        projectile.setOwner(player);
        projectile.moveTo(player.getX(), player.getEyeY(), player.getZ());
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, projectile.getSpeed(), 0.0F);
        player.level().addFreshEntity(projectile);
    }
}
