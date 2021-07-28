package fr.minecraft.survival.plugin.utils;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import fr.minecraft.survival.plugin.main.PluginMain;

public class Bid {
    static BidParty currentBidParty = new BidParty();

    Random random;
    Server server = Bukkit.getServer();
    BukkitScheduler scheduler = server.getScheduler();
    BidTimer bidTimer;

    // BID_TIME in minutes
    int BID_TIME = 1;

    // BID_DELAY_TIME in minutes
    int BID_DELAY_TIME = 60;

    // Minimum players required to start auction
    int MIN_PLAYERS = 2;

    public Bid() {
        random = new Random();
        scheduler.scheduleSyncRepeatingTask(PluginMain.getInstance(), this::startBidParty, 0, BID_DELAY_TIME * 60 * 20);
    }

    public static BidParty getCurrentBidParty() {
        return currentBidParty;
    }

    public void startBidParty() {
        if (Bukkit.getOnlinePlayers().size() < MIN_PLAYERS)
            return;

        // Every one second, we run this task
        if (bidTimer != null && !bidTimer.isCancelled()) {
            bidTimer.cancel();
            bidTimer = null;
        }

        bidTimer = new BidTimer(BID_TIME * 60);
        bidTimer.runTaskTimer(PluginMain.getInstance(), 0, 1 * 20);

        ItemStack bidItem = randomItem();
        // Can't do better code to get name for item than this :(
        String bidItemName = bidItem.getItemMeta().hasDisplayName() ? bidItem.getItemMeta().getDisplayName()
                : bidItem.getType().name().replace("_", " ");

        currentBidParty.startBid(bidItem, bidTimer);
        server.broadcastMessage(
                ChatColor.DARK_PURPLE + bidItemName + " en enchère !\nEnchérissez avec la commande /bet <montant> !");

        scheduler.scheduleSyncDelayedTask(PluginMain.getInstance(), () -> {
            Player winner = currentBidParty.getBestBidder();
            String price = String.format("%.2f", currentBidParty.getBestBidPrice());

            if (winner != null) {
                winner.getServer()
                        .broadcastMessage(ChatColor.AQUA + "Le joueur " + winner.getDisplayName()
                                + " a gagné l'enchère !\nIl remporte donc l'item " + bidItemName + " au prix de "
                                + price + " points !");
                winner.getInventory().addItem(bidItem);
                winner.sendMessage(
                        ChatColor.GREEN + "" + ChatColor.ITALIC + "Tu as remporté l'item " + bidItemName + " ! GG!");
                currentBidParty.giveMoneyBack();
            } else {
                server.broadcastMessage(ChatColor.AQUA + "Personne n'a remporté l'enchère !");
            }

            currentBidParty.endBid();
        }, (long) BID_TIME * 60L * 20L);
    }

    public ItemStack randomItem() {
        Material randomMaterial = Material.values()[random.nextInt(Material.values().length)];
        return new ItemStack(randomMaterial);
    }
}
