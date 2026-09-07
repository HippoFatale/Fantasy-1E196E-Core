package me.hippofatale.fantasy1e196ecore.util;

import com.mojang.serialization.Codec;
import me.hippofatale.fantasy1e196ecore.attribute.ModAttributes;
import me.hippofatale.fantasy1e196ecore.item.weapon.GunItem;
import me.hippofatale.fantasy1e196ecore.item.weapon.MainWeaponItem;
import me.hippofatale.fantasy1e196ecore.item.weapon.SubWeaponItem;
import net.minecraft.core.Holder;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;

public enum PlayerClass implements StringRepresentable {
    NONE("none", "class.fantasy1e196ecore.none", ModAttributes.STRENGTH, MainWeaponItem.class, SubWeaponItem.class),

    //sabre
    SWORDMASTER("swordmaster", "class.fantasy1e196ecore.swordmaster", ModAttributes.STRENGTH, MainWeaponItem.class, SubWeaponItem.class),
    SPELLSWORD("spellsword", "class.fantasy1e196ecore.spellsword", ModAttributes.STRENGTH, MainWeaponItem.class, SubWeaponItem.class),
    PALADIN("paladin", "class.fantasy1e196ecore.paladin", ModAttributes.STRENGTH, MainWeaponItem.class, SubWeaponItem.class),

    //scythe
    DRUID("druid", "class.fantasy1e196ecore.druid", ModAttributes.STRENGTH, MainWeaponItem.class, SubWeaponItem.class),
    EXECUTIONER("executioner", "class.fantasy1e196ecore.executioner", ModAttributes.STRENGTH, MainWeaponItem.class, SubWeaponItem.class),

    //gun
    MECHANIC("mechanic", "class.fantasy1e196ecore.mechanic", ModAttributes.DEXTERITY, GunItem.class, SubWeaponItem.class),
    DOCTOR("doctor", "class.fantasy1e196ecore.doctor", ModAttributes.DEXTERITY, GunItem.class, SubWeaponItem.class),

    //staff
    ELEMENTALIST("elementalist", "class.fantasy1e196ecore.elementalist", ModAttributes.INTELLIGENCE, MainWeaponItem.class, SubWeaponItem.class),
    PRIEST("priest", "class.fantasy1e196ecore.priest", ModAttributes.INTELLIGENCE, MainWeaponItem.class, SubWeaponItem.class),

    //mana sphere
    BARD("bard", "class.fantasy1e196ecore.bard", ModAttributes.INTELLIGENCE, MainWeaponItem.class, SubWeaponItem.class),
    ASTROLOGER("astrologer", "class.fantasy1e196ecore.astrologer", ModAttributes.INTELLIGENCE, MainWeaponItem.class, SubWeaponItem.class),
    ;

    public static final Codec<PlayerClass> CODEC = StringRepresentable.fromEnum(PlayerClass::values);

    private final String id;
    private final String translationKey;
    private final Holder<Attribute> mainAttribute;
    private final Class<? extends MainWeaponItem> mainWeapon;
    private final Class<? extends SubWeaponItem> subWeapon;

    PlayerClass(String id, String translationKey, Holder<Attribute> mainAttribute, Class<? extends MainWeaponItem> mainWeapon, Class<? extends SubWeaponItem> subWeapon) {
        this.id = id;
        this.translationKey = translationKey;
        this.mainAttribute = mainAttribute;
        this.mainWeapon = mainWeapon;
        this.subWeapon = subWeapon;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public Holder<Attribute> getMainAttribute() {
        return mainAttribute;
    }

    public Class<? extends MainWeaponItem> getMainWeapon() {
        return mainWeapon;
    }

    public Class<? extends SubWeaponItem> getSubWeapon() {
        return subWeapon;
    }

    @Override
    public String getSerializedName() {
        return this.id;
    }
}
