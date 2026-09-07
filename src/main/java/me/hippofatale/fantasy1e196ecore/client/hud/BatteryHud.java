package me.hippofatale.fantasy1e196ecore.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import me.hippofatale.fantasy1e196ecore.attachment.ModAttachments;
import me.hippofatale.fantasy1e196ecore.entity.projectile.BulletProjectile;
import me.hippofatale.fantasy1e196ecore.item.weapon.GunItem;
import me.hippofatale.fantasy1e196ecore.util.PlayerClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.Font;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

import java.util.List;

@EventBusSubscriber(modid = "fantasy1e196ecore", value = Dist.CLIENT)
public class BatteryHud {

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) {
            return;
        }

        PlayerClass playerClass = mc.player.getData(ModAttachments.CURRENT_CLASS.get());
        int yOffset = 10;

        // Display battery for MECHANIC class
        if (playerClass == PlayerClass.MECHANIC) {
            int battery = mc.player.getData(ModAttachments.MECHANIC_BATTERY.get());
            renderBatteryHud(event.getGuiGraphics(), battery, yOffset);
            yOffset += 15;
        }

        // Display bullet count for classes that use GunItem
        if (playerClass.getMainWeapon() == GunItem.class) {
            List<BulletProjectile.BulletType> cylinder = mc.player.getData(ModAttachments.GUN_CYLINDER.get());
            renderBulletHud(event.getGuiGraphics(), cylinder, yOffset);
        }
    }

    private static void renderBatteryHud(GuiGraphics guiGraphics, int battery, int y) {
        Font font = Minecraft.getInstance().font;
        int x = 10;
        String text = "Battery: " + battery;

        guiGraphics.drawString(font, text, x, y, 0xFFFFFF);
    }

    private static void renderBulletHud(GuiGraphics guiGraphics, List<BulletProjectile.BulletType> cylinder, int y) {
        Font font = Minecraft.getInstance().font;
        int x = 10;

        StringBuilder bulletList = new StringBuilder();
        for (int i = 0; i < cylinder.size(); i++) {
            if (i > 0) {
                bulletList.append(", ");
            }
            bulletList.append(cylinder.get(i).getSerializedName());
        }

        String text = "Bullets: " + bulletList.toString();
        guiGraphics.drawString(font, text, x, y, 0xFFFFFF);
    }
}
