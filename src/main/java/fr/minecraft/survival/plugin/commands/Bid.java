package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import fr.minecraft.survival.plugin.utils.BidParty;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import java.util.Random;

public class Bid {
    static BidParty currentBidParty = new BidParty();

    Random random;
    Server server = Bukkit.getServer();
    BukkitScheduler scheduler = server.getScheduler();

    // BidTime in seconds
    int bidTime = 30;

    public Bid()
    {
        random = new Random();
        scheduler.scheduleSyncRepeatingTask(PluginMain.getInstance(), this::startBidParty, 0L, (long) bidTime * 2 * 20);
    }

    public void startBidParty()
    {
        /*if (Bukkit.getOnlinePlayers().size() < 2)
            return;*/

        ItemStack bidItem = randomItem();
        // Can't do better code to get name for item than this :(
        String bidItemName = bidItem.getItemMeta().hasDisplayName() ? bidItem.getItemMeta().getDisplayName() : bidItem.getType().name().replace("_", " ");

        currentBidParty.startBid(bidItem);
        server.broadcastMessage(ChatColor.DARK_PURPLE + bidItemName + " en enchère !\nEnchérissez avec la commande /bet <montant> !");

        scheduler.scheduleSyncDelayedTask(PluginMain.getInstance(), () -> {
            Player winner = currentBidParty.getBestBidder();
            int price = currentBidParty.getBestBidPrice();

            if (winner != null) {
                winner.getServer().broadcastMessage(ChatColor.AQUA + "Le joueur " + winner.getDisplayName() + " a gagné l'enchère !\nIl remporte donc l'item " + bidItemName + " au prix de " + price + " points !");
                winner.getInventory().addItem(bidItem);
                winner.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "Tu as remporté l'item " + bidItemName + " ! GG!");
            }
            else {
                server.broadcastMessage(ChatColor.AQUA + "Personne n'a remporté l'enchère !");
            }

            currentBidParty.endBid();
        }, bidTime * 20L);
    }

    public ItemStack randomItem()
    {
        Material randomMaterial = Material.values()[random.nextInt(Material.values().length)];
        return new ItemStack(randomMaterial);
    }

    public static BidParty getCurrentBidParty() {
        return currentBidParty;
    }
}
