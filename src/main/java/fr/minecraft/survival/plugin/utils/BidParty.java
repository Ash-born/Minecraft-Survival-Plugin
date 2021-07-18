package fr.minecraft.survival.plugin.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BidParty {
    ItemStack bidItem;
    HashMap<Player, Integer> bidders;
    public boolean hasFinished = true;

    public BidParty()
    {
        bidders = new HashMap<>();
    }

    public void startBid(ItemStack item)
    {
        bidItem = item;
        hasFinished = false;
    }

    public void endBid()
    {
        hasFinished = true;
        bidders.clear();
        bidItem = null;
    }

    public void setBid(Player bidder, int bidValue)
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
            int bestBidValue = getBestBidPrice();
            for (Map.Entry<Player, Integer> entry : bidders.entrySet()) {
                if (Objects.equals(bestBidValue, entry.getValue())) {
                    return entry.getKey();
                }
            }
        }

        return null;
    }

    public Integer getBestBidPrice()
    {
        if (!bidders.keySet().isEmpty()) {
            return Collections.max(bidders.values());
        }

        return 0;
    }

    public Integer getBidPrice(Player bidder)
    {
        if (!bidders.keySet().isEmpty()) {
            return bidders.get(bidder);
        }

        return 0;
    }
}
