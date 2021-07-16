package fr.minecraft.survival.plugin.main;

import fr.minecraft.survival.plugin.commands.DelHome;
import fr.minecraft.survival.plugin.commands.Home;
import fr.minecraft.survival.plugin.commands.Points;
import fr.minecraft.survival.plugin.commands.SetHome;
import fr.minecraft.survival.plugin.events.onConnexion;
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
        XML.create_xml(null,null);
        getCommand("points").setExecutor(new Points());
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("home").setExecutor(new Home());
        getCommand("delhome").setExecutor(new DelHome());
        instance = this;

    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }

}
