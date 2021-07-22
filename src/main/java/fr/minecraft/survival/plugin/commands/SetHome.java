package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import fr.minecraft.survival.plugin.utils.XML;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player p = (Player) commandSender;
        XML xml = new XML();
        if (!(commandSender instanceof Player)) {

            return false;
            }

            if (args.length >= 1) {
                try {

                    FileConfiguration config = PluginMain.getInstance().getConfig();
                    String home = args[0].toLowerCase();
                    String playerName = p.getName();
                    int maxHomes = config.getInt("maxhome." + p.getDisplayName());
                    int playerHomes = config.getInt("homecree." + p.getDisplayName());
                    if ( maxHomes > playerHomes) {
                        if(config.contains("home." + playerName + "." + home)){
                            p.sendMessage(ChatColor.RED + "Un home du meme nom est deja existant");
                            return false;
                        } else {
                            Location playerLoc = p.getLocation();
                            config.set("home." + playerName + ".number" + playerHomes + ".homeName" , home);
                            config.set("home." + playerName + "." + home + ".world", playerLoc.getWorld().getName());
                            config.set("home." + playerName + "." + home + ".x", playerLoc.getX());
                            config.set("home." + playerName + "." + home + ".y", playerLoc.getY());
                            config.set("home." + playerName + "." + home + ".z", playerLoc.getZ());
                            config.set("home." + playerName + "." + home + ".pitch", p.getEyeLocation().getPitch());
                            config.set("home." + playerName + "." + home + ".yaw", p.getEyeLocation().getYaw());
                            config.set("homecree." + p.getDisplayName(),playerHomes + 1);
                            PluginMain.getInstance().saveConfig();

                            p.sendMessage(ChatColor.GREEN + "Le home " + args[0] + " a été sauvegardé");

                        }
                    } else {
                        p.sendMessage(ChatColor.BOLD + "Limite de home atteinte ");
                    }

                }
                catch (Exception ignored){

                }
            }
            else {
                p.sendMessage(ChatColor.RED + " Erreur : Commande invalide . /sethome <nom_du_home>");
            }

            return false;
        }


}
