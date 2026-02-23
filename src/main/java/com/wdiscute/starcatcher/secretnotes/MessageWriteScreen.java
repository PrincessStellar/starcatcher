package com.wdiscute.starcatcher.secretnotes;

import com.mojang.blaze3d.platform.InputConstants;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.network.SetMessagePayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class MessageWriteScreen extends Screen
{
    private static final ResourceLocation BACKGROUND_OVERWORLD = Starcatcher.rl("textures/gui/message/message_overworld.png");
    private static final ResourceLocation BACKGROUND_NETHER = Starcatcher.rl("textures/gui/message/message_nether.png");
    private static final ResourceLocation BACKGROUND_END = Starcatcher.rl("textures/gui/message/message_end.png");

    private final ResourceLocation background;

    private final LetterItem.Message message;

    private final List<String> text = new ArrayList<>();
    private final List<EditBox> boxes = new ArrayList<>();
    private EditBox name = null;

    public MessageWriteScreen(LetterItem.Message message)
    {
        super(Component.empty());

        this.message = message;

        if (Minecraft.getInstance().level.dimension().location().equals(Level.NETHER))
            background = BACKGROUND_NETHER;
        else if (Minecraft.getInstance().level.dimension().location().equals(Level.END)) background = BACKGROUND_END;
        else background = BACKGROUND_OVERWORLD;

        text.addAll(message.text());
    }

    int uiX;
    int uiY;

    @Override
    protected void init()
    {
        super.init();
        uiX = (width - 512) / 2;
        uiY = (height - 256) / 2;

        boolean firstRun = boxes.isEmpty();

        //text
        boxes.clear();
        for (int i = 0; i < 15; i++)
        {
            EditBox box = new EditBox(this.font, uiX + 136, uiY + 55 + i * 10, 500, 12, Component.empty());
            box.setCanLoseFocus(true);
            box.setTextColor(0x635040);
            box.setBordered(false);
            box.setMaxLength(40);
            box.setTextShadow(false);
            box.setEditable(true);
            addWidget(box);
            boxes.add(box);
        }

        //name
        name = new EditBox(this.font, uiX + 255, uiY + 208, 500, 12, Component.empty());
        name.setCanLoseFocus(true);
        name.setTextColor(0x635040);
        name.setBordered(false);
        name.setMaxLength(17);
        name.setTextShadow(false);
        name.setEditable(true);
        addWidget(name);

        //if first run assign textboxes to text from Message data component
        if (firstRun)
        {
            if (message.text().isEmpty())
            {
                name.setValue("-" + Minecraft.getInstance().player.getName().getString());
                boxes.get(0).setValue("[click to edit]");
                return;
            }

            name.setValue(message.senderDisplayName());
            for (int i = 0; i < 15; i++)
            {
                boxes.get(i).setValue(text.get(i));
            }
        }

    }

    @Override
    public void resize(Minecraft minecraft, int width, int height)
    {
        List<String> s = new ArrayList<>();
        for (int i = 0; i < 15; i++) s.add(this.name.getValue());
        this.init(minecraft, width, height);
        for (int i = 0; i < 15; i++) boxes.get(i).setValue(s.get(i));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderImage(guiGraphics, background);
        boxes.forEach(b -> b.render(guiGraphics, mouseX, mouseY, partialTick));
        if (name != null) name.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        InputConstants.Key key = InputConstants.getKey(keyCode, scanCode);
        if (this.minecraft.options.keyInventory.isActiveAndMatches(key) && boxes.stream().noneMatch(EditBox::canConsumeInput) && !name.canConsumeInput())
        {
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        boxes.forEach(o -> o.setFocused(false));
        name.setFocused(false);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void renderImage(GuiGraphics guiGraphics, ResourceLocation rl)
    {
        guiGraphics.blit(rl, uiX, uiY, 0, 0, 512, 256, 512, 256);
    }

    @Override
    public void onClose()
    {
        List<String> list = new ArrayList<>();
        boxes.forEach(b -> list.add(b.getValue()));
        PacketDistributor.sendToServer(new SetMessagePayload(list, name.getValue()));
        super.onClose();
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
