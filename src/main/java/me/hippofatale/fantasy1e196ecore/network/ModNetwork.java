package me.hippofatale.fantasy1e196ecore.network;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Fantasy1E196ECore.MOD_ID)
public class ModNetwork {
    
    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(AbilityInputC2SPacket.TYPE, AbilityInputC2SPacket.STREAM_CODEC, AbilityInputC2SPacket::handle);
        registrar.playToServer(ClassChangeC2SPacket.TYPE, ClassChangeC2SPacket.STREAM_CODEC, ClassChangeC2SPacket::handle);
    }
}
