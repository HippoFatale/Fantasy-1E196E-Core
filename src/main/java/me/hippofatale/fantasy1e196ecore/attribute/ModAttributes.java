package me.hippofatale.fantasy1e196ecore.attribute;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Fantasy1E196ECore.MOD_ID);

    //main stats
    public static final Holder<Attribute> STRENGTH = ATTRIBUTES.register("strength",
            () -> new RangedAttribute("attribute.fantasy1e196ecore.strength", 0.0, 0.0, 1024.0).setSyncable(true));

    public static final Holder<Attribute> DEXTERITY = ATTRIBUTES.register("dexterity",
            () -> new RangedAttribute("attribute.fantasy1e196ecore.dexterity", 0.0, 0.0, 1024.0).setSyncable(true));

    public static final Holder<Attribute> INTELLIGENCE = ATTRIBUTES.register("intelligence",
            () -> new RangedAttribute("attribute.fantasy1e196ecore.intelligence", 0.0, 0.0, 1024.0).setSyncable(true));

    //attack stats
    public static final Holder<Attribute> SKILL_SPEED = ATTRIBUTES.register("skill_speed",
            () -> new RangedAttribute("attribute.fantasy1e196ecore.skill_speed", 0.0, 0.0, 1024.0).setSyncable(true));

    public static final Holder<Attribute> CRIT_RATE = ATTRIBUTES.register("crit_rate",
            () -> new RangedAttribute("attribute.fantasy1e196ecore.crit_rate", 0.0, 0.0, 100.0).setSyncable(true));

    public static final Holder<Attribute> CRIT_DAMAGE = ATTRIBUTES.register("crit_damage",
            () -> new RangedAttribute("attribute.fantasy1e196ecore.crit_damage", 100.0, 0.0, 1024.0).setSyncable(true));

    public static final Holder<Attribute> DAMAGE_DEALT = ATTRIBUTES.register("damage_dealt",
            () -> new RangedAttribute("attribute.fantasy1e196ecore.damage_dealt", 100.0, 0.0, 1024.0).setSyncable(true));

    public static final Holder<Attribute> DAMAGE_TAKEN = ATTRIBUTES.register("damage_taken",
            () -> new RangedAttribute("attribute.fantasy1e196ecore.damage_taken", 1.0, 0.0, 10.0).setSyncable(true));

//    //vanilla stats
//    public static final Supplier<Attribute> ATTACK_SPEED = Attributes.ATTACK_SPEED::value;
//    public static final Supplier<Attribute> MAX_HEALTH = Attributes.MAX_HEALTH::value;
//    public static final Supplier<Attribute> ARMOR = Attributes.ARMOR::value;
//    public static final Supplier<Attribute> MOVEMENT_SPEED = Attributes.MOVEMENT_SPEED::value;
//
//    public static final Map<String,AttributeData> ALL_ATTRIBUTES = new HashMap<>();
//
//    public static void initAttributeMap() {
//        ALL_ATTRIBUTES.put("strength", new AttributeData(STRENGTH, "attribute.fantasy1e196ecore.strength", ))
//    }


}
