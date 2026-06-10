package com.wdiscute.starcatcher.bobentity;

import com.mojang.datafixers.util.Pair;
import com.wdiscute.starcatcher.SCConfig;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.FishApi;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.io.network.CBFishingStartedPayload;
import com.wdiscute.starcatcher.modifiers.Modifier;
import com.wdiscute.starcatcher.registry.*;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.AbstractCatchModifier;
import com.wdiscute.starcatcher.registry.fishrestrictions.AbstractFishRestriction;
import com.wdiscute.starcatcher.registry.tackleskin.AbstractTackleSkin;
import com.wdiscute.starcatcher.registry.tackleskin.BaseTackleSkin;
import com.wdiscute.starcatcher.registry.tackleskin.SCTackleSkins;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.network.PacketDistributor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FishingBobEntity extends Projectile
{
    private static final Logger log = LoggerFactory.getLogger(FishingBobEntity.class);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(FishingBobEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> VOID = SynchedEntityData.defineId(FishingBobEntity.class, EntityDataSerializers.BOOLEAN);

    public final Player player;
    private FishHookState currentState;
    public FishProperties fpToFish;
    public ItemStack treasure;
    public ResourceLocation rlToAwardUponFishingComplete;
    public ItemStack rod = ItemStack.EMPTY;
    public final List<AbstractCatchModifier> modifiers;

    public boolean survivesLava = false;

    public int minTicksToFish;
    public int maxTicksToFish;
    public float chanceToFishEachTick;

    public int timeBiting;

    public int ticksInFluid;

    boolean noGravity = false;

    enum FishHookState
    {
        FLYING,
        BOBBING,
        BITING,
        FISHING
    }

    //client
    public FishingBobEntity(EntityType<? extends FishingBobEntity> entityType, Level level)
    {
        super(entityType, level);
        this.player = level.isClientSide() ? PlayerGetter.getPlayer() : null;
        if (player != null)
        {
            this.modifiers = Modifier.getCatchModifiers(player);
            modifiers.forEach(acm -> acm.onAdd(this));
        }
        else
        {
            this.modifiers = new ArrayList<>();
        }
    }

    // This is how you replace the OnlyIn annotation
    public static class PlayerGetter
    {
        public static Player getPlayer()
        {
            return Minecraft.getInstance().player;
        }
    }

    //server
    public FishingBobEntity(Level level, Player player, ItemStack rod)
    {
        super(SCEntities.FISHING_BOB.get(), level);

        this.setOwner(player);
        this.player = player;
        this.rod = rod;
        this.modifiers = new ArrayList<>(Modifier.getCatchModifiers(player));

        //add fish messages modifier
        modifiers.addAll(Modifier.BASE_CATCH_MODIFIERS);

        noGravity = modifiers.stream().anyMatch(AbstractCatchModifier::noGravity);

        entityData.set(VOID, noGravity);

        survivesLava = SCDataComponents.getOrDefault(rod, SCDataComponents.NETHERITE_UPGRADE, false) || modifiers.stream().anyMatch(AbstractCatchModifier::survivesLava);

        minTicksToFish = SCConfig.BASE_MIN_TICKS_TO_FISH.getAsInt();
        maxTicksToFish = SCConfig.BASE_MAX_TICKS_TO_FISH.getAsInt();
        chanceToFishEachTick = (float) SCConfig.BASE_CHANCE_TO_FISH.getAsDouble();

        //modify base chances
        for (AbstractCatchModifier acm : modifiers)
        {
            minTicksToFish = acm.adjustMinTicksToFish(minTicksToFish);
            maxTicksToFish = acm.adjustMaxTicksToFish(maxTicksToFish);
            chanceToFishEachTick = acm.adjustChanceToFishEachTick(chanceToFishEachTick);
        }

        minTicksToFish = Math.max(1, minTicksToFish);
        maxTicksToFish = Math.max(1, maxTicksToFish);

        //trigger onBobSummon
        modifiers.forEach(acm -> acm.onAdd(this));

        float playerXRot = player.getXRot();
        float playerYRot = player.getYRot();
        float f2 = Mth.cos(-playerYRot * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = Mth.sin(-playerYRot * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -Mth.cos(-playerXRot * ((float) Math.PI / 180F));
        float f5 = Mth.sin(-playerXRot * ((float) Math.PI / 180F));
        double d0 = player.getX() - (double) f3 * 0.3;
        double d1 = player.getEyeY();
        double d2 = player.getZ() - (double) f2 * 0.3;
        this.moveTo(d0, d1, d2, playerYRot, playerXRot);
        Vec3 vec3 = new Vec3(-f3, Mth.clamp(-(f5 / f4), -5.0F, 5.0F), -f2);
        double d3 = vec3.length();
        vec3 = vec3.multiply(0.6 / d3 + this.random.triangle(0.5F, 0.0103365), 0.6 / d3 + this.random.triangle(0.5F, 0.0103365), 0.6 / d3 + this.random.triangle(0.5F, 0.0103365));

        for (AbstractCatchModifier modifier : modifiers)
        {
            vec3 = modifier.modifyThrowVec(vec3);
        }

        this.setDeltaMovement(vec3);
        this.setYRot((float) (Mth.atan2(vec3.x, vec3.z) * (double) 180.0F / (double) (float) Math.PI));
        this.setXRot((float) (Mth.atan2(vec3.y, vec3.horizontalDistance()) * (double) 180.0F / (double) (float) Math.PI));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();


        if (!level.isClientSide)
            SCDataAttachments.get(player, SCDataAttachments.FISHING_BOB).setUuid(player, this.uuid);

        currentState = FishHookState.FLYING;
    }

    public void reel()
    {
        Pair<FishProperties, ResourceLocation> fp = FishApi.getFP(this, player, modifiers, rod, true);

        if (fp == null)
        {
            kill();
            return;
        }

        fpToFish = fp.getFirst();
        rlToAwardUponFishingComplete = fp.getSecond();

        //skips minigame if (skipsminigame() or server config of minigame enabled = false) OR any modifier wants to
        if ((fpToFish.skipMinigame() || !SCConfig.ENABLE_MINIGAME.get())
            || modifiers.stream().anyMatch(m -> m.forceSkipMinigame(SCConfig.ENABLE_MINIGAME.get())))
        {
            FishApi.spawnFishFromPlayerFishing(((ServerPlayer) player), 0, false, false, 0);
        }
        else
        {
            //load treasure itemstack
            treasure = FishApi.getTreasure(((ServerPlayer) player), fpToFish);

            //trigger modifiers to modify treasure fished
            for (AbstractCatchModifier modifier : modifiers)
                treasure = modifier.modifyTreasure(treasure, fpToFish, player, this);

            //should hide catch from config or modifiers
            boolean shouldHideCatch = SCConfig.HIDE_CATCHES.get() || modifiers.stream().anyMatch(AbstractCatchModifier::shouldHideCatch);

            //create payload
            CBFishingStartedPayload payload = new CBFishingStartedPayload(
                    //hide catch
                    shouldHideCatch ? fpToFish.withCatchInfo(fpToFish.catchInfo().withFish(new MaybeStack(SCItems.UNKNOWN_FISH))) : fpToFish,
                    //hide treasure
                    SCConfig.HIDE_TREASURES.get() ? new MaybeStack(SCItems.UNKNOWN_FISH.toStack()) : new MaybeStack(treasure),
                    new MaybeStack(rod));

            //send payload
            PacketDistributor.sendToPlayer(((ServerPlayer) player), payload);
        }
    }

    private boolean shouldStopFishing(Player player)
    {
        if (level().isClientSide) return false;

        //if any modifier wants to stop fishing
        if (modifiers.stream().anyMatch(AbstractCatchModifier::shouldStopFishing)) return true;

        boolean holdingRod = player.getMainHandItem().is(SCTags.RODS)
                             || player.getOffhandItem().is(SCTags.RODS);

        if (!player.isRemoved() && player.isAlive() && holdingRod && !(this.distanceToSqr(player) > 7024))
        {
            return false;
        }
        else
        {
            this.kill();
            return true;
        }
    }

    @Override
    public boolean fireImmune()
    {
        return survivesLava;
    }

    @Override
    public void lavaHurt()
    {
        super.lavaHurt();
        if (!survivesLava && !level().isClientSide)
        {
            kill();
        }
    }

    @Override
    public void kill()
    {
        SCDataAttachments.remove(player, SCDataAttachments.FISHING_BOB);
        super.kill();
    }

    @Override
    public void tick()
    {
        super.tick();

        noGravity = entityData.get(VOID);

        if (!level().isClientSide)
        {
            if (currentState == FishHookState.FLYING) entityData.set(STATE, 1);
            if (currentState == FishHookState.BOBBING) entityData.set(STATE, 2);
            if (currentState == FishHookState.BITING) entityData.set(STATE, 3);
            if (currentState == FishHookState.FISHING) entityData.set(STATE, 4);
        }
        else
        {
            if (entityData.get(STATE) == 1) currentState = FishHookState.FLYING;
            if (entityData.get(STATE) == 2) currentState = FishHookState.BOBBING;
            if (entityData.get(STATE) == 3) currentState = FishHookState.BITING;
            if (entityData.get(STATE) == 4) currentState = FishHookState.FISHING;
        }

        Player player = ((Player) this.getOwner());
        if (player == null || this.shouldStopFishing(player))
        {
            this.discard();
            if (player != null) SCDataAttachments.remove(player, SCDataAttachments.FISHING_BOB);
        }

        BlockPos blockpos = new BlockPos(new Vec3i((int) position().x, (int) (position().y + 0.175f), (int) position().z));
        FluidState fluid = this.level().getFluidState(blockpos);
        FluidState fluidBellow = this.level().getFluidState(blockpos.below());

        if (this.currentState == FishHookState.FLYING)
        {

            if (!noGravity)
                if (getDeltaMovement().y < 1.2f)
                    this.setDeltaMovement(this.getDeltaMovement().add(0, -0.02, 0));

            if (!fluid.isEmpty() || (noGravity && tickCount > 50))
            {
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.3, 0.3, 0.3));
                if (!level().isClientSide) this.currentState = FishHookState.BOBBING;
                return;
            }
        }

        if (this.currentState == FishHookState.BITING)
        {
            timeBiting++;
            setDeltaMovement(Vec3.ZERO);
            for (int i = 0; i < 5; i++)
            {
                if (level().getFluidState(blockpos).is(Fluids.LAVA))
                    level().addParticle(
                            SCParticles.FISHING_BITING_LAVA.get(),
                            position().x + random.nextFloat() - 0.5,
                            position().y + random.nextFloat() * 0.5 - 0.25,
                            position().z + random.nextFloat() - 0.5,
                            0, 0, 0);
                else
                    level().addParticle(
                            SCParticles.FISHING_BITING.get(),
                            position().x + random.nextFloat() - 0.5,
                            position().y + random.nextFloat() * 0.5 - 0.25,
                            position().z + random.nextFloat() - 0.5,
                            0, 0, 0);
            }

            if (timeBiting > 80)
            {
                SCDataAttachments.remove(player, SCDataAttachments.FISHING_BOB);
                SCTackleSkins.get(level(), rod).onMissed(player);

                player.awardStat(SCStats.STARCAUGHT_FISH_MISSED.get());

                ItemStack bait = SCDataComponents.getOrDefault(rod, SCDataComponents.BAIT, new SingleStackContainer(ItemStack.EMPTY)).stack();
                if (!bait.is(Tags.Items.BUCKETS))
                {
                    bait.shrink(1);
                    if (bait.getCount() < 5)
                    {
                        player.displayClientMessage(Component.translatable("gui.starcatcher.bait_running_low"), true);
                    }
                    SCDataComponents.set(rod, SCDataComponents.BAIT, new SingleStackContainer(bait));
                }

                kill();
            }
        }
        else
        {
            timeBiting = 0;
        }

        //if theres no fluid on block or under, changes to FLYING
        if (fluid.isEmpty() && fluidBellow.isEmpty() && !noGravity)
        {
            if (!level().isClientSide) currentState = FishHookState.FLYING;
        }

        if (this.currentState == FishHookState.FISHING)
            setDeltaMovement(Vec3.ZERO);

        //TODO check for water level instead of just blockstate to make the entity sit better in water
        if (this.currentState == FishHookState.BOBBING || this.currentState == FishHookState.FISHING)
        {
            checkForFish();

            if (!noGravity)
            {
                if (!fluid.isEmpty())
                {
                    setDeltaMovement(this.getDeltaMovement().add(0.0F, 0.01, 0.0F));
                }
                else
                {
                    if (random.nextFloat() > 0.02)
                    {
                        setDeltaMovement(this.getDeltaMovement().add(0.0F, -0.03, 0.0F));
                    }
                    else
                    {
                        setDeltaMovement(this.getDeltaMovement().add(0.0F, -0.01, 0.0F));
                    }
                }
            }
            else
            {
                setDeltaMovement(getDeltaMovement().x, getDeltaMovement().y * 0.9, getDeltaMovement().z);
            }
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        //this.updateRotation();

        if (this.onGround() || this.horizontalCollision)
        {
            this.setDeltaMovement(Vec3.ZERO);
        }

        this.setDeltaMovement(this.getDeltaMovement().scale(0.92));
        this.reapplyPosition();
    }

    public boolean checkBiting()
    {
        if (currentState == FishHookState.BITING)
        {
            if (SCDataComponents.has(rod, SCDataComponents.TACKLE_SKIN))
            {
                ResourceLocation rl = SCDataComponents.get(rod, SCDataComponents.TACKLE_SKIN);

                Optional<AbstractTackleSkin> optional = Minecraft.getInstance().level.registryAccess().registryOrThrow(Starcatcher.TACKLE_SKIN).getOptional(rl);
                optional.orElseGet(BaseTackleSkin::new).onMinigameStarted(player);
            }

            currentState = FishHookState.FISHING;
            reel();
            return true;
        }
        else
        {
            return false;
        }
    }

    private void checkForFish()
    {
        if (!level().isClientSide && currentState == FishHookState.BOBBING)
        {
            ticksInFluid++;
            boolean fish = U.r.nextFloat() < chanceToFishEachTick;
            if ((fish || ticksInFluid > maxTicksToFish) && ticksInFluid > minTicksToFish)
            {
                this.setPos(position().x, position().y - 0.5f, position().z);
                if (!level().isClientSide) currentState = FishHookState.BITING;

                //trigger tackle skin on biting
                if (SCDataComponents.has(rod, SCDataComponents.TACKLE_SKIN))
                {
                    ResourceLocation rl = SCDataComponents.get(rod, SCDataComponents.TACKLE_SKIN);
                    Optional<AbstractTackleSkin> optional = Minecraft.getInstance().level.registryAccess().registryOrThrow(Starcatcher.TACKLE_SKIN).getOptional(rl);
                    optional.orElseGet(BaseTackleSkin::new).onBiting(player, this);
                }
            }
        }


    }

    @Override
    public boolean shouldRender(double x, double y, double z)
    {
        return true;
    }

    @Override
    public AABB getBoundingBoxForCulling()
    {
        AABB box = new AABB(-100, -100, -100, 100, 100, 100);
        return box.move(position());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        builder.define(STATE, 0);
        builder.define(VOID, false);
    }
}
