package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Claim implements CommandExecutor {

    FileConfiguration config = PluginMain.getInstance().getConfig();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {




        return false;
    }
}
