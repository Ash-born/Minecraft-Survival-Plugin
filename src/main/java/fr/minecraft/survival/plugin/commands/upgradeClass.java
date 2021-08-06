package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class upgradeClass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player target = (Player) commandSender;
        FileConfiguration config = PluginMain.getInstance().getConfig();
        String classe = config.getString("classe." + target.getDisplayName());
        double points = config.getDouble("points." + target.getDisplayName());
        int classlevel = config.getInt(    classe +"." + target.getDisplayName() + ".level");
        if(classlevel == 1 ) {
            if (points >= 200) {

                config.set("points." + target.getDisplayName(), points-200 );
                config.set(classe +"." + target.getDisplayName() + ".level",2);
                classlevel = config.getInt(    classe +"." + target.getDisplayName() + ".level");
                target.sendMessage(ChatColor.AQUA + "Votre classe "+ classe + " est devenu desormais de lvl "+ classlevel);
            }
            else{
                target.sendMessage(ChatColor.RED +" Vous n'avez pas assez de points");
            }
        }
        if(classlevel == 2 ) {
            if (points >= 500) {

                config.set("points." + target.getDisplayName(), points-500 );
                config.set(classe +"." + target.getDisplayName() + ".level",3);
                classlevel = config.getInt(    classe +"." + target.getDisplayName() + ".level");
                target.sendMessage(ChatColor.AQUA + "Votre classe "+ classe + " est devenu desormais de lvl "+ classlevel);

            } else{
                target.sendMessage(ChatColor.RED +" Vous n'avez pas assez de points");
            }
        }
        if(classlevel == 3 ) {
            if (points >= 850) {

                config.set("points." + target.getDisplayName(), points-850 );
                config.set(classe +"." + target.getDisplayName() + ".level",4);
                classlevel = config.getInt(    classe +"." + target.getDisplayName() + ".level");

                target.sendMessage(ChatColor.AQUA + "Votre classe "+ classe + " est devenu desormais de lvl "+ classlevel);
            }
            else{
                target.sendMessage(ChatColor.RED +" Vous n'avez pas assez de points");
            }
        }
        if(classlevel == 4 ) {
            if (points >= 1250) {

                config.set("points." + target.getDisplayName(), points-1250 );
                config.set(classe +"." + target.getDisplayName() + ".level",5);
                classlevel = config.getInt(    classe +"." + target.getDisplayName() + ".level");

                target.sendMessage(ChatColor.AQUA + "Votre classe "+ classe + " est devenu desormais de lvl "+ classlevel);
            }
            else{
                target.sendMessage(ChatColor.RED +" Vous n'avez pas assez de points");
            }
        }



        return false;
    }
}
