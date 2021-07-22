package fr.minecraft.survival.plugin.main;

import fr.minecraft.survival.plugin.commands.*;
import fr.minecraft.survival.plugin.events.onConnexion;
import fr.minecraft.survival.plugin.events.onTradeInvClick;
import fr.minecraft.survival.plugin.utils.Bid;
import fr.minecraft.survival.plugin.utils.XML;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin  {
    public  static PluginMain instance;
    public static XML xml = new XML();

    public static PluginMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        XML.create_xml(null, null);

        getLogger().info("onEnable has been invoked!");

        getServer().getPluginManager().registerEvents(new onConnexion(), this);
        getServer().getPluginManager().registerEvents(new onTradeInvClick(), this);

        getCommand("points").setExecutor(new Points());
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("home").setExecutor(new Home());
        getCommand("delhome").setExecutor(new DelHome());
        getCommand("bet").setExecutor(new Bet());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("trade").setExecutor(new Trade());
        getCommand("pay").setExecutor(new Pay());

        instance = this;
        new Bid();
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }

}
