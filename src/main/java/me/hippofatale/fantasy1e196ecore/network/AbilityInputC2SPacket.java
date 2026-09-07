package me.hippofatale.fantasy1e196ecore.network;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import me.hippofatale.fantasy1e196ecore.ability.ModAbility;
import me.hippofatale.fantasy1e196ecore.ability.ModAbilities;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class AbilityInputC2SPacket implements CustomPacketPayload {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "ability_activation");
    public static final CustomPacketPayload.Type<AbilityInputC2SPacket> TYPE = new CustomPacketPayload.Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, AbilityInputC2SPacket> STREAM_CODEC = StreamCodec.composite(
        ResourceLocation.STREAM_CODEC,
        AbilityInputC2SPacket::getAbilityId,
        AbilityInputC2SPacket::new
    );
    
    private final ResourceLocation abilityId;
    
    public AbilityInputC2SPacket(ResourceLocation abilityId) {
        this.abilityId = abilityId;
    }
    
    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
    
    public static void handle(AbilityInputC2SPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                ModAbility ability = ModAbilities.getAbility(packet.abilityId);
                if (ability != null) {
                    Fantasy1E196ECore.LOGGER.info("Executing ability: {}", packet.abilityId);
                    ability.execute(serverPlayer);
                } else {
                    Fantasy1E196ECore.LOGGER.warn("Ability not found: {}", packet.abilityId);
                }
            }
        });
    }
    
    public ResourceLocation getAbilityId() {
        return abilityId;
    }
}
