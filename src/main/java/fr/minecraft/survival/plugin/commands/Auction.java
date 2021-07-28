package fr.minecraft.survival.plugin.commands;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import fr.minecraft.survival.plugin.utils.Bid;
import fr.minecraft.survival.plugin.utils.BidParty;
import net.md_5.bungee.api.ChatColor;

public class Auction implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        BidParty bidParty = Bid.getCurrentBidParty();
        if (bidParty == null || bidParty.hasFinished) {
            player.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC
                    + "Aucune enchère n'est en cours actuellement, réessayez plus tard !");
            return false;
        }

        int seconds = bidParty.getTimeLeft();
        String time = DurationFormatUtils.formatDuration(seconds * 1000, "mm:ss");

        double topBidPrice = bidParty.getBestBidPrice();
        double playerBidPrice = bidParty.getBidPrice(player);

        player.sendMessage(ChatColor.ITALIC + "" + ChatColor.BLUE + "Temps restant pour l'enchère : " + time);
        player.sendMessage(ChatColor.ITALIC + "" + ChatColor.DARK_GREEN + "Meilleur prix d'enchère : "
                + String.format("%.2f", bidParty.getBestBidPrice()));
        if (topBidPrice > 0) {
            player.sendMessage(ChatColor.ITALIC + "" + ChatColor.DARK_GREEN + "Meilleur enchéreur : "
                    + bidParty.getBestBidder().getDisplayName());
        }
        player.sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "Votre prix d'enchère actuel : "
                + String.format("%.2f", playerBidPrice));

        return true;
    }

}
