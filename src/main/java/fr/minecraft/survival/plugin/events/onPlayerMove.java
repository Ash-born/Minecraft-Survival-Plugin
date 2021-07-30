package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.main.PluginMain;
import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class onPlayerMove implements Listener {
    HashMap<Player, Location> frozenPlayers = PluginMain.frozenPlayers;

    @EventHandler
    public void onPlayerMoveBlock(PlayerMoveEvent e) {
        if (!frozenPlayers.containsKey(e.getPlayer())) {
            return;
        }
        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
            e.getPlayer().teleport(frozenPlayers.get(e.getPlayer()));
        }
    }
}

