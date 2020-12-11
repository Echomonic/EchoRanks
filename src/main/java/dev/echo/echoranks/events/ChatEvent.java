package dev.echo.echoranks.events;

import dev.echo.echoranks.EchoRanks;
import dev.echo.echoranks.utils.Ranks;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onRankedChatEvent(AsyncPlayerChatEvent event){

        Player player = event.getPlayer();
        event.setCancelled(true);

        Ranks ranks = EchoRanks.getInstance().getFileManager().getRank(player);


        if(ranks.name() != null){
            for(Player ranked : event.getRecipients()) {
                if (EchoRanks.getInstance().getFileManager().getRank(player) == Ranks.MEMBER) {

                    ranked.sendMessage(ranks.getRankColor() + ranks.getRankName() + " " + ranks.getRankColor() + player.getName() + ChatColor.GRAY + ": " + event.getMessage());

                } else {
                    ranked.sendMessage(ranks.getRankColor() + ranks.getRankName() + " " + ranks.getRankColor() + player.getName() + ChatColor.WHITE + ": " + event.getMessage());


                }
            }
        } else {
            return;

        }
    }
    
}
