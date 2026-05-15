package com.wdiscute.starcatcher.tournament;

import com.wdiscute.starcatcher.io.network.tournament.CBClearTournamentPayload;
import com.wdiscute.starcatcher.registry.FishProperties;
import com.wdiscute.starcatcher.io.network.tournament.CBActiveTournamentUpdatePayload;
import net.minecraft.network.chat.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.CommonColors;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.checkerframework.checker.units.qual.C;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class TournamentHandler
{
    private static final List<Tournament> finishedTournaments = new ArrayList<>();
    private static final List<Tournament> activeTournaments = new ArrayList<>();

    public static Tournament getActiveTournamentOrNull(UUID uuid)
    {
        for (Tournament t : activeTournaments)
            if (t.tournamentUUID.equals(uuid)) return t;

        return null;
    }

    public static void sendActiveTournamentUpdateToClient(ServerPlayer sp, Tournament tournament)
    {
        if (sp == null || tournament == null) return;
        PacketDistributor.sendToPlayer(sp, CBActiveTournamentUpdatePayload.helper(sp, tournament));
    }

    public static void clearTournamentToClient(ServerPlayer sp)
    {
        if (sp == null) return;
        PacketDistributor.sendToPlayer(sp, new CBClearTournamentPayload(":)"));
    }

    public static void startTournament(Player playerWhoStartedTheTournament, Tournament tournament)
    {
        activeTournaments.add(tournament);
        tournament.status = Tournament.Status.ACTIVE;
        tournament.startTimeEpoch = System.currentTimeMillis();

        Level level = playerWhoStartedTheTournament.level();
        for (Tournament.PlayerScore playerScore : tournament.playerScores)
        {
            ServerPlayer player = level.getServer().getPlayerList().getPlayer(playerScore.uuid);
            sendActiveTournamentUpdateToClient(player, tournament);

            if (player != null)
            {
                player.sendSystemMessage(Component.literal(tournament.name).append(Component.translatable("gui.starcatcher.tournament.started")));
                player.sendSystemMessage(Component.translatable("gui.starcatcher.tournament.press_tab").withColor(CommonColors.LIGHT_GRAY));
            }
        }
    }

    public static void cancelTournament(Level level, Tournament tournament)
    {
        for (var entry : tournament.playerScores)
        {
            ServerPlayer player = level.getServer().getPlayerList().getPlayer(entry.uuid);
            sendActiveTournamentUpdateToClient(player, tournament);
            clearTournamentToClient(player);
        }

        activeTournaments.remove(tournament);
    }

    public static void addScore(Player player, FishProperties fp, boolean perfectCatch, float percentile)
    {
        for (Tournament t : activeTournaments)
        {
            List<Tournament.PlayerScore> list = t.playerScores.stream().filter(o -> o.uuid.equals(player.getUUID())).toList();

            if (list.isEmpty()) continue;

            Tournament.PlayerScore first = list.getFirst();

            float baseScore = switch (fp.rarity())
            {
                case TRASH -> t.scoreSettings.trashScore;
                case COMMON -> t.scoreSettings.commonScore;
                case UNCOMMON -> t.scoreSettings.uncommonScore;
                case RARE -> t.scoreSettings.rareScore;
                case EPIC -> t.scoreSettings.epicScore;
                case LEGENDARY -> t.scoreSettings.legendaryScore;
                default -> 0;
            };

            float extraAwardPercentile = baseScore * ((100 - percentile) / 100) * t.scoreSettings.perfectCatchMultiplier;
            float extraAwardPerfectCatch = 0;
            if (perfectCatch)
                extraAwardPercentile = baseScore * t.scoreSettings.perfectCatchMultiplier;
            first.score += baseScore + extraAwardPercentile + extraAwardPerfectCatch;
        }

    }

    public static void tick(ServerTickEvent.Post event)
    {
        MinecraftServer server = event.getServer();
        long levelTicks = server.getTickCount();
        if (levelTicks % 20 != 0) return;

        List<Tournament> finished = new ArrayList<>();
        for (Tournament t : activeTournaments)
        {
            if (System.currentTimeMillis() >= t.startTimeEpoch)
            {
                finished.add(t);
                t.status = Tournament.Status.FINISHED;
                UUID winner = null;
                int bestScore = 0;

                for (Tournament.PlayerScore playerscore : t.playerScores)
                {
                    if (playerscore.score > bestScore)
                    {
                        bestScore = ((int) playerscore.score);
                        winner = playerscore.uuid;
                    }
                }

                String winnerString = "???";

                if (winner != null)
                    winnerString = server.getProfileCache().get(winner).get().getName();

                for (var playerScore : t.playerScores)
                {
                    ServerPlayer player = server.getPlayerList().getPlayer(playerScore.uuid);
                    if (player != null)
                    {
                        TournamentHandler.clearTournamentToClient(player);
                        player.sendSystemMessage(Component.literal(t.name + " has ended! The winner is " + winnerString + "!"));
                    }
                }

            }
        }

        finishedTournaments.addAll(finished);
        activeTournaments.removeAll(finished);
    }

    public static List<Tournament> getAll()
    {
        List<Tournament> t = new ArrayList<>();
        t.addAll(activeTournaments);
        t.addAll(finishedTournaments);
        return t;
    }

    public static void setAll(List<Tournament> tournaments)
    {
        activeTournaments.clear();
        activeTournaments.addAll(tournaments.stream().filter(t -> t.status.equals(Tournament.Status.ACTIVE)).toList());

        finishedTournaments.clear();
        finishedTournaments.addAll(tournaments.stream().filter(t -> t.status.equals(Tournament.Status.FINISHED)).toList());
    }

    public static Tournament getTournamentForPlayer(Player player)
    {
        AtomicReference<Tournament> tToReturn = new AtomicReference<>();
        activeTournaments.forEach(t ->
        {
            Stream<Tournament.PlayerScore> tournamentPlayerScoreStream = t.playerScores.stream().filter(playerScore -> playerScore.uuid.equals(player.getUUID()));
            if (tournamentPlayerScoreStream.findFirst().isPresent()) tToReturn.set(t);
        });

        return tToReturn.get();
    }
}
