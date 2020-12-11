package dev.echo.echoranks.events;

import dev.echo.echoranks.EchoRanks;
import dev.echo.echoranks.tests.RankTag;
import dev.echo.echoranks.utils.Ranks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onRankJoinEvent(PlayerJoinEvent event){

        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()){
            EchoRanks.getInstance().getFileManager().setRank(player, Ranks.MEMBER);
            try {
                RankTag.setNametag(player, EchoRanks.getInstance().getFileManager().getRank(player).getRankColor() +  EchoRanks.getInstance().getFileManager().getRank(player).getRankName());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            EchoRanks.getInstance().getFileManager().getRank(player);
            try {
                RankTag.setNametag(player, EchoRanks.getInstance().getFileManager().getRank(player).getRankColor() +  EchoRanks.getInstance().getFileManager().getRank(player).getRankName());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
