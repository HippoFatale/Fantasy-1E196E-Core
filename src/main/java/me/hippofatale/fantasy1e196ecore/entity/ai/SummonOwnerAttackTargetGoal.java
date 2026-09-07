package me.hippofatale.fantasy1e196ecore.entity.ai;

import me.hippofatale.fantasy1e196ecore.entity.summon.ModSummonEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class SummonOwnerAttackTargetGoal extends TargetGoal {
    private final ModSummonEntity summon;
    private LivingEntity ownerLastTarget;
    private int timestamp;


    public SummonOwnerAttackTargetGoal(ModSummonEntity summon) {
        super(summon, false);
        this.summon = summon;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        Player owner = this.summon.getOwner();
        if (owner == null) {
            return false;
        }

        this.ownerLastTarget = owner.getLastHurtMob();
        int i = owner.getLastHurtMobTimestamp();

        return i != this.timestamp && this.canAttack(this.ownerLastTarget, TargetingConditions.DEFAULT);
    }

    @Override
    public void start() {
        this.mob.setTarget(this.ownerLastTarget);
        Player owner = this.summon.getOwner();
        if (owner != null) {
            this.timestamp = owner.getLastHurtMobTimestamp();
        }

        super.start();
    }
}
