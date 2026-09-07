package me.hippofatale.fantasy1e196ecore.ability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ExampleAbility extends ModAbility {
    
    public ExampleAbility() {
        super(
            ResourceLocation.fromNamespaceAndPath("fantasy1e196ecore", "example_ability"),
            "ability.fantasy1e196ecore.example",
            "ability.fantasy1e196ecore.example.tooltip"
        );
    }
    
    @Override
    public void execute(ServerPlayer player) {
        // Example ability logic: heal the player
        player.heal(4.0f);
        player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Ability activated! You were healed."));
    }
}
