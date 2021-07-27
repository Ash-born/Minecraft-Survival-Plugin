package fr.minecraft.survival.plugin.main;

import fr.minecraft.survival.plugin.commands.*;
import fr.minecraft.survival.plugin.events.*;
import fr.minecraft.survival.plugin.utils.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class PluginMain extends JavaPlugin  {
    public  static PluginMain instance;

    public static PluginMain getInstance() {
        return instance;
    }
    public HashMap<Player, Location> frozenPlayers = new HashMap<Player, Location>();

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("onEnable has been invoked!");
        getServer().getPluginManager().registerEvents(new onConnexion(), this);
        getServer().getPluginManager().registerEvents(new onTradeInvClick(), this);

        getServer().getPluginManager().registerEvents(new onBlockMined(), this);
        getServer().getPluginManager().registerEvents(new onPlayerConnect(), this);
        getServer().getPluginManager().registerEvents(new onPlayerLeft(), this);

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


        new Bid();
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("freeze").setExecutor(new Freeze());



    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }

}
