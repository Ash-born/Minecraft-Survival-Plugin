package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class onPlayerMove implements Listener {
    PluginMain pl = new PluginMain();
    @EventHandler
    public void onPlayerMoveBlock(PlayerMoveEvent e){
        if(!pl.frozenPlayers.containsKey(e.getPlayer())){
            return;
        }
        if(e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()){
            e.getPlayer().teleport(pl.frozenPlayers.get(e.getPlayer()));
        }
    }
}

