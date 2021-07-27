package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pay implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player) || args.length < 2)
            return false;

        Player sender = (Player) commandSender;
        Player receiver = Bukkit.getPlayer(args[0]);
        if (receiver == null) {
            sender.sendMessage(ChatColor.RED + "Joueur introuvable !");

        } else if (receiver.getUniqueId().equals(sender.getUniqueId())) {
            sender.sendMessage(ChatColor.DARK_RED + "Tu ne peux pas envoyer de l'argent à toi-même !");

        } else {
            double pts;
            try {
                pts = Double.parseDouble(args[1]);
                if (pts < 2) {
                    sender.sendMessage(
                            ChatColor.DARK_RED + "Vous ne pouvez pas envoyer un montant inférieur à 2 points !");
                    return false;
                }
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.DARK_RED + "Mauvaise entrée, réessayez !");
                return false;
            }

            double senderPoints = Points.getPoints(sender);
            if (senderPoints >= pts) {
                try {
                    Points.setPoints(sender, senderPoints - pts);
                    Points.setPoints(receiver, Points.getPoints(receiver) + pts);

                    sender.sendMessage(ChatColor.GREEN + "" + pts + " points ont bien été envoyés chez "
                            + receiver.getDisplayName() + " !");
                    receiver.sendMessage(ChatColor.AQUA + "Vous avez reçu " + pts + " points de la part de "
                            + sender.getDisplayName() + " !");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Vous n'avez pas assez d'argent pour envoyer ce montant !");
            }
        }

        return true;
    }
}
