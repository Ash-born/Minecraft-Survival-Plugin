package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.commands.Vanish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onPlayerLeft implements Listener {
        Vanish vanish = new Vanish();
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        vanish.vanished.remove(e.getPlayer());
    }


}
