package fr.minecraft.survival.plugin.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onMessage implements Listener {


    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        if(p.isOp()){
            String msg = event.getMessage();
            event.setCancelled(true);

            for (int i = 0; i < p.getWorld().getPlayers().size(); i++) {
                p.getWorld().getPlayers().get(i).sendMessage(ChatColor.DARK_RED  + "[ADMIN] " + p.getDisplayName()  + " : " +  ChatColor.WHITE  + msg);

            }

           }
        else{
            String msg = event.getMessage();
            event.setCancelled(true);
            for (int i = 0; i < p.getWorld().getPlayers().size(); i++) {
                p.getWorld().getPlayers().get(i).sendMessage( ChatColor.YELLOW +  p.getDisplayName()  + " : "  + ChatColor.WHITE + msg);

            }

        }



    }
}
