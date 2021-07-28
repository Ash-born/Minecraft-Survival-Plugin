package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.main.PluginMain;
import fr.minecraft.survival.plugin.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onConnexion implements Listener {
    FileConfiguration config = PluginMain.getInstance().getConfig();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws Exception {
        if (!config.contains("diamondMined." + event.getPlayer().getDisplayName())
                && !config.contains("ancientDebritMined." + event.getPlayer().getDisplayName())) {
            config.set("diamondMined." + event.getPlayer().getDisplayName() + ".diamondMined", 0);
            config.set("diamondMined." + event.getPlayer().getDisplayName() + ".stoneMined", 0);
            config.set("diamondMined." + event.getPlayer().getDisplayName() + ".ratio", 0);
            config.set("ancientDebritMined." + event.getPlayer().getDisplayName() + ".netherrackMined", 0);
            config.set("ancientDebritMined." + event.getPlayer().getDisplayName() + ".ancientDebritMined", 0);
            config.set("ancientDebritMined." + event.getPlayer().getDisplayName() + ".ratio", 0);

            PluginMain.getInstance().saveConfig();
        }
        if (!config.contains("points." + event.getPlayer().getDisplayName())) {
            config.set("points." + event.getPlayer().getDisplayName(), 0.0);
            config.set("maxhome." + event.getPlayer().getDisplayName(), 2);
            config.set("homecree." + event.getPlayer().getDisplayName(), 0);
            config.set("maxclaim." + event.getPlayer().getDisplayName(), 1);
            config.set("claimcree." + event.getPlayer().getDisplayName(), 0);

            PluginMain.getInstance().saveConfig();
        } else {
            event.getPlayer().sendMessage(ChatColor.AQUA + "Vous avez "
                    + config.getDouble("points." + event.getPlayer().getDisplayName()) + " points.");
        }

    }
}
