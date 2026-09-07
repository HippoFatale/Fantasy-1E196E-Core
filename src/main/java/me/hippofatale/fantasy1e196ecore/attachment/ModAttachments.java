package me.hippofatale.fantasy1e196ecore.attachment;

import com.mojang.serialization.Codec;
import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import me.hippofatale.fantasy1e196ecore.entity.projectile.BulletProjectile;
import me.hippofatale.fantasy1e196ecore.entity.projectile.BulletProjectile.BulletType;
import me.hippofatale.fantasy1e196ecore.util.PlayerClass;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Fantasy1E196ECore.MOD_ID);

    //player data
    public static final Supplier<AttachmentType<Integer>> ARCANUM_POINT = ATTACHMENT_TYPES.register("arcanum_point",
            () -> AttachmentType.<Integer>builder(() -> 0)
                    .serialize(Codec.INT)
                    .copyOnDeath()
                    .sync((holder, to) -> holder == to, ByteBufCodecs.INT)
                    .build());

    public static final Supplier<AttachmentType<PlayerClass>> CURRENT_CLASS = ATTACHMENT_TYPES.register("current_class",
            () -> AttachmentType.builder(() -> PlayerClass.NONE)
                    .serialize(PlayerClass.CODEC)
                    .copyOnDeath()
                    .sync((holder, to) -> holder == to, ByteBufCodecs.fromCodec(PlayerClass.CODEC))
                    .build());

    //weapon attachments
    private static final StreamCodec<RegistryFriendlyByteBuf, List<BulletType>> CYLINDER_STREAM_CODEC = StreamCodec.of(
            (buf, list) -> {
                buf.writeVarInt(list.size());
                for (BulletProjectile.BulletType t : list) buf.writeEnum(t);
            },
            buf -> {
                int size = buf.readVarInt();
                List<BulletProjectile.BulletType> list = new ArrayList<>();
                for (int i = 0; i < size; i++) list.add(buf.readEnum(BulletProjectile.BulletType.class));
                return list;
            }
    );

    public static final Supplier<AttachmentType<List<BulletProjectile.BulletType>>> GUN_CYLINDER = ATTACHMENT_TYPES.register(
            "gun_cylinder", () -> AttachmentType.builder(() -> (List<BulletProjectile.BulletType>) new ArrayList<BulletProjectile.BulletType>())
                    .sync((holder, to) -> holder == to, CYLINDER_STREAM_CODEC)
                    .build()
    );

    //summon attachments
    private static final StreamCodec<RegistryFriendlyByteBuf, List<UUID>> SUMMONED_ENTITIES_STREAM_CODEC = StreamCodec.of(
            (buf, list) -> {
                buf.writeVarInt(list.size());
                for (UUID uuid : list) UUIDUtil.STREAM_CODEC.encode(buf, uuid);
            },
            buf -> {
                int size = buf.readVarInt();
                List<UUID> list = new ArrayList<>();
                for (int i = 0; i < size; i++) list.add(UUIDUtil.STREAM_CODEC.decode(buf));
                return list;
            }
    );

    public static final Supplier<AttachmentType<List<UUID>>> SUMMONED_ENTITIES = ATTACHMENT_TYPES.register(
            "summoned_entities", () -> AttachmentType.builder(() -> (List<UUID>) new ArrayList<UUID>())
                    .sync((holder, to) -> holder == to, SUMMONED_ENTITIES_STREAM_CODEC)
                    .build()
    );

    //class attachments
    public static final Supplier<AttachmentType<Integer>> MECHANIC_BATTERY = ATTACHMENT_TYPES.register("mechanic_battery",
            () -> AttachmentType.builder(() -> 0)
                    .sync((holder, to) -> holder == to, ByteBufCodecs.INT)
                    .build()
    );
}
