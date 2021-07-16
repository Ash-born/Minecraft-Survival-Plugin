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
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player p = (Player) commandSender;
        if(commandSender instanceof Player){
            if(strings.length == 0 || strings.length >= 2){
                p.sendMessage(ChatColor.RED + "Erreur : /delhome <nom_du_home>");
                return false;
            }
            else{
                if(PluginMain.getInstance().getConfig().contains("home." + p.getName() + "." + strings[0])){
                    XML xml = new XML();
                    PluginMain.getInstance().getConfig().set("home." + p.getName() + "." + strings[0],null);
                    p.sendMessage(ChatColor.AQUA  + "Le home " + strings[0] + "a été supprimé");
                    try {
                        xml.updateHomeCree(p.getUniqueId().toString(),(Integer.parseInt(xml.get_home_cree(p.getUniqueId().toString())) -  1 ) + "" );
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    p.sendMessage(ChatColor.RED + "Erreur , l'home " + strings[0] + "n'existe pas");
                    return false;
                }
            }
        }
        return false;
    }
}
