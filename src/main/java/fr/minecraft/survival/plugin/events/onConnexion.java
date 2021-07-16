package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class onConnexion implements Listener {
    XML xml = new XML();
    @EventHandler
    public   void onPlayerJoin(PlayerJoinEvent event) throws Exception {
        xml.addPlayer(new File(".\\config.xml"),event.getPlayer().getUniqueId().toString());
        event.getPlayer().sendMessage("Vous  aviez " + xml.get_points(event.getPlayer().getUniqueId().toString()) + " Points" +  ChatColor.AQUA);
    }
}
