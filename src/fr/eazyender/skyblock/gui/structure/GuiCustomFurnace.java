package fr.eazyender.skyblock.gui.structure;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiCustomFurnace implements Listener{
	
	private static ItemStack font = createCustomItem("§0Font noir",null,null,Material.FEATHER,1);
	private static ItemStack fleche = createCustomItem("§0Fleche noir",null,null,Material.FEATHER,1);
	private static ItemStack actualize = getCustomItem(Material.LIME_TERRACOTTA,"§4§lPour confirmer la cuisson",false,1);
	
	public static void createGui(Player player){
		
		Inventory inv = Bukkit.createInventory(null, 27, "§f§lFour > §e§l" + player.getDisplayName());
		for(int i = 0; i < 9; i++) {
			inv.setItem(i, font);
			inv.setItem(i + 18, font);
			}
		
		inv.setItem(9, font);
		
		inv.setItem(11, font);
		
		inv.setItem(13, font);
		inv.setItem(14, fleche);
		
		inv.setItem(16, font);
		inv.setItem(17, actualize);
		
		player.openInventory(inv);
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(current != null) {
			
			if(event.getView().getTitle().equalsIgnoreCase("§f§lFour > §e§l" + player.getDisplayName())) {
				
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
	
	public static ItemStack getCustomItem(Material material, String customName, boolean EnchantEffect, int nbr) {
		
		ItemStack item = new ItemStack(material, nbr);
		ItemMeta itemMeta = item.getItemMeta();
		if(customName != null) {itemMeta.setDisplayName(customName);}
		if(EnchantEffect) {itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);}
		item.setItemMeta(itemMeta);
		
		
		return item;
		
	}

	public static ItemStack getCustomItemWithLore(Material material, String customName, boolean EnchantEffect, int nbr, List<String> lore) {
		
		ItemStack item = new ItemStack(material, nbr);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setLore(lore);
		if(customName != null) {itemMeta.setDisplayName(customName);}
		if(EnchantEffect) {itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);}
		item.setItemMeta(itemMeta);
		
		
		return item;
		
	}
		

}
