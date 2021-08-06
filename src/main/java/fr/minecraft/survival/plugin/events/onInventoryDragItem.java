package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;

public class onInventoryDragItem implements Listener {



    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e){

        FileConfiguration config = PluginMain.getInstance().getConfig();
        Player p = (Player) e.getWhoClicked();
        String classe = config.getString("classe." + p.getDisplayName());
        if (classe == null ) return;
        if( e.getInventory().getType() != InventoryType.CRAFTING|| !e.getInventorySlots().contains(40)) {

            return;
        }
        else   if(classe.equals("guerrier")){

            e.setResult(Event.Result.DENY);
            e.setCancelled(true);
        }
    }
}
