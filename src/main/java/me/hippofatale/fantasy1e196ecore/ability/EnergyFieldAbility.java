package me.hippofatale.fantasy1e196ecore.ability;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class EnergyFieldAbility extends ModAbility{
    public EnergyFieldAbility() {
        super(
                ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "energy_field_ability"),
                "ability.fantasy1e196ecore.energy_field",
                "ability.fantasy1e196ecore.energy_field.tooltip"
        );
    }

    @Override
    public void execute(ServerPlayer player) {

    }
}
