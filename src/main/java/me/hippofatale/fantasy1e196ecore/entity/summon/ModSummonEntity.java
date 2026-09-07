package me.hippofatale.fantasy1e196ecore.entity.summon;

import me.hippofatale.fantasy1e196ecore.attachment.ModAttachments;
import me.hippofatale.fantasy1e196ecore.entity.ai.SummonOwnerAttackTargetGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.UUID;

public class ModSummonEntity extends PathfinderMob {
    private int lifeTicks;
    @Nullable private UUID ownerUUID;

    protected ModSummonEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.setInvulnerable(true);
    }

    public void setupSummon(Player owner, int durationTicks) {
        this.ownerUUID = owner.getUUID();
        this.lifeTicks = durationTicks;
    }

    @Nullable
    public Player getOwner() {
        if (this.ownerUUID == null){
            return null;
        }

        return this.level().getPlayerByUUID(this.ownerUUID);
    }

    @Nullable
    public UUID getOwnerUUID() {
        return this.ownerUUID;
    }

    @Override
    protected void registerGoals() {
        //attack
        this.attack();

        //set target
        this.targetSelector.addGoal(1, new SummonOwnerAttackTargetGoal(this));

        super.registerGoals();
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            Player owner = this.getOwner();
            if (owner == null || !owner.isAlive()) {
                this.summonDiscard();
                return;
            }

            this.lifeTicks--;

            if (this.lifeTicks <= 0) {
                this.onSummonTimeOut();
            }
        }
    }

    protected void onSummonTimeOut() {
        this.summonDiscard();
    }

    public void summonDiscard() {
        if (!this.level().isClientSide() && this.ownerUUID != null) {
            Player owner = this.getOwner();
            if (owner != null) {
                owner.getData(ModAttachments.SUMMONED_ENTITIES).remove(this.getUUID());
            }
        }
        super.discard();
    }

    public void attack() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("lifeTicks", this.lifeTicks);
        if (this.ownerUUID != null) {
            compound.putUUID("ownerUUID", this.ownerUUID);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("lifeTicks")) {
            this.lifeTicks = compound.getInt("lifeTicks");
        }
        if (compound.hasUUID("ownerUUID")) {
            this.ownerUUID = compound.getUUID("ownerUUID");
        }
    }
}
