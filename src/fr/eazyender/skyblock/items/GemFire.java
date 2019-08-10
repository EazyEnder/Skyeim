package fr.eazyender.skyblock.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GemFire implements Listener{

	static ItemStack sword_fire = createCustomItem("§e§lEpee en §6§lAstate§r§4§o Feu","Arme","§f§lGemme :§r§4Feu",Material.DIAMOND_SWORD,1);
	
	static ItemStack pickaxe_empty = createCustomItem("§e§lPioche en §6§lAstate","Outil","§f§lGemme :§r§8Aucune",Material.DIAMOND_PICKAXE,1);
	static ItemStack sword_empty = createCustomItem("§e§lEpee en §6§lAstate","Arme","§f§lGemme :§r§8Aucune",Material.DIAMOND_SWORD,1);
	
	static ItemStack gem_fire = createCustomItem("§f§lGem de §4§lfeu","Gem","§f§lConcentré à son maximum !",Material.FEATHER,1);
	
	@EventHandler
	private static void onPlayerSwapHands(PlayerSwapHandItemsEvent event) {
		
		ItemStack main = event.getMainHandItem();
		ItemStack off = event.getOffHandItem();
		
		if(main != null && off != null) {
		if(main.getItemMeta().getDisplayName().equals(sword_empty.getItemMeta().getDisplayName()) && off.equals(gem_fire)) {
			
			ItemStack sword_fire = createCustomItem("§e§lEpee en §6§lAstate§r§4§o Feu","Arme","§f§lGemme :§r§4Feu",Material.DIAMOND_SWORD,1);
			
			sword_fire.setDurability(main.getDurability());
			event.setMainHandItem(sword_fire);
			event.setOffHandItem(new ItemStack(Material.AIR,1));
		}
		}
		
	}
	
	@EventHandler
	private static void onPlayerRightClick(PlayerInteractEvent event) {
		
		if(event.getItem() != null) {
			
			if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if(event.getPlayer().isSneaking()) {
						if(event.getItem().getItemMeta().getDisplayName().equals(sword_fire.getItemMeta().getDisplayName())) {
							
							ItemStack sword_fire = createCustomItem("§e§lEpee en §6§lAstate§r§4§o Feu","Arme","§f§lGemme :§r§4Feu",Material.DIAMOND_SWORD,1);
							
							sword_empty.setDurability(event.getItem().getDurability());
							event.getPlayer().getInventory().setItemInMainHand(sword_empty);
							event.getPlayer().getInventory().setItemInOffHand(gem_fire);
						}
				}
				else{
					if(event.getItem().getItemMeta().getDisplayName().equals(sword_fire.getItemMeta().getDisplayName())) {
						
						
						ItemStack sword_fire = createCustomItem("§e§lEpee en §6§lAstate§r§4§o Feu","Arme","§f§lGemme :§r§4Feu",Material.DIAMOND_SWORD,1);
						
						System.out.println("test");
						ItemMeta im = sword_fire.getItemMeta();
						im.addEnchant(Enchantment.DAMAGE_ALL, 10, false);
						sword_fire.setItemMeta(im);
						sword_fire.setDurability(event.getItem().getDurability());
						event.getPlayer().getInventory().setItemInMainHand(sword_fire);
					}
				}
			}
		}
		
	}
	
	private static ItemStack createCustomItem(String name,String type,String lore,Material material,int nbr) {
		
		ItemStack item;
		
		if(nbr != 0) {
		item = new ItemStack(material,nbr);
		}else {
		item = new ItemStack(material);
		}
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(name);
        List<String> itemL = new ArrayList<String>();
        if(type != null) {
		String itemL1 = "§f§lType :§r§e" + type;
		itemL.add(itemL1);
        }
		if(lore != null) {
			itemL.add(lore);
		}
		itemM.setLore(itemL);
		item.setItemMeta(itemM);
        
        return item;
		
	}
	
}
