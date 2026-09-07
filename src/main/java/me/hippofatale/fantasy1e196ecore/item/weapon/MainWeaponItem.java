package me.hippofatale.fantasy1e196ecore.item.weapon;

import me.hippofatale.fantasy1e196ecore.attachment.ModAttachments;
import me.hippofatale.fantasy1e196ecore.util.PlayerClass;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MainWeaponItem extends Item {
    private final Holder<Attribute> attribute;

    public MainWeaponItem(Properties properties, Holder<Attribute> attribute) {
        super(properties.stacksTo(1));
        this.attribute = attribute;
    }

    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return false;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        //STR based melee damage
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack weapon = player.getItemInHand(usedHand);

        if (level.isClientSide()) {
            return InteractionResultHolder.sidedSuccess(weapon, level.isClientSide());
        }

        //check if weapon matches class
        PlayerClass playerClass = player.getData(ModAttachments.CURRENT_CLASS.get());
        if (!playerClass.getMainWeapon().isInstance(weapon.getItem())) {
            return InteractionResultHolder.fail(weapon);
        }

        this.specialAttack(level, player);

        return super.use(level, player, usedHand);
    }

    protected void specialAttack(Level level, Player player) {

    }
}
