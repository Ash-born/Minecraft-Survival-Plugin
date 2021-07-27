package fr.minecraft.survival.plugin.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.minecraft.survival.plugin.main.PluginMain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BidParty {
    ItemStack bidItem;
    HashMap<Player, Double> bidders;
    BidTimer timer;
    FileConfiguration config = PluginMain.getInstance().getConfig();
    public boolean hasFinished = true;

    public BidParty()
    {
        bidders = new HashMap<>();
    }

    public void startBid(ItemStack item, BidTimer timer)
    {
        bidItem = item;
        hasFinished = false;
        this.timer = timer;
    }

    public void endBid()
    {
        hasFinished = true;
        bidders.clear();
        bidItem = null;
        timer = null;
    }

    public void setBid(Player bidder, double bidValue)
    {
        bidders.put(bidder, bidValue);
    }

    public ItemStack getBidItem()
    {
        return bidItem;
    }

    public Player getBestBidder()
    {
        if (!bidders.keySet().isEmpty())
        {
            double bestBidValue = getBestBidPrice();
            for (Map.Entry<Player, Double> entry : bidders.entrySet()) {
                if (Objects.equals(bestBidValue, entry.getValue())) {
                    return entry.getKey();
                }
            }
        }

        return null;
    }

    public double getBestBidPrice()
    {
        if (!bidders.keySet().isEmpty()) {
            return Collections.max(bidders.values());
        }

        return 0;
    }

    public double getBidPrice(Player bidder)
    {
        if (!bidders.keySet().isEmpty() && bidders.containsKey(bidder)) {
            return bidders.get(bidder);
        }

        return 0;
    }

    public Integer getTimeLeft() {
        return timer.getTimeLeft();
    }

    public void giveMoneyBack() {
        double bestBidPrice = getBestBidPrice();
        if (bestBidPrice <= 0) {
            return;
        }

        for (Player bidder: bidders.keySet()) {
            double bidderBidPrice = getBidPrice(bidder);
            if (bidderBidPrice == bestBidPrice) {
                continue;
            }

            double bidderPoints = config.getDouble("points." + bidder.getName());
            config.set("points." + bidder.getName(), bidderPoints + bidderBidPrice);
        }
    }
}
