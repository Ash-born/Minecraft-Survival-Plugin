package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import fr.minecraft.survival.plugin.utils.XML;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class DelHome implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player p = (Player) commandSender;
        if (!(commandSender instanceof Player)){
            return false;
        }

        if (args.length >= 1){
            String home = args[0].toLowerCase();
            if (PluginMain.getInstance().getConfig().contains("home." + p.getName() + "." + home)){
                XML xml = new XML();
                PluginMain.getInstance().getConfig().set("home." + p.getName() + "." + home,null);
                p.sendMessage(ChatColor.AQUA  + "Le home " + home + "a été supprimé");

                try {
                    xml.updateHomeCree(p.getUniqueId().toString(),(Integer.parseInt(xml.get_home_cree(p.getUniqueId().toString())) -  1 ) + "" );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                p.sendMessage(ChatColor.RED + "Erreur , l'home " + args[0] + "n'existe pas");
            }
        }
        else{
            p.sendMessage(ChatColor.RED + "Erreur : /delhome <nom_du_home>");
        }

        return false;
    }
}
