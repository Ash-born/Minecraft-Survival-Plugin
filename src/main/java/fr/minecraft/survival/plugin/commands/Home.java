package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player p = (Player) commandSender;
            if (!(commandSender instanceof Player)){
                return false;
            }

            if (args.length >= 1){
                FileConfiguration config = PluginMain.getInstance().getConfig();
                String playerName = p.getName();
                String home = args[0].toLowerCase();
                if (config.contains("home." + playerName +  "." + home)){
                    World w = Bukkit.getServer().getWorld(config.getString("home." + p.getName() +  "." + home + ".world"));

                    double x = config.getDouble("home." + playerName +  "." + home + ".x");
                    double y = config.getDouble("home." + playerName +  "." + home + ".y");
                    double z = config.getDouble("home." + playerName +  "." + home + ".z");

                    double pitch = config.getDouble("home." + playerName +  "." + home + ".pitch");
                    double yaw = config.getDouble("home." + playerName +  "." + home + ".yaw");

                    p.teleport(new Location(w, x, y, z,(float) yaw,(float) pitch));
                    p.sendMessage(ChatColor.GREEN + "Vous avez été teleporté a votre home  " + home);
                }
                else{
                    p.sendMessage(ChatColor.RED + "Home "+ home +" Inexistant");
                }
            }
            else {
                p.sendMessage(ChatColor.RED + "Erreur : /home <nom_du_home>");
            }

        return false;
    }
}
