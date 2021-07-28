package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class onBlockMined implements Listener {

    FileConfiguration config = PluginMain.getInstance().getConfig();
    @EventHandler
    public void onBlockMined(BlockBreakEvent event){
        if(event.getBlock().getType().equals(Material.STONE)){
            int diamsMined = config.getInt("diamondMined." + event.getPlayer().getDisplayName()  +".diamondMined");
            int  stoneMined = config.getInt("diamondMined." + event.getPlayer().getDisplayName()  +".stoneMined");
            double ratio = 0;
            stoneMined ++;
            if(stoneMined != 0){
                ratio = (double) diamsMined/stoneMined;
            }

            config.set("diamondMined." + event.getPlayer().getDisplayName()  +".stoneMined" , stoneMined);
            config.set("diamondMined." + event.getPlayer().getDisplayName()  +".ratio" , ratio*100);
            PluginMain.getInstance().saveConfig();

        }
        else if(event.getBlock().getType().equals(Material.DIAMOND_ORE)){
            int diamsMined = config.getInt("diamondMined." + event.getPlayer().getDisplayName()  +".diamondMined");
            int  stoneMined = config.getInt("diamondMined." + event.getPlayer().getDisplayName()  +".stoneMined");
            double ratio = 0;
            diamsMined ++;
            if(stoneMined != 0){
                ratio = (double) diamsMined/stoneMined;
            }

            config.set("diamondMined." + event.getPlayer().getDisplayName()  +".diamondMined" , diamsMined);
            config.set("diamondMined." + event.getPlayer().getDisplayName()  +".ratio" , ratio*100);
            PluginMain.getInstance().saveConfig();

        }
        else if(event.getBlock().getType().equals(Material.NETHERRACK)){
            int netherrackMined = config.getInt("ancientDebritMined." + event.getPlayer().getDisplayName()  +".netherrackMined");
            int  ancientDebritMined =  config.getInt("ancientDebritMined." + event.getPlayer().getDisplayName()  +".ancientDebritMined");
            double ratio = 0;
            netherrackMined ++;
            if(netherrackMined != 0){
                ratio = (double) ancientDebritMined/netherrackMined;
            }
            config.set("ancientDebritMined." + event.getPlayer().getDisplayName()  +".netherrackMined" , netherrackMined);
            config.set("ancientDebritMined." + event.getPlayer().getDisplayName()  +".ratio" , ratio * 100);
            PluginMain.getInstance().saveConfig();
        }
        else if(event.getBlock().getType().equals(Material.ANCIENT_DEBRIS)){
            int netherrackMined = config.getInt("ancientDebritMined." + event.getPlayer().getDisplayName()  +".netherrackMined");
            int  ancientDebritMined =  config.getInt("ancientDebritMined." + event.getPlayer().getDisplayName()  +".ancientDebritMined");
            double ratio = 0;
            ancientDebritMined ++;
            if(netherrackMined != 0){
                ratio = (double) ancientDebritMined/netherrackMined;
            }
            config.set("ancientDebritMined." + event.getPlayer().getDisplayName()  +".ancientDebritMined" , ancientDebritMined);
            config.set("ancientDebritMined." + event.getPlayer().getDisplayName()  +".ratio" , ratio * 100);
            PluginMain.getInstance().saveConfig();
        }
        Material material = event.getBlock().getType();
        if(material.equals(Material.DIAMOND_ORE) || material.equals(Material.IRON_ORE)  || material.equals(Material.GOLD_ORE)  || material.equals(Material.REDSTONE_ORE)  || material.equals(Material.ANCIENT_DEBRIS)  || material.equals(Material.LAPIS_ORE)){
            FileConfiguration config = PluginMain.getInstance().getConfig();
            Player p =  event.getPlayer();
            String classe = config.getString("classe." + p.getDisplayName());
            int classlevel = config.getInt(    classe +"." + p.getDisplayName() + ".level");
            if(classe.equals("mineur")){
            int random = -2 * classlevel;
            int randomnumber  = 1 + (int)(Math.random() * (((12-random) - 1) + 1));
            if(randomnumber == 2){
                p.getInventory().addItem(new ItemStack(material));

            }


            }

        }
    }
}
