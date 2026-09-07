package me.hippofatale.fantasy1e196ecore.attribute;

import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.function.Supplier;

public record AttributeData (Supplier<Attribute> attributeSupplier, String translationKey, double defaultValute, double minValue, double maxValue){
    public Attribute get() {
        return attributeSupplier().get();
    }
}
