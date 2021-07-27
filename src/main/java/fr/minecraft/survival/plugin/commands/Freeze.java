package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Freeze implements CommandExecutor {

    PluginMain pl = new PluginMain();
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 1){
            Player p = (Player) sender;
            if(p.isOp()) {
                String targetName = args[0];
                if (Bukkit.getOfflinePlayer(targetName).getPlayer() != null) {
                    Player target = Bukkit.getPlayer(targetName);
                    if (pl.frozenPlayers.containsKey(target)) {
                        pl.frozenPlayers.remove(target);
                        p.sendMessage(ChatColor.GREEN + "Joueur n'est plus en freeze");
                    } else {
                        pl.frozenPlayers.put(target, target.getLocation().clone());
                        p.sendMessage(ChatColor.GREEN + "Joueur mit en freeze avec succes");
                    }
                }
            }
            else{
                p.sendMessage(ChatColor.RED + "Vous n'avez pas assez de permissions");
                return false;
            }
        }
        return false;
    }

}