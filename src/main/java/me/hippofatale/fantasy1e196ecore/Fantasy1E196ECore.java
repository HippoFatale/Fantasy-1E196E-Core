package me.hippofatale.fantasy1e196ecore;

import me.hippofatale.fantasy1e196ecore.ability.ExampleAbility;
import me.hippofatale.fantasy1e196ecore.ability.ModAbilities;
import me.hippofatale.fantasy1e196ecore.attachment.ModAttachments;
import me.hippofatale.fantasy1e196ecore.attribute.ModAttributes;
import me.hippofatale.fantasy1e196ecore.datacomponent.ModDataComponents;
import me.hippofatale.fantasy1e196ecore.entity.ModEntities;
import me.hippofatale.fantasy1e196ecore.item.ModItems;
import me.hippofatale.fantasy1e196ecore.network.ModNetwork;
import me.hippofatale.fantasy1e196ecore.statuseffect.ModStatusEffects;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Fantasy1E196ECore.MOD_ID)
public class Fantasy1E196ECore {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "fantasy1e196ecore";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Fantasy1E196ECore(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game event we are interested in.
        // Note that this is necessary if and only if we want *this* class (Fantasy1E196ECore) to respond directly to event.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModAttachments.ATTACHMENT_TYPES.register(modEventBus);
        ModAttributes.ATTRIBUTES.register(modEventBus);
        ModDataComponents.COMPONENTS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModStatusEffects.STATUS_EFFECTS.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    private void commonSetup(FMLCommonSetupEvent event) {
        ModAbilities.init();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
