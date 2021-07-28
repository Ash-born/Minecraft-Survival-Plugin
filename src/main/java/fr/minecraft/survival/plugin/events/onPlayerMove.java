package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class onPlayerMove implements Listener {

    @EventHandler
    public void onPlayerMoveBlock(PlayerMoveEvent e){
        if(!PluginMain.frozenPlayers.containsKey(e.getPlayer())){
            return;
        }
        if(e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()){
            e.getPlayer().teleport(PluginMain.frozenPlayers.get(e.getPlayer()));
        }
    }
}

