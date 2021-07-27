package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import fr.minecraft.survival.plugin.utils.Bid;
import fr.minecraft.survival.plugin.utils.BidParty;
import fr.minecraft.survival.plugin.utils.XML;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Bet implements CommandExecutor {
    XML xml = PluginMain.xml;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player))
            return false;

        Player player = (Player) commandSender;
        BidParty bidParty = Bid.getCurrentBidParty();
        if (bidParty == null || bidParty.hasFinished) {
            player.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Aucune enchère n'est en cours actuellement, réessayez plus tard !");
            return false;
        }

        int bidPrice;
        try {
            bidPrice = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Mauvaise entrée, Réessayez !");
            return false;
        }

        if (args.length >= 1) {
            String playerId = player.getUniqueId().toString();

            int playerBidPrice = bidParty.getBidPrice(player);
            int playerPoints = Integer.parseInt(xml.get_points(playerId));

            if (bidPrice > playerBidPrice && bidPrice <= playerPoints) {
                int pointsUpdate = playerPoints + playerBidPrice - bidPrice;
                bidParty.setBid(player, bidPrice);

                try {
                    xml.updatePoints(playerId, pointsUpdate + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                player.sendMessage(ChatColor.GREEN + "Votre demande a bien été enregistré !");
            } else if (bidPrice > playerPoints) {
                player.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Vous n'avez pas assez d'argent pour enchérir !");
            } else {
                player.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Votre montant est inférieur à votre dernière demande !");
            }
        }

        return false;
    }
}
