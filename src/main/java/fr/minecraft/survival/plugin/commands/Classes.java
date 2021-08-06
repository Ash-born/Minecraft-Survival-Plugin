package fr.minecraft.survival.plugin.commands;

import fr.minecraft.survival.plugin.main.PluginMain;
import fr.minecraft.survival.plugin.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Classes implements CommandExecutor {
  static  Item item = new Item();
    FileConfiguration config = PluginMain.getInstance().getConfig();
 public static    Inventory inv =  Bukkit.createInventory(null, 9, "§8 Classes Menu");
    public static   ItemStack Tank = item.createGuiItem(Material.IRON_CHESTPLATE,1,"Class : Tank ","Left Click to switch Classe");
    public static    ItemStack Guerrier = item.createGuiItem(Material.IRON_SWORD,1,"Class : Guerrier", "Left Click to switch Classe");
    public static   ItemStack Mineur = item.createGuiItem(Material.IRON_PICKAXE ,1,"Class : Mineur",  "Left Click to switch Classe");
    public static    ItemStack Archer = item.createGuiItem(Material.BOW,1,"Class : Archer" , "Left Click to switch Classe");
    public static     ItemStack Null = item.createGuiItem(Material.ANVIL,1,"Reset your classes","Left click to reset your classe");
    public static    ItemStack vitre = item.createGuiItem(Material.WHITE_STAINED_GLASS_PANE, 1, " ","");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;

            String playerName = p.getDisplayName();
            if(!config.contains("classe." +p.getDisplayName())){
                config.set("classe." + p.getDisplayName() , "null");

                PluginMain.getInstance().saveConfig();
            }
            if(!config.contains("guerrier." + p.getDisplayName())){
                config.set("guerrier." + p.getDisplayName() + ".level" , 1);
                config.set("tank." + p.getDisplayName() + ".level" , 1);
                config.set("mineur." + p.getDisplayName() + ".level" , 1);
                config.set("archer." + p.getDisplayName() + ".level" , 1);
                PluginMain.getInstance().saveConfig();
            }


            inv.setItem(0,vitre);
            inv.setItem(1,vitre);
            inv.setItem(2,Tank);
            inv.setItem(3,Guerrier);
            inv.setItem(4,Mineur);
            inv.setItem(5,Archer);
            inv.setItem(6,Null);
            inv.setItem(7,vitre);
            inv.setItem(8,vitre);
            p.openInventory(inv);

        }

        return false;
    }
}
