package me.hippofatale.fantasy1e196ecore.event;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import me.hippofatale.fantasy1e196ecore.attachment.ModAttachments;
import me.hippofatale.fantasy1e196ecore.attribute.ModAttributes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;

@EventBusSubscriber(modid = Fantasy1E196ECore.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerLevelUp(PlayerXpEvent.LevelChange event) {
        if (event.getEntity().level().isClientSide()) {
            return;
        }
        ServerPlayer player = (ServerPlayer) event.getEntity();
        int currentLevel = player.experienceLevel;
        int newLevel = event.getLevels();

        if (newLevel > currentLevel) {
            int levelsGained = newLevel - currentLevel;
            int pointsToGive = levelsGained * 5;

            int currentPoints = player.getData(ModAttachments.ARCANUM_POINT.get());
            player.setData(ModAttachments.ARCANUM_POINT.get(), currentPoints + pointsToGive);
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.getEntity() instanceof ServerPlayer newPlayer) {
            newPlayer.syncData(ModAttachments.ARCANUM_POINT.get());
        }
    }

    @SubscribeEvent
    public static void onAttributeModification(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, ModAttributes.STRENGTH);
        event.add(EntityType.PLAYER, ModAttributes.DEXTERITY);
        event.add(EntityType.PLAYER, ModAttributes.INTELLIGENCE);
        event.add(EntityType.PLAYER, ModAttributes.SKILL_SPEED);
        event.add(EntityType.PLAYER, ModAttributes.CRIT_RATE);
        event.add(EntityType.PLAYER, ModAttributes.CRIT_DAMAGE);
    }
}
