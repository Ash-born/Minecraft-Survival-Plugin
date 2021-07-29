package fr.minecraft.survival.plugin.main;

import fr.minecraft.survival.plugin.commands.*;
import fr.minecraft.survival.plugin.events.*;
import fr.minecraft.survival.plugin.utils.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class PluginMain extends JavaPlugin {
    public static PluginMain instance;
    public static HashMap<Player, Location> frozenPlayers = new HashMap<>();
    public static HashMap<String, String> chunks = new HashMap<>();
    public Bid bid;

    public static PluginMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("onEnable has been invoked!");

        PluginManager plManager = getServer().getPluginManager();
        plManager.registerEvents(new onConnexion(), this);
        plManager.registerEvents(new onInteractWithClaimedChunk(), this);
        plManager.registerEvents(new onTradeInvClick(), this);
        plManager.registerEvents(new onBlockMined(), this);
        plManager.registerEvents(new onPlayerConnect(), this);
        plManager.registerEvents(new onPlayerLeft(), this);
        plManager.registerEvents(new onPlayerMove(), this);

        getCommand("points").setExecutor(new Points());
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("home").setExecutor(new Home());
        getCommand("delhome").setExecutor(new DelHome());
        getCommand("bet").setExecutor(new Bet());
        getCommand("ratio").setExecutor(new Ratio());
        getCommand("trade").setExecutor(new Trade());
        getCommand("pay").setExecutor(new Pay());
        getCommand("auction").setExecutor(new Auction());
        getCommand("stats").setExecutor(new Stats());
        getCommand("claim").setExecutor(new Claim());
        getCommand("unclaim").setExecutor(new Unclaim());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("freeze").setExecutor(new Freeze());

        bid = new Bid();
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
        bid = null;
        saveConfig();
    }

}
