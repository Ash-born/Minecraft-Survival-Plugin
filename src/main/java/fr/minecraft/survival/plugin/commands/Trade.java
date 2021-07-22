package fr.minecraft.survival.plugin.commands;


import fr.minecraft.survival.plugin.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class Trade implements CommandExecutor {


    public Inventory inv = Bukkit.createInventory(null, 27, "§8 Trade Menu");
    Item item = new Item();
    public ItemStack diamond_one = item.createGuiItem(Material.DIAMOND, 1, "1 diamond <=> 5 points", "Left Click for buying   " + '\n' + " Right click for selling");
    public ItemStack diamond_two = item.createGuiItem(Material.DIAMOND, 16, "16 diamond <=> 80 points", "Left Click for buying , Right click for selling");
    public ItemStack diamond_tree = item.createGuiItem(Material.DIAMOND, 32, "32 diamond <=> 160 points", "Left Click for buying , Right click for selling");
    public ItemStack diamond_for = item.createGuiItem(Material.DIAMOND, 64, "64 diamond <=> 320 points", "Left Click for buying , Right click for selling");
    public ItemStack iron_one = item.createGuiItem(Material.IRON_INGOT, 1, "1 iron ingot <=> 1 point", "Left Click for buying , Right click for selling");
    public ItemStack iron_two = item.createGuiItem(Material.IRON_INGOT, 16, "16 iron ingot <=> 16 points", "Left Click for buying , Right click for selling");
    public ItemStack iron_tree = item.createGuiItem(Material.IRON_INGOT, 32, "32 iron ingot <=> 32 points", "Left Click for buying , Right click for selling");
    public ItemStack iron_for = item.createGuiItem(Material.IRON_INGOT, 64, "64 iron ingot <=> 64 points", "Left Click for buying , Right click for selling");
    public ItemStack gold_one = item.createGuiItem(Material.GOLD_INGOT, 1, "1 gold ingot <=> 2 points", "Left Click for buying , Right click for selling");
    public ItemStack gold_two = item.createGuiItem(Material.GOLD_INGOT, 16, "16 gold ingot <=> 32 points", "Left Click for buying , Right click for selling");
    public ItemStack gold_tree = item.createGuiItem(Material.GOLD_INGOT, 32, "32 gold ingot <=>64 points", "Left Click for buying , Right click for selling");
    public ItemStack gold_for = item.createGuiItem(Material.GOLD_INGOT, 64, "64 gold ingot <=> 128 points", "Left Click for buying , Right click for selling");
    public ItemStack emeraude_one = item.createGuiItem(Material.EMERALD, 1, "1 emerald <=> 3 points", "Left Click for buying , Right click for selling");
    public ItemStack emeraude_two = item.createGuiItem(Material.EMERALD, 16, "16 emerald <=> 48 points", "Left Click for buying , Right click for selling");
    public ItemStack emeraude_tree = item.createGuiItem(Material.EMERALD, 32, "32 emerald <=> 96 points", "Left Click for buying , Right click for selling");
    public ItemStack emeraude_for = item.createGuiItem(Material.EMERALD, 64, "64 emerald <=> 192 points", "Left Click for buying , Right click for selling");
    public ItemStack mending = item.createGuiItem(Material.ENCHANTED_BOOK, 1, "Mending book <=> 1200 points", "Left Click for buying , Right click for selling");
    public ItemStack goldenAplle = item.createGuiItem(Material.ENCHANTED_GOLDEN_APPLE, 1, "Golden Apple <=> 2000 points", "Left Click for buying , Right click for selling");
    public ItemStack addHomes = item.createGuiItem(Material.DIRT, 1, "One more home == 800 points", "Left Click for buying");
    public ItemStack netherite_one = item.createGuiItem(Material.NETHER_BRICK, 1, "1 netherite <=> 60 points", "Left Click for buying , Right click for selling");
    public ItemStack netherite_two = item.createGuiItem(Material.NETHER_BRICK, 16, "16 netherite <=> 960 points", "Left Click for buying , Right click for selling");
    public ItemStack netherite_tree = item.createGuiItem(Material.NETHER_BRICK, 32, "32 netherite <=> 1920 points", "Left Click for buying , Right click for selling");
    public ItemStack netherite_for = item.createGuiItem(Material.NETHER_BRICK, 64, "64 netherite <=> 3840 points", "Left Click for buying , Right click for selling");
    public ItemStack Experience = item.createGuiItem(Material.EXPERIENCE_BOTTLE, 64, "700 points == 1800 experiences orbes");


    public ItemStack vitre = item.createGuiItem(Material.WHITE_STAINED_GLASS_PANE, 1, " ");

    public Trade(Inventory inv) {
        this.inv = inv;
    }

    public Trade() {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        if (commandSender instanceof Player) {

            inv.setItem(0, diamond_one);
            inv.setItem(1, diamond_two);
            inv.setItem(2, diamond_tree);
            inv.setItem(3, diamond_for);
            inv.setItem(4, vitre);
            inv.setItem(5, iron_one);
            inv.setItem(6, iron_two);
            inv.setItem(7, iron_tree);
            inv.setItem(8, iron_for);
            inv.setItem(9, gold_one);
            inv.setItem(10, gold_two);
            inv.setItem(11, gold_tree);
            inv.setItem(12, gold_for);
            inv.setItem(13, vitre);
            inv.setItem(14, emeraude_one);
            inv.setItem(15, emeraude_two);
            inv.setItem(16, emeraude_tree);
            inv.setItem(17, emeraude_for);
            inv.setItem(18, mending);
            inv.setItem(19, goldenAplle);
            inv.setItem(20, addHomes);
            inv.setItem(21, Experience);
            inv.setItem(22, vitre);
            inv.setItem(23, netherite_one);
            inv.setItem(24, netherite_two);
            inv.setItem(25, netherite_tree);
            inv.setItem(26, netherite_for);


            p.openInventory(inv);


            return false;
        }


        return false;
    }

}
