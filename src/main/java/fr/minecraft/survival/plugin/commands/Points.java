package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.utils.XML;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Points implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;

            XML xml = new XML();

            p.sendMessage(ChatColor.AQUA + "Vous avez actuellement " + xml.get_points(p.getUniqueId().toString()) + " points.");
        return false;
            }

        return false;

    }
}
