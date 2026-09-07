package me.hippofatale.fantasy1e196ecore.network;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import me.hippofatale.fantasy1e196ecore.attachment.ModAttachments;
import me.hippofatale.fantasy1e196ecore.util.PlayerClass;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;

public class ClassChangeC2SPacket implements CustomPacketPayload {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "class_change");
    public static final CustomPacketPayload.Type<ClassChangeC2SPacket> TYPE = new CustomPacketPayload.Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ClassChangeC2SPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.stringUtf8(64),
            packet -> packet.playerClass.getSerializedName(),
            name -> {
                for (PlayerClass playerClass : PlayerClass.values()) {
                    if (playerClass.getSerializedName().equals(name)) return new ClassChangeC2SPacket(playerClass);
                }
                return new ClassChangeC2SPacket(PlayerClass.NONE);
            }
    );
    private final PlayerClass playerClass;

    public ClassChangeC2SPacket(PlayerClass playerClass) {
        this.playerClass = playerClass;
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ClassChangeC2SPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                Fantasy1E196ECore.LOGGER.info("Player {} changing class to {}", serverPlayer.getName(), packet.playerClass);
                serverPlayer.setData(ModAttachments.CURRENT_CLASS.get(), packet.playerClass);
            }
        });
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }
}
