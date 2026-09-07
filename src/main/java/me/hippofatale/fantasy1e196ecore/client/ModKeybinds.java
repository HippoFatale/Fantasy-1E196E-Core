package me.hippofatale.fantasy1e196ecore.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = "fantasy1e196ecore", value = Dist.CLIENT)
public class ModKeybinds {

    public static KeyMapping SKILL_1;
    public static KeyMapping SKILL_2;
    public static KeyMapping SKILL_3;
    public static KeyMapping SKILL_4;
    public static KeyMapping OPEN_CLASS_SELECTION;

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        SKILL_1 = new KeyMapping(
            "key.fantasy1e196ecore.skill_1",
            KeyConflictContext.UNIVERSAL,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_1,
            "key.category.fantasy1e196ecore"
        );

        SKILL_2 = new KeyMapping(
            "key.fantasy1e196ecore.skill_2",
            KeyConflictContext.UNIVERSAL,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_2,
            "key.category.fantasy1e196ecore"
        );

        SKILL_3 = new KeyMapping(
            "key.fantasy1e196ecore.skill_3",
            KeyConflictContext.UNIVERSAL,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_3,
            "key.category.fantasy1e196ecore"
        );

        SKILL_4 = new KeyMapping(
            "key.fantasy1e196ecore.skill_4",
            KeyConflictContext.UNIVERSAL,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_4,
            "key.category.fantasy1e196ecore"
        );

        OPEN_CLASS_SELECTION = new KeyMapping(
            "key.fantasy1e196ecore.open_class_selection",
            KeyConflictContext.UNIVERSAL,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "key.category.fantasy1e196ecore"
        );

        event.register(SKILL_1);
        event.register(SKILL_2);
        event.register(SKILL_3);
        event.register(SKILL_4);
        event.register(OPEN_CLASS_SELECTION);
    }
}
