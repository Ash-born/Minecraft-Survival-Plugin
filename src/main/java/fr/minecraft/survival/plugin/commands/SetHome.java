package fr.minecraft.survival.plugin.commands;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import fr.minecraft.survival.plugin.main.PluginMain;
import fr.minecraft.survival.plugin.utils.XML;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        XML xml = new XML();
        if (commandSender instanceof Player) {
            if (s.equalsIgnoreCase("sethome")) {
                if (strings.length == 0 || strings.length >= 2) {
                    p.sendMessage(ChatColor.RED + " Erreur : Commande invalide . /sethome <nom_du_home>");
                } if(strings.length == 1) {
                    try {

                        if (Integer.parseInt(xml.get_max_homes(p.getUniqueId().toString())) > Integer.parseInt(xml.get_home_cree(p.getUniqueId().toString()))) {
                            PluginMain.getInstance().getConfig().set("home." + p.getName() + "." + strings[0] + ".world", p.getLocation().getWorld().getName());
                            PluginMain.getInstance().getConfig().set("home." + p.getName() + "." + strings[0] + ".world", p.getLocation().getX());
                            PluginMain.getInstance().getConfig().set("home." + p.getName() + "." + strings[0] + ".world", p.getLocation().getY());
                            PluginMain.getInstance().getConfig().set("home." + p.getName() + "." + strings[0] + ".world", p.getLocation().getZ());
                            PluginMain.getInstance().getConfig().set("home." + p.getName() + "." + strings[0] + ".world", p.getEyeLocation().getPitch());
                            PluginMain.getInstance().getConfig().set("home." + p.getName() + "." + strings[0] + ".world", p.getEyeLocation().getYaw());
                            PluginMain.getInstance().saveConfig();


                            p.sendMessage(ChatColor.GREEN + "Le home " + strings[0] + " a été sauvegardé");
                            xml.updateHomeCree(p.getUniqueId().toString(),(Integer.parseInt(xml.get_home_cree(p.getUniqueId().toString())) +  1 ) + "" );
                            return false;
                        } else {
                            p.sendMessage(ChatColor.BOLD + "Limite de home atteinte ");
                            return false;
                        }
                    }
                    catch(Exception e ){

                    }

                    }
                }

            }


            return false;
        }


}
