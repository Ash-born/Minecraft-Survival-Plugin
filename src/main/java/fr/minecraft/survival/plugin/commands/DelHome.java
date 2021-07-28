package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DelHome implements CommandExecutor {
    FileConfiguration config = PluginMain.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player p = (Player) commandSender;
        if (!(commandSender instanceof Player)) {
            return false;
        }

        if (args.length >= 1) {
            String home = args[0].toLowerCase();
            if (config.contains("home." + p.getName() + "." + home)) {
                config.set("home." + p.getName() + "." + home, null);
                p.sendMessage(ChatColor.AQUA + "Le home  ' " + home + " '   a été supprimé");
                int test = config.getInt("homecree." + p.getDisplayName());

                try {
                    config.set("homecree." + p.getDisplayName(), test - 1);
                    PluginMain.getInstance().saveConfig();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                p.sendMessage(ChatColor.RED + "Erreur , l'home " + args[0] + " n'existe pas");
            }
        } else {
            p.sendMessage(ChatColor.RED + "Erreur : /delhome <nom_du_home>");
        }

        return false;
    }
}
