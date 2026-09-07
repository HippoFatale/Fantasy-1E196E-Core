package me.hippofatale.fantasy1e196ecore.client;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import me.hippofatale.fantasy1e196ecore.client.screen.ClassSelectionScreen;
import me.hippofatale.fantasy1e196ecore.network.AbilityInputC2SPacket;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Fantasy1E196ECore.MOD_ID, value = Dist.CLIENT)
public class KeybindHandler {
    
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null || mc.screen != null) {
            return;
        }

        if (ModKeybinds.SKILL_1.consumeClick()) {
            onSkillPressed(1);
        } else if (ModKeybinds.SKILL_2.consumeClick()) {
            onSkillPressed(2);
        } else if (ModKeybinds.SKILL_3.consumeClick()) {
            onSkillPressed(3);
        } else if (ModKeybinds.SKILL_4.consumeClick()) {
            onSkillPressed(4);
        } else if (ModKeybinds.OPEN_CLASS_SELECTION.consumeClick()) {
            mc.setScreen(new ClassSelectionScreen());
        }
    }

    private static void onSkillPressed(int skillSlot) {
        Fantasy1E196ECore.LOGGER.info("Skill {} key pressed", skillSlot);

        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            net.minecraft.resources.ResourceLocation abilityId;

            switch (skillSlot) {
                case 1:
                    abilityId = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "railgun_ability");
                    break;
                default:
                    abilityId = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "example_ability");
                    break;
            }

            // Send packet to server to activate ability
            PacketDistributor.sendToServer(new AbilityInputC2SPacket(abilityId));
        }
    }
}
