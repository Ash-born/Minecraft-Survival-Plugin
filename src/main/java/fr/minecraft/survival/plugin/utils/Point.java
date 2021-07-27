package fr.minecraft.survival.plugin.utils;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.configuration.file.FileConfiguration;

public class Point {
    FileConfiguration config = PluginMain.getInstance().getConfig();

    public double getPlayerPoints(String PlayerName){



        return config.getDouble("points." + PlayerName);
    }
    public void setPlayerPoints(String PlayerName , Double value){



         config.set("points." + PlayerName , value);
    }


    public  Point (){

    }

}
