package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class onSwapInventoryItem implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSwapHandItems(PlayerSwapHandItemsEvent e){
        FileConfiguration config = PluginMain.getInstance().getConfig();
        Player p =  e.getPlayer();
        String classe = config.getString("classe." + p.getDisplayName());

        if(classe.equals("guerrier")) {
            if (e.getMainHandItem().isSimilar(new ItemStack(Material.SHIELD))) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You can't use a shield in your second hand when you're a Berserker");
            }
        }
    }
}
