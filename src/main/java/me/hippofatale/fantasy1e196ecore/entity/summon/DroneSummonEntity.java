package me.hippofatale.fantasy1e196ecore.entity.summon;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class DroneSummonEntity extends ModSummonEntity {

    public DroneSummonEntity(EntityType<? extends ModSummonEntity> entityType, Level level) {
        super(entityType, level);
    }


    @Override
    public void summonDiscard() {
        super.summonDiscard();
    }
}
