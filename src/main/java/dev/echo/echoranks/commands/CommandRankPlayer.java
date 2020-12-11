package dev.echo.echoranks.commands;

import dev.echo.echoranks.EchoRanks;
import dev.echo.echoranks.tests.RankTag;
import dev.echo.echoranks.utils.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandRankPlayer implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){

            Player player = (Player) sender;

            if(player.hasPermission("rank.use")){

                if(args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);

                    EchoRanks.getInstance().getFileManager().setRank(target.getUniqueId(), Ranks.valueOf(args[1]));

                    try {
                        RankTag.setNametag(target,Ranks.valueOf(args[1]).getRankColor() + Ranks.valueOf(args[1]).getRankName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    target.sendMessage(ChatColor.WHITE + "Your rank has been updated to " + EchoRanks.getInstance().getFileManager().getRank(target).getRankColor().toString().toUpperCase()
                            + Ranks.valueOf(args[1]).getRankName());


                    player.sendMessage(ChatColor.GREEN + "Successful!");



                }else {
                    String unFormatted = EchoRanks.getInstance().getFileManager().getMessagesConfiguration().getString("PermissionMessages.NoPerm");
                    String formmated = ChatColor.translateAlternateColorCodes('&', unFormatted);
                    player.sendMessage(formmated);
                }

            }else{

                String unFormatted = EchoRanks.getInstance().getFileManager().getMessagesConfiguration().getString("PermissionMessages.NotPlayer");
                String formmated = ChatColor.translateAlternateColorCodes('&', unFormatted);
                sender.sendMessage(formmated);
                return true;

            }


        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> arguments = new ArrayList<>();

        if(!(sender instanceof Player));

        Player player =  (Player) sender;
        if(command.getName().equalsIgnoreCase("rank")) {
            if (player.hasPermission("rank.player")) {

                if (args.length == 1) {

                    for(Player online : Bukkit.getOnlinePlayers()){

                        arguments.addAll(Arrays.asList(online.getName()));

                    }

                }

                if (args.length == 2) {

                    for(Ranks ranks : Ranks.values()){

                        String s1 = ChatColor.translateAlternateColorCodes('§', ranks.toString());

                        arguments.addAll(Collections.singleton("" + s1));
                    }

                }
            }
        }
        return arguments;
    }
}
