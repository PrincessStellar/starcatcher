package com.wdiscute.starcatcher.fish;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.ExtraComposites;
import com.wdiscute.starcatcher.modifiers.Modifier;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.*;
import com.wdiscute.starcatcher.registry.sweetspotbehaviour.SCSweetSpotsBehaviour;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//region dif
public record Difficulty(
        int hp,
        int speed,
        int penalty,
        float decay,
        List<Modifier> modifiers,
        List<SweetSpot> sweetSpots
)
{
    public Difficulty(int hp, int speed, int penalty, float decay, List<Modifier> modifiers, SweetSpot... sweetSpots)
    {
        this(hp, speed, penalty, decay, modifiers, Arrays.stream(sweetSpots).toList());
    }

    public Difficulty(int speed, int penalty, float decay, List<Modifier> modifiers, SweetSpot... sweetSpots)
    {
        this(100, speed, penalty, decay, modifiers, Arrays.stream(sweetSpots).toList());
    }

    public Difficulty addModifiers(List<Modifier> newModifier)
    {
        List<Modifier> list = new ArrayList<>();
        list.addAll(newModifier);
        list.addAll(this.modifiers);
        return new Difficulty(this.hp, this.speed, this.penalty, this.decay, list, this.sweetSpots);
    }

    public Difficulty vanishing(float vanishingRate)
    {
        List<SweetSpot> sss = new ArrayList<>();
        sweetSpots.forEach(s -> sss.add(s.vanishing(vanishingRate)));
        return new Difficulty(hp, speed, penalty, decay, modifiers, sss);
    }

    public Difficulty withHP(int hp)
    {
        return new Difficulty(hp, speed, penalty, decay, modifiers, sweetSpots);
    }

    public Difficulty withPenalty(int penalty)
    {
        return new Difficulty(hp, speed, penalty, decay, modifiers, sweetSpots);
    }

    public Difficulty withDecay(int decay)
    {
        return new Difficulty(hp, speed, penalty, decay, modifiers, sweetSpots);
    }

    public Difficulty withSpeed(int speed)
    {
        return new Difficulty(hp, speed, penalty, decay, modifiers, sweetSpots);
    }

    public Difficulty vanishing()
    {
        List<SweetSpot> sss = new ArrayList<>();
        sweetSpots.forEach(s -> sss.add(s.vanishing(0.1f)));
        return new Difficulty(hp, speed, penalty, decay, modifiers, sss);
    }

    public Difficulty moving(float movingRate)
    {
        List<SweetSpot> sss = new ArrayList<>();
        sweetSpots.forEach(s -> sss.add(s.moving(movingRate)));
        return new Difficulty(hp, speed, penalty, decay, modifiers, sss);
    }

    public Difficulty moving()
    {
        List<SweetSpot> sss = new ArrayList<>();
        sweetSpots.forEach(s -> sss.add(s.moving(1)));
        return new Difficulty(hp, speed, penalty, decay, modifiers, sss);
    }

    public Difficulty flip()
    {
        List<SweetSpot> sss = new ArrayList<>();
        sweetSpots.forEach(s -> sss.add(s.flip()));
        return new Difficulty(hp, speed, penalty, decay, modifiers, sss);
    }


    //region preset difficulties

    public static Difficulty TRASH = new Difficulty(
            10, 0, 0,
            List.of(),
            SweetSpot.TRASH, SweetSpot.TRASH
    );

    public static Difficulty EASY = new Difficulty(
            9, 5, 1,
            List.of(),
            SweetSpot.NORMAL, SweetSpot.NORMAL
    );
    public static Difficulty EASY_VANISHING = EASY.vanishing();
    public static Difficulty EASY_MOVING = EASY.moving();

    public static Difficulty MEDIUM = new Difficulty(
            10, 20, 1,
            List.of(),
            SweetSpot.NORMAL, SweetSpot.THIN
    );
    public static Difficulty MEDIUM_VANISHING = MEDIUM.vanishing();
    public static Difficulty MEDIUM_MOVING = MEDIUM.moving();
    public static Difficulty MEDIUM_VANISHING_MOVING = MEDIUM.moving().vanishing();

    public static Difficulty HARD = new Difficulty(
            11, 10, 1,
            List.of(),
            SweetSpot.THIN, SweetSpot.THIN);
    public static Difficulty HARD_VANISHING = HARD.vanishing();
    public static Difficulty HARD_MOVING = HARD.moving();

    public static Difficulty THIN_NO_DECAY_NOT_FORGIVING = new Difficulty(
            11, 40, 0,
            List.of(),
            SweetSpot.THIN, SweetSpot.THIN
    );

    public static Difficulty HEAVY_FIVE_NORMAL = new Difficulty(
            5, 40, 0,
            List.of(),
            SweetSpot.NORMAL_HEAVY, SweetSpot.NORMAL_HEAVY, SweetSpot.NORMAL_HEAVY, SweetSpot.NORMAL_HEAVY, SweetSpot.NORMAL_HEAVY
    );

    public static Difficulty FOUR_BIG = new Difficulty(
            9, 20, 0,
            List.of(),
            SweetSpot.NORMAL, SweetSpot.NORMAL, SweetSpot.NORMAL, SweetSpot.NORMAL
    );
    public static Difficulty FOUR_BIG_VANISHING = FOUR_BIG.vanishing();
    public static Difficulty FOUR_BIG_MOVING = FOUR_BIG.moving();

    public static Difficulty HEAVY_EIGHT_AQUA = new Difficulty(
            1000,
            12, 20, 0,
            List.of(),
            SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA
    );

    public static Difficulty HEAVY_EIGHT_AQUA_MOVING = new Difficulty(
            1000,
            12, 20, 0,
            List.of(),
            SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA
    ).moving();

    public static Difficulty TWO_AQUA_ONE_THIN = new Difficulty(
            9, 20, 0,
            List.of(),
            SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.THIN
    );
    public static Difficulty TWO_AQUA_ONE_THIN_VANISHING = TWO_AQUA_ONE_THIN.vanishing();

    public static Difficulty FOUR_THIN = new Difficulty(
            9, 20, 0,
            List.of(),
            SweetSpot.THIN, SweetSpot.THIN, SweetSpot.THIN, SweetSpot.THIN
    );

    public static Difficulty FOUR_THIN_VANISHING = FOUR_THIN.vanishing();
    public static Difficulty FOUR_THIN_MOVING = FOUR_THIN.moving();

    public static Difficulty EIGHT_THIN = new Difficulty(
            9, 20, 0,
            List.of(),
            SweetSpot.THIN, SweetSpot.THIN, SweetSpot.THIN, SweetSpot.THIN, SweetSpot.THIN, SweetSpot.THIN, SweetSpot.THIN, SweetSpot.THIN
    );
    public static Difficulty EIGHT_THIN_VANISHING = EIGHT_THIN.vanishing();
    public static Difficulty EIGHT_THIN_MOVING = EIGHT_THIN.moving();
    public static Difficulty EIGHT_THIN_MOVING_VANISHING = EIGHT_THIN.vanishing();

    public static Difficulty THREE_BIG_TWO_THIN = new Difficulty(
            9, 20, 0,
            List.of(),
            SweetSpot.NORMAL, SweetSpot.NORMAL, SweetSpot.NORMAL, SweetSpot.THIN, SweetSpot.THIN
    ).vanishing();
    public static Difficulty THREE_BIG_TWO_THIN_VANISHING = THREE_BIG_TWO_THIN.vanishing();

    public static Difficulty EIGHT_STONE_SPOTS = new Difficulty(
            12, 20, 0,
            List.of(),
            SweetSpot.STONE, SweetSpot.STONE, SweetSpot.STONE, SweetSpot.STONE, SweetSpot.STONE, SweetSpot.STONE, SweetSpot.STONE, SweetSpot.STONE
    );

    public static Difficulty TWO_STONE_SPOTS_EASY = new Difficulty(
            12, 20, 0,
            List.of(),
            SweetSpot.STONE_5, SweetSpot.STONE_5, SweetSpot.STONE_5, SweetSpot.STONE_5
    );

    public static Difficulty FOUR_STONE_SPOTS = new Difficulty(
            12, 20, 0,
            List.of(),
            SweetSpot.STONE, SweetSpot.STONE, SweetSpot.STONE, SweetSpot.STONE
    );

    public static Difficulty EASY_FAST_FISH = new Difficulty(
            15, 20, 1,
            List.of(),
            SweetSpot.NORMAL, SweetSpot.NORMAL
    );

    public static Difficulty SINGLE_AQUA = new Difficulty(
            13, 5, 1,
            List.of(),
            SweetSpot.AQUA
    );

    public static Difficulty SINGLE_AQUA_MOVING = new Difficulty(
            13, 5, 1,
            List.of(),
            SweetSpot.AQUA
    ).moving();

    public static Difficulty SINGLE_BIG_FAST = new Difficulty(
            13, 30, 1,
            List.of(),
            SweetSpot.NORMAL_STEADY
    );
    public static Difficulty SINGLE_BIG_FAST_MOVING = SINGLE_BIG_FAST.moving();
    public static Difficulty SINGLE_BIG_FAST_VANISHING = SINGLE_BIG_FAST.vanishing();

    public static Difficulty TWO_AQUA = new Difficulty(
            10, 20, 1,
            List.of(),
            SweetSpot.AQUA, SweetSpot.AQUA
    );

    public static Difficulty FOUR_AQUA = new Difficulty(
            10, 20, 1,
            List.of(),
            SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA
    );

    public static Difficulty SINGLE_THIN_FAST = new Difficulty(
            14, 10, 1,
            List.of(),
            SweetSpot.THIN
    );

    public static Difficulty TWO_THIN = new Difficulty(
            11, 15, 1,
            List.of(),
            SweetSpot.THIN, SweetSpot.THIN
    );

    public static Difficulty TWO_THIN_NO_DECAY = new Difficulty(
            10, 20, 0,
            List.of(),
            SweetSpot.THIN, SweetSpot.THIN
    );

    public static Difficulty NON_STOP_ACTION_THREE_BIG = new Difficulty(
            14, 20, 1,
            List.of(),
            SweetSpot.NORMAL, SweetSpot.NORMAL, SweetSpot.NORMAL
    );

    public static Difficulty NON_STOP_ACTION_AQUA = new Difficulty(
            300,
            14, 20, 2f,
            List.of(),
            SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA, SweetSpot.AQUA
    );

    public static Difficulty AURORA = new Difficulty(
            500,
            14, 75, 3f,
            List.of(),
            SweetSpot.FROZEN, SweetSpot.FROZEN
    );

    public static Difficulty STONEFISH = new Difficulty(
            3000,
            14, 30, 0f,
            List.of(),
            SweetSpot.STONE, SweetSpot.STONE, SweetSpot.STONE, SweetSpot.STONE
    );

    public static Difficulty WITHER = new Difficulty(300,
            10, 30, 1,
            List.of(),
            SweetSpot.WITHER_BIG, SweetSpot.WITHER, SweetSpot.WITHER_REVERSED
    );

    public static Difficulty CREEPER = new Difficulty(
            10, 20, 1,
            List.of(new SpawnSweetSpotsModifier(-1, 5, 0.25f, Difficulty.SweetSpot.TNT, true, "")),
            SweetSpot.CREEPER, SweetSpot.CREEPER
    );

    public static Difficulty NO_SWEET_SPOTS = new Difficulty(10, 20, 1, List.of());

    public static Difficulty DEEPSLATE_CRAB = new Difficulty(
            200, 14, 10, 1,
            List.of(),
            SweetSpot.DEEPSLATE_CRAB_CLAW, SweetSpot.DEEPSLATE_CRAB_CLAW,
            SweetSpot.DEEPSLATE_CRAB_LEG, SweetSpot.DEEPSLATE_CRAB_LEG, SweetSpot.DEEPSLATE_CRAB_LEG,
            SweetSpot.DEEPSLATE_CRAB_LEG, SweetSpot.DEEPSLATE_CRAB_LEG, SweetSpot.DEEPSLATE_CRAB_LEG);

    public static Difficulty OBSIDIAN_CRAB = new Difficulty(
            200, 14, 10, 1,
            List.of(),
            SweetSpot.OBSIDIAN_CRAB_CLAW, SweetSpot.OBSIDIAN_CRAB_CLAW,
            SweetSpot.OBSIDIAN_CRAB_LEG, SweetSpot.OBSIDIAN_CRAB_LEG, SweetSpot.OBSIDIAN_CRAB_LEG,
            SweetSpot.OBSIDIAN_CRAB_LEG, SweetSpot.OBSIDIAN_CRAB_LEG, SweetSpot.OBSIDIAN_CRAB_LEG);

    public static Difficulty NETHER_CRAB = new Difficulty(
            200, 14, 10, 1,
            List.of(),
            SweetSpot.NETHER_CRAB_CLAW, SweetSpot.NETHER_CRAB_CLAW,
            SweetSpot.NETHER_CRAB_LEG, SweetSpot.NETHER_CRAB_LEG, SweetSpot.NETHER_CRAB_LEG,
            SweetSpot.NETHER_CRAB_LEG, SweetSpot.NETHER_CRAB_LEG, SweetSpot.NETHER_CRAB_LEG);

    public static Difficulty END_CRAB = new Difficulty(
            200, 14, 10, 1,
            List.of(),
            SweetSpot.END_CRAB_CLAW, SweetSpot.END_CRAB_CLAW,
            SweetSpot.END_CRAB_LEG, SweetSpot.END_CRAB_LEG, SweetSpot.END_CRAB_LEG,
            SweetSpot.END_CRAB_LEG, SweetSpot.END_CRAB_LEG, SweetSpot.END_CRAB_LEG);

    public static Difficulty VOIDBITER = new Difficulty(
            500, 10, 30, 1,
            List.of(
                    new TeleportModifier(""),
                    new FreezeOnMissModifier(40, 10, ""),
                    new Nikdo53Modifier(1, "")
            ),
            SweetSpot.VOIDBITER_SPOT, SweetSpot.VOIDBITER_SPOT);

    public static Difficulty JOEL = new Difficulty(
            200,
            14, 6, 1,
            List.of(),
            SweetSpot.AQUA, SweetSpot.AQUA
    );

    public static Difficulty CERBERAY = new Difficulty(
            500, 14, 10, 1.5f,
            List.of(
                    new BurnOnMissModifier(40, 10, 20, ""),
                    new Nikdo53Modifier(2, "")
            ),
            SweetSpot.THIN, SweetSpot.THIN
    );

    //endregion preset difficulties


    public static final Codec<Difficulty> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("hp").forGetter(Difficulty::hp),
                    Codec.INT.fieldOf("speed").forGetter(Difficulty::speed),
                    Codec.INT.fieldOf("missPenalty").forGetter(Difficulty::penalty),
                    Codec.FLOAT.fieldOf("decay").forGetter(Difficulty::decay),
                    Modifier.CODEC.listOf().fieldOf("modifiers").forGetter(Difficulty::modifiers),
                    SweetSpot.LIST_CODEC.fieldOf("sweetspots").forGetter(Difficulty::sweetSpots)
            ).apply(instance, Difficulty::new));


    public static final StreamCodec<FriendlyByteBuf, Difficulty> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, Difficulty::hp,
            ByteBufCodecs.INT, Difficulty::speed,
            ByteBufCodecs.INT, Difficulty::penalty,
            ByteBufCodecs.FLOAT, Difficulty::decay,
            ByteBufCodecs.fromCodec(Modifier.CODEC).apply(ByteBufCodecs.list()), Difficulty::modifiers,
            SweetSpot.LIST_STREAM_CODEC, Difficulty::sweetSpots,
            Difficulty::new
    );

    public record SweetSpot(
            ResourceLocation sweetSpotType,
            ResourceLocation texturePath,
            int size,
            int reward,
            boolean isFlip,
            float vanishingRate,
            float movingRate,
            int particleColor,
            List<Modifier> modifiers
    )
    {
        public SweetSpot(ResourceLocation sweetSpotType, ResourceLocation texturePath, int size, int reward, int particleColor, List<Modifier> onHitModifiers)
        {
            this(sweetSpotType, texturePath, size, reward, false, 0, 0, particleColor, onHitModifiers);
        }

        public SweetSpot(ResourceLocation sweetSpotType, ResourceLocation texturePath, int size, int reward, int particleColor)
        {
            this(sweetSpotType, texturePath, size, reward, false, 0, 0, particleColor, List.of());
        }

        public SweetSpot(ResourceLocation sweetSpotType, ResourceLocation texturePath, int size, int reward, boolean isFlip, float vanishingRate, float movingRate, int particleColor)
        {
            this(sweetSpotType, texturePath, size, reward, isFlip, vanishingRate, movingRate, particleColor, List.of());
        }


        private static final ResourceLocation RL_NORMAL = Starcatcher.rl("textures/gui/minigame/spots/normal.png");
        private static final ResourceLocation RL_NORMAL_STEADY = Starcatcher.rl("textures/gui/minigame/spots/normal_steady.png");
        private static final ResourceLocation RL_THIN = Starcatcher.rl("textures/gui/minigame/spots/thin.png");
        private static final ResourceLocation RL_THIN_STEADY = Starcatcher.rl("textures/gui/minigame/spots/thin_steady.png");
        private static final ResourceLocation RL_FREEZE = Starcatcher.rl("textures/gui/minigame/spots/frozen.png");
        private static final ResourceLocation RL_TREASURE = Starcatcher.rl("textures/gui/minigame/spots/treasure.png");
        private static final ResourceLocation RL_WITHER = Starcatcher.rl("textures/gui/minigame/spots/wither.png");
        private static final ResourceLocation RL_WITHER_BIG = Starcatcher.rl("textures/gui/minigame/spots/wither_big.png");
        private static final ResourceLocation RL_CREEPER = Starcatcher.rl("textures/gui/minigame/spots/creeper.png");
        private static final ResourceLocation RL_TNT = Starcatcher.rl("textures/gui/minigame/spots/tnt.png");
        private static final ResourceLocation RL_STONE = Starcatcher.rl("textures/gui/minigame/spots/stone.png");
        private static final ResourceLocation RL_AQUA = Starcatcher.rl("textures/gui/minigame/spots/aqua.png");
        private static final ResourceLocation RL_DEEP_OCEAN = Starcatcher.rl("textures/gui/minigame/spots/deep_ocean.png");
        private static final ResourceLocation RL_GLOWING = Starcatcher.rl("textures/gui/minigame/spots/glowing.png");
        private static final ResourceLocation RL_LEAF = Starcatcher.rl("textures/gui/minigame/spots/leaf.png");

        private static final ResourceLocation RL_NETHER_CRAB_CLAW = Starcatcher.rl("textures/gui/minigame/spots/nether_crab_claw.png");
        private static final ResourceLocation RL_NETHER_CRAB_LEG = Starcatcher.rl("textures/gui/minigame/spots/nether_crab_leg.png");

        private static final ResourceLocation RL_END_CRAB_LEG = Starcatcher.rl("textures/gui/minigame/spots/end_crab_leg.png");
        private static final ResourceLocation RL_END_CRAB_CLAW = Starcatcher.rl("textures/gui/minigame/spots/end_crab_claw.png");

        private static final ResourceLocation RL_DEEPSLATE_CRAB_LEG = Starcatcher.rl("textures/gui/minigame/spots/deepslate_crab_leg.png");
        private static final ResourceLocation RL_DEEPSLATE_CRAB_CLAW = Starcatcher.rl("textures/gui/minigame/spots/deepslate_crab_claw.png");

        private static final ResourceLocation RL_OBSIDIAN_CRAB_LEG = Starcatcher.rl("textures/gui/minigame/spots/obsidian_crab_leg.png");
        private static final ResourceLocation RL_OBSIDIAN_CRAB_CLAW = Starcatcher.rl("textures/gui/minigame/spots/obsidian_crab_claw.png");

        private static final ResourceLocation RL_THIN_STEADY_MOSSY = Starcatcher.rl("textures/gui/minigame/spots/thin_mossy.png");


        public SweetSpot flip()
        {
            return new SweetSpot(this.sweetSpotType, this.texturePath, this.size, this.reward, true, this.vanishingRate, this.movingRate, this.particleColor, this.modifiers);
        }

        public SweetSpot vanishing(float vanishingRate)
        {
            return new SweetSpot(this.sweetSpotType, this.texturePath, this.size, this.reward, this.isFlip, vanishingRate, this.movingRate, this.particleColor, this.modifiers);
        }

        public SweetSpot moving(float movingRate)
        {
            return new SweetSpot(this.sweetSpotType, this.texturePath, this.size, this.reward, this.isFlip, this.vanishingRate, movingRate, this.particleColor, this.modifiers);
        }

        public final SweetSpot withModifiers(Modifier... modifiers)
        {
            return new SweetSpot(this.sweetSpotType, this.texturePath, this.size, this.reward, this.isFlip, this.vanishingRate, this.movingRate, this.particleColor, Arrays.stream(modifiers).toList());
        }


        public static SweetSpot TRASH = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_NORMAL,
                22,
                30,
                0xff00ff00
        );

        public static SweetSpot NORMAL_STEADY = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_NORMAL_STEADY,
                33,
                15,
                0xff00ff00
        );

        public static SweetSpot NORMAL = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_NORMAL,
                22,
                15,
                0xff00ff00
        );

        public static SweetSpot THIN_STEADY = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_THIN_STEADY,
                20,
                20,
                0xff00ff00
        );

        public static SweetSpot THIN_STEADY_MOSSY = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_THIN_STEADY_MOSSY,
                20,
                10,
                true, 0.01f, 1,
                0xff00ff00
        );

        public static SweetSpot THIN = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_THIN,
                15,
                20,
                0xff00ff00
        );

        public static SweetSpot FROZEN = new SweetSpot(
                SCSweetSpotsBehaviour.FROZEN,
                RL_FREEZE,
                33,
                15,
                0xffADD8E6
        );

        public static SweetSpot NORMAL_HEAVY = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_NORMAL,
                22,
                1,
                0xff00ff00
        );

        public static SweetSpot TREASURE = new SweetSpot(
                SCSweetSpotsBehaviour.TREASURE,
                RL_TREASURE,
                20,
                15,
                0xffFFD700
        );

        public static SweetSpot WITHER = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_WITHER,
                22,
                15,
                false,
                0,
                3,
                0xff1f1f1f
        );

        public static SweetSpot WITHER_REVERSED = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_WITHER,
                22,
                15,
                false,
                0,
                -3,
                0xff1f1f1f
        );


        public static SweetSpot WITHER_BIG = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_WITHER_BIG,
                33,
                15,
                0xff1f1f1f
        );

        public static SweetSpot CREEPER = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_CREEPER,
                22,
                15,
                0xff515353
        );

        public static SweetSpot TNT = new SweetSpot(
                SCSweetSpotsBehaviour.TNT,
                RL_TNT,
                33,
                30,
                0xffff0000
        );

        public static SweetSpot STONE_5 = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_STONE,
                33,
                5,
                0xff494949
        );


        public static SweetSpot STONE = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_STONE,
                33,
                10,
                0xff494949
        );

        public static SweetSpot AQUA = new SweetSpot(
                SCSweetSpotsBehaviour.AQUA,
                RL_AQUA,
                22,
                10,
                0xff387982
        );

        public static SweetSpot DEEP_OCEAN = new SweetSpot(
                SCSweetSpotsBehaviour.DEEP_OCEAN,
                RL_DEEP_OCEAN,
                22,
                10,
                0xff4f756d
        );

        public static SweetSpot GLOWING = new SweetSpot(
                SCSweetSpotsBehaviour.GLOWING_SWEET_SPOT,
                RL_GLOWING,
                20,
                0,
                0xfffdf55f
        );

        public static SweetSpot LEAF = new SweetSpot(
                SCSweetSpotsBehaviour.LEAF,
                RL_LEAF,
                15,
                15,
                0xff00ff00
        );

        public static SweetSpot DEEPSLATE_CRAB_CLAW = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_DEEPSLATE_CRAB_CLAW,
                24,
                10,
                0xffff8400
        );

        public static SweetSpot DEEPSLATE_CRAB_LEG = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_DEEPSLATE_CRAB_LEG,
                15,
                1,
                0xffff8400
        );

        public static SweetSpot OBSIDIAN_CRAB_CLAW = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_OBSIDIAN_CRAB_CLAW, 24,
                10,
                0xff3b2754
        );

        public static SweetSpot OBSIDIAN_CRAB_LEG = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_OBSIDIAN_CRAB_LEG, 15,
                1,
                0xff3b2754
        );

        public static SweetSpot NETHER_CRAB_CLAW = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_NETHER_CRAB_CLAW, 24,
                10,
                0xffcd4545
        );

        public static SweetSpot NETHER_CRAB_LEG = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_NETHER_CRAB_LEG, 15,
                1,
                0xffcd4545
        );

        public static SweetSpot END_CRAB_CLAW = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_END_CRAB_CLAW, 24,
                10,
                0xffc67ed9
        );

        public static SweetSpot END_CRAB_LEG = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_END_CRAB_LEG, 15,
                1,
                0xffc67ed9
        );

        public static SweetSpot VOIDBITER_SPOT = new SweetSpot(
                SCSweetSpotsBehaviour.NORMAL,
                RL_END_CRAB_LEG, 15,
                15,
                0xffc67ed9
        );

        public static final Codec<SweetSpot> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        ResourceLocation.CODEC.fieldOf("sweet_spot_type").forGetter(SweetSpot::sweetSpotType),
                        ResourceLocation.CODEC.fieldOf("texture_path").forGetter(SweetSpot::texturePath),
                        Codec.INT.fieldOf("hitbox_size_in_pixels").forGetter(SweetSpot::size),
                        Codec.INT.fieldOf("reward").forGetter(SweetSpot::reward),
                        Codec.BOOL.fieldOf("is_flip").forGetter(SweetSpot::isFlip),
                        Codec.FLOAT.fieldOf("vanishing_rate").forGetter(SweetSpot::vanishingRate),
                        Codec.FLOAT.fieldOf("moving_rate").forGetter(SweetSpot::movingRate),
                        Codec.INT.fieldOf("color_as_int").forGetter(SweetSpot::particleColor),
                        Modifier.CODEC.listOf().optionalFieldOf("add_modifiers_on_hit", List.of()).forGetter(SweetSpot::modifiers)
                ).apply(instance, SweetSpot::new));

        public static final Codec<List<SweetSpot>> LIST_CODEC = CODEC.listOf();

        public static final StreamCodec<FriendlyByteBuf, SweetSpot> STREAM_CODEC = ExtraComposites.composite(
                ResourceLocation.STREAM_CODEC, SweetSpot::sweetSpotType,
                ResourceLocation.STREAM_CODEC, SweetSpot::texturePath,
                ByteBufCodecs.INT, SweetSpot::size,
                ByteBufCodecs.INT, SweetSpot::reward,
                ByteBufCodecs.BOOL, SweetSpot::isFlip,
                ByteBufCodecs.FLOAT, SweetSpot::vanishingRate,
                ByteBufCodecs.FLOAT, SweetSpot::movingRate,
                ByteBufCodecs.INT, SweetSpot::particleColor,
                ByteBufCodecs.fromCodec(Modifier.CODEC).apply(ByteBufCodecs.list()), SweetSpot::modifiers,
                SweetSpot::new
        );

        public static final StreamCodec<FriendlyByteBuf, List<SweetSpot>> LIST_STREAM_CODEC = STREAM_CODEC.apply(ByteBufCodecs.list());
    }
}
