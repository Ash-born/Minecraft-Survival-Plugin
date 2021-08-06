package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.commands.Classes;
import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onClassInventoryClick implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event){
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory();
        FileConfiguration config = PluginMain.getInstance().getConfig();
        Player p = (Player) event.getWhoClicked();
        String classe = config.getString("classe." + p.getDisplayName());
        int classlevel = config.getInt(    classe +"." + p.getDisplayName() + ".level");
        if(classe!=null){
            if(classe.equals("guerrier")) {

                if (clicked.isSimilar(new ItemStack(Material.SHIELD))) {
                    p.getInventory().setItem(40, new ItemStack(Material.AIR, 1));

                    event.setCancelled(true);
                    event.setResult(Event.Result.DENY);
                }
            }

            }






        if(inventory.contains(Classes.Guerrier) && inventory.contains(Classes.Archer)&& inventory.contains(Classes.Mineur) && inventory.contains(Classes.Tank) && inventory.contains(Classes.vitre)){
            event.setCancelled(true);



            if(clicked.isSimilar(Classes.Guerrier)){


                config.set("classe." + p.getDisplayName() , "guerrier");
                p.closeInventory();
                   p.sendMessage(ChatColor.GREEN +"Vous etes désormais un Guerrier");

                       if(!p.getInventory().isEmpty()){
                            ItemStack item = p.getInventory().getItemInOffHand().clone();
                            item.setAmount(-1);

                           p.getInventory().addItem( p.getInventory().getItemInOffHand().clone());

                           p.getInventory().setItemInOffHand(item);

                       }

                p.setMaxHealth(20 );
                   p.closeInventory();
                PluginMain.getInstance().saveConfig();
                }
            if(clicked.isSimilar(Classes.Tank)){
                config.set("classe." + p.getDisplayName(),"tank");
                p.sendMessage(ChatColor.GREEN +"Vous etes désormais un Tank");

                p.closeInventory();
                p.setMaxHealth(24 + (classlevel* 2));
                PluginMain.getInstance().saveConfig();
            }
            if(clicked.isSimilar(Classes.Mineur)){
                config.set("classe." + p.getDisplayName(),"mineur");
                p.sendMessage(ChatColor.GREEN +"Vous etes désormais un Mineur");

                p.setMaxHealth(14 );
                p.closeInventory();
            }
            if(clicked.isSimilar(Classes.Archer)){
                config.set("classe." + p.getDisplayName(),"archer");
                p.sendMessage(ChatColor.GREEN +"Vous etes désormais un Archer");

                p.setMaxHealth(12 );
                p.closeInventory();
            }
            if(clicked.isSimilar(Classes.Null)){
                config.set("classe." + p.getDisplayName(),null);
                p.sendMessage(ChatColor.GREEN +"Vous venez de reset votre classe");
                p.setMaxHealth(10 );
                p.closeInventory();
            }

            }

        }


    }

