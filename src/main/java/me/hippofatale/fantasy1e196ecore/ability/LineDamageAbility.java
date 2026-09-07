package me.hippofatale.fantasy1e196ecore.ability;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class LineDamageAbility extends ModAbility {
    private final float damage;
    private final double length;
    private final double width;
    private final double range;

    public LineDamageAbility(float damage, double length, double width, double range) {
        super(
            ResourceLocation.fromNamespaceAndPath(Fantasy1E196ECore.MOD_ID, "line_damage"),
            "ability.fantasy1e196ecore.line_damage",
            "ability.fantasy1e196ecore.line_damage.tooltip"
        );
        this.damage = damage;
        this.length = length;
        this.width = width;
        this.range = range;
    }

    public LineDamageAbility() {
        this(5.0f, 10.0, 3.0, 8.0);
    }

    @Override
    public void execute(ServerPlayer player) {
        Vec3 lookVec = player.getLookAngle();
        Vec3 playerPos = player.position();
        Vec3 eyePos = player.getEyePosition();

        // Calculate the center of the rectangle area in front of the player
        Vec3 areaCenter = eyePos.add(lookVec.scale(range));

        // Calculate perpendicular vectors for the rectangle
        Vec3 rightVec = new Vec3(-lookVec.z, 0.0, lookVec.x).normalize();
        Vec3 upVec = lookVec.cross(rightVec).normalize();

        // Calculate the 8 corners of the rectangular prism
        Vec3 halfForward = lookVec.scale(length / 2.0);
        Vec3 halfRight = rightVec.scale(width / 2.0);
        Vec3 halfUp = upVec.scale(2.0); // Height of the area

        Vec3 corner1 = areaCenter.subtract(halfForward).subtract(halfRight).subtract(halfUp);
        Vec3 corner2 = areaCenter.add(halfForward).add(halfRight).add(halfUp);

        // Create AABB from the corners
        AABB damageArea = new AABB(corner1, corner2);

        // Find all entities in the area
        for (Entity entity : player.level().getEntitiesOfClass(LivingEntity.class, damageArea)) {
            if (entity != player && !entity.isAlliedTo(player)) {
                entity.hurt(player.level().damageSources().playerAttack(player), damage);
            }
        }
    }
}
