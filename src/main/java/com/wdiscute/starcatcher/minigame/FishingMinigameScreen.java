package com.wdiscute.starcatcher.minigame;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.mojang.math.Axis;
import com.wdiscute.starcatcher.*;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.modifiers.Modifier;
import com.wdiscute.starcatcher.registry.SCDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.io.network.FishingCompletedPayload;
import com.wdiscute.starcatcher.registry.SCAttributes;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.BaseMinigameModifier;
import com.wdiscute.starcatcher.registry.SCKeymappings;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.AbstractMinigameModifier;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.SCMinigameModifiers;
import com.wdiscute.starcatcher.registry.tackleskin.AbstractTackleSkin;
import com.wdiscute.starcatcher.registry.tackleskin.BaseTackleSkin;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.PacketDistributor;
import org.joml.Quaternionf;
import org.joml.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class FishingMinigameScreen extends Screen implements GuiEventListener
{
    public ResourceLocation texture;

    public Difficulty difficulty;
    public Rarity rarity;

    public final ItemStack itemBeingFished;
    public final ItemStack bobber;
    public final ItemStack bait;
    public final ItemStack hook;
    public final ItemStack treasureIS;

    public final AbstractTackleSkin tackleSkin;

    public final InteractionHand handToSwing;

    public int hp;
    public int penalty;
    public float decay;

    public float kimbeMarkerPos = 0;
    public float kimbeMarkerAlpha = 0;
    public int kimbeMarkerColor = 0x00ff00;

    public int gracePeriod = Integer.MAX_VALUE;

    public float pointerSpeed;
    public float pointerBaseSpeed;

    public int tickCount = 0;
    public float pointerPos = 0;
    public int currentRotation = 1;
    public float partial;
    public float hitDelay;

    public float progress = 20;
    public float progressSmooth = 20;

    public boolean perfectCatch = true;
    public int consecutiveHits = 0;

    public boolean treasureActive;
    public int treasureProgress = 0;
    public int treasureProgressSmooth = 0;

    List<HitFakeParticle> hitParticles = new ArrayList<>();


    // Nikdo53 fields, these are mine dont steal them
    public final int holdingDelay = 6;
    public int holdingTicks = 0;
    protected boolean isHoldingKey = false;
    protected boolean isHoldingMouse = false;

    public float renderScale;
    public int xOffset = SCConfig.MINIGAME_X_OFFSET.getAsInt();
    public int yOffset = SCConfig.MINIGAME_Y_OFFSET.getAsInt();


    protected final List<ActiveSweetSpot> activeSweetSpots = new ArrayList<>();
    protected final List<ActiveSweetSpot> spotsToAdd = new ArrayList<>(); // delays the adding process to avoid concurrency exceptions

    protected final List<AbstractMinigameModifier> modifiers = new ArrayList<>();
    protected final List<AbstractMinigameModifier> modifiersToAdd = new ArrayList<>(); // delays the adding process to avoid concurrency exceptions

    public FishingMinigameScreen(FishProperties fp, ItemStack treasure, ItemStack rod)
    {
        super(Component.empty());

        handToSwing = Minecraft.getInstance().player.getMainHandItem().is(SCTags.RODS) ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;

        texture = fp.textures();

        renderScale = SCConfig.MINIGAME_RENDER_SCALE.get().floatValue();
        hitDelay = SCConfig.HIT_DELAY.get().floatValue();

        this.difficulty = fp.dif();
        this.rarity = fp.rarity();

        //set treasure being fished
        this.treasureIS = treasure;

        //set item being fished
        if (fp.catchInfo().overrideMinigameWith().isEmpty())
            this.itemBeingFished = fp.catchInfo().fish().toStack();
        else
            this.itemBeingFished = fp.catchInfo().overrideMinigameWith().toStack();

        this.bobber = SCDataComponents.getOrDefault(rod, SCDataComponents.BOBBER, SingleStackContainer.empty()).stack();
        this.bait = SCDataComponents.getOrDefault(rod, SCDataComponents.BAIT, SingleStackContainer.empty()).stack();
        this.hook = SCDataComponents.getOrDefault(rod, SCDataComponents.HOOK, SingleStackContainer.empty()).stack();


        if (SCDataComponents.has(rod, SCDataComponents.TACKLE_SKIN))
        {
            ResourceLocation rl = SCDataComponents.get(rod, SCDataComponents.TACKLE_SKIN);

            Optional<AbstractTackleSkin> optional = Minecraft.getInstance().level.registryAccess().registryOrThrow(Starcatcher.TACKLE_SKIN).getOptional(rl);
            this.tackleSkin = optional.orElseGet(BaseTackleSkin::new);
        }
        else
        {
            this.tackleSkin = new BaseTackleSkin();
        }


        //tank texture change
        Player player = Minecraft.getInstance().player;

        //base - a lot of these are now hitZone-based
        this.pointerSpeed = (float) (difficulty.speed() * player.getAttributeValue(SCAttributes.HANDLE_ROTATION_SPEED_MULTIPLIER) * SCConfig.POINTER_SPEED_MULTIPLIER.get());
        this.pointerBaseSpeed = (float) (difficulty.speed() * player.getAttributeValue(SCAttributes.HANDLE_ROTATION_SPEED_MULTIPLIER) * SCConfig.POINTER_SPEED_MULTIPLIER.get());
        this.penalty = (int) (difficulty.penalty() * player.getAttributeValue(SCAttributes.PENALTY_MULTIPLIER) * SCConfig.PENALTY_MULTIPLIER.get());
        this.decay = (float) (difficulty.decay() * player.getAttributeValue(SCAttributes.BASE_DECAY_MULTIPLIER) * SCConfig.DECAY_RATE_MULTIPLIER.get());
        this.hp = (int) (difficulty.hp() * player.getAttributeValue(SCAttributes.REQUIRED_SCORE_MULTIPLIER) * SCConfig.HP_RATE_MULTIPLIER.get());

        //add base modifier for kimbe before other modifiers so they can override kimbe if needed
        addModifier(new BaseMinigameModifier(""));

        //add every modifier from fp json which is registered
        for (Modifier mod : fp.dif().modifiers())
        {
            if (mod instanceof AbstractMinigameModifier amm)
                addModifier(amm);
        }

        //add modifiers in armor/curios/rod
        modifiersToAdd.addAll(Modifier.getMinigameModifiers(player));

        //add every sweet spot from fp Json which is registered
        for (Difficulty.SweetSpot ss : fp.dif().sweetSpots())
        {
            var newSweetSpot = new ActiveSweetSpot(this, ss, bobber, bait, hook);
            addSweetSpot(newSweetSpot);
        }
    }

    public List<ActiveSweetSpot> getActiveSweetSpots()
    {
        return activeSweetSpots;
    }

    public void addModifier(AbstractMinigameModifier mod)
    {
        if (!mod.removed) this.modifiersToAdd.add(mod);
    }

    public void addUniqueModifier(AbstractMinigameModifier mod)
    {
        //only adds if there's not a modifier of the same class already present
        if (modifiers.stream().noneMatch(m -> m.getClass() == mod.getClass()))
        {
            if (!mod.removed) this.modifiersToAdd.add(mod);
        }
    }

    public void addSweetSpot(ActiveSweetSpot ass)
    {
        if (!ass.removed) this.spotsToAdd.add(ass);
    }

    public int getRandomFreePosition(int sizeOfTheSweetspotToPlace)
    {
        int posBeingChecked = U.r.nextInt(360);

        //find the closest available pos
        for (int i = 0; i < 180; i++)
        {

            //check left and right
            for (int j = 1; j > -2; j -= 2)
            {

                int checkPos = clampPos(posBeingChecked + (i * j));

                if (activeSweetSpots.stream().noneMatch(s -> doDegreesOverlapWithLeeway(checkPos, s.pos, (s.thickness + sizeOfTheSweetspotToPlace) / 2)
                ))
                {
                    return checkPos;
                }
            }
        }

        LogUtils.getLogger().warn("Starcatcher's minigame couldn't find a non-overlapping free position! Consider not having so many active sweet spots");
        return U.r.nextInt(360);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTickNeo)
    {
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTickNeo);

        final float partialTick = PartialTickHelper.INSTANCE.getPartialTicks(minecraft.level);

        PoseStack poseStack = guiGraphics.pose();
        partial = partialTick;

        poseStack.pushPose();
        poseStack.translate(width >> 1, height >> 1, 0);

        poseStack.translate(xOffset, yOffset, 0);

        poseStack.scale(renderScale, renderScale, 1);

        poseStack.translate(-width >> 1, -height >> 1, 0);

        //render modifiers background
        modifiers.forEach(modifier -> modifier.renderBackground(guiGraphics, partialTick, width, height));

        if (treasureActive) renderTreasure(guiGraphics);

        int centerX = width / 2;
        int centerY = height / 2;

        //render tank background
        guiGraphics.blit(texture, centerX - 44 - 100, centerY - 48,
                85, 97, 0, 112, 96, 112, 256, 256);

        //render wheel background
        guiGraphics.blit(texture, centerX - 32, centerY - 32,
                64, 64, 96, 112, 64, 64, 256, 256);

        //render spacebar
        Component displayName = SCKeymappings.MINIGAME_HIT.getKey().getDisplayName();
        String string = displayName.getString();

        guiGraphics.blit(texture, centerX - 24, centerY + 40,
                48, 16,
                96, (isHoldingKey ? 16 : 0) + (string.length() < 3 ? 32 : 0),
                48, 16,
                256, 256);

        //render spacebar keybind
        renderCenteredString(guiGraphics, font, displayName,
                centerX, centerY + 42 + (isHoldingKey ? 2 : 0), 0xff546170, false);

        //render all sweet spots
        activeSweetSpots.forEach(ass -> renderSweetSpot(ass, guiGraphics, partialTick, poseStack));

        //render wheel second layer
        guiGraphics.blit(texture, centerX - 32, centerY - 32,
                64, 64, 160, 112, 64, 64, 256, 256);

        //render pointer
        renderPointer(guiGraphics, poseStack, partialTick);

        //render kimbe marker
        renderKimbeMarker(guiGraphics);

        //silver thing on top
        guiGraphics.blit(texture, centerX - 16, centerY - 16,
                32, 32, 224, 128, 32, 32, 256, 256);

        //fishing rod
        guiGraphics.blit(texture, centerX - 32 - 70, centerY - 24 - 57,
                64, 48, 192, 0, 64, 48, 256, 256);

        float yoffset = progressSmooth == 0 ? 0 : (progressSmooth / (float) hp * 77);

        //fishing line
        guiGraphics.blit(
                texture, centerX - 6 - 102, centerY - 56 - 18,
                16, (int) (112 - yoffset),
                176F, yoffset,
                16, (int) (112 - yoffset),
                256, 256);

        //item being fished
        poseStack.pushPose();
        poseStack.translate(0, -yoffset, 0);
        guiGraphics.renderItem(itemBeingFished, centerX - 8 - 100, centerY - 8 + 35);
        poseStack.popPose();

        //render sweet spots foreground
        activeSweetSpots.forEach(sweetspot -> sweetspot.behaviour.renderForeground(guiGraphics, partialTick, width, height));

        //render modifiers foreground
        modifiers.forEach(modifier -> modifier.renderForeground(guiGraphics, partialTick, width, height));

        //render particles
        hitParticles.forEach(p -> p.render(guiGraphics, width, height));

        poseStack.popPose();
    }

    public void renderSweetSpot(ActiveSweetSpot ass, GuiGraphics guiGraphics, float partialTick, PoseStack poseStack)
    {
        float centerX = (float) width / 2;
        float centerY = (float) height / 2;

        poseStack.pushPose();

        poseStack.translate(centerX, centerY, 0);

        poseStack.rotateAround(Axis.ZP.rotationDegrees(ass.pos + (partialTick * ass.movingRate) * ass.currentRotation), 0, 0, 0);

        boolean isDisabled = modifiers.stream().anyMatch(mod -> mod.disableSweetSpotRendering(ass));
        if (!isDisabled)
            ass.behaviour.render(guiGraphics, poseStack, partialTick);

        modifiers.forEach(mod -> mod.renderOnSweetSpot(guiGraphics, poseStack, ass, partialTick));

        poseStack.popPose();
    }

    public void renderTreasure(GuiGraphics guiGraphics)
    {
        int centerX = width / 2;
        int centerY = height / 2;


        int barSize = Math.min(64, (64 * treasureProgressSmooth) / 100);

        //treasure bar
        guiGraphics.blit(texture, centerX - 3 - 155, centerY + 22 - barSize,
                5, barSize,
                77, 6,
                5, barSize,
                256, 256);

        //treasure bar outline + chest at bottom
        guiGraphics.blit(texture, centerX - 16 - 155, centerY - 48,
                32, 96,
                32, 0,
                32, 96,
                256, 256);

        //render treasure on top of bar
        guiGraphics.renderItem(treasureIS,
                centerX - 163,
                centerY - barSize + 16);

        //outline when treasure complete
        if (treasureProgress > 99)
            guiGraphics.blit(texture, centerX - 16 - 155, centerY - 48,
                    32, 96,
                    0, 0,
                    32, 96,
                    256, 256);

    }

    public void renderKimbeMarker(GuiGraphics guiGraphics)
    {
        if (modifiers.stream().anyMatch(AbstractMinigameModifier::skipRenderingKimbeMarker)) return;
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();

        float centerX = (float) width / 2;
        float centerY = (float) height / 2;

        poseStack.translate(centerX, centerY, 0);
        poseStack.mulPose(new Quaternionf().rotateZ((float) Math.toRadians(kimbeMarkerPos)));
        poseStack.translate(-centerX, -centerY, 0);

        RenderSystem.setShaderColor(
                (float) U.intToRed(kimbeMarkerColor) / 255,
                (float) U.intToGreen(kimbeMarkerColor) / 255,
                (float) U.intToBlue(kimbeMarkerColor) / 255,
                kimbeMarkerAlpha);
        RenderSystem.enableBlend();

        guiGraphics.renderOutline((int) centerX, (int) centerY - 34, 2, 34, 0xffffffff);

        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.disableBlend();

        poseStack.popPose();
    }

    public void renderPointer(GuiGraphics guiGraphics, PoseStack poseStack, float partialTick)
    {
        poseStack.pushPose();

        float centerX = (float) width / 2;
        float centerY = (float) height / 2;

        poseStack.translate(centerX, centerY, 0);

        poseStack.mulPose(Axis.ZP.rotationDegrees(pointerPos + ((pointerSpeed * partialTick) * currentRotation)));

        poseStack.translate(0, -16, 0);

        boolean isDisabled = modifiers.stream().anyMatch(AbstractMinigameModifier::disablePointerRendering);
        if (!isDisabled)
            renderPoseCentered(guiGraphics, texture, 32, 64, 144, 0, 256);

        poseStack.translate(0, 16, 0);

        modifiers.forEach(mod -> mod.renderOnPointer(guiGraphics, poseStack, partialTick));

        poseStack.popPose();
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int keyModifiers)
    {
        if (keyCode == SCKeymappings.MINIGAME_HIT.getKey().getValue())
        {
            isHoldingKey = false;
            holdingTicks = 0;
        }

        modifiers.forEach(mod -> mod.onKeyReleased(keyCode, scanCode, keyModifiers));

        return super.keyReleased(keyCode, scanCode, keyModifiers);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button)
    {
        isHoldingMouse = false;
        holdingTicks = 0;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        inputPressed();
        isHoldingMouse = true;
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int keyModifiers)
    {
        //closes when pressing E unless keybind is set to E
        InputConstants.Key mouseKey = InputConstants.getKey(keyCode, scanCode);
        if (this.minecraft.options.keyInventory.isActiveAndMatches(mouseKey) && !SCKeymappings.MINIGAME_HIT.getKey().equals(mouseKey))
        {
            if (SCConfig.ENABLE_VILLAGER_SOUND.get()
                && modifiers.stream().noneMatch(AbstractMinigameModifier::skipMissSound)
                && !tackleSkin.skipMissSound()
            )
                Minecraft.getInstance().player.playSound(SoundEvents.VILLAGER_NO);
            this.onClose();
            return true;
        }

        //hit input
        if (SCKeymappings.MINIGAME_HIT.isActiveAndMatches(mouseKey))
        {
            if (!isHoldingKey) inputPressed();

            isHoldingKey = true;
        }

        this.modifiers.forEach(mod -> mod.onKeyPress(keyCode, scanCode, keyModifiers));

        return super.keyPressed(keyCode, scanCode, keyModifiers);
    }

    public void inputPressed()
    {
        if (gracePeriod > 0) gracePeriod = 0;

        Minecraft.getInstance().player.swing(handToSwing, true);

        boolean hitSomething = false;

        Vec3 pos = Minecraft.getInstance().player.position();
        ClientLevel level = Minecraft.getInstance().level;

        kimbeMarkerPos = getPointerPosPrecise();


        for (ActiveSweetSpot ass : activeSweetSpots)
        {
            if (doDegreesOverlapWithLeeway(getPointerPosPrecise(), ass.pos, ass.thickness / 2))
            {

                //check if each modifier allows the hit to register
                boolean isCanceled = false;
                for (AbstractMinigameModifier modifier : modifiers)
                {

                    if (modifier.onHit(ass))
                        isCanceled = true;
                }

                if (isCanceled) continue;

                hitSomething = true;
                ass.behaviour.onHit();
                break;
            }
        }


        if (!hitSomething)
        {
            this.modifiers.forEach(AbstractMinigameModifier::onMiss);

            consecutiveHits = 0;
            if (SCConfig.ENABLE_MISS_SOUND.get())
                level.playLocalSound(pos.x, pos.y, pos.z, SoundEvents.COMPARATOR_CLICK, SoundSource.BLOCKS, 1, 1, false);
            progress -= penalty;
        }
    }

    public float getPointerPosPrecise()
    {
        float pointerPosPrecise = (pointerPos + ((pointerSpeed * partial) * currentRotation));

        pointerPosPrecise += hitDelay * pointerSpeed * currentRotation;
        return pointerPosPrecise;
    }

    public static boolean doDegreesOverlapWithLeeway(float degrees1, float degrees2, int leeway)
    {
        boolean b = Math.abs(degrees2 - degrees1) < (float) leeway;
        boolean b2 = Math.abs(degrees2 - degrees1) > 360 - ((float) leeway);
        return b || b2;
    }

    @Override
    public void tick()
    {
        if (isHoldingInput())
        { //mimics the keyboard behavior
            holdingTicks++;
            if (holdingTicks > holdingDelay) inputPressed();
        }

        //tick modifiers
        modifiers.forEach(AbstractMinigameModifier::tick);
        //tick behaviour
        activeSweetSpots.forEach(s -> s.behaviour.tick());

        //remove activeSweetSpots marked for removal
        activeSweetSpots.removeIf(s ->
        {
            if (s.removed) s.behaviour.onRemove();
            return s.removed;
        });

        //remove modifiers marked for removal
        modifiers.removeIf(m ->
        {
            if (m.removed) m.onRemove();
            return m.removed;
        });

        //add queued modifiers
        modifiers.addAll(modifiersToAdd);
        modifiersToAdd.forEach(o -> o.onAdd(this));
        modifiersToAdd.clear();

        //add queued sweetspots
        activeSweetSpots.addAll(spotsToAdd);
        //trigger onAdd of SweetSpotBehaviour
        spotsToAdd.forEach(o -> o.behaviour.onAdd(this, o));
        //trigger onSpotAdded of modifiers
        spotsToAdd.forEach(s -> modifiers.forEach(m -> m.onSpotAdded(s)));
        spotsToAdd.clear();

        //move pointer
        pointerPos += pointerSpeed * currentRotation;

        //decrease kimbe markers alpha
        kimbeMarkerAlpha -= 0.1f;

        if (pointerPos > 360) pointerPos -= 360;
        if (pointerPos < 0) pointerPos += 360;

        gracePeriod--;

        tickCount++;

        progressSmooth += ((progress - progressSmooth) / 6);

        treasureProgressSmooth += (int) Math.signum(treasureProgress - treasureProgressSmooth);

        if (tickCount % 5 == 0 && gracePeriod < 0)
        {
            progress -= decay;
        }

        if (!isSettingsScreen())
        {

            if (progressSmooth < 0)
            {
                if (SCConfig.ENABLE_VILLAGER_SOUND.get()
                    && modifiers.stream().noneMatch(AbstractMinigameModifier::skipMissSound)
                    && !tackleSkin.skipMissSound()
                )
                    Minecraft.getInstance().player.playSound(SoundEvents.VILLAGER_NO);
                this.onClose();
            }

            if (progressSmooth > hp)
            {
                //if completed treasure minigame, or is a perfect catch with the mossy hook
                boolean awardTreasure = treasureProgress > 100 || modifiers.stream().anyMatch(AbstractMinigameModifier::forceAwardTreasure);

                if (SCConfig.ENABLE_VILLAGER_SOUND.get()
                    && modifiers.stream().noneMatch(AbstractMinigameModifier::skipMissSound)
                    && !tackleSkin.skipSuccessSound()
                )
                    Minecraft.getInstance().player.playSound(SoundEvents.VILLAGER_CELEBRATE);

                PacketDistributor.sendToServer(new FishingCompletedPayload(tickCount, awardTreasure, perfectCatch, consecutiveHits));
                this.onClose();
            }
        }

        hitParticles.removeIf(HitFakeParticle::tick);
    }

    @Override
    public void onClose()
    {
        modifiers.forEach(AbstractMinigameModifier::onRemove);

        PacketDistributor.sendToServer(new FishingCompletedPayload(-1, false, false, consecutiveHits));
        this.minecraft.popGuiLayer();
    }

    public void addParticles(float posInDegrees, int count, int color)
    {
        int xPos = (int) (30 * Math.cos(Math.toRadians(posInDegrees - 90)));
        int yPos = (int) (30 * Math.sin(Math.toRadians(posInDegrees - 90)));

        for (int i = 0; i < count; i++)
        {
            hitParticles.add(
                    new HitFakeParticle(
                            xPos,
                            yPos,
                            new Vector2d(U.r.nextFloat() * 2 - 1, U.r.nextFloat() * 2 - 1),
                            (float) U.intToRed(color) / 255,
                            (float) U.intToGreen(color) / 255,
                            (float) U.intToBlue(color) / 255,
                            1
                    ));
        }
    }

    /**
     * Renders a texture centered to the top left corner, to be moved with poseStack
     */
    public static void renderPoseCentered(GuiGraphics guiGraphics, ResourceLocation texture, int spriteSize)
    {
        guiGraphics.blit(
                texture, -spriteSize >> 1, -spriteSize >> 1,
                spriteSize, spriteSize, 0, 0, spriteSize, spriteSize, spriteSize, spriteSize);
    }

    public static void renderPoseCentered(GuiGraphics guiGraphics, ResourceLocation texture, int spriteWidth, int spriteHeight, int uOffset, int vOffset, int textureSize)
    {
        guiGraphics.blit(
                texture, -spriteWidth >> 1, -spriteHeight >> 1,
                spriteWidth, spriteHeight, uOffset, vOffset, spriteWidth, spriteHeight, textureSize, textureSize);
    }

    public void renderCenteredString(GuiGraphics guiGraphics, Font font, Component text, int x, int y, int color, boolean shadow)
    {
        FormattedCharSequence formattedcharsequence = text.getVisualOrderText();
        guiGraphics.drawString(font, formattedcharsequence, x - font.width(formattedcharsequence) / 2, y, color, shadow);
    }

    public boolean isHoldingInput()
    {
        return isHoldingMouse || isHoldingKey;
    }

    public static boolean hasDistantHorizons()
    {
        return ModList.get().isLoaded("distanthorizons");
    }

    public boolean isSettingsScreen()
    {
        return false;
    }

    public static int clampPos(int pos)
    {
        pos %= 360;
        if (pos < 0)
        {
            pos += 360;
        }
        return pos;
    }

    public List<AbstractMinigameModifier> getModifiers()
    {
        return modifiers;
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public void refreshSweetSpotsAlphas()
    {
        activeSweetSpots.forEach(s -> s.alpha = 1);
    }

    public void removeAllSweetSpots()
    {
        for (var dws : activeSweetSpots)
        {
            dws.removed = true;
        }
    }
}
