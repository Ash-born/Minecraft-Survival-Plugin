package fr.minecraft.survival.plugin.main;

import fr.minecraft.survival.plugin.commands.*;
import fr.minecraft.survival.plugin.events.onBlockMined;
import fr.minecraft.survival.plugin.events.onConnexion;
import fr.minecraft.survival.plugin.utils.*;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin  {
    public static PluginMain instance;
    public static XML xml = new XML();

    public static PluginMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        XML.create_xml(null,null);
        instance = this;

        getLogger().info("onEnable has been invoked!");
        getServer().getPluginManager().registerEvents(new onConnexion(), this);
        getServer().getPluginManager().registerEvents(new onBlockMined(), this);

        getCommand("points").setExecutor(new Points());
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("home").setExecutor(new Home());
        getCommand("delhome").setExecutor(new DelHome());
        getCommand("bet").setExecutor(new Bet());
        getCommand("ratio").setExecutor(new Ratio());



    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }

}
