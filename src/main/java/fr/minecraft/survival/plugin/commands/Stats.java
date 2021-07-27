package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Stats implements CommandExecutor {
    FileConfiguration config = PluginMain.getInstance().getConfig();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        String name = p.getDisplayName();
        double Points = config.getDouble("points." + name );
        int maxHome = config.getInt("maxhome." + name);
        int homecree = config.getInt("homecree." + name);
        int maxClaim = config.getInt("maxclaim." + name);
        int claimcree = config.getInt("claimcree." + name);
        p.sendMessage("Vous avez actuellement  " + Points + " Points");
        p.sendMessage("Vous avez actuellement   " + homecree + "/" + maxHome  + " Home  Cree");
        p.sendMessage("Vous avez actuellement   " + claimcree + "/" + maxClaim  + " Claim  Cree");

        return false;
    }
}
