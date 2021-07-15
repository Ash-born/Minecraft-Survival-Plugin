package fr.minecraft.survival.plugin.main;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin  {

    @Override
    public void onEnable() {
        getLogger().info("onEnable has been invoked!");

    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }
}
