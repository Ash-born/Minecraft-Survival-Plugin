package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.commands.Vanish;
import fr.minecraft.survival.plugin.main.PluginMain;
import fr.minecraft.survival.plugin.utils.Bid;
import fr.minecraft.survival.plugin.utils.BidParty;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onPlayerConnect implements Listener {
    Vanish vanish = new Vanish();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        for (Player p : vanish.vanished) {
            player.hidePlayer(p);
        }
        

        BidParty bidParty = Bid.getCurrentBidParty();
        UUID playerId = player.getUniqueId();
        Inventory playerInv = player.getInventory();
        if (bidParty.offlineWinners.containsKey(playerId)) {
            ItemStack bidItem = bidParty.offlineWinners.get(playerId);
            bidParty.offlineWinners.remove(playerId);
            if (playerInv.firstEmpty() == -1) {
                player.getWorld().dropItem(player.getLocation(), bidItem);
            } else {
                player.getInventory().addItem(bidItem);
            }
        }

        Bid.getInstance().startTask();
    }
}
