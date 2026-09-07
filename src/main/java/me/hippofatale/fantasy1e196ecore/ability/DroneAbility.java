package me.hippofatale.fantasy1e196ecore.ability;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import me.hippofatale.fantasy1e196ecore.entity.ModEntities;
import me.hippofatale.fantasy1e196ecore.entity.summon.DroneSummonEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class DroneAbility extends ModAbility {
    public DroneAbility() {
        super(
            ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "drone_ability"),
            "ability.fantasy1e196ecore.drone",
            "ability.fantasy1e196ecore.drone.tooltip"
        );
    }
    
    @Override
    public void execute(ServerPlayer player) {

    }
    
    private DroneSummonEntity getActiveDrone(ServerPlayer player) {

    }
    
    private void summonDrone(ServerPlayer player) {
        DroneSummonEntity drone = ModEntities.MECHANIC_DRONE.get().create(player.level());
        if (drone != null) {
            drone.moveTo(player.position());
            drone.setupSummon(player, 600);
            player.level().addFreshEntity(drone);
        }
    }
}
