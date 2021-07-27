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
        player.sendMessage(ChatColor.ITALIC + "" + ChatColor.BLUE + "Temps restant pour l'enchère : " + time);
        player.sendMessage(ChatColor.ITALIC + "" + ChatColor.DARK_GREEN + "Top bid price : " + bidParty.getBestBidPrice());

        return true;
    }

}
