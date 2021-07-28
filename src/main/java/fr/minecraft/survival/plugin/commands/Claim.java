package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Claim implements CommandExecutor {

    FileConfiguration config = PluginMain.getInstance().getConfig();
    fr.minecraft.survival.plugin.utils.Chunk chunks = new fr.minecraft.survival.plugin.utils.Chunk();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            String name = p.getDisplayName();
            Chunk chunk = p.getLocation().getChunk();
            String ChunkId = chunk.getX()  + "." + chunk.getZ();
            int maxClaim = config.getInt("maxclaim." + name);
            int claimcree = config.getInt("claimcree." + name);
            if(chunks.isChunk(ChunkId)){
                p.sendMessage(ChatColor.RED + "This Chunk is already Claimed");
            }
            else{
                if(claimcree< maxClaim){
                    config.set("claimcree." + name , claimcree + 1);
                    PluginMain.getInstance().saveConfig();
                    chunks.addChunk(ChunkId,name);
                    p.sendMessage(ChatColor.GREEN + "The Chunk has been Claimed");
                    for (int i = 0; i < p.getWorld().getPlayers().size(); i++) {
                        p.getWorld().getPlayers().get(i).sendMessage(ChatColor.DARK_PURPLE + name  + " has claimed the Chunk with X : " + chunk.getX() + ". And Z :  " + chunk.getZ());
                    }
                }
                else{
                    p.sendMessage(ChatColor.RED + "Vous n'avez plus de Claim libre");
                }
            }

        }




        return false;
    }
}
