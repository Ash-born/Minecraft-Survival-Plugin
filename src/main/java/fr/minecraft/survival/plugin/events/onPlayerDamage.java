package fr.minecraft.survival.plugin.events;

import fr.minecraft.survival.plugin.main.PluginMain;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;


public class onPlayerDamage implements Listener {


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            FileConfiguration config = PluginMain.getInstance().getConfig();
            Player p = (Player) event.getDamager();
            String classe = config.getString("classe." + p.getDisplayName());
            if(classe == null ){ return;}
            int classlevel = config.getInt(    classe +"." + p.getDisplayName() + ".level");
            if(classe.equals("guerrier")){
                if(p.getItemInHand().equals( new ItemStack(Material.DIAMOND_SWORD) )   || p.getItemInHand().equals( new ItemStack(Material.IRON_SWORD) ) ||   p.getItemInHand().equals( new ItemStack(Material.WOODEN_SWORD) ) ||  p.getItemInHand().equals( new ItemStack(Material.STONE_SWORD) ) ||  p.getItemInHand().equals( new ItemStack(Material.GOLDEN_SWORD) ) ) {
                    event.setDamage(event.getDamage() + ((event.getDamage() / 100) * (12.5 * classlevel)));

                }
            }
            else if(classe.equals("archer")){

                if(p.getItemInUse().equals(new ItemStack(Material.BOW))){
                    event.setDamage(event.getDamage() + ((event.getDamage() / 100) * (25 * classlevel)));
                }
            }
            else if (classe.equals("tank") || classe.equals("mineur")){
                event.setDamage(event.getDamage() - ( (event.getDamage()/100) * 12.5));

            }

        }
        else if( event.getEntity() instanceof  Player){
            Player target = (Player) event.getEntity();
            FileConfiguration config = PluginMain.getInstance().getConfig();
            String classe = config.getString("classe." + target.getDisplayName());
            int classlevel = config.getInt(    classe +"." + target.getDisplayName() + ".level");
            event.setDamage(event.getDamage() - ( (event.getDamage()/100) *(12.5 * classlevel)));



        }

    }
}
