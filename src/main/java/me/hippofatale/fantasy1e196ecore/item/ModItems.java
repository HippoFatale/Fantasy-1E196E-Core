package me.hippofatale.fantasy1e196ecore.item;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import me.hippofatale.fantasy1e196ecore.item.weapon.GunItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Fantasy1E196ECore.MOD_ID);

    public static final Supplier<Item> WOOD_GUN = ITEMS.register("wood_gun",
            () -> new GunItem(new Item.Properties()));
}
