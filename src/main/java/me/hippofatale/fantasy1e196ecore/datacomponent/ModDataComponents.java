package me.hippofatale.fantasy1e196ecore.datacomponent;

import com.mojang.serialization.Codec;
import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Fantasy1E196ECore.MOD_ID);

    public static final Supplier<DataComponentType<Integer>> GUN_AMMO = COMPONENTS.register("gun_ammo",
            () -> DataComponentType.<Integer>builder()
                    .persistent(Codec.INT)
                    .build());
}
