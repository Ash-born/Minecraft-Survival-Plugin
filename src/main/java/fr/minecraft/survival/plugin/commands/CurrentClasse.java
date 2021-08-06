package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CurrentClasse implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        FileConfiguration config = PluginMain.getInstance().getConfig();
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (config.contains("classe." + p.getDisplayName())) {

                if (!config.getString("classe." + p.getDisplayName()).equals("null")) {
                    String classe = config.getString("classe." + p.getDisplayName());
                    int classlevel = config.getInt(    classe +"." + p.getDisplayName() + ".level");
                    p.sendMessage(ChatColor.AQUA + "Votre Classe actuelle est : " + classe);
                    p.sendMessage(ChatColor.AQUA + "Votre level de classe est : " + classlevel );
                    if(classe.equals("guerrier") || classe.equals("archer")){
                        p.sendMessage(ChatColor.YELLOW + "Bonus de classe Actuelle : " + (classlevel * 12.5) + " % de degats suplementaire");

                    }
                    else  if(classe.equals("tank")){
                        p.sendMessage(ChatColor.YELLOW + "Bonus de classe Actuelle : " + (classlevel * 12.5) + " % de degats reduit ");

                    }
                    else if (classe.equals("mineur")){

                        p.sendMessage(ChatColor.YELLOW + "Bonus de classe Actuelle" + (classlevel *12.5) + " % de doubler ses minerais");
                    }
                    PluginMain.getInstance().saveConfig();
                } else {
                    p.sendMessage(ChatColor.RED + "Vous n'avez pas choisis de classe . Veuillez  en choisir grace au /Classe");

                }

            }
        }


            return false;
        }
    }

