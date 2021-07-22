package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.commands.Vanish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerConnect  implements Listener {
    Vanish vanish = new Vanish();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        for (Player p : vanish.vanished) {
            e.getPlayer().hidePlayer(p);
        }
    }
}
