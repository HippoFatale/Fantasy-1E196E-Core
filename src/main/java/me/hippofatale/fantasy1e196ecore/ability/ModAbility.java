package me.hippofatale.fantasy1e196ecore.ability;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public abstract class ModAbility {
    private final ResourceLocation abilityId;
    private final String nameTranslationKey;
    private final String tooltipTranslationKey;

    public ModAbility(ResourceLocation abilityId, String nameTranslationKey, String tooltipTranslationKey) {
        this.abilityId = abilityId;
        this.nameTranslationKey = nameTranslationKey;
        this.tooltipTranslationKey = tooltipTranslationKey;
    }

    public ResourceLocation getAbilityId() {
        return this.abilityId;
    }

    public Component getName() {
        return Component.translatable(this.nameTranslationKey);
    }

    public Component getTooltip() {
        return Component.translatable(this.tooltipTranslationKey);
    }

    public abstract void execute(ServerPlayer player);
}
