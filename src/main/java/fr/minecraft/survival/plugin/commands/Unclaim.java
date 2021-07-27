package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.utils.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Unclaim implements CommandExecutor {

    Chunk chunk = new Chunk();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(commandSender instanceof Player){
            org.bukkit.Chunk chunks = p.getLocation().getChunk();
            String ChunkID = chunks.getX() + "." + chunks.getZ();
            chunk.removeOwner(ChunkID,p.getDisplayName(),p);
        }
        return false;
    }
}
