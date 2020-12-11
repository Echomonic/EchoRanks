package dev.echo.echoranks.tests;

import dev.echo.echoranks.EchoRanks;
import dev.echo.echoranks.utils.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class RankTag {

    private static Ranks ranks;

    public static void setNametag(Player player, String prefix) throws Exception
    {
        prefix = EchoRanks.getInstance().getFileManager().getRank(player).getRankColor() + EchoRanks.getInstance().getFileManager().getRank(player).getRankName();



        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam(player.getName());
        if (team == null)
        {
            team = scoreboard.registerNewTeam(player.getName());
            team.setPrefix(prefix + " ");
            team.addPlayer(Objects.requireNonNull(player.getPlayer()));
        }
        else
        {
            team = scoreboard.getTeam(player.getName());
            team.setPrefix(prefix + " ");
            team.addPlayer(player);
        }
        team.setColor(EchoRanks.getInstance().getFileManager().getRank(player).getRankColor());
        for (Player players : Bukkit.getOnlinePlayers())
        {
            players.setScoreboard(scoreboard);
        }
    }
    
}
