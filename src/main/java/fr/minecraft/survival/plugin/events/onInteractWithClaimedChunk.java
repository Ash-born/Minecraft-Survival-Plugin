package fr.minecraft.survival.plugin.events;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class onInteractWithClaimedChunk implements Listener {

    fr.minecraft.survival.plugin.utils.Chunk c = new fr.minecraft.survival.plugin.utils.Chunk();
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getClickedBlock() != null){
            Chunk chunk = event.getPlayer().getLocation().getChunk();
            String ChunkID = chunk.getX() +"." + chunk.getZ();
            if(c.isChunk(ChunkID)){
                Player p = event.getPlayer();
                if(!c.getOwner(ChunkID).equals(p.getDisplayName())){
                    if(!p.isOp()){
                        event.setCancelled(true);
                        p.sendMessage(ChatColor.RED + "This Chunk is already claimed , you can't touch anything on it");
                    }

                }
            }

        }
    }

}
