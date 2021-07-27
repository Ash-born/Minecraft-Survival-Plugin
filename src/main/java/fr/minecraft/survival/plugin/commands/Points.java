package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Points implements CommandExecutor {
    static FileConfiguration config = PluginMain.getInstance().getConfig();

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;

            p.sendMessage(ChatColor.AQUA + "Vous avez actuellement " + getPoints(p));
            return false;
        }

        return false;

    }

    public static double getPoints(Player player) {

        return config.getDouble("points." + player.getName());
    }

    public static void setPoints(Player player, Double points) {

        config.set("points." + player.getName(), points);
        PluginMain.getInstance().saveConfig();
    }
}
