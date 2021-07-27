package fr.minecraft.survival.plugin.utils;

import fr.minecraft.survival.plugin.main.PluginMain;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class Chunk {
    


    public Chunk(){

    }
    public void addChunk(String ChunkId , String PlayerName){
        PluginMain.chunks.put(ChunkId,PlayerName);
    }

    public boolean isChunk(String ChunkId){

        return  PluginMain.chunks.containsKey(ChunkId);
    }

    public String getOwner(String ChunkId){
        return PluginMain.chunks.get(ChunkId);
    }
    public void removeOwner(String ChunkID, String PlayerName, Player p ){
        if(PluginMain.chunks.containsKey(ChunkID)){
            if(PluginMain.chunks.get(ChunkID).equals(PlayerName)){
                PluginMain.chunks.remove(ChunkID);
                p.sendMessage(ChatColor.AQUA + "Chunk unclaim avec succes");
                int maxClaim = PluginMain.getInstance().getConfig().getInt("maxclaim." + PlayerName);
                int claimcree = PluginMain.getInstance().getConfig().getInt("claimcree." + PlayerName);
                PluginMain.getInstance().getConfig().set("claimcree." + PlayerName,claimcree -1 );
            }
        }

    }
}
