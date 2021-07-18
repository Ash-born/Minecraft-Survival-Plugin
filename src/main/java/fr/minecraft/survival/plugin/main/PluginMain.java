package fr.minecraft.survival.plugin.main;

import fr.minecraft.survival.plugin.commands.*;
import fr.minecraft.survival.plugin.events.onConnexion;
import fr.minecraft.survival.plugin.events.onTradeInvClick;
import fr.minecraft.survival.plugin.utils.*;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin  {
    public  static PluginMain instance;

    public static PluginMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        getLogger().info("onEnable has been invoked!");
        getServer().getPluginManager().registerEvents(new onConnexion(), this);
        getServer().getPluginManager().registerEvents(new onTradeInvClick(), this);
        XML.create_xml(null,null);
        getCommand("points").setExecutor(new Points());
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("home").setExecutor(new Home());
        getCommand("delhome").setExecutor(new DelHome());
        getCommand("trade").setExecutor(new Trade());
        instance = this;

    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }

}
