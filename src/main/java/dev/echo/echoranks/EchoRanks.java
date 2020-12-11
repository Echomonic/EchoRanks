package dev.echo.echoranks;

import dev.echo.echoranks.commands.CommandRankPlayer;
import dev.echo.echoranks.events.ChatEvent;
import dev.echo.echoranks.events.JoinEvent;
import dev.echo.echoranks.managers.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EchoRanks extends JavaPlugin {

    private FileManager fileManager;


    @Override
    public void onEnable() {

        register(Bukkit.getPluginManager());

        fileManager = new FileManager();

        fileManager.createMainFiles(this);

        saveResource(fileManager.getMessages().getName(), false);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static EchoRanks getInstance(){

        return EchoRanks.getPlugin(EchoRanks.class);
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    void register(PluginManager pluginManager){

        pluginManager.registerEvents(new JoinEvent(), this);
        pluginManager.registerEvents(new ChatEvent(), this);

        getCommand("rank").setExecutor(new CommandRankPlayer());

    }

}
