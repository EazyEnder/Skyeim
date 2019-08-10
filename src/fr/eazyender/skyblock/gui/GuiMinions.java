package fr.eazyender.skyblock.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiMinions implements Listener{
	
public static void createGui(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 27, "§e§l Gestion des minions");
		ItemStack glass = getCustomItem(Material.YELLOW_STAINED_GLASS_PANE,"§e§lUne simple bordure",false,1);
		
		for(int i = 0; i < 9; i++) {
		inv.setItem(i, glass);
		inv.setItem(i + 18, glass);
		}
		
		ItemStack leave = getCustomItem(Material.BARRIER,"§4§lQuitter",true,1);
		inv.setItem(22, leave);
		
		List<String> str_shop = new ArrayList<String>();
		str_shop.add("§f§lPour agrandir ses rangs !");
		ItemStack Shop_Minions = getCustomItemWithLore(Material.GOLD_BLOCK,"§e§lBoutique",true,1,str_shop);
		inv.setItem(10, Shop_Minions);
		
		List<String> str_place = new ArrayList<String>();
		str_place.add("§f§lPermet de placer ses ouvriers");
		ItemStack place_Minions = getCustomItemWithLore(Material.LEGACY_REDSTONE_LAMP_ON,"§e§lPlacement",true,1,str_place);
		inv.setItem(13, place_Minions);
		
		List<String> str_inv = new ArrayList<String>();
		str_inv.add("§f§lContient des petits Hommes");
		ItemStack inv_Minions = getCustomItemWithLore(Material.CHEST,"§e§lInventaire",true,1,str_inv);
		inv.setItem(16, inv_Minions);
		
		player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
		player.openInventory(inv);
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		
		
		
		
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


