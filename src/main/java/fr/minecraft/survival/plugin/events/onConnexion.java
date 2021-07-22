package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.main.PluginMain;
import fr.minecraft.survival.plugin.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class onConnexion implements Listener {
    XML xml = new XML();
    FileConfiguration config = PluginMain.getInstance().getConfig();
    @EventHandler
    public   void onPlayerJoin(PlayerJoinEvent event) throws Exception {
        xml.addPlayer(new File(".\\config.xml"),event.getPlayer().getUniqueId().toString());
        event.getPlayer().sendMessage("Vous  aviez " + xml.get_points(event.getPlayer().getUniqueId().toString()) + " Points" +  ChatColor.AQUA);
        if( ! config.contains("diamondMined." + event.getPlayer().getDisplayName() ) && !config.contains("ancientDebritMined." + event.getPlayer().getDisplayName()) ){
            config.set("diamondMined." + event.getPlayer().getDisplayName()  +".diamondMined" , 0);
            config.set("diamondMined." + event.getPlayer().getDisplayName()  +".stoneMined" , 0);
            config.set("diamondMined." + event.getPlayer().getDisplayName()  +".ratio" , 0);
            config.set("ancientDebritMined." + event.getPlayer().getDisplayName()  +".netherrackMined" , 0);
            config.set("ancientDebritMined." + event.getPlayer().getDisplayName()  +".ancientDebritMined" , 0);
            config.set("ancientDebritMined." + event.getPlayer().getDisplayName()  +".ratio" , 0);

            PluginMain.getInstance().saveConfig();
        }

    }
}
