package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.commands.Trade;
import fr.minecraft.survival.plugin.utils.Item;
import fr.minecraft.survival.plugin.utils.XML;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class onTradeInvClick implements Listener {
    Trade tr = new Trade();
    XML xml = new XML();
    Item item = new Item();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        if (inventory.contains(tr.diamond_one)) {
            event.setCancelled(true);


            try {
                if (clicked != null) {
                    String uuid = player.getUniqueId().toString();
                    int points = Integer.parseInt(xml.get_points(uuid));
                    String name = clicked.getItemMeta().getDisplayName();
                    if (clicked.getType().equals(Material.DIAMOND)) {
                        if (clicked.getAmount() == 1) {
                            checkitem(clicked.getItemMeta().getDisplayName(), 1, 5, clicked, event, Material.DIAMOND);
                        } else if (clicked.getAmount() == 16) {
                            checkitem(clicked.getItemMeta().getDisplayName(), 16, 80, clicked, event, Material.DIAMOND);
                        } else if (clicked.getAmount() == 32) {
                            checkitem(clicked.getItemMeta().getDisplayName(), 32, 160, clicked, event, Material.DIAMOND);
                        } else {
                            checkitem(name, 64, 320, clicked, event, Material.DIAMOND);
                        }
                    }
                    if (clicked.getType().equals(Material.IRON_INGOT)) {
                        if (clicked.getAmount() == 1) {
                            checkitem(name, 1, 1, clicked, event, Material.IRON_INGOT);
                        } else if (clicked.getAmount() == 16) {
                            checkitem(name, 16, 16, clicked, event, Material.IRON_INGOT);
                        } else if (clicked.getAmount() == 32) {
                            checkitem(name, 32, 32, clicked, event, Material.IRON_INGOT);
                        } else {
                            checkitem(name, 64, 64, clicked, event, Material.IRON_INGOT);
                        }
                    }
                    if (clicked.getType().equals(Material.EMERALD)) {
                        if (clicked.getAmount() == 1) {
                            checkitem(name, 1, 3, clicked, event, Material.EMERALD);
                        } else if (clicked.getAmount() == 16) {
                            checkitem(name, 16, 48, clicked, event, Material.EMERALD);
                        } else if (clicked.getAmount() == 32) {
                            checkitem(name, 32, 96, clicked, event, Material.EMERALD);
                        } else {
                            checkitem(name, 64, 192, clicked, event, Material.EMERALD);
                        }
                    }
                    if (clicked.getType().equals(Material.GOLD_INGOT)) {
                        if (clicked.getAmount() == 1) {
                            checkitem(name, 1, 2, clicked, event, Material.GOLD_INGOT);
                        } else if (clicked.getAmount() == 16) {
                            checkitem(name, 16, 32, clicked, event, Material.GOLD_INGOT);
                        } else if (clicked.getAmount() == 32) {
                            checkitem(name, 32, 64, clicked, event, Material.GOLD_INGOT);
                        } else {
                            checkitem(name, 64, 128, clicked, event, Material.GOLD_INGOT);
                        }

                    }
                    if (clicked.getType().equals(Material.NETHER_BRICK)) {
                        if (clicked.getAmount() == 1) {
                            checkitem(name, 1, 60, clicked, event, Material.NETHER_BRICK);
                        } else if (clicked.getAmount() == 16) {
                            checkitem(name, 16, 960, clicked, event, Material.NETHER_BRICK);
                        } else if (clicked.getAmount() == 32) {
                            checkitem(name, 32, 1920, clicked, event, Material.NETHER_BRICK);
                        } else {
                            checkitem(name, 64, 3840, clicked, event, Material.NETHER_BRICK);
                        }
                    }
                    if (name.equals("Mending book <=> 1200 points")) {
                        checkitem(name, 1, 1200, clicked, event, Material.ENCHANTED_BOOK);
                    }
                    if (name.equals("One more home == 800 points")) {
                        checkitem(name, 1, 800, clicked, event, Material.DIRT);
                    }
                    if (name.equals("700 points == 1800 experiences orbes")) {
                        checkitem(name, 1, 700, clicked, event, Material.BOOKSHELF);
                    }
                    if (name.equals("Golden Apple <=> 2000 points")) {
                        checkitem(name, 1, 2000, clicked, event, Material.ENCHANTED_GOLDEN_APPLE);
                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void checkitem(String name,  int amount,  int price, ItemStack Clicked, InventoryClickEvent e, Material m) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        Player player = (Player) e.getWhoClicked();
        String uuid = player.getUniqueId().toString();

        int points = Integer.parseInt(xml.get_points(uuid));
        int home_cree = Integer.parseInt(xml.get_max_homes(uuid));
        e.setCancelled(true);



            if (e.isLeftClick()) {
                if (points >= price) {
                    if (  ! name.equals("700 points == 1800 experiences orbes") && !name.equals("Mending book <=> 1200 points") && !name.equals("One more home == 800 points")) {
                        if (item.hasAvaliableSlot(player)) {
                            xml.updatePoints(uuid, (points - price) + "");
                            for (int i = 0; i < amount; i++) {

                                player.getInventory().addItem(new ItemStack(m));
                            }
                            player.sendMessage(ChatColor.GREEN + "Achat Effectué avec succes ,  Vous avez encore "+ (points - price)  + " points " );
                        } else {
                            player.sendMessage(ChatColor.RED + "Inventory Full . You can't purshase anymore.");
                        }


                    } else if (name.equals("One more home == 800 points")) {
                        if (points >= price) {
                            xml.updatemaxHomes(uuid, (home_cree + 1) + "");
                            xml.updatePoints(uuid, (points - price) + "");
                            player.sendMessage(ChatColor.GREEN + "Home acheté  Effectué avec Succes , Vous avez encore " + (points - price)  + " points ");

                        } else {

                            player.sendMessage(ChatColor.RED + "Vous n'avez pas assez de points");
                        }
                    } else if (name.equals("700 points == 1800 experiences orbes")) {

                            player.giveExp(1800);
                            xml.updatePoints(uuid, (points - price) + "");
                        player.sendMessage(ChatColor.GREEN + "Achat Effectué avec Succes , Vous avez encore " + points  + " points ");


                    } else if (name.equals("Mending book <=> 1200 points")) {

                            ItemStack mending = new ItemStack(Material.ENCHANTED_BOOK);

                            player.getInventory().addItem(item.addBookEnchantment(mending, Enchantment.MENDING, 1));
                            xml.updatePoints(uuid, (points - price) + "");
                            player.sendMessage(ChatColor.GREEN + "Achat Effectué avec Succes , Vous avez encore " + points   + " points ") ;

                    }
                }
                else{
                    player.sendMessage("Vous n'avez aps assez de points");
                }

            }

         else if (e.isRightClick()) {

            if (! (name.equals("One more home == 800 points")) ) {

                if(!name.equals("700 points == 1800 experiences orbes") && !name.equals("Mending book <=> 1200 points")) {

                    if (player.getInventory().containsAtLeast(new ItemStack(m), amount)) {


                        ItemStack paper = new ItemStack(m, amount);
                        player.getInventory().removeItem(paper);
                        player.sendMessage(ChatColor.RED + "Vous avez gagné " + name.split(">")[1] +  "en vendant "  + paper.getItemMeta().getDisplayName() + " * " + paper.getAmount());

                        xml.updatePoints(uuid, (points + price) + "");
                    } else {
                        player.sendMessage(ChatColor.RED + "Vous n'avais pas l'item selectionné");
                    }
                }
                else{
                    player.sendMessage(ChatColor.DARK_RED + "Vous ne pouvez pas vendre cet item");
                }
            }
            else{
                player.sendMessage(ChatColor.DARK_RED + "Vous ne pouvez pas vendre cet item");
            }

        }

    }
}




