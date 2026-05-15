package com.wdiscute.starcatcher.tournament;

import com.wdiscute.starcatcher.SCColors;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.guide.FishingGuideScreen;
import com.wdiscute.starcatcher.io.network.tournament.SBStandTournamentNameChangePayload;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.*;

public class StandScreen extends AbstractContainerScreen<StandMenu>
{
    public Tournament tournament;

    private EditBox nameEditBox;
    private boolean nameWasFocused;

    private static final ResourceLocation BACKGROUND = Starcatcher.rl("textures/gui/tournament/stand/background.png");

    private static final ResourceLocation LEFT_ARROW = Starcatcher.rl("textures/gui/tournament/stand/left_arrow.png");
    private static final ResourceLocation LEFT_ARROW_HIGHLIGHT = Starcatcher.rl("textures/gui/tournament/stand/left_arrow_highlight.png");
    private static final ResourceLocation RIGHT_ARROW = Starcatcher.rl("textures/gui/tournament/stand/left_right.png");
    private static final ResourceLocation RIGHT_ARROW_HIGHLIGHT = Starcatcher.rl("textures/gui/tournament/stand/left_right_highlight.png");

    private static final ResourceLocation TINY_ARROW_RIGHT = Starcatcher.rl("textures/gui/tournament/stand/tiny_arrow_right.png");
    private static final ResourceLocation TINY_ARROW_LEFT = Starcatcher.rl("textures/gui/tournament/stand/tiny_arrow_left.png");

    int uiX;
    int uiY;
    boolean isOwner = false;

    @Override
    protected void init()
    {
        super.init();
        tournament = menu.sbe.tournament;
        uiX = (width - imageWidth) / 2;
        uiY = (height - imageHeight) / 2;
        subInit();
    }

    protected void subInit()
    {
        nameEditBox = new EditBox(this.font, uiX + 53, uiY + 36, 210, 12, Component.translatable("container.repair"));
        nameEditBox.setCanLoseFocus(true);
        nameEditBox.setTextColor(0x635040);
        nameEditBox.setBordered(false);
        nameEditBox.setMaxLength(20);
        nameEditBox.setValue("");
        nameEditBox.setTextShadow(false);
        nameEditBox.setEditable(false);
        addWidget(this.nameEditBox);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1)
    {
        this.renderBlurredBackground(i);
    }

    private void onFocusNameEditBox()
    {
        nameEditBox.setValue(tournament.name);
        tournament.name = "";
    }

    private void onUnfocusNameEditBox()
    {
        //send packet
        PacketDistributor.sendToServer(new SBStandTournamentNameChangePayload(tournament.tournamentUUID, nameEditBox.getValue()));
        tournament.name = nameEditBox.getValue();
        nameEditBox.setValue("");
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        //render background
        renderImage(guiGraphics, BACKGROUND);

        //update every frame for when server sends a new tournament
        if (tournament != menu.sbe.tournament) onTournamentReceived();
        if (tournament == null) return;

        double x = mouseX - uiX;
        double y = mouseY - uiY;

        //handle Name editbox focusing
        if (nameWasFocused != nameEditBox.isFocused())
        {
            if (nameEditBox.isFocused())
                onFocusNameEditBox();
            else
                onUnfocusNameEditBox();
        }

        nameWasFocused = nameEditBox.isFocused();

        //render tournament name
        guiGraphics.drawString(this.font, tournament.name, uiX + 53, uiY + 36, 0x635040, false);
        nameEditBox.render(guiGraphics, mouseX, mouseY, partialTick);


        //organizer
        guiGraphics.drawString(this.font, tournament.ownerName, uiX + 55, uiY + 56, 0x635040, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.organizer"), uiX + 55, uiY + 68, 0x9c897c, false);

        //status
        guiGraphics.drawString(this.font, Component.translatable(tournament.status.getSerializedName()), uiX + 130, uiY + 56, 0x635040, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.status"), uiX + 130, uiY + 68, 0x9c897c, false);

        //duration
        guiGraphics.drawString(this.font, U.calculateRealLifeTimeFromTicks(tournament.durationInTicks), uiX + 55, uiY + 88, 0x635040, false);
        if (isOwner)
        {
            renderImage(guiGraphics, TINY_ARROW_LEFT, 54, 101, 6, 5);
            renderImage(guiGraphics, TINY_ARROW_RIGHT, 109, 101, 6, 5);
            renderCenteredString(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.duration"),
                    uiX + 85, uiY + 100, SCColors.GUIDE_TEXT);
        }
        else
        {
            guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.duration"),
                    uiX + 55, uiY + 100, 0x9c897c, false);
        }

        //duration hover
        if (x > 52 && x < 116 && y > 85 && y < 98)
        {
            List<Component> durationTooltip = new ArrayList<>();

            durationTooltip.add(Component.literal(tournament.durationInTicks + " ticks"));

            MutableComponent durationComp = Component.literal(String.format("%.2f ", (float) tournament.durationInTicks / 24000));
            if (tournament.durationInTicks % 24000 == 0)
                durationComp = Component.literal(tournament.durationInTicks / 24000 + " ");
            durationTooltip.add(durationComp.append(Component.translatable("gui.starcatcher.tournament.duration.days")));

            guiGraphics.renderTooltip(this.font, durationTooltip, Optional.empty(), mouseX, mouseY);
        }

        //scoring rules
        guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.scoring"),
                uiX + 56, uiY + 114, SCColors.GUIDE_TEXT, false);

        //trash
        renderStringWithLimitedSpace(guiGraphics, this.font, Component.translatable("gui.guide.rarity.trash"),
                uiX + 56, uiX + 104, uiY + 126, mouseX, mouseY);

        guiGraphics.drawString(this.font, String.format("%.1f", tournament.scoreSettings.trashScore),
                uiX + 107, uiY + 126, SCColors.GUIDE_TEXT_DARK, false);

        //common
        renderStringWithLimitedSpace(guiGraphics, this.font, Component.translatable("gui.guide.rarity.common"),
                uiX + 124, uiX + 172, uiY + 126, mouseX, mouseY);

        guiGraphics.drawString(this.font, String.format("%.1f", tournament.scoreSettings.commonScore),
                uiX + 174, uiY + 126, SCColors.GUIDE_TEXT_DARK, false);

        //uncommon
        renderStringWithLimitedSpace(guiGraphics, this.font, Component.translatable("gui.guide.rarity.uncommon"),
                uiX + 56, uiX + 104, uiY + 138, mouseX, mouseY);

        guiGraphics.drawString(this.font, String.format("%.1f", tournament.scoreSettings.uncommonScore),
                uiX + 107, uiY + 138, SCColors.GUIDE_TEXT_DARK, false);

        //rare
        renderStringWithLimitedSpace(guiGraphics, this.font, Component.translatable("gui.guide.rarity.rare"),
                uiX + 124, uiX + 172, uiY + 138, mouseX, mouseY);

        guiGraphics.drawString(this.font, String.format("%.1f", tournament.scoreSettings.rareScore),
                uiX + 174, uiY + 138, SCColors.GUIDE_TEXT_DARK, false);

        //epic
        renderStringWithLimitedSpace(guiGraphics, this.font, Component.translatable("gui.guide.rarity.epic"),
                uiX + 56, uiX + 104, uiY + 150, mouseX, mouseY);

        guiGraphics.drawString(this.font, String.format("%.1f", tournament.scoreSettings.epicScore),
                uiX + 107, uiY + 150, SCColors.GUIDE_TEXT_DARK, false);

        //legendary
        renderStringWithLimitedSpace(guiGraphics, this.font, Component.translatable("gui.guide.rarity.legendary"),
                uiX + 124, uiX + 172, uiY + 150, mouseX, mouseY);

        guiGraphics.drawString(this.font, String.format("%.1f", tournament.scoreSettings.legendaryScore),
                uiX + 174, uiY + 150, SCColors.GUIDE_TEXT_DARK, false);

        //percentile
        renderStringWithLimitedSpace(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.scoring.percentile"),
                uiX + 56, uiX + 172, uiY + 162, mouseX, mouseY);

        guiGraphics.drawString(this.font, String.format("%.1f", tournament.scoreSettings.percentileMultiplier),
                uiX + 174, uiY + 162, SCColors.GUIDE_TEXT_DARK, false);

        //perfect catch
        renderStringWithLimitedSpace(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.scoring.perfect"),
                uiX + 56, uiX + 172, uiY + 174, mouseX, mouseY);

        guiGraphics.drawString(this.font, String.format("%.1f", tournament.scoreSettings.perfectCatchMultiplier),
                uiX + 174, uiY + 174, SCColors.GUIDE_TEXT_DARK, false);

        //sign up button
        boolean isHovering = x > 79 && x < 163 && y > 192 && y < 208;
        if (isOwner)
        {
            if (tournament.status.equals(Tournament.Status.PREPARING))
            {
                MutableComponent comp = Component.translatable("gui.starcatcher.tournament.button.start");
                if (isHovering)
                {
                    renderScrollingString(guiGraphics, this.font, comp, uiX + 120, uiX + 83, uiX + 160, uiY + 196, SCColors.WHITE);
                    renderScrollingString(guiGraphics, this.font, comp, uiX + 120, uiX + 83, uiX + 160, uiY + 198, SCColors.WHITE);
                    renderScrollingString(guiGraphics, this.font, comp, uiX + 119, uiX + 82, uiX + 159, uiY + 197, SCColors.WHITE);
                    renderScrollingString(guiGraphics, this.font, comp, uiX + 121, uiX + 84, uiX + 161, uiY + 197, SCColors.WHITE);
                    renderScrollingString(guiGraphics, this.font, comp, uiX + 120, uiX + 83, uiX + 160, uiY + 197, SCColors.GUIDE_TEXT_DARK);

                    guiGraphics.renderOutline(uiX + 79, uiY + 192, 85, 17, SCColors.WHITE);
                }
                else
                    renderScrollingString(guiGraphics, this.font, comp,
                            uiX + 120, uiX + 83, uiX + 160, uiY + 197, SCColors.GUIDE_TEXT_DARK
                    );
            }

            if (tournament.status.equals(Tournament.Status.ACTIVE))
            {
                if (isHovering)
                {
                    renderCenteredString(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.button.cancel"), uiX + 121, uiY + 197, SCColors.WHITE);
                    renderCenteredString(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.button.cancel"), uiX + 119, uiY + 197, SCColors.WHITE);
                    renderCenteredString(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.button.cancel"), uiX + 120, uiY + 196, SCColors.WHITE);
                    renderCenteredString(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.button.cancel"), uiX + 120, uiY + 198, SCColors.WHITE);
                    guiGraphics.renderOutline(uiX + 79, uiY + 192, 85, 17, SCColors.WHITE);
                }

                renderCenteredString(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.button.cancel"),
                        uiX + 120, uiY + 197, SCColors.GUIDE_TEXT_DARK);
            }

        }
        else
        {
            if (x > 79 && x < 163 && y > 192 && y < 208)
            {
                renderCenteredString(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.button.sign_up"), uiX + 121, uiY + 197, SCColors.WHITE);
                renderCenteredString(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.button.sign_up"), uiX + 119, uiY + 197, SCColors.WHITE);
                renderCenteredString(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.button.sign_up"), uiX + 120, uiY + 196, SCColors.WHITE);
                renderCenteredString(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.button.sign_up"), uiX + 120, uiY + 198, SCColors.WHITE);
                guiGraphics.renderOutline(uiX + 79, uiY + 192, 85, 17, SCColors.WHITE);
            }

            renderCenteredString(guiGraphics, this.font, Component.translatable("gui.starcatcher.tournament.button.sign_up"),
                    uiX + 120, uiY + 197, SCColors.GUIDE_TEXT_DARK);
        }


        //player list
        //signed up
        guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.signed_up"),
                uiX + 220, uiY + 45, SCColors.GUIDE_TEXT, false);

        System.out.println(tournament.playerScores.size());
        for (int i = 0; i < tournament.playerScores.size(); i++)
            if (i < 10)
                guiGraphics.drawString(this.font, tournament.playerScores.get(i).name, uiX + 220, uiY + 57, 0x635040, false);

        //previous tournaments
        guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.previous_tournaments"),
                uiX + 220, uiY + 175, SCColors.GUIDE_TEXT, false);


    }

    public void onTournamentReceived()
    {
        tournament = menu.sbe.tournament;
        isOwner = this.tournament.owner.equals(Minecraft.getInstance().player.getUUID());
        nameEditBox.setEditable(isOwner);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY)
    {
        double x = mouseX - uiX;
        double y = mouseY - uiY;

        System.out.println(x);
        System.out.println(y);

        if (isOwner)
        {
            //duration decrease, shift does x10
            if (x > 53 && x < 117 && y > 88 && y < 107 && scrollY < -0.5f)
            {
                if (!hasShiftDown())
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 101);
                else
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 102);
                return true;
            }

            //duration increase, shift does x10
            if (x > 53 && x < 117 && y > 88 && y < 107 && scrollY > 0.5f)
            {
                if (!hasShiftDown())
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 103);
                else
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 104);
                return true;
            }

            //trash
            if (x > 54 && x < 120 && y > 124 && y < 134)
            {
                if (scrollY < 0.5f)
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 200);
                else
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 201);
                return true;
            }

            //common
            if (x > 122 && x < 187 && y > 124 && y < 134)
            {
                if (scrollY < 0.5f)
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 210);
                else
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 211);
                return true;
            }

            //uncommon
            if (x > 54 && x < 120 && y > 136 && y < 146)
            {
                if (scrollY < 0.5f)
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 220);
                else
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 221);
                return true;
            }

            //rare
            if (x > 122 && x < 187 && y > 136 && y < 146)
            {
                if (scrollY < 0.5f)
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 230);
                else
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 231);
                return true;
            }

            //epic
            if (x > 54 && x < 120 && y > 148 && y < 158)
            {
                if (scrollY < 0.5f)
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 240);
                else
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 241);
                return true;
            }

            //legendary
            if (x > 122 && x < 187 && y > 148 && y < 158)
            {
                if (scrollY < 0.5f)
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 250);
                else
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 251);
                return true;
            }

            //percentile
            if (x > 54 && x < 187 && y > 160 && y < 170)
            {
                if (scrollY < 0.5f)
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 260);
                else
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 261);
                return true;
            }

            //perfect catch
            if (x > 54 && x < 187 && y > 172 && y < 182)
            {
                if (scrollY < 0.5f)
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 270);
                else
                    minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 271);
                return true;
            }

        }

        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        double x = mouseX - uiX;
        double y = mouseY - uiY;
        assert minecraft != null;
        assert minecraft.gameMode != null;

        //System.out.println("clicked relative x: " + x);
        //System.out.println("clicked relative y: " + y);

        if (x > 349 && x < 396 && y > 48 && y < 95)
        {
            minecraft.player.playSound(SoundEvents.NOTE_BLOCK_CHIME.value(), 0.1f, 1.2f);
            minecraft.player.playSound(SoundEvents.GLASS_STEP, 0.4f, 1.2f);
            return true;
        }

        //System.out.println(x);
        //System.out.println(y);

        //gold button click
        if (x > 81 && x < 161 && y > 193 && y < 205)
        {
            minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 67);
            return true;
        }

        //duration decrease, shift does x10
        if (x > 50 && x < 60 && y > 98 && y < 107 && isOwner)
        {
            if (!hasShiftDown())
                minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 101);
            else
                minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 102);
            return true;
        }

        //duration increase, shift does x10
        if (x > 109 && x < 119 && y > 99 && y < 109 && isOwner)
        {
            if (!hasShiftDown())
                minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 103);
            else
                minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 104);
            return true;
        }

        nameEditBox.setFocused(false);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {
    }

    private void renderImage(GuiGraphics guiGraphics, ResourceLocation rl)
    {
        guiGraphics.blit(rl, uiX, uiY, 0, 0, 420, 260, 420, 260);
    }

    private void renderImage(GuiGraphics guiGraphics, ResourceLocation rl, int xOffset, int yOffset, int width, int height)
    {
        guiGraphics.blit(rl, uiX + xOffset, uiY + yOffset, 0, 0, width, height, width, height);
    }

    public void renderCenteredString(GuiGraphics guiGraphics, Font font, Component text, int x, int y, int color)
    {
        FormattedCharSequence formattedcharsequence = text.getVisualOrderText();
        guiGraphics.drawString(font, formattedcharsequence, x - font.width(formattedcharsequence) / 2, y, color, false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        if (keyCode == 256)
        {
            this.minecraft.player.closeContainer();
        }

        boolean editbox = this.nameEditBox.keyPressed(keyCode, scanCode, modifiers) || this.nameEditBox.canConsumeInput();
        return editbox || super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void onClose()
    {
        super.onClose();
        if (!nameEditBox.getValue().isEmpty())
            PacketDistributor.sendToServer(new SBStandTournamentNameChangePayload(tournament.tournamentUUID, nameEditBox.getValue()));
    }

    public StandScreen(StandMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title);
        tournament = menu.sbe.tournament;
        imageWidth = 420;
        imageHeight = 260;
    }

    public static void renderScrollingString(GuiGraphics guiGraphics, Font font, Component text, int centerX, int minX, int maxX, int y, int color)
    {
        int i = font.width(text);
        int k = maxX - minX;
        if (i > k)
        {
            int l = i - k;
            double d0 = (double) Util.getMillis() / (double) 300.0F;
            double d1 = Math.max((double) l * (double) 0.5F, 3.0F);
            double d2 = Math.sin((Math.PI / 2D) * Math.cos((Math.PI * 2D) * d0 / d1)) / (double) 2.0F + (double) 0.5F;
            double d3 = Mth.lerp(d2, 0.0F, l);
            guiGraphics.enableScissor(minX, y - 10, maxX, y + 10);
            int x = minX - (int) d3;
            guiGraphics.drawString(font, text, x, y, color, false);
            guiGraphics.disableScissor();
        }
        else
        {
            int i1 = Mth.clamp(centerX, minX + i / 2, maxX - i / 2);
            guiGraphics.drawString(font, text.getVisualOrderText(), i1 - font.width(text.getVisualOrderText()) / 2, y, color, false);
        }
    }

    public static void renderStringWithLimitedSpace(GuiGraphics guiGraphics, Font font, Component comp, int minX, int maxX, int y, double mouseX, double mouseY)
    {
        boolean hovering = mouseX > minX && mouseX < maxX && mouseY > y - 3 && mouseY < y + 9;
        int width = font.width(comp);
        int sizeAvailable = maxX - minX;
        if (width > sizeAvailable)
        {
            int l = width - sizeAvailable;
            double d0 = (double) Util.getMillis() / (double) 300.0F;
            double d1 = Math.max((double) l * (double) 0.5F, 3.0F);
            double d2 = Math.sin((Math.PI / 2D) * Math.cos((Math.PI * 2D) * d0 / d1)) / (double) 2.0F + (double) 0.5F;
            double d3 = Mth.lerp(d2, 0.0F, l);
            guiGraphics.enableScissor(minX, y - 20, maxX, y + 20);
            int x = minX - (int) d3;
            if (hovering)
                guiGraphics.drawString(font, comp, x, y, SCColors.GUIDE_TEXT_DARK, false);
            else
                guiGraphics.drawString(font, comp, minX, y, SCColors.GUIDE_TEXT_DARK, false);
            guiGraphics.disableScissor();
        }
        else
        {
            guiGraphics.drawString(font, comp, minX, y, SCColors.GUIDE_TEXT_DARK, false);
        }
    }
}
