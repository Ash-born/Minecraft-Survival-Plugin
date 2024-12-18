package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.commands.Trade;
import fr.minecraft.survival.plugin.main.PluginMain;
import fr.minecraft.survival.plugin.utils.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class onTradeInvClick implements Listener {
    Trade tr = new Trade();

    Item item = new Item();
    FileConfiguration config = PluginMain.getInstance().getConfig();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        if (inventory.contains(tr.diamond_one)) {
            event.setCancelled(true);

            try {
                if (clicked != null) {
                    String name = clicked.getItemMeta().getDisplayName();
                    if (clicked.getType().equals(Material.DIAMOND)) {
                        if (clicked.getAmount() == 1) {
                            checkitem(clicked.getItemMeta().getDisplayName(), 1, 5, clicked, event, Material.DIAMOND);
                        } else if (clicked.getAmount() == 16) {
                            checkitem(clicked.getItemMeta().getDisplayName(), 16, 80, clicked, event, Material.DIAMOND);
                        } else if (clicked.getAmount() == 32) {
                            checkitem(clicked.getItemMeta().getDisplayName(), 32, 160, clicked, event,
                                    Material.DIAMOND);
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
                    if (clicked.getType().equals(Material.BEDROCK)) {
                        checkitem(name, 1, 3000, clicked, event, Material.BEDROCK);
                    }
                    if(clicked.getType().equals(Material.DIRT)){

                        checkitem(name,1,400,clicked,event,Material.DIRT);
                    }
                    if (clicked.getType().equals(Material.NETHERITE_INGOT)) {
                        if (clicked.getAmount() == 1) {
                            checkitem(name, 1, 320, clicked, event, Material.NETHERITE_INGOT);
                        } else if (clicked.getAmount() == 16) {
                            checkitem(name, 16, 5120, clicked, event, Material.NETHERITE_INGOT);
                        } else if (clicked.getAmount() == 32) {
                            checkitem(name, 32, 10240, clicked, event, Material.NETHERITE_INGOT);
                        } else {
                            checkitem(name, 64, 20480, clicked, event, Material.NETHERITE_INGOT);
                        }

                    }
                    if (name.equals("Mending book => 1200 points")) {
                        checkitem(name, 1, 1200, clicked, event, Material.ENCHANTED_BOOK);
                    }

                    if (clicked.getType().equals(Material.EXPERIENCE_BOTTLE)) {
                        checkitem(name, 1, 450, clicked, event, Material.BOOKSHELF);
                    }
                    if (name.equals("Golden Apple => 2000 points")) {
                        checkitem(name, 1, 2000, clicked, event, Material.ENCHANTED_GOLDEN_APPLE);
                    }
                    PluginMain.getInstance().saveConfig();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void checkitem(String name, int amount, int price, ItemStack Clicked, InventoryClickEvent e, Material m)
            throws ParserConfigurationException, IOException, TransformerException, SAXException {
        Player player = (Player) e.getWhoClicked();

        double points = config.getDouble("points." + ((Player) e.getWhoClicked()).getDisplayName());
        int home_cree = config.getInt("homecree." + ((Player) e.getWhoClicked()).getDisplayName());
        e.setCancelled(true);

        if (e.isLeftClick()) {

            if (points >= price) {
                if (!name.equals("450 points == 1800 experiences orbes") && !name.equals("Mending book <=> 1200 points")
                        && !name.equals("One more home == 400 points")
                        && !name.equals("1500 points for one more claim")) {
                    if (item.hasAvaliableSlot(player)) {
                        config.set("points." + ((Player) e.getWhoClicked()).getDisplayName(), points - price);
                        for (int i = 0; i < amount; i++) {

                            player.getInventory().addItem(new ItemStack(m));
                        }
                        player.sendMessage(ChatColor.GREEN + "Achat Effectué avec succes ,  Vous avez encore "
                                + (points - price) + " points ");
                    } else {
                        player.sendMessage(ChatColor.RED + "Inventory Full . You can't purshase anymore.");
                    }

                } else if (name.equals("One more home == 400 points")) {
                    if (points >= price) {
                        config.set("homecree." + ((Player) e.getWhoClicked()).getDisplayName(), home_cree + 1);
                        config.set("points." + ((Player) e.getWhoClicked()).getDisplayName(), points - price);
                        player.sendMessage(ChatColor.GREEN + "Home acheté  Effectué avec Succes , Vous avez encore "
                                + (points - price) + " points ");

                    } else {

                        player.sendMessage(ChatColor.RED + "Vous n'avez pas assez de points");
                    }
                } else if (name.equals("450 points == 1800 experiences orbes")) {

                    player.giveExp(1800);
                    config.set("points." + ((Player) e.getWhoClicked()).getDisplayName(), points - price);
                    player.sendMessage(
                            ChatColor.GREEN + "Achat Effectué avec Succes , Vous avez encore " + (points - price) + " points ");

                } else if (name.equals("Mending book => 1200 points")) {

                    ItemStack mending = new ItemStack(Material.ENCHANTED_BOOK);

                    player.getInventory().addItem(item.addBookEnchantment(mending, Enchantment.MENDING, 1));
                    config.set("points." + ((Player) e.getWhoClicked()).getDisplayName(), points - price);
                    player.sendMessage(
                            ChatColor.GREEN + "Achat Effectué avec Succes , Vous avez encore " + (points - price) + " points ");

                } else if (name.equals("1500 points for one more claim")) {
                    int maxclaim = config.getInt("maxclaim." +  ((Player) e.getWhoClicked()).getDisplayName());
                    config.set("maxclaim." +  ((Player) e.getWhoClicked()).getDisplayName(), maxclaim + 1 );
                    config.set("points." + ((Player) e.getWhoClicked()).getDisplayName(), points - price);
                    player.sendMessage(
                            ChatColor.GREEN + "Achat Effectué avec Succes , Vous avez encore " + (points - price) + " points ");

                }

            } else {
                player.sendMessage("Vous n'avez aps assez de points");
            }

        }

        else if (e.isRightClick()) {

            double test = (double) price / 100;
            double retest = test * 70;
            if (!(name.equals("One more home == 400 points") && !(name.equals("1500 points for one more claim")))) {

                if (!name.equals("450 points == 1800 experiences orbes")
                        && !name.equals("Mending book <=> 1200 points")) {

                    if (player.getInventory().containsAtLeast(new ItemStack(m), amount)) {

                        ItemStack paper = new ItemStack(m, amount);
                        player.getInventory().removeItem(paper);
                        player.sendMessage(ChatColor.RED + "Vous avez gagné " + retest + " points  en vendant "
                                + paper.getType() + " * " + paper.getAmount());

                        config.set("points." + ((Player) e.getWhoClicked()).getDisplayName(), points + retest);
                    } else {
                        player.sendMessage(ChatColor.RED + "Vous n'avais pas l'item selectionné");
                    }
                } else {
                    player.sendMessage(ChatColor.DARK_RED + "Vous ne pouvez pas vendre cet item");
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + "Vous ne pouvez pas vendre cet item");
            }
            PluginMain.getInstance().saveConfig();

        }

    }
}
