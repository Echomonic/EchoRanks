package dev.echo.echoranks.managers;

import dev.echo.echoranks.EchoRanks;
import dev.echo.echoranks.utils.Ranks;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileManager {

    private File mainData;


    private File messages;


    private YamlConfiguration messagesConfiguration;

    private YamlConfiguration mainConfiguration;

    public void createMainFiles(EchoRanks main){

        mainData = new File(main.getDataFolder(), "rankData.yml");
        messages = new File(main.getDataFolder() + File.separator, "messages.yml");


        if(!mainData.exists() && !messages.exists()){


            try{
                mainData.createNewFile();
                messages.createNewFile();

            } catch (IOException | IllegalArgumentException ex) {
                return;
            }
        }

        if(!main.getDataFolder().exists()){
            main.getDataFolder().mkdir();
        }

        messagesConfiguration =  YamlConfiguration.loadConfiguration(messages);
        mainConfiguration = YamlConfiguration.loadConfiguration(mainData);


    }

    public YamlConfiguration getMessagesConfiguration() {
        return messagesConfiguration;
    }


    public YamlConfiguration getMainConfiguration() {
        return mainConfiguration;
    }

    public File getMessages() {
        return messages;
    }


    public File getMainData() {
        return mainData;
    }

    public void setRank(Player player, Ranks rank){
        mainConfiguration.set(player.getUniqueId().toString(), rank.name());
        save();
    }
    public void setRank(UUID uuid, Ranks rank){
        mainConfiguration.set(uuid.toString(), rank.name());
        save();
    }
    public Ranks getRank(Player player){

        return Ranks.valueOf(mainConfiguration.getString(player.getUniqueId().toString()));
    }
    public void save(){

        try {
            mainConfiguration.save(mainData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            messagesConfiguration.save(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
