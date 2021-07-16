package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
            if(commandSender instanceof Player){
                if(s.equalsIgnoreCase("home")){
                     if(strings.length == 0 || strings.length >= 2){
                         p.sendMessage(ChatColor.RED + "Erreur : /home <nom_du_home>");
                     }
                    if(strings.length == 1){
                         if(PluginMain.getInstance().getConfig().contains("home." + p.getName() +  "." + strings[0])){
                            World w = Bukkit.getServer().getWorld(PluginMain.getInstance().getConfig().getString("home." + p.getName() +  "." + strings[0] +".world"));
                            double x = PluginMain.getInstance().getConfig().getDouble("home." + p.getName() +  "." + strings[0] + ".x");
                             double y = PluginMain.getInstance().getConfig().getDouble("home." + p.getName() +  "." + strings[0] + ".y");
                             double z = PluginMain.getInstance().getConfig().getDouble("home." + p.getName() +  "." + strings[0] + ".z");
                             double pitch = PluginMain.getInstance().getConfig().getDouble("home." + p.getName() +  "." + strings[0] + ".pitch");
                             double yaw = PluginMain.getInstance().getConfig().getDouble("home." + p.getName() +  "." + strings[0] + ".yaw");
                             p.teleport(new Location(w,x,y,z,(float)yaw,(float) pitch));
                             p.sendMessage(ChatColor.GREEN + "Vous avez été teleporté a votre home  " + strings[0]);
                         }
                         else{
                             p.sendMessage(ChatColor.RED + "Home "+ strings[0] +" Inexistant");
                         }
                        return false;
                    }
                }


            }

        return false;
    }
}
