package fr.minecraft.survival.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Vanish implements CommandExecutor {

    public static ArrayList<Player> vanished = new ArrayList<Player>();
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "§eVanish §8Â» §c§lKeine Rechte");
            return true;
        }

        Player p = (Player) sender;
        if(p.isOp()){
        if (cmd.getName().equalsIgnoreCase("v") || cmd.getName().equalsIgnoreCase("vanish")) {
            if (p.hasPermission("defelhopper.system.vanish"))

                if (!vanished.contains(p)) {
                    for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                        pl.hidePlayer(p);
                        pl.sendMessage(ChatColor.YELLOW  +  p.getDisplayName() + " left the game");
                    }
                    if (args.length >= 1) {
                        p.setGameMode(GameMode.CREATIVE);
                    } else {
                        p.setGameMode(GameMode.SPECTATOR);
                    }
                    
                    vanished.add(p);
                    p.setAllowFlight(true);
                    p.sendMessage(ChatColor.GREEN + "Vous venez de vous mettre ne vanish");
                    return true;

                } else {
                    for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                        pl.showPlayer(p);
                        pl.sendMessage(ChatColor.YELLOW  +  p.getDisplayName() + " joined the game");
                    }
                    if (args.length >= 1) {
                        p.setGameMode(GameMode.SURVIVAL);
                    } else {
                        p.setGameMode(GameMode.CREATIVE);
                    }
                    vanished.remove(p);
                    p.sendMessage(ChatColor.GREEN + "Vous n'etes plus en vanish");

                    return true;

                }
        }
        else{
            p.sendMessage(ChatColor.RED +"Vous n'avez pas les permission d'utiliser cette comande ");
            return true;
        }
        }
        return true;
    }
    public Vanish(){

    }
}
