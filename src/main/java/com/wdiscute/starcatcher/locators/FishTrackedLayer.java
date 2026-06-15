package com.wdiscute.starcatcher.locators;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sun.jna.platform.win32.WinNT;
import com.teamtea.eclipticseasons.config.ClientConfig;
import com.wdiscute.starcatcher.SCColors;
import com.wdiscute.starcatcher.SCConfig;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.FishApi;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.guide.FishingGuideScreen;
import com.wdiscute.starcatcher.guide.SettingsScreen;
import com.wdiscute.starcatcher.registry.SCDataAttachments;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.fishrestrictions.AbstractFishRestriction;
import com.wdiscute.starcatcher.tournament.StandScreen;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FishTrackedLayer implements LayeredDraw.Layer
{

    private static final ResourceLocation BACKGROUND = Starcatcher.rl("textures/gui/fish_tracker/background.png");
    private static final ResourceLocation EMPTY = Starcatcher.rl("textures/gui/fish_tracker/empty.png");

    int uiX;
    int uiY;

    float offScreen = -150;

    Font font;

    int imageWidth = 155;
    int imageHeight = 135;

    float counterSinceLastRefresh = 999;

    Player player;
    ClientLevel level;

    ItemStack cachedItemMain = null;
    ItemStack cachedItemOff = null;
    int cachedChance = 0;
    int cachedTotalChance = 0;
    boolean cachedCaughtFish = false;

    FishProperties cachedFP = null;
    ResourceLocation cachedRL = null;

    private void recalculate()
    {
        LocalPlayer player = Minecraft.getInstance().player;
        cachedRL = player.getData(SCDataAttachments.TRACKED_FISH);
        cachedCaughtFish = player.getData(SCDataAttachments.FISHING_GUIDE).fishesCaught.containsKey(cachedRL);

        cachedItemMain = player.getMainHandItem();
        cachedItemOff = player.getOffhandItem();

        //chances
        cachedChance = 0;
        cachedTotalChance = 0;
        List<FishProperties> fishes = FishApi.getFishes(level);

        for (FishProperties fish : fishes)
        {
            ResourceLocation key = level.registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY_KEY).getKey(fish);

            int chance = fish.calculateChance(player, level, cachedItemMain.is(SCTags.RODS) ? cachedItemMain : cachedItemOff, AbstractFishRestriction.Context.GUIDE_FISHES_HOVER);

            if (key != null && key.equals(cachedRL))
            {
                cachedFP = fish;
                if (chance > 0)
                    cachedChance = chance;
                cachedTotalChance += chance;
            }
            else if (chance > 0)
                cachedTotalChance += chance;
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker)
    {
        font = Minecraft.getInstance().font;
        uiX = Minecraft.getInstance().getWindow().getGuiScaledWidth() - imageWidth;
        uiY = Minecraft.getInstance().getWindow().getGuiScaledHeight() - imageHeight - 80;

        if (Minecraft.getInstance().level == null) return;
        else level = Minecraft.getInstance().level;
        if (Minecraft.getInstance().player == null) return;
        else player = Minecraft.getInstance().player;

        boolean shouldShow = player.getMainHandItem().is(SCTags.HAS_TRACKER_LAYER) || player.getOffhandItem().is(SCTags.HAS_TRACKER_LAYER) || Minecraft.getInstance().screen instanceof SettingsScreen;

        ResourceLocation trackedRL = Minecraft.getInstance().player.getData(SCDataAttachments.TRACKED_FISH);
        shouldShow = shouldShow && !trackedRL.equals(Starcatcher.MISSINGNO);

        //smoothly moves ui in and out of screen
        if (!shouldShow)
            if (offScreen > -150 + SCConfig.RADAR_X_OFFSET.get())
                offScreen -= 15 * deltaTracker.getGameTimeDeltaTicks();
            else
            {
                offScreen = (float) (-150 + SCConfig.RADAR_X_OFFSET.get());
                return;
            }
        else if (offScreen < 0)
            offScreen += 15 * deltaTracker.getGameTimeDeltaTicks();
        else
            offScreen = 0;


        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(((float) SCConfig.RADAR_SCALE.getAsDouble()), ((float) SCConfig.RADAR_SCALE.getAsDouble()), 1);
        guiGraphics.pose().translate(SCConfig.RADAR_X_OFFSET.get(), SCConfig.RADAR_Y_OFFSET.get(), 0);

        guiGraphics.pose().translate(-offScreen, 0, 0);

        renderImage(guiGraphics, BACKGROUND);

        //recalculate every 100 ticks or item in hand changed (for rod modifiers)
        counterSinceLastRefresh += 1 * deltaTracker.getGameTimeDeltaTicks();
        if (counterSinceLastRefresh > 100 ||
            cachedItemMain != Minecraft.getInstance().player.getMainHandItem() ||
            cachedItemOff != Minecraft.getInstance().player.getOffhandItem()
        ) recalculate();

        if (cachedFP == null || cachedRL == null)
        {
            guiGraphics.pose().popPose();
            return;
        }


        //render fish + name
        if (cachedCaughtFish || !SCConfig.HIDE_ENTRIES_UNTIL_FOUND.get())
        {
            StandScreen.renderCenteredScrollingString(guiGraphics, font, cachedFP.getDisplayName(),
                    uiX + 80, uiX + 77, uiX + 151, uiY + 27, SCColors.GUIDE_TEXT_DARK);
            renderItem(guiGraphics, level, cachedFP.catchInfo().fish().toStack(), uiX + 42, uiY + 15, 2);
        }
        else
        {
            guiGraphics.blit(EMPTY,
                    uiX + 34, uiY + 7,
                    34, 34,
                    0, 0,
                    34, 34,
                    34, 34);
        }




        //render weight
        double percentage = (double) cachedChance / cachedTotalChance * 100;
        renderCenteredString(guiGraphics, font, Component.literal(new DecimalFormat("0.#").format(percentage) + "%"),
                uiX + 34, uiY + 65, SCColors.GUIDE_TEXT_DARK);
        renderCenteredString(guiGraphics, font, Component.literal(cachedChance + "/" + cachedTotalChance),
                uiX + 34, uiY + 75, SCColors.GUIDE_TEXT_DARK);


        //render restrictions
        List<Component> list = new ArrayList<>();
        cachedFP.restrictions().stream().filter(AbstractFishRestriction::isEnabled).forEach(o -> list.addAll(o.getIndexHover(level, cachedFP, player, AbstractFishRestriction.Context.GUIDE_FISHES_HOVER)));
        for (int i = 0; i < list.size(); i++)
            guiGraphics.drawString(font, list.get(i), uiX + 70, uiY + 49 + i * 10, SCColors.GUIDE_TEXT_DARK, false);


        guiGraphics.pose().popPose();

    }

    private void renderItem(GuiGraphics guiGraphics, @Nullable Level level, ItemStack stack, int x, int y, float scale)
    {
        PoseStack pose = guiGraphics.pose();
        if (!stack.isEmpty())
        {
            Minecraft minecraft = Minecraft.getInstance();
            BakedModel bakedmodel = minecraft.getItemRenderer().getModel(stack, level, null, 0);
            pose.pushPose();
            pose.translate((float) (x + 8), (float) (y + 8), (float) (150));

            try
            {
                pose.scale(16.0F * scale, -16.0F * scale, 16.0F * scale);
                boolean flag = !bakedmodel.usesBlockLight();
                if (flag)
                {
                    Lighting.setupForFlatItems();
                }

                minecraft
                        .getItemRenderer()
                        .render(stack, ItemDisplayContext.GUI, false, pose, guiGraphics.bufferSource(), 15728880, OverlayTexture.NO_OVERLAY, bakedmodel);
                RenderSystem.disableDepthTest();
                guiGraphics.bufferSource().endBatch();
                RenderSystem.enableDepthTest();
                if (flag)
                {
                    Lighting.setupFor3DItems();
                }
            } catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.forThrowable(throwable, "Rendering item");
                CrashReportCategory crashreportcategory = crashreport.addCategory("Item being rendered");
                crashreportcategory.setDetail("Item Type", () -> String.valueOf(stack.getItem()));
                crashreportcategory.setDetail("Item Components", () -> String.valueOf(stack.getComponents()));
                crashreportcategory.setDetail("Item Foil", () -> String.valueOf(stack.hasFoil()));
                throw new ReportedException(crashreport);
            }

            pose.popPose();
        }
    }

    public void renderCenteredString(GuiGraphics guiGraphics, Font font, Component text, int x, int y, int color)
    {
        FormattedCharSequence formattedcharsequence = text.getVisualOrderText();
        guiGraphics.drawString(font, formattedcharsequence, x - font.width(formattedcharsequence) / 2, y, color, false);
    }

    private void renderImage(GuiGraphics guiGraphics, ResourceLocation rl)
    {
        guiGraphics.blit(rl, uiX, uiY, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
    }

    private void drawComp(GuiGraphics guiGraphics, Component comp, int xOffset, int yOffset)
    {
        guiGraphics.drawString(font, comp, uiX + xOffset, uiY + yOffset, 0, false);
    }
}
