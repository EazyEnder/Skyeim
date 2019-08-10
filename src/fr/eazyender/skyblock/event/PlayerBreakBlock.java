package fr.eazyender.skyblock.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerBreakBlock implements Listener {
	
	@EventHandler
	private static void onBlockBreak(BlockBreakEvent event) {
		
		Player p = event.getPlayer();
		
		if(event.getBlock() != null) {
		if(event.getBlock().getType().equals(Material.COBBLESTONE) || event.getBlock().getType().equals(Material.STONE)) {
			
			Random r = new Random();
			int chance = r.nextInt(100);
			if(chance >= 2) {
				
			}else {
				event.getBlock().setType(Material.AIR);
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), createCustomItem("§f§lPierre §8§lParfaite","Ressource","§f§lPierre aux bords parfaits",Material.STICK,1));
				
			}
			
		}
		if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§e§lPioche en §6§lAstate§r§8§o Stone")) {
			
			Random r = new Random();
			int chance = r.nextInt(400);
			if(chance >= 2) {
				
			}else {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), createCustomItem("§kf§r§f§lInconnu§kf","Ressource","§f§lDécouvrez le avec une loupe.",Material.FEATHER,1));
				
			}
			
		}
		}
		
	}
	
	private static ItemStack createCustomItem(String name,String type,String lore,Material material,int nbr) {
		
		ItemStack item = new ItemStack(material,nbr);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(name);
        List<String> itemL = new ArrayList<String>();
		String itemL1 = "§f§lType :§r§e" + type;
		itemL.add(itemL1);
		if(lore != null) {
			itemL.add(lore);
		}
		itemM.setLore(itemL);
		item.setItemMeta(itemM);
        
        return item;
		
	}

}
