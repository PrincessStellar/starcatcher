package com.wdiscute.starcatcher.guide;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.wdiscute.libtooltips.Tooltips;
import com.wdiscute.starcatcher.*;
import com.wdiscute.starcatcher.io.FishCaughtCounter;
import com.wdiscute.starcatcher.io.attachments.FishingGuideAttachment;
import com.wdiscute.starcatcher.registry.SCEntities;
import com.wdiscute.starcatcher.blocks.SCBlocks;
import com.wdiscute.starcatcher.io.network.FPsSeenPayload;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.fishrestrictions.AbstractFishRestriction;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.TrophyProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FastColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.PacketDistributor;

import java.awt.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class FishingGuideScreen extends Screen
{
    private static final ResourceLocation BACKGROUND_COVER = Starcatcher.rl("textures/gui/guide/background_cover.png");
    private static final ResourceLocation BACKGROUND_LAST_PAGE = Starcatcher.rl("textures/gui/guide/background_last_page.png");
    private static final ResourceLocation BACKGROUND_INDEX_FIRST = Starcatcher.rl("textures/gui/guide/background_index_first.png");
    private static final ResourceLocation BACKGROUND_INDEX_SECOND = Starcatcher.rl("textures/gui/guide/background_index_second.png");
    private static final ResourceLocation BACKGROUND_ENTRY = Starcatcher.rl("textures/gui/guide/background_entry.png");
    private static final ResourceLocation BACKGROUND_BASICS = Starcatcher.rl("textures/gui/guide/background_basics.png");

    private static final ResourceLocation HIGHLIGHT_LEFT = Starcatcher.rl("textures/gui/guide/highlight_page_left.png");
    private static final ResourceLocation HIGHLIGHT_RIGHT = Starcatcher.rl("textures/gui/guide/highlight_page_right.png");

    private static final ResourceLocation FISHES_IN_AREA_TOP_RIGHT_DECORATION = Starcatcher.rl("textures/gui/guide/fishes_in_area_top_right_decoration.png");
    private static final ResourceLocation FISHES_IN_AREA_BOTTOM_LEFT_DECORATION = Starcatcher.rl("textures/gui/guide/fishes_in_area_bottom_left_decoration.png");
    private static final ResourceLocation FISHES_IN_AREA_BOTTOM_DECORATION = Starcatcher.rl("textures/gui/guide/fishes_in_area_bottom_decoration.png");
    private static final ResourceLocation FISHES_IN_AREA_FISH_DECORATION = Starcatcher.rl("textures/gui/guide/fishes_in_area_fish_decoration.png");

    private static final ResourceLocation HELP_PAGE_BASICS = Starcatcher.rl("textures/gui/guide/help_basics.png");
    private static final ResourceLocation HELP_PAGE_SWEETSPOTS = Starcatcher.rl("textures/gui/guide/help_sweetspots.png");
    private static final ResourceLocation HELP_PAGE_TREASURE = Starcatcher.rl("textures/gui/guide/help_treasure.png");
    private static final ResourceLocation HELP_PAGE_LAVA_FISHING = Starcatcher.rl("textures/gui/guide/help_lava_fishing.png");
    private static final ResourceLocation HELP_PAGE_HOOKS_BOBBERS_BAITS = Starcatcher.rl("textures/gui/guide/help_hooks_bobbers_baits.png");
    private static final ResourceLocation HELP_PAGE_GADGETS_COSMETICS = Starcatcher.rl("textures/gui/guide/help_gadgets_cosmetics.png");
    private static final ResourceLocation HELP_PAGE_TEMPLATES_EQUIPMENT = Starcatcher.rl("textures/gui/guide/help_templates_equipment.png");
    private static final ResourceLocation HELP_PAGE_TROPHIES = Starcatcher.rl("textures/gui/guide/help_trophies.png");
    private static final ResourceLocation HELP_PAGE_TOURNAMENTS = Starcatcher.rl("textures/gui/guide/help_tournaments.png");

    private static final ResourceLocation ARROW_PREVIOUS = Starcatcher.rl("textures/gui/guide/arrow_previous.png");
    private static final ResourceLocation ARROW_PREVIOUS_PRESSED = Starcatcher.rl("textures/gui/guide/arrow_previous_pressed.png");
    private static final ResourceLocation ARROW_PREVIOUS_HIGHLIGHT = Starcatcher.rl("textures/gui/guide/arrow_previous_highlight.png");

    private static final ResourceLocation ARROW_LEFT = Starcatcher.rl("textures/gui/guide/arrow_left.png");
    private static final ResourceLocation ARROW_RIGHT = Starcatcher.rl("textures/gui/guide/arrow_right.png");

    private static final ResourceLocation ARROW_NEXT = Starcatcher.rl("textures/gui/guide/arrow_next.png");
    private static final ResourceLocation ARROW_NEXT_PRESSED = Starcatcher.rl("textures/gui/guide/arrow_next_pressed.png");
    private static final ResourceLocation ARROW_NEXT_HIGHLIGHT = Starcatcher.rl("textures/gui/guide/arrow_next_highlight.png");

    private static final ResourceLocation ARROW_INDEX = Starcatcher.rl("textures/gui/guide/arrow_index.png");
    private static final ResourceLocation ARROW_INDEX_PRESSED = Starcatcher.rl("textures/gui/guide/arrow_index_pressed.png");
    private static final ResourceLocation ARROW_INDEX_HIGHLIGHT = Starcatcher.rl("textures/gui/guide/arrow_index_highlight.png");

    private static final ResourceLocation NEW_FISH = Starcatcher.rl("textures/gui/guide/new_fish.png");
    private static final ResourceLocation STAR = Starcatcher.rl("textures/gui/guide/star.png");
    private static final ResourceLocation GLOW = Starcatcher.rl("textures/gui/guide/glow.png");

    private static final ResourceLocation BUCKET = Starcatcher.rl("textures/gui/guide/bucketable.png");
    private static final ResourceLocation ENTITY = Starcatcher.rl("textures/gui/guide/entity.png");
    private static final ResourceLocation ALWAYS_ENTITY = Starcatcher.rl("textures/gui/guide/always_entity.png");

    private static final int MAX_HELP_PAGES = 8;


    private final List<ItemStack> hooksAndBobbers = new ArrayList<>();
    private final List<ItemStack> baits = new ArrayList<>();
    private final List<ItemStack> gadgets = new ArrayList<>();
    private final List<ItemStack> templates = new ArrayList<>();
    private final List<ItemStack> equipments = new ArrayList<>();


    private final ItemStack basicsIndexIcon;
    private final ItemStack upgradeIndexIcon;
    private final ItemStack addonsIndexIcon;
    private final ItemStack cosmeticsIndexIcon;
    private final ItemStack tournamentIndexIcon;
    private final ItemStack trophiesIndexIcon;
    private final ItemStack settingsIndexIcon;

    private final ItemStack sweetspotsIcon;
    private final ItemStack treasureIcon;
    private final ItemStack cosmeticsIcon;
    private final ItemStack equipmentIcon;
    private final ItemStack gadgetsIcon;

    private final ItemStack hookIcon;
    private final ItemStack baitIcon;
    private final ItemStack secretsIcon;

    private final ItemStack templateIcon;


    private List<Pair<ItemStack, String>> indexEntries;

    int uiX;
    int uiY;

    int imageWidth;
    int imageHeight;

    boolean clicked;

    int leftPageScroll = 0;
    int rightPageScroll = 0;

    float highlightLeftAlpha = 0;
    float highlightRightAlpha = 0;

    boolean arrowPreviousPressed;
    boolean arrowNextPressed;
    boolean arrowIndexPressed;

    boolean hasNextPage = false;
    int lastIndexPage = 0;

    int menu = 0;
    int page = 0;

    ClientLevel level;
    LocalPlayer player;

    List<ResourceLocation> fpsSeen = new ArrayList<>();
    List<FishProperties> entries = new ArrayList<>(999);
    List<TrophyProperties> trophiesTps = new ArrayList<>();
    List<TrophyProperties> secretsTps = new ArrayList<>();
    List<FishProperties> fishInArea = new ArrayList<>();
    Map<ResourceLocation, FishCaughtCounter> fishCaughtCounterMap = new HashMap<>();

    TrophyProperties.RarityProgress all = TrophyProperties.RarityProgress.DEFAULT;
    private final Map<FishProperties.Rarity, TrophyProperties.RarityProgress> progressMap = new EnumMap<>(Map.of(
            FishProperties.Rarity.COMMON, TrophyProperties.RarityProgress.DEFAULT,
            FishProperties.Rarity.UNCOMMON, TrophyProperties.RarityProgress.DEFAULT,
            FishProperties.Rarity.RARE, TrophyProperties.RarityProgress.DEFAULT,
            FishProperties.Rarity.EPIC, TrophyProperties.RarityProgress.DEFAULT,
            FishProperties.Rarity.LEGENDARY, TrophyProperties.RarityProgress.DEFAULT
    ));

    @Override
    protected void init()
    {
        super.init();

        entries = new ArrayList<>();
        trophiesTps = new ArrayList<>();
        secretsTps = new ArrayList<>();

        imageWidth = 420;
        imageHeight = 260;

        uiX = (width - imageWidth) / 2;
        uiY = (height - imageHeight) / 2;

        level = Minecraft.getInstance().level;
        player = Minecraft.getInstance().player;

        fishInArea = new ArrayList<>();

        for (FishProperties fp : player.level().registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY))
        {
            if (fp.hasGuideEntry() && fp.calculateChance(player, player.level(), ItemStack.EMPTY, AbstractFishRestriction.Context.GUIDE_FISHES_HOVER) > 0)
                fishInArea.add(fp);
        }

        fishCaughtCounterMap = FishingGuideAttachment.getFishesCaught(player);

        for (FishProperties fp : FishProperties.getFPs(level)) if (fp.hasGuideEntry()) entries.add(fp);
        entries = sortEntries(Config.SORT.get(), entries, player);
        fishInArea = sortEntries(Config.SORT.get(), fishInArea, player);

        for (TrophyProperties tp : level.registryAccess().registryOrThrow(Starcatcher.TROPHY_REGISTRY))
            if (tp.trophyType() == TrophyProperties.TrophyType.TROPHY) trophiesTps.add(tp);

        for (TrophyProperties tp : level.registryAccess().registryOrThrow(Starcatcher.TROPHY_REGISTRY))
        {
            if (tp.trophyType() == TrophyProperties.TrophyType.SECRET
                    && FishingGuideAttachment.getTrophiesCaught(player).containsKey(level.registryAccess().registryOrThrow(Starcatcher.TROPHY_REGISTRY).getKey(tp)))
                secretsTps.add(tp);
        }

        all = TrophyProperties.RarityProgress.fromAttachment(player);

        fishCaughtCounterMap.forEach((rl, counter) ->
        {
            all = new TrophyProperties.RarityProgress(all.total() + counter.count(), all.unique() + 1);

            this.progressMap.computeIfPresent(U.getFpFromRl(level, rl).rarity(), (r, p) -> new TrophyProperties.RarityProgress(p.total() + counter.count(), p.unique() + 1));
        });
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        InputConstants.Key key = InputConstants.getKey(keyCode, scanCode);
        if (this.minecraft.options.keyInventory.isActiveAndMatches(key))
        {
            this.onClose();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button)
    {
        double x = mouseX - uiX;
        double y = mouseY - uiY;

        arrowIndexPressed = false;
        arrowNextPressed = false;
        arrowPreviousPressed = false;

        //previous arrow
        if (x > 49 && x < 69 && y > 203 && y < 217)
        {
            switch (menu)
            {
                case 0 ->
                {
                    //index <- previous page of index
                    if (page != 0)
                    {
                        minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
                        page--;
                        return true;
                    }
                    //go to book cover
                    else
                    {
                        menu = -1;
                    }
                }
                case 1 ->
                {
                    minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
                    //help -> index
                    if (page == 0)
                    {
                        menu = 0;
                        page = lastIndexPage;
                        return true;
                    }
                    //help -> previous help page
                    page--;
                    return true;
                }
                case 2 ->
                {
                    minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
                    //entries -> last page of help
                    if (page == 0)
                    {
                        menu = 1;
                        page = MAX_HELP_PAGES;
                        return true;
                    }
                    //entries -> previous entry
                    page--;
                    return true;
                }

                case 3 ->
                {
                    minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
                    //end of the book -> last page of entries
                    if (page == 0)
                    {
                        menu = 2;
                        page = entries.size() / 2 - 1;
                        return true;
                    }
                    return true;
                }
            }
        }

        //next arrow
        if (x > 336 && x < 356 && y > 202 && y < 216)
        {
            switch (menu)
            {
                case -1 ->
                {
                    //cover -> index
                    minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
                    menu = 0;
                    page = 0;
                    return true;
                }
                case 0 ->
                {
                    //index -> next page of index
                    minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
                    if (hasNextPage)
                    {
                        page++;
                        return true;
                    }
                    //index -> first page of help
                    menu = 1;
                    page = 0;
                    return true;
                }
                case 1 ->
                {
                    //help -> next page of help
                    minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
                    if (page != MAX_HELP_PAGES)
                    {
                        minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
                        page++;
                        return true;
                    }
                    //index -> first page of help
                    menu = 2;
                    page = 0;
                    return true;
                }
                case 2 ->
                {
                    //entries -> next entry
                    if (entries.size() > page * 2 + 2)
                    {
                        minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
                        page++;
                    }
                    else
                    {
                        menu = 3;
                        page = 0;
                    }
                    return true;
                }
            }
        }

        //index arrow
        if (x > 174 && x < 196 && y > 202 && y < 216)
        {
            minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
            menu = 0;
            page = 0;
            return true;
        }

        if (button == 0)
        {
            clicked = true;
        }

        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY)
    {
        if (scrollY > 0)
            if (mouseX < (double) width / 2)
                leftPageScroll--;
            else
                rightPageScroll--;

        if (scrollY < 0)
            if (mouseX < (double) width / 2)
                leftPageScroll++;
            else
                rightPageScroll++;

        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        double x = mouseX - uiX;
        double y = mouseY - uiY;

        //System.out.println("clicked on x :" + x);
        //System.out.println("clicked on x :" + y);

        //sort
        if (x > 51 && x < 116 && y > 67 && y < 76)
        {
            if (button == 0) Config.SORT.set(Config.SORT.get().next());
            if (button == 1) Config.SORT.set(Config.SORT.get().previous());
            Config.SORT.save();
            entries = sortEntries(Config.SORT.get(), entries, player);
            fishInArea = sortEntries(Config.SORT.get(), fishInArea, player);
        }

        //previous arrow
        if (x > 49 && x < 69 && y > 203 && y < 217)
        {
            if (!(menu == 0 && page == 0))
            {
                arrowPreviousPressed = true;
            }
        }

        //next arrow
        if (x > 336 && x < 356 && y > 202 && y < 216)
        {
            if (entries.size() > page * 2 + 2 && menu != 3)
            {
                arrowNextPressed = true;
            }
        }

        //index arrow
        if (x > 174 && x < 196 && y > 202 && y < 216)
        {
            arrowIndexPressed = true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void tick()
    {
        super.tick();
        highlightLeftAlpha -= 0.025f;
        highlightRightAlpha -= 0.025f;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        switch (menu)
        {
            case -1 ->
            {
                renderImage(guiGraphics, BACKGROUND_COVER);
                renderCover(guiGraphics, mouseX, mouseY);
            }

            //render settings screen
            case -99 ->
            {
                Minecraft.getInstance().setScreen(
                        new NewSettingsScreen(
                                FishProperties.builder().withFish(SCItems.AURORA).build(),
                                new ItemStack(SCItems.ROD.get()
                                )
                        ));
                return;
            }
            //render index
            case 0 ->
            {
                if (page == 0) renderImage(guiGraphics, BACKGROUND_INDEX_FIRST);
                else renderImage(guiGraphics, BACKGROUND_INDEX_SECOND);
                renderIndex(guiGraphics, mouseX, mouseY);
            }
            //render help pages
            case 1 ->
            {
                renderImage(guiGraphics, BACKGROUND_BASICS);
                renderTheBasics(guiGraphics, mouseX, mouseY);
            }
            //render entries
            case 2 ->
            {
                renderImage(guiGraphics, BACKGROUND_ENTRY);
                renderEntry(guiGraphics, mouseX, mouseY, 52, page * 2);
                renderEntry(guiGraphics, mouseX, mouseY, 212, page * 2 + 1);
            }

            case 3 ->
            {
                renderImage(guiGraphics, BACKGROUND_LAST_PAGE);
            }
        }

        double x = mouseX - uiX;
        double y = mouseY - uiY;

        //previous arrow should not render on book cover
        if (menu != -1)
        {
            //previous arrow
            if (x > 49 && x < 69 && y > 203 && y < 217)
                renderImage(guiGraphics, ARROW_PREVIOUS_HIGHLIGHT);
            renderImage(guiGraphics, arrowPreviousPressed ? ARROW_PREVIOUS_PRESSED : ARROW_PREVIOUS);
        }

        //index should not render on book cover and first page of index
        if (menu != -1 && !(menu == 0 && page == 0))
        {
            if (x > 174 && x < 196 && y > 202 && y < 216)
                renderImage(guiGraphics, ARROW_INDEX_HIGHLIGHT);
            renderImage(guiGraphics, arrowIndexPressed ? ARROW_INDEX_PRESSED : ARROW_INDEX);
        }

        //next arrow
        if (menu != 3)
        {
            if (x > 336 && x < 356 && y > 202 && y < 216)
                renderImage(guiGraphics, ARROW_NEXT_HIGHLIGHT);
            renderImage(guiGraphics, arrowNextPressed ? ARROW_NEXT_PRESSED : ARROW_NEXT);
        }

        clicked = false;
    }

    private void renderCover(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {
        double x = mouseX - uiX;
        double y = mouseY - uiY;

        if (x > 233 && x < 334 && y > 117 && y < 125)
        {
            List<Component> list = new ArrayList<>();
            list.add(Component.literal("[not implemented yet] This will lock the book with the current recorded entries so it "));
            list.add(Component.literal("can be shared with others or displayed in a lectern!"));
            guiGraphics.renderTooltip(this.font, list, Optional.empty(), mouseX, mouseY);
        }

        //if (clickedX > 233 && clickedX < 334 && clickedY > 117 && clickedY < 125)
        {
            //System.out.println("send packet");
        }

    }

    private void renderHelpText(GuiGraphics guiGraphics, String pageName)
    {
        for (int i = 0; i < 40; i++)
        {
            if (!I18n.exists("gui.guide.page." + pageName + ".left." + i)) break;
            Component comp = Component.translatable("gui.guide.page." + pageName + ".left." + i).copy().withStyle(Style.EMPTY.withColor(0x635040));
            guiGraphics.drawString(this.font, comp, uiX + 52, uiY + 10 * i + 13, 0xff000000, false);
        }

        for (int i = 0; i < 40; i++)
        {
            if (!I18n.exists("gui.guide.page." + pageName + ".right." + i)) break;
            Component comp = Component.translatable("gui.guide.page." + pageName + ".right." + i).copy().withStyle(Style.EMPTY.withColor(0x635040));
            guiGraphics.drawString(this.font, comp, uiX + 213, uiY + 10 * i + 13, 0xff000000, false);
        }
    }

    private void renderSecrets(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {
        for (int i = 0; i < secretsTps.size(); i++)
        {
            int rowSize = Math.min(6, (secretsTps.size() - i / 6 * 6));
            int x = 70 - rowSize * 23 / 2;

            int xrender = x + (i % 6) * 23;
            int y = i / 6 * 25;

            //offset to page
            xrender += uiX + 223;
            y += uiY + 110;

            TrophyProperties tp = secretsTps.get(i);

            ItemStack is;

            is = new ItemStack(tp.fish());

            guiGraphics.renderOutline(xrender - 10, y - 2, 20, 20, 0xff000000);
            renderItem(is, xrender - 8, y, 1);

            if (mouseX > xrender - 10 && mouseX < xrender + 10 && mouseY > y - 2 && mouseY < y + 18)
            {
                guiGraphics.renderTooltip(this.font, is, mouseX, mouseY);
            }

            //todo fix this
//            if (clickedX > xrender - 10 && clickedX < xrender + 10 && clickedY > y - 2 && clickedY < y + 18)
//            {
//                if (is.getItem() instanceof NoteContainer nc)
//                {
//                    Minecraft.getInstance().setScreen(new SecretNoteScreen(nc.note));
//                }
//            }
        }
    }

    private void renderTrophies(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {
        for (int i = 0; i < trophiesTps.size(); i++)
        {
            int rowSize = Math.min(6, (trophiesTps.size() - i / 6 * 6));
            int x = 60 - rowSize * 23 / 2;

            int xrender = x + (i % 6) * 23;
            int y = i / 6 * 25;

            //offset to page
            xrender += uiX + 73;
            y += uiY + 120;

            TrophyProperties tp = trophiesTps.get(i);

            ItemStack is;
            boolean isMouseOnTop = mouseX > xrender - 10 && mouseX < xrender + 10 && mouseY > y - 2 && mouseY < y + 18;

            //if caught
            if (FishingGuideAttachment.getTrophiesCaught(player).containsKey(level.registryAccess().registryOrThrow(Starcatcher.TROPHY_REGISTRY).getKey(tp)))
            {
                is = new ItemStack(tp.fish());
                if (isMouseOnTop)
                {
                    guiGraphics.renderTooltip(this.font, is, mouseX, mouseY);
                }
            }
            else
            {
                if (isMouseOnTop)
                {
                    List<Component> list = new ArrayList<>(List.of(Component.literal("Requirements:")));

                    if (tp.all().total() != 0)
                        list.add(Component.empty().append(Component.translatable("gui.guide.trophy.all.total")).append("[" + all.total() + "/" + tp.all().total() + "]"));
                    if (tp.all().unique() != 0)
                        list.add(Component.empty().append(Component.translatable("gui.guide.trophy.all.unique")).append("[" + all.unique() + "/" + tp.all().unique() + "]"));

                    for (FishProperties.Rarity value : FishProperties.Rarity.values())
                    {
                        TrophyProperties.RarityProgress progress = tp.getProgress(value);
                        TrophyProperties.RarityProgress active = this.progressMap.get(value);
                        if (progress.total() != 0)
                        {
                            list.add(Component.empty().append(Component.translatable("gui.guide.trophy." + value.getSerializedName() + ".total")).append("[" + active.total() + "/" + progress.total() + "]"));
                        }
                        if (progress.unique() != 0)
                        {
                            list.add(Component.empty().append(Component.translatable("gui.guide.trophy." + value.getSerializedName() + ".unique")).append("[" + active.unique() + "/" + progress.unique() + "]"));
                        }
                    }

                    guiGraphics.renderTooltip(this.font, list, Optional.empty(), mouseX, mouseY);
                }
                is = new ItemStack(SCItems.MISSINGNO.get());
            }

            guiGraphics.renderOutline(xrender - 10, y - 2, 20, 20, 0xff000000);
            renderItem(is, xrender - 8, y, 1);
        }
    }

    private void renderTheBasics(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {

        guiGraphics.drawString(this.font, page + "/" + MAX_HELP_PAGES, uiX + 213, uiY + 206, 0x9c897c, false);

        switch (page)
        {
            //the basics
            case 0 ->
            {
                renderHelpText(guiGraphics, "basics");
                renderImage(guiGraphics, HELP_PAGE_BASICS);
                renderItem(basicsIndexIcon, uiX + 166, uiY + 39, 1);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.basics"), uiX + 80, uiY + 45, 0x635040, false);
            }

            //sweetspots
            case 1 ->
            {
                renderHelpText(guiGraphics, "sweetspots");
                renderImage(guiGraphics, HELP_PAGE_SWEETSPOTS);
                renderItem(sweetspotsIcon, uiX + 166, uiY + 39, 1);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.sweetspots"), uiX + 80, uiY + 45, 0x635040, false);
            }

            //treasures
            case 2 ->
            {
                renderHelpText(guiGraphics, "treasure");
                renderImage(guiGraphics, HELP_PAGE_TREASURE);
                renderItem(treasureIcon, uiX + 166, uiY + 39, 1);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.treasures"), uiX + 80, uiY + 45, 0x635040, false);
            }

            //upgrades
            case 3 ->
            {
                renderHelpText(guiGraphics, "upgrades");
                renderImage(guiGraphics, HELP_PAGE_LAVA_FISHING);
                renderItem(upgradeIndexIcon, uiX + 166, uiY + 39, 1);
                renderItem(equipmentIcon, uiX + 321, uiY + 39, 1);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.lava_fishing"), uiX + 80, uiY + 45, 0x635040, false);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.modifiers"), uiX + 230, uiY + 45, 0x635040, false);
            }

            //hooks bobbers and baits
            case 4 ->
            {
                renderHelpText(guiGraphics, "hooks");
                renderImage(guiGraphics, HELP_PAGE_HOOKS_BOBBERS_BAITS);

                //hooks & bobbers title + top icon
                renderItem(hookIcon, uiX + 166, uiY + 39, 1);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.hooks_and_bobbers"), uiX + 60, uiY + 45, 0x635040, false);

                //baits title + top icon
                renderItem(baitIcon, uiX + 321, uiY + 39, 1);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.baits"), uiX + 230, uiY + 45, 0x635040, false);

                //left page scroll arrows
                guiGraphics.blit(ARROW_LEFT, uiX + 63, uiY + 170, 0, 0, 16, 16, 16, 16);
                guiGraphics.blit(ARROW_RIGHT, uiX + 171, uiY + 170, 0, 0, 16, 16, 16, 16);
                if (clicked && mouseX > uiX + 64 && mouseX < uiX + 74 && mouseY > uiY + 170 && mouseY < uiY + 170 + 16)
                    leftPageScroll--;
                if (clicked && mouseX > uiX + 175 && mouseX < uiX + 185 && mouseY > uiY + 170 && mouseY < uiY + 170 + 16)
                    leftPageScroll++;

                //right page scroll arrows
                guiGraphics.blit(ARROW_LEFT, uiX + 224, uiY + 170, 0, 0, 16, 16, 16, 16);
                guiGraphics.blit(ARROW_RIGHT, uiX + 332, uiY + 170, 0, 0, 16, 16, 16, 16);
                if (clicked && mouseX > uiX + 225 && mouseX < uiX + 235 && mouseY > uiY + 170 && mouseY < uiY + 170 + 16)
                    rightPageScroll--;
                if (clicked && mouseX > uiX + 336 && mouseX < uiX + 346 && mouseY > uiY + 170 && mouseY < uiY + 170 + 16)
                    rightPageScroll++;

                //scrollable hooks & bobbers icons
                for (int i = 0; i < 5; i++)
                {
                    int x = uiX + 77 + (i * 20);
                    int y = uiY + 170;
                    ItemStack stack = hooksAndBobbers.get(Math.abs((leftPageScroll + i) % hooksAndBobbers.size()));
                    //render item
                    renderItem(stack, x, y, 1);
                    //render hover item tooltip
                    if (mouseX > x && mouseX < x + 16 && mouseY > y && mouseY < y + 16)
                        guiGraphics.renderTooltip(this.font, stack, mouseX, mouseY);
                    //scrollable background fill
                    guiGraphics.fill(x - 1, y - 1, x + 17, y + 17, 0xffb4a697);
                }

                //scrollable baits icons
                for (int i = 0; i < 5; i++)
                {
                    int x = uiX + 238 + (i * 20);
                    int y = uiY + 170;
                    ItemStack stack = baits.get(Math.abs((rightPageScroll + i) % hooksAndBobbers.size()));
                    //render item
                    renderItem(stack, x, y, 1);
                    //render hover item tooltip
                    if (mouseX > x && mouseX < x + 16 && mouseY > y && mouseY < y + 16)
                        guiGraphics.renderTooltip(this.font, stack, mouseX, mouseY);
                    //scrollable background fill
                    guiGraphics.fill(x - 1, y - 1, x + 17, y + 17, 0xffb4a697);
                }

            }

            //gadgets & cosmetics
            case 5 ->
            {
                renderHelpText(guiGraphics, "cosmetics");
                renderImage(guiGraphics, HELP_PAGE_GADGETS_COSMETICS);
                renderItem(gadgetsIcon, uiX + 166, uiY + 39, 1);
                renderItem(cosmeticsIcon, uiX + 321, uiY + 39, 1);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.gadgets"), uiX + 80, uiY + 45, 0x635040, false);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.cosmetics"), uiX + 230, uiY + 45, 0x635040, false);

                //dont ask me whats going on here ⏬

                int s1 = 5;
                int d1 = 23;
                int h1 = 0;

                //baits max column size
                if (gadgets.size() > 15) s1 = 6;
                if (gadgets.size() > 18) s1 = 7;
                if (gadgets.size() > 18) d1 = 21;
                if (gadgets.size() > 18) h1 = 6;

                for (int i = 0; i < Math.min(gadgets.size(), 21); i++)
                {
                    int x = 70 - Math.min(s1, (gadgets.size() - i / s1 * s1)) * 23 / 2;
                    int xrender = x + (i % s1) * d1;
                    int y = i / s1 * 25;

                    //offset to page
                    xrender += uiX + 60 + h1;
                    y += uiY + 130;

                    //render item and background
                    guiGraphics.fill(xrender - 10, y - 2, xrender + 10, y + 18, 0xffb4a697);
                    renderItem(gadgets.get(i), xrender - 8, y, 1);

                    //render when hover
                    if (mouseX > xrender - 10 && mouseX < xrender + 10 && mouseY > y - 2 && mouseY < y + 18)
                        guiGraphics.renderTooltip(this.font, gadgets.get(i), mouseX, mouseY);
                }
            }

            //cosmetic templates
            case 6 ->
            {
                renderHelpText(guiGraphics, "templates");
                renderImage(guiGraphics, HELP_PAGE_TEMPLATES_EQUIPMENT);

                //templates title + top icon
                renderItem(templateIcon, uiX + 166, uiY + 39, 1);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.templates"), uiX + 60, uiY + 45, 0x635040, false);

                //equipment title + top icon
                renderItem(equipmentIcon, uiX + 321, uiY + 39, 1);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.equipment"), uiX + 230, uiY + 45, 0x635040, false);

                //left page scroll arrows
                guiGraphics.blit(ARROW_LEFT, uiX + 63, uiY + 170, 0, 0, 16, 16, 16, 16);
                guiGraphics.blit(ARROW_RIGHT, uiX + 171, uiY + 170, 0, 0, 16, 16, 16, 16);
                if (clicked && mouseX > uiX + 64 && mouseX < uiX + 74 && mouseY > uiY + 170 && mouseY < uiY + 170 + 16)
                    leftPageScroll--;
                if (clicked && mouseX > uiX + 175 && mouseX < uiX + 185 && mouseY > uiY + 170 && mouseY < uiY + 170 + 16)
                    leftPageScroll++;

                //right page scroll arrows
                guiGraphics.blit(ARROW_LEFT, uiX + 224, uiY + 170, 0, 0, 16, 16, 16, 16);
                guiGraphics.blit(ARROW_RIGHT, uiX + 332, uiY + 170, 0, 0, 16, 16, 16, 16);
                if (clicked && mouseX > uiX + 225 && mouseX < uiX + 235 && mouseY > uiY + 170 && mouseY < uiY + 170 + 16)
                    rightPageScroll--;
                if (clicked && mouseX > uiX + 336 && mouseX < uiX + 346 && mouseY > uiY + 170 && mouseY < uiY + 170 + 16)
                    rightPageScroll++;

                //scrollable templates icons
                for (int i = 0; i < 5; i++)
                {
                    int x = uiX + 77 + (i * 20);
                    int y = uiY + 170;
                    ItemStack stack = templates.get(Math.abs((leftPageScroll + i) % templates.size()));
                    //render item
                    renderItem(stack, x, y, 1);
                    //render hover item tooltip
                    if (mouseX > x && mouseX < x + 16 && mouseY > y && mouseY < y + 16)
                        guiGraphics.renderTooltip(this.font, stack, mouseX, mouseY);
                    //scrollable background fill
                    guiGraphics.fill(x - 1, y - 1, x + 17, y + 17, 0xffb4a697);
                }

                //scrollable equipments icons
                for (int i = 0; i < 5; i++)
                {
                    int x = uiX + 238 + (i * 20);
                    int y = uiY + 170;
                    ItemStack stack = equipments.get(Math.abs((rightPageScroll + i) % equipments.size()));
                    //render item
                    renderItem(stack, x, y, 1);
                    //render hover item tooltip
                    if (mouseX > x && mouseX < x + 16 && mouseY > y && mouseY < y + 16)
                        guiGraphics.renderTooltip(this.font, stack, mouseX, mouseY);
                    //scrollable background fill
                    guiGraphics.fill(x - 1, y - 1, x + 17, y + 17, 0xffb4a697);
                }
            }


            case 7 ->
            {
                //tournaments
                renderHelpText(guiGraphics, "tournaments");
                renderImage(guiGraphics, HELP_PAGE_TOURNAMENTS);
                renderItem(tournamentIndexIcon, uiX + 166, uiY + 39, 1);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.tournaments"), uiX + 60, uiY + 45, 0x635040, false);

            }

            case 8 ->
            {
                //trophies
                renderHelpText(guiGraphics, "trophies");
                renderImage(guiGraphics, HELP_PAGE_TROPHIES);
                renderItem(trophiesIndexIcon, uiX + 166, uiY + 39, 1);
                renderItem(secretsIcon, uiX + 321, uiY + 39, 1);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.trophies"), uiX + 60, uiY + 45, 0x635040, false);
                renderTrophies(guiGraphics, mouseX, mouseY);
                guiGraphics.drawString(this.font, Component.translatable("gui.guide.secrets"), uiX + 230, uiY + 45, 0x635040, false);
                renderSecrets(guiGraphics, mouseX, mouseY);
            }

        }
    }

    private void renderIndex(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {

//        if (player.level().getDayTime() % 5 == 0)
//        {
//            System.out.println(player.level().getDayTime());
//            fishInArea.add(FishProperties.DEFAULT);
//        }

        //render top index
        int xx = uiX + 55;
        if (page == 0)
        {
            for (int i = 0; i < 7; i++)
            {
                renderItem(indexEntries.get(i).getFirst(), xx + i * 20, uiY + 47, 1);

                if (mouseX > xx + (i * 20) - 2 && mouseX < xx + (i * 20) + 17 && mouseY > uiY + 47 - 2 && mouseY < uiY + 47 + 17)
                    guiGraphics.renderTooltip(this.font, Component.translatable(indexEntries.get(i).getSecond()), mouseX, mouseY);

                if (clicked && mouseX > xx + (i * 20) - 2 && mouseX < xx + (i * 20) + 17 && mouseY > uiY + 47 - 2 && mouseY < uiY + 47 + 17)
                {
                    minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
                    menu = 1;
                    switch (i)
                    {
                        case 0 -> page = 0;
                        case 1 -> page = 3;
                        case 2 -> page = 4;
                        case 3 -> page = 5;
                        case 4 -> page = 7;
                        case 5 -> page = 8;
                    }
                    if (i == 6) menu = -99;
                }
            }
        }

        //[sort] text
        guiGraphics.drawString(this.font, Component.translatable("gui.guide.sort"), uiX + 52, uiY + 67, 0xe4e0d8, false);

        //render fishes in area
        {
            if (page == 0)
            {
                //render fishes in area clickable squares and stuff
                for (int i = 0; i < fishInArea.size(); i++)
                {
                    if (i >= 42) break;
                    FishProperties fp = fishInArea.get(i);

                    int xpos = uiX + 53 + (i % 7) * 20;
                    int ypos = uiY + 47 + (i / 7 * 20) + 38;

                    renderFishIndex(guiGraphics, xpos, ypos, mouseX, mouseY, fp, 0xffc6bdaf);
                }

                //render decorations and stuff
                {
                    //render top right deco unless theres no fish in the top right slot
                    if (fishInArea.size() > 6) renderImage(guiGraphics, FISHES_IN_AREA_TOP_RIGHT_DECORATION);

                    int numberOfRows = (fishInArea.size() - 1) / 7 + 1;

                    int x = mouseX - uiX;
                    int y = mouseY - uiY;

                    if (x > 51 && x < 116 && y > 67 && y < 76)
                    {
                        guiGraphics.renderTooltip(this.font, Component.translatable(Config.SORT.get().getTranslationKey()), mouseX, mouseY);
                    }

                    //render bottom decoration if theres space
                    if (numberOfRows < 4)
                        renderImage(guiGraphics, FISHES_IN_AREA_BOTTOM_DECORATION);

                    if (numberOfRows == 4 && fishInArea.size() % 7 < 5 && fishInArea.size() % 7 != 0)
                        renderImage(guiGraphics, FISHES_IN_AREA_BOTTOM_DECORATION);


                    //render bottom left thingy, offset by the number of rows
                    if (!fishInArea.isEmpty())
                        renderImage(guiGraphics, FISHES_IN_AREA_BOTTOM_LEFT_DECORATION, 0, (Math.min(numberOfRows, 6) - 1) * 20);

                    //render fish skeleton unless theres no space for it
                    int xFishSkeletonOffset = 0;
                    if (fishInArea.size() % 7 > 4 || fishInArea.size() % 7 == 0) xFishSkeletonOffset = 20;
                    if (numberOfRows < 6)
                        renderImage(guiGraphics, FISHES_IN_AREA_FISH_DECORATION, 0, (numberOfRows - 1) * 20 + xFishSkeletonOffset);
                    if (numberOfRows == 6 && fishInArea.size() % 7 < 5 && fishInArea.size() % 7 != 0)
                        renderImage(guiGraphics, FISHES_IN_AREA_FISH_DECORATION, 0, (numberOfRows - 1) * 20 + xFishSkeletonOffset);
                }
            }

        }

        hasNextPage = false;
        //render all fishes
        if (page == 0)
        {
            for (int i = 0; i < 49; i++)
            {
                if (i > entries.size() - 1) return;
                renderFishIndex(guiGraphics, xx + 160 + (i % 7 * 20), uiY + 56 + (i / 7 * 20), mouseX, mouseY, entries.get(i), 0xffc6bdaf);
            }
        }
        else
        {
            //render second page, left
            for (int i = 0; i < 49; i++)
            {
                int order = i + 49 * (page * 2 - 1);
                if (order > entries.size() - 1) break;
                renderFishIndex(guiGraphics, xx + (i % 7 * 20), uiY + 56 + (i / 7 * 20), mouseX, mouseY, entries.get(order), 0xffc6bdaf);
            }

            //render second page, right
            for (int i = 0; i < 49; i++)
            {
                int order = i + 49 + 49 * (page * 2 - 1);
                if (order > entries.size() - 1) return;
                renderFishIndex(guiGraphics, xx + 160 + (i % 7 * 20), uiY + 56 + (i / 7 * 20), mouseX, mouseY, entries.get(order), 0xffc6bdaf);
            }
        }
        lastIndexPage = Math.max(page + 1, lastIndexPage);
        hasNextPage = true;
    }

    private void renderFishIndex(GuiGraphics guiGraphics, int xOffset, int yOffset, int mouseX, int mouseY, FishProperties fp, int backgroundFillColor)
    {
        Map<ResourceLocation, FishCaughtCounter> fishesCaught = FishingGuideAttachment.getFishesCaught(player);
        FishCaughtCounter fishCaughtCounter = FishCaughtCounter.get(player, fp);
        ItemStack is = new ItemStack(fp.catchInfo().fish());

        //calculate caught counter
        int caught = fishCaughtCounter == null ? 0 : fishCaughtCounter.count();

        //handle click
        if (clicked && mouseX > xOffset - 3 && mouseX < xOffset + 21 - 3 && mouseY > yOffset - 3 && mouseY < yOffset + 21 - 3)
        {
            minecraft.player.playSound(SoundEvents.BOOK_PAGE_TURN);
            menu = 2;
            page = entries.indexOf(fp) / 2;

            if (entries.indexOf(fp) % 2 == 0)
                highlightLeftAlpha = 0.5f;
            else
                highlightRightAlpha = 0.5f;
        }

        //render fill
        guiGraphics.fill(xOffset - 1, yOffset - 1, xOffset + 17, yOffset + 17, backgroundFillColor);

        //glow color
        int color = switch (fp.rarity())
        {
            case TRASH -> FastColor.ARGB32.color(0, -1);
            case FishProperties.Rarity.COMMON -> FastColor.ARGB32.color(0, -1);
            case FishProperties.Rarity.UNCOMMON -> FastColor.ARGB32.color(255, 0x92f28d);
            case FishProperties.Rarity.RARE -> FastColor.ARGB32.color(255, 0x78c8ff);
            case FishProperties.Rarity.EPIC -> FastColor.ARGB32.color(255, 0xc060ff);
            case FishProperties.Rarity.LEGENDARY, FishProperties.Rarity.GOLDEN ->
                    FastColor.ARGB32.color(175, Color.HSBtoRGB((float) Util.getMillis() / 10000, 1, 1));
        };

        float red = FastColor.ARGB32.red(color) / 255f;
        float green = FastColor.ARGB32.green(color) / 255f;
        float blue = FastColor.ARGB32.blue(color) / 255f;
        float alpha = FastColor.ARGB32.alpha(color) / 255f;

        guiGraphics.setColor(red, green, blue, alpha);

        //render glow
        RenderSystem.enableBlend();
        guiGraphics.blit(
                GLOW, xOffset - 1, yOffset - 1,
                0, 0, 18, 18, 18, 18);
        RenderSystem.disableBlend();
        guiGraphics.setColor(1, 1, 1, 1);

        //render fish with missingno if not caught
        if (caught != 0 || !Config.HIDE_ENTRIES_UNTIL_FOUND.get())
            renderItem(is, xOffset, yOffset, 1);
        else
            renderItem(new ItemStack(SCItems.MISSINGNO.get()), xOffset, yOffset, 1);

        //render fish notification icon
        if (fishCaughtCounter != null && fishCaughtCounter.hasGuideNotification())
            guiGraphics.blit(STAR, xOffset + 10, yOffset + 7, 0, 0, 10, 10, 10, 10);


        //render tooltip
        if (mouseX > xOffset - 3 && mouseX < xOffset + 21 - 3 && mouseY > yOffset - 3 && mouseY < yOffset + 21 - 3)
        {
            ArrayList<Component> components = new ArrayList<>(getCachedTooltipForHoverEntry(fp, caught));
            components.add(1, Component.translatable("gui.guide.rarity." + fp.rarity().getSerializedName()));

            guiGraphics.renderTooltip(this.font, components, Optional.empty(), mouseX, mouseY);
        }
    }

    private FishProperties cachedFp = null;
    private List<Component> cachedHoverList = List.of();

    private List<Component> getCachedTooltipForHoverEntry(FishProperties fp, int caught)
    {
        if (fp == cachedFp && cachedFp != null) return cachedHoverList;
        cachedFp = fp;

        List<Component> components = new ArrayList<>();

        if (caught == 0 && Config.HIDE_ENTRIES_UNTIL_FOUND.get())
        {
            components.add(Component.translatable("gui.guide.not_caught_fish_name"));
            components.add(Component.translatable("gui.guide.not_caught_yet").withStyle(Style.EMPTY.withColor(SCColors.GUIDE_RED)));
        }
        else
        {
            if (fp.catchInfo().alwaysSpawnEntity() && !fp.catchInfo().entityToSpawn().is(U.holderEntity(SCEntities.FISH)))
                components.add(Component.translatable("entity." + fp.catchInfo().entityToSpawn().getRegisteredName().replace(":", ".")));
            else
                components.add(Component.translatable(fp.catchInfo().fish().value().getDescriptionId()));

            components.add(Component.translatable("gui.guide.caught").append(Component.literal(" [" + caught + "]")).withStyle(Style.EMPTY.withColor(SCColors.GUIDE_GREEN)));
        }

        //Aurora
        //Legendary
        //Not Caught yet!
        //
        //✅ dimension
        //❌ biome
        //Not in Season!

        components.add(Component.empty());
        for (AbstractFishRestriction restriction : fp.restrictions())
        {
            if (!restriction.isEnabled()) continue;
            List<Component> indexHover = restriction.getIndexHover(level, fp, player);
            components.addAll(indexHover);
        }

        if (components.getLast().equals(Component.empty())) components.removeLast();

        cachedHoverList = components;
        return components;
    }

    private void renderEntry(GuiGraphics guiGraphics, int mouseX, int mouseY, int xOffset, int entry)
    {
        if (level == null) level = getMinecraft().level;

        if (entries.size() <= entry) return;

        FishProperties fp = entries.get(entry);

        ResourceLocation loc = fp.toLoc(level);
        FishCaughtCounter fishCaughtCounter = fishCaughtCounterMap.get(loc);
        if (fishCaughtCounter != null && !fpsSeen.contains(loc) && fishCaughtCounter.hasGuideNotification())
            fpsSeen.add(loc);

        //get fishCaughtCount
        FishCaughtCounter fcc = FishCaughtCounter.get(player, fp);

        ItemStack is = fcc == null ? ItemStack.EMPTY : new ItemStack(entries.get(entry).catchInfo().fish());

        renderFishEntryPage(guiGraphics, fp, is, fcc, uiX + xOffset, uiY, mouseX, mouseY);

        //white highlight on jumping to
        if (highlightRightAlpha > 0)
        {
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1, 1, 1, highlightRightAlpha);
            renderImage(guiGraphics, HIGHLIGHT_RIGHT);
            RenderSystem.setShaderColor(1, 1, 1, 1);
            RenderSystem.disableBlend();
        }

        if (highlightLeftAlpha > 0)
        {
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1, 1, 1, highlightLeftAlpha);
            renderImage(guiGraphics, HIGHLIGHT_LEFT);
            RenderSystem.setShaderColor(1, 1, 1, 1);
            RenderSystem.disableBlend();
        }
    }

    private void renderImage(GuiGraphics guiGraphics, ResourceLocation rl)
    {
        renderImage(guiGraphics, rl, 0, 0);
    }

    private void renderImage(GuiGraphics guiGraphics, ResourceLocation rl, int xOffset, int yOffset)
    {
        guiGraphics.blit(rl, uiX + xOffset, uiY + yOffset, 0, 0, 420, 260, 420, 260);
    }

    private static void renderItem(ItemStack stack, int x, int y)
    {
        renderItem(stack, x, y, 3);
    }

    private static void renderItem(ItemStack stack, int x, int y, float scale)
    {
        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        LivingEntity entity = mc.player;

        if (!stack.isEmpty())
        {
            ItemRenderer itemRenderer = mc.getItemRenderer();
            BakedModel bakedmodel = itemRenderer.getModel(stack, level, entity, 234234);

            PoseStack pose = new PoseStack();

            pose.pushPose();
            pose.translate((float) (x + 8), (float) (y + 8), (float) (150));

            pose.scale(16F * scale, -16F * scale, 16F * scale);
            boolean usesBlockLight = !bakedmodel.usesBlockLight();
            if (usesBlockLight)
            {
                Lighting.setupForFlatItems();
            }

            itemRenderer.render(
                    stack, ItemDisplayContext.GUI, false, pose, mc.renderBuffers().bufferSource(),
                    15728880, OverlayTexture.NO_OVERLAY, bakedmodel);

            //flush()
            RenderSystem.disableDepthTest();
            mc.renderBuffers().bufferSource().endBatch();
            RenderSystem.enableDepthTest();

            if (usesBlockLight)
            {
                Lighting.setupFor3DItems();
            }

            pose.popPose();
        }
    }

    @Override
    public void onClose()
    {
        PacketDistributor.sendToServer(new FPsSeenPayload(fpsSeen));
        super.onClose();
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    @Override
    protected boolean shouldNarrateNavigation()
    {
        return false;
    }

    public enum Sort
    {
        ALPHABETICAL_UP("gui.guide.sort.alphabetical_up"),
        ALPHABETICAL_DOWN("gui.guide.sort.alphabetical_down"),
        MOD_UP("gui.guide.sort.mod_up"),
        MOD_DOWN("gui.guide.sort.mod_down"),
        RARITY_UP("gui.guide.sort.rarity_up"),
        RARITY_DOWN("gui.guide.sort.rarity_down"),
        CAUGHT_UP("gui.guide.sort.caught_up"),
        CAUGHT_DOWN("gui.guide.sort.caught_down"),
        //FLUID_UP("gui.guide.sort.fluid_up"),
        //FLUID_DOWN("gui.guide.sort.fluid_down"),
        //SEASON_UP("gui.guide.sort.season_up"),
        //SEASON_DOWN("gui.guide.sort.season_down")
        ;

        private static final Sort[] vals = values();

        private final String translationKey;

        String getTranslationKey()
        {
            return this.translationKey;
        }

        Sort(String translationKey)
        {
            this.translationKey = translationKey;
        }

        public Sort previous()
        {
            int lenght = vals.length - 2;
            if (ModList.get().isLoaded("sereneseasons") || ModList.get().isLoaded("eclipticseasons")) lenght += 2;

            if (this.ordinal() == 0) return vals[lenght - 1];
            return vals[(this.ordinal() - 1) % lenght];
        }

        public Sort next()
        {
            int lenght = vals.length - 2;
            if (ModList.get().isLoaded("sereneseasons") || ModList.get().isLoaded("eclipticseasons")) lenght += 2;

            return vals[(this.ordinal() + 1) % lenght];
        }
    }

    public static List<FishProperties> sortEntries(Sort sort, List<FishProperties> entriesToSort, Player player)
    {
        //rarity
        if (sort.equals(Sort.RARITY_DOWN) || sort.equals(Sort.RARITY_UP))
        {
            //sort alphabetical first
            entriesToSort = entriesToSort.stream().sorted(Comparator.comparing(o -> o.catchInfo().fish().unwrapKey().get().location().getPath())).toList();

            List<FishProperties> entriesSorted = new ArrayList<>();

            entriesToSort.forEach(e ->
            {
                if (e.rarity().equals(FishProperties.Rarity.COMMON)) entriesSorted.add(e);
            });
            entriesToSort.forEach(e ->
            {
                if (e.rarity().equals(FishProperties.Rarity.UNCOMMON)) entriesSorted.add(e);
            });
            entriesToSort.forEach(e ->
            {
                if (e.rarity().equals(FishProperties.Rarity.RARE)) entriesSorted.add(e);
            });
            entriesToSort.forEach(e ->
            {
                if (e.rarity().equals(FishProperties.Rarity.EPIC)) entriesSorted.add(e);
            });
            entriesToSort.forEach(e ->
            {
                if (e.rarity().equals(FishProperties.Rarity.LEGENDARY)) entriesSorted.add(e);
            });

            return sort.equals(Sort.RARITY_UP) ? entriesSorted : entriesSorted.reversed();
        }

        //alphabetical
        if (sort.equals(Sort.ALPHABETICAL_DOWN) || sort.equals(Sort.ALPHABETICAL_UP))
        {
            List<FishProperties> entriesSorted = entriesToSort.stream().sorted(Comparator.comparing(o -> o.catchInfo().fish().unwrapKey().get().location().getPath())).toList();
            return sort.equals(Sort.ALPHABETICAL_UP) ? entriesSorted : entriesSorted.reversed();
        }

        //mod
        if (sort.equals(Sort.MOD_DOWN) || sort.equals(Sort.MOD_UP))
        {
            //sort alphabetical first
            entriesToSort = entriesToSort.stream().sorted(Comparator.comparing(o -> o.catchInfo().fish().unwrapKey().get().location().getPath())).toList();

            List<FishProperties> entriesSorted = new ArrayList<>();
            List<String> allNamespaces = new ArrayList<>();

            for (FishProperties fp : entriesToSort)
            {
                String namespace = fp.catchInfo().fish().unwrapKey().get().location().getNamespace();
                if (!allNamespaces.contains(namespace)) allNamespaces.add(namespace);
            }

            for (String s : allNamespaces)
            {
                for (FishProperties fp : entriesToSort)
                {
                    String namespace = fp.catchInfo().fish().unwrapKey().get().location().getNamespace();
                    if (namespace.equals(s)) entriesSorted.add(fp);
                }

            }

            entriesToSort = sort.equals(Sort.MOD_UP) ? entriesSorted : entriesSorted.reversed();
        }

        //fluid
//        if (sort.equals(Sort.FLUID_DOWN) || sort.equals(Sort.FLUID_UP))
//        {
//            //sort alphabetical first
//            entriesToSort = entriesToSort.stream().sorted(Comparator.comparing(o -> o.catchInfo().fish().unwrapKey().get().location().getPath())).toList();
//
//            List<FishProperties> entriesSorted = new ArrayList<>();
//            List<FishProperties> entriesRemaining = new ArrayList<>(entriesToSort);
//
//            while (!entriesRemaining.isEmpty())
//            {
//                ResourceLocation rlBeingSorted = entriesRemaining.getFirst().wr().fluids().getFirst();
//                List<FishProperties> temp = new ArrayList<>(entriesRemaining);
//                temp.forEach(e ->
//                {
//                    if (e.wr().fluids().getFirst().equals(rlBeingSorted))
//                    {
//                        entriesSorted.add(e);
//                        entriesRemaining.remove(e);
//                    }
//                });
//            }
//
//            entriesToSort = sort.equals(Sort.FLUID_UP) ? entriesSorted : entriesSorted.reversed();
//        }

        //caught
        if (sort.equals(Sort.CAUGHT_UP) || sort.equals(Sort.CAUGHT_DOWN))
        {
            //sort alphabetical first
            entriesToSort = entriesToSort.stream().sorted(Comparator.comparing(o -> o.catchInfo().fish().unwrapKey().get().location().getPath())).toList();


            //add all fishes caught to start
            Map<ResourceLocation, FishCaughtCounter> fishesCaught = FishingGuideAttachment.getFishesCaught(player);

            List<FishProperties> hasCaught = new ArrayList<>();
            List<FishProperties> hasNotCaught = new ArrayList<>();
            List<FishProperties> toReturn = new ArrayList<>();

            //populate hasCaught and hasNotCaught
            entriesToSort.forEach(e ->
            {
                if (e.hasGuideEntry() && fishesCaught.containsKey(U.getRlFromFp(player.level(), e)))
                    hasCaught.add(e);
                else
                    hasNotCaught.add(e);
            });


            if (sort.equals(Sort.CAUGHT_UP))
            {
                toReturn.addAll(hasCaught);
                toReturn.addAll(hasNotCaught);
            }
            else
            {
                toReturn.addAll(hasNotCaught);
                toReturn.addAll(hasCaught);
            }

            return toReturn;
        }

//        //SEASONS
//        if (sort.equals(Sort.SEASON_DOWN) || sort.equals(Sort.SEASON_UP))
//        {
//            //sort alphabetical first
//            entriesToSort = entriesToSort.stream().sorted(Comparator.comparing(o -> o.catchInfo().fish().unwrapKey().get().location().getPath())).toList();
//
//            List<FishProperties> entriesSorted = new ArrayList<>();
//            List<FishProperties> entriesUnsorted = new ArrayList<>(entriesToSort);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.ALL)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.SPRING)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.EARLY_SPRING)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.MID_SPRING)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.LATE_SPRING)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.SUMMER)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.EARLY_SUMMER)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.MID_SUMMER)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.LATE_SUMMER)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.AUTUMN)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.EARLY_AUTUMN)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.MID_AUTUMN)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.LATE_AUTUMN)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.WINTER)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.EARLY_WINTER)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.MID_WINTER)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            for (FishProperties fp : entriesUnsorted)
//                if (fp.wr().seasons().contains(Seasons.LATE_WINTER)) entriesSorted.add(fp);
//            entriesUnsorted.removeAll(entriesSorted);
//
//            return sort.equals(Sort.SEASON_UP) ? entriesSorted : entriesSorted.reversed();
//        }

        return entriesToSort;
    }

    public FishingGuideScreen()
    {
        super(Component.empty());

        //get all items in bobbers/hooks/baits tags
        BuiltInRegistries.ITEM.getTag(SCTags.BOBBERS).ifPresent(o -> o.stream().forEach(i -> hooksAndBobbers.add(i.value().getDefaultInstance())));
        BuiltInRegistries.ITEM.getTag(SCTags.HOOKS).ifPresent(o -> o.stream().forEach(i -> hooksAndBobbers.add(i.value().getDefaultInstance())));
        BuiltInRegistries.ITEM.getTag(SCTags.BAITS).ifPresent(o -> o.stream().forEach(i -> baits.add(i.value().getDefaultInstance())));
        BuiltInRegistries.ITEM.getTag(SCTags.GADGETS).ifPresent(o -> o.stream().forEach(i -> gadgets.add(i.value().getDefaultInstance())));
        BuiltInRegistries.ITEM.getTag(SCTags.TEMPLATES).ifPresent(o -> o.stream().forEach(i -> templates.add(i.value().getDefaultInstance())));
        BuiltInRegistries.ITEM.getTag(SCTags.EQUIPMENTS).ifPresent(o -> o.stream().forEach(i -> equipments.add(i.value().getDefaultInstance())));

        //index
        basicsIndexIcon = new ItemStack(SCItems.ROD.get());
        upgradeIndexIcon = new ItemStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        addonsIndexIcon = new ItemStack(SCItems.HOOK.get());
        cosmeticsIndexIcon = new ItemStack(SCItems.PEARL_SMITHING_TEMPLATE.get());
        tournamentIndexIcon = new ItemStack(SCBlocks.STAND.get());
        trophiesIndexIcon = new ItemStack(SCBlocks.TROPHY_GOLD.get());
        settingsIndexIcon = new ItemStack(SCItems.SETTINGS.get());
        indexEntries = new ArrayList<>(List.of(
                Pair.of(basicsIndexIcon, "gui.guide.index.basics"),
                Pair.of(upgradeIndexIcon, "gui.guide.index.upgrades"),
                Pair.of(addonsIndexIcon, "gui.guide.index.addons"),
                Pair.of(cosmeticsIndexIcon, "gui.guide.index.cosmetics"),
                Pair.of(tournamentIndexIcon, "gui.guide.index.tournament"),
                Pair.of(trophiesIndexIcon, "gui.guide.index.trophies"),
                Pair.of(settingsIndexIcon, "gui.guide.index.settings")
        ));

        //other items
        sweetspotsIcon = new ItemStack(SCItems.AURORA.get());
        treasureIcon = new ItemStack(SCItems.WATERLOGGED_SATCHEL.get());
        equipmentIcon = new ItemStack(SCBlocks.FISHERMAN_HAT_GREEN.get());
        cosmeticsIcon = new ItemStack(SCItems.AZURE_CRYSTAL_ROD.get());
        gadgetsIcon = new ItemStack(SCItems.FISH_RADAR.get());

        hookIcon = new ItemStack(SCItems.HOOK.get());
        baitIcon = new ItemStack(SCItems.CHERRY_BAIT.get());
        secretsIcon = new ItemStack(SCItems.HOPEFUL_BOTTLE.get());

        templateIcon = new ItemStack(SCItems.PEARL_SMITHING_TEMPLATE.get());
    }


    public static void renderFishEntryPage(GuiGraphics guiGraphics, FishProperties fp, ItemStack fishToDisplay,
                                           FishCaughtCounter fcc, int x, int y, int absoluteMouseX, int absoluteMouseY)
    {
        Level level = Minecraft.getInstance().level;
        Font font = Minecraft.getInstance().font;

        int mouseX = absoluteMouseX - x;
        int mouseY = absoluteMouseY - y;

//        if (false && U.r.nextFloat() > 0.99f)
//        {
//            System.out.println("------");
//            System.out.println(mouseX);
//            System.out.println(mouseY);
//            System.out.println(x);
//            System.out.println(y);
//        }

        //render caught:
        //caught:
        guiGraphics.drawString(
                font, Component.translatable("gui.guide.caught"),
                x + 73, y + 64, 0x9c897c, false);

        //render caught count
        if (fcc == null)
        {
            //------
            guiGraphics.drawString(
                    font, Component.translatable("gui.guide.not_caught"),
                    x + 73, y + 73, 0x9c897c, false);
        }
        else
        {
            //[324]
            Component c = Component.literal("[" + fcc.count() + "]").withStyle(Style.EMPTY.withColor(0x635040));
            guiGraphics.drawString(font, Component.empty().append(c), x + 73, y + 73, 0, false);
        }

        //render rarity (always shown)
        //rarity:
        guiGraphics.drawString(
                font, Component.translatable("gui.guide.rarity"),
                x + 73, y + 84, 0x9c897c, false);
        //common
        guiGraphics.drawString(
                font, Component.translatable("gui.guide.rarity." + fp.rarity().getSerializedName()),
                x + 73, y + 93, 0, false);

        //render bucketable
        if (!fp.catchInfo().bucketedFish().is(SCItems.MISSINGNO))
        {
            guiGraphics.blit(BUCKET, x + 77, y + 103, 0, 0, 14, 14, 14, 14);
            if (mouseX > 75 && mouseX < 93 && mouseY > 105 && mouseY < 115)
                guiGraphics.renderTooltip(font, Component.translatable("gui.guide.bucketable"), absoluteMouseX, absoluteMouseY);
        }

        //render almighty wormable
        if ((!fp.catchInfo().entityToSpawn().equals(U.holderEntity("starcatcher", "fish")) && !fp.catchInfo().alwaysSpawnEntity())
                || (fp.catchInfo().entityToSpawn().equals(U.holderEntity("starcatcher", "fish")) && fp.catchInfo().fish().is(SCTags.BUCKETABLE_FISHES)))
        {
            guiGraphics.blit(ENTITY, x + 93, y + 103, 0, 0, 14, 14, 14, 14);
            if (mouseX > 92 && mouseX < 107 && mouseY > 105 && mouseY < 115)
                guiGraphics.renderTooltip(font, Component.translatable("gui.guide.entity"), absoluteMouseX, absoluteMouseY);
        }

        //render sword
        if (fp.catchInfo().alwaysSpawnEntity())
        {
            guiGraphics.blit(ALWAYS_ENTITY, x + 93, y + 103, 0, 0, 14, 14, 14, 14);
            if (mouseX > 92 && mouseX < 107 && mouseY > 105 && mouseY < 115)
                guiGraphics.renderTooltip(font, Component.translatable("gui.guide.always_entity"), absoluteMouseX, absoluteMouseY);
        }

        if (fishToDisplay != ItemStack.EMPTY)
        {
            MutableComponent compName = Component.translatable(fp.catchInfo().fish().value().getDescriptionId());
            if (fp.catchInfo().alwaysSpawnEntity())
                compName = Component.translatable("entity." + fp.catchInfo().entityToSpawn().getRegisteredName().replace(":", "."));
            renderItem(fishToDisplay, x + 26, y + 70);
            guiGraphics.drawString(font, compName, x + 30, y + 36, 0x635040, false);
        }

        int color = switch (fp.rarity())
        {
            case FishProperties.Rarity.TRASH, FishProperties.Rarity.COMMON -> FastColor.ARGB32.color(0, -1);
            case FishProperties.Rarity.UNCOMMON -> FastColor.ARGB32.color(200, 0x92f28d);
            case FishProperties.Rarity.RARE -> FastColor.ARGB32.color(200, 0x78c8ff);
            case FishProperties.Rarity.EPIC -> FastColor.ARGB32.color(200, 0xc060ff);
            case FishProperties.Rarity.LEGENDARY, GOLDEN ->
                    FastColor.ARGB32.color(175, Color.HSBtoRGB((float) Util.getMillis() / 1000, 1, 1));
        };

        float red = FastColor.ARGB32.red(color) / 255f;
        float green = FastColor.ARGB32.green(color) / 255f;
        float blue = FastColor.ARGB32.blue(color) / 255f;
        float alpha = FastColor.ARGB32.alpha(color) / 255f;

        guiGraphics.setColor(red, green, blue, alpha);

        //render glow
        RenderSystem.enableBlend();
        if (fcc != null) guiGraphics.blit(
                GLOW, x + 10, y + 55,
                0, 0, 48, 48, 48, 48);
        RenderSystem.disableBlend();
        guiGraphics.setColor(1, 1, 1, 1);

        //render new fish icon
        if (fcc != null && fcc.hasGuideNotification())
            guiGraphics.blit(NEW_FISH, x + 120, y + 95, 0, 0, 32, 32, 32, 32);

        //render fish tooltip
        if (mouseX > 6 && mouseX < 61 && mouseY > 51 && mouseY < 105)
        {
            //if entity, display entity name
            if (fp.catchInfo().alwaysSpawnEntity() && (fcc != null || !Config.HIDE_ENTRIES_UNTIL_FOUND.get()))
                guiGraphics.renderTooltip(font, Component.translatable("entity." + fp.catchInfo().entityToSpawn().getRegisteredName().replace(":", ".")), absoluteMouseX, absoluteMouseY);
                //else display itemstack name if not empty
            else if (fishToDisplay != ItemStack.EMPTY)
                guiGraphics.renderTooltip(font, fishToDisplay, absoluteMouseX, absoluteMouseY);
        }

        int yOffset = y + 132;
        int counter = 0;


        //render restrictions
        for (var restriction : fp.restrictions())
        {
            if (!restriction.isEnabled()) continue;
            counter++;
            if (counter > 6) break;
            //todo make system to allow scrolling
            boolean hoveringMain = mouseX > 0 && mouseX < 126 && mouseY > yOffset - y - 2 && mouseY < yOffset - y - 2 + 12;
            boolean hoveringBlacklist = mouseX > 128 && mouseX < 139 && mouseY > yOffset - y - 2 && mouseY < yOffset - y - 2 + 12;

            Component description = restriction.getDescription(level, fp, Minecraft.getInstance().player, AbstractFishRestriction.Context.GUIDE_ENTRY);
            List<Component> hover = restriction.getHover(level, fp, Minecraft.getInstance().player, AbstractFishRestriction.Context.GUIDE_ENTRY);
            List<Component> blacklist = restriction.getBlacklist(level, fp, Minecraft.getInstance().player, AbstractFishRestriction.Context.GUIDE_ENTRY);

            renderScrollingString(guiGraphics, font, description, x, x, yOffset - 2, x + 128, yOffset + 10, hoveringMain);

            //if has hover and cursor is hovering
            if (!hover.isEmpty() && hoveringMain)
            {
                guiGraphics.renderTooltip(font, hover, Optional.empty(), absoluteMouseX, absoluteMouseY);
            }

            //if blacklist then render [!]
            if (!blacklist.isEmpty())
            {
                guiGraphics.drawString(font, "[!]", x + 129, yOffset, SCColors.GUIDE_RED, false);
                if (hoveringBlacklist)
                    guiGraphics.renderTooltip(font, blacklist, Optional.empty(), absoluteMouseX, absoluteMouseY);
            }

            yOffset += 12;
        }

        //render stats tooltip (at the end because of the scisor bug)
        if (mouseX > 70 && mouseX < 132 && mouseY > 62 && mouseY < 100 && fcc != null)
        {
            List<Component> components = new ArrayList<>();
            float averageTicks = (int) ((fcc.averageTicks() / 20) * 100) / 100.0f;

            SettingsScreen.Units unit = Config.UNIT.get();
            String size = unit.getSizeAsString(fcc.size());
            String weight = unit.getWeightAsString(fcc.weight());

            //format first catch
            Instant instant = Instant.ofEpochSecond(fcc.firstCatch());
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:ma");
            String formatted = zdt.format(formatter);
            String formatted2 = zdt.format(formatter2);

            components.add(Component.translatable("gui.guide.first"));
            components.add(Component.literal(formatted).withStyle(ChatFormatting.BOLD));
            components.add(Component.literal(formatted2).withStyle(ChatFormatting.BOLD));
            components.add(Component.literal(""));
            components.add(Component.translatable("gui.guide.fastest").append(Component.literal((((float) fcc.fastestTicks()) / 20) + "s").withStyle(ChatFormatting.BOLD)));
            components.add(Component.translatable("gui.guide.average").append(Component.literal(averageTicks + "s").withStyle(ChatFormatting.BOLD)));
            components.add(Component.literal(""));
            components.add(Component.translatable("gui.guide.biggest").append(Component.literal(size).withStyle(ChatFormatting.BOLD)));
            components.add(Component.translatable("gui.guide.heaviest").append(Component.literal(weight).withStyle(ChatFormatting.BOLD)));

            guiGraphics.renderTooltip(font, components, Optional.empty(), absoluteMouseX, absoluteMouseY);
        }

    }

    public static void renderScrollingString(GuiGraphics guiGraphics, Font font, Component text, int centerX, int minX, int minY, int maxX, int maxY, boolean hovering)
    {
        int i = font.width(text);
        int j = (minY + maxY - 9) / 2 + 1;
        int k = maxX - minX;
        if (i > k)
        {
            int l = i - k;
            double d0 = (double) Util.getMillis() / (double) 300.0F;
            double d1 = Math.max((double) l * (double) 0.5F, 3.0F);
            double d2 = Math.sin((Math.PI / 2D) * Math.cos((Math.PI * 2D) * d0 / d1)) / (double) 2.0F + (double) 0.5F;
            double d3 = Mth.lerp(d2, 0.0F, l);
            guiGraphics.enableScissor(minX, minY, maxX, maxY);
            int x = minX - (int) d3;
            if(!hovering) x = minX;
            guiGraphics.drawString(font, text, x, j, SCColors.GUIDE_TEXT_DARK, false);
            guiGraphics.disableScissor();
        }
        else
        {
            int i1 = Mth.clamp(centerX, minX + i / 2, maxX - i / 2);
            guiGraphics.drawString(font, text.getVisualOrderText(), i1 - font.width(text.getVisualOrderText()) / 2, j, SCColors.GUIDE_TEXT_DARK, false);
        }
    }
}
