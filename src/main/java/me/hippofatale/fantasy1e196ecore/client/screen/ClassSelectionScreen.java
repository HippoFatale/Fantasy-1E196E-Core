package me.hippofatale.fantasy1e196ecore.client.screen;

import me.hippofatale.fantasy1e196ecore.Fantasy1E196ECore;
import me.hippofatale.fantasy1e196ecore.network.ClassChangeC2SPacket;
import me.hippofatale.fantasy1e196ecore.util.PlayerClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

public class ClassSelectionScreen extends Screen {
    private static final Component TITLE = Component.translatable("screen.fantasy1e196ecore.class_selection");
    
    public ClassSelectionScreen() {
        super(TITLE);
    }
    
    @Override
    protected void init() {
        int buttonWidth = 200;
        int buttonHeight = 20;
        int startY = 60;
        int spacing = 25;
        
        int x = (this.width - buttonWidth) / 2;
        int y = startY;
        
        for (PlayerClass playerClass : PlayerClass.values()) {
            final PlayerClass selectedClass = playerClass;
            this.addRenderableWidget(Button.builder(
                Component.translatable(playerClass.getTranslationKey()),
                button -> {
                    PacketDistributor.sendToServer(new ClassChangeC2SPacket(selectedClass));
                    Minecraft.getInstance().setScreen(null);
                }
            ).bounds(x, y, buttonWidth, buttonHeight).build());
            
            y += spacing;
        }
        
        // Add close button
        this.addRenderableWidget(Button.builder(
            Component.translatable("gui.close"),
            button -> this.onClose()
        ).bounds(x, y + 10, buttonWidth, buttonHeight).build());
    }
    
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 30, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
