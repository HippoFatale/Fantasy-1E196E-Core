package me.hippofatale.fantasy1e196ecore.ability;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModAbilities {
    private static final Map<ResourceLocation, Supplier<ModAbility>> ABILITIES = new HashMap<>();

    public static final Supplier<ModAbility> EXAMPLE_ABILITY = register(
            ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "example_ability"),
            ExampleAbility::new
    );

    //mechanic
    public static final Supplier<ModAbility> RAILGUN_ABILITY = register(
            ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "railgun_ability"),
            RailgunAbility::new
    );

    public static final Supplier<ModAbility> DRONE_ABILITY = register(
            ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "drone_ability"),
            DroneAbility::new
    );

    private static Supplier<ModAbility> register(ResourceLocation id, Supplier<ModAbility> ability) {
        ABILITIES.put(id, ability);
        Fantasy1E196ECore.LOGGER.info("Registered ability: {}", id);
        return ability;
    }

    public static ModAbility getAbility(ResourceLocation id) {
        Supplier<ModAbility> supplier = ABILITIES.get(id);
        return supplier != null ? supplier.get() : null;
    }

    public static boolean hasAbility(ResourceLocation id) {
        return ABILITIES.containsKey(id);
    }

    public static void init() {

    }
}
