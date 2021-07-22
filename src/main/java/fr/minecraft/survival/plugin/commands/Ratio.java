package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Ratio implements CommandExecutor {
    FileConfiguration config = PluginMain.getInstance().getConfig();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            return false;
        }
        Player p = (Player) commandSender ;
        Player suspect = null;
        if(p.isOp()) {
            if (strings.length >= 1) {
                for (int i = 0; i < p.getWorld().getPlayers().size(); i++) {
                    if (strings[0].equals(p.getWorld().getPlayers().get(i).getDisplayName())) {
                        suspect = p.getWorld().getPlayers().get(i);

                    }
                }
                if(suspect == null ){
                    p.sendMessage(ChatColor.RED + "Joueur introuvable");
                    return false;
                }
                int diamsMined = config.getInt("diamondMined." + suspect.getDisplayName()  +".diamondMined");
                int  stoneMined = config.getInt("diamondMined." + suspect.getDisplayName()  +".stoneMined");
                double ratioDiams = config.getDouble("diamondMined." + suspect.getDisplayName()  +".ratio");
                int netherrackMined = config.getInt("ancientDebritMined." + suspect.getDisplayName()  +".netherrackMined");
                int  ancientDebritMined =  config.getInt("ancientDebritMined." + suspect.getDisplayName()  +".ancientDebritMined");
                double ratioAncientDebrit = config.getDouble("ancientDebritMined." + suspect.getDisplayName() + ".ratio");


                p.sendMessage( ChatColor.AQUA + "Stats of " +  suspect.getDisplayName() + "are : ");
                p.sendMessage(ChatColor.YELLOW + "Stone mined : " + stoneMined);
                p.sendMessage(ChatColor.YELLOW + "Diams mined : " + diamsMined);
                p.sendMessage(ChatColor.YELLOW + "Ratio Diams/Stone  : " + ratioDiams + " %");
                p.sendMessage(ChatColor.YELLOW + "Netherrack mined : " + netherrackMined);
                p.sendMessage(ChatColor.YELLOW + "AncientDebrit mined : " + ancientDebritMined);
                p.sendMessage(ChatColor.YELLOW + "Ratio AncientDebrit/Netherrack mined : " + ratioAncientDebrit);



            } else {
                p.sendMessage(ChatColor.RED + "Erreur de syntaxe  :  /ratio <PlayerName>");
            }
        }
        else{
            p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande");
        }

        return false;
    }
}
