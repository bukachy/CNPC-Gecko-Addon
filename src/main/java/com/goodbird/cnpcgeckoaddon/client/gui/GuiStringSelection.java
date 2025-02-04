package com.goodbird.cnpcgeckoaddon.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.shared.client.gui.components.GuiButtonNop;
import noppes.npcs.shared.client.gui.components.GuiLabel;
import noppes.npcs.shared.client.gui.components.GuiStringSlotNop;

import java.util.List;
import java.util.function.Consumer;

public class GuiStringSelection extends Screen {
//    public GuiStringSlotNop<?> slot;
    public Consumer<String> action;
    public Screen parent;
    public String title;
    public List<String> options;
    private EditBox input;
    private Button saveButton;

    public GuiStringSelection(Screen parent, String title, List<String> options, Consumer<String> action) {
        super(Component.literal("asdadsdas"));
        this.parent = parent;
        this.action = action;
        this.title = title;
        this.options = options;
    }

    @Override
    public void init() {
        super.init();
//        addLabel(new GuiLabel(0, title, width / 2 - (this.font.width(title) / 2), 20, 0xffffff));
//        options.sort(String.CASE_INSENSITIVE_ORDER);
//        slot = new GuiStringSlotNop<>(options, this, false);
//        addRenderableWidget(this.slot);

        input = new net.minecraft.client.gui.components.EditBox(font, width / 2 - (this.font.width(title) / 2), 200, 200, 30, Component.literal("111111"));
        input.setMaxLength(100);
        this.addRenderableWidget(input);
//        this.addButton(new GuiButtonNop(this, 2, width / 2 - 100, height - 44, 98, 20, "gui.back"));

        saveButton = Button.builder(Component.literal("Save"), button -> onSaveButton()).pos(width / 2 - (this.font.width(title) / 2), 300).size(100, 20).build();
        this.addRenderableWidget(saveButton);
    }

    public void onSaveButton() {
        System.out.println("saved");
        action.accept(input.getValue());
        onClose();
    }

    @Override
    public void render(GuiGraphics matrixStack, int mouseX, int mouseY, float partialTicks) {
//        this.slot.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
//
//    @Override
//    public void doubleClicked() {
////        action.accept(slot.getSelectedString());
//        action.accept(input.getValue());
//        close();
//    }

//    @Override
//    public void buttonEvent(GuiButtonNop guibutton) {
//        int id = guibutton.id;
//        if (id == 2) {
//            close();
//        }
//        else {
//            super.buttonEvent(guibutton);
//        }
//    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for(var renderable : renderables) {
            if (renderable instanceof EditBox editBox) {
                if(editBox.isFocused()){
                    if (keyCode == Minecraft.getInstance().options.keyInventory.getKey().getValue()) {
                        return true;
                    }
                }
            }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}