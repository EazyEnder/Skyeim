package fr.eazyender.skyblock.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

import fr.eazyender.skyblock.TradeStats;

public class GuiTrade implements Listener{
	
	private static Map<UUID, TradeStats> trades_stats = new HashMap<UUID, TradeStats>();
	
	private static Map<Player, Player> trades_player = new HashMap<Player, Player>();
	private static Map<Player, Player> trades_player_2 = new HashMap<Player, Player>();
	
	public static void createGui(Player sender,Player target) {
		
		trades_player.put(sender, target);
		trades_player_2.put(target, sender);
		
		Inventory inv = Bukkit.createInventory(null, 54, "§e§lEchange:" + sender.getDisplayName() + "§r§6§l/§r§e§l" + target.getDisplayName());
		ItemStack glass = getCustomItem(Material.RED_STAINED_GLASS_PANE,"§e§lUne simple bordure",true);
		inv.setItem(4, glass);
		inv.setItem(4 + 9, glass);
		inv.setItem(4 + 9 * 2, glass);
		inv.setItem(4 + 9 * 3, glass);
		inv.setItem(4 + 9 * 4, glass);
		inv.setItem(4 + 9 * 4 - 1, glass);
		inv.setItem(4 + 9 * 4 + 1, glass);
		inv.setItem(4 + 9 * 4 - 2, glass);
		inv.setItem(4 + 9 * 4 + 2, glass);
		inv.setItem(4 + 9 * 4 - 3, glass);
		inv.setItem(4 + 9 * 4 + 3, glass);
		inv.setItem(4 + 9 * 4 - 4, glass);
		inv.setItem(4 + 9 * 4 + 4, glass);
		
		ItemStack add_100 = getCustomItem(Material.GOLD_BLOCK,"§e§lAjouter §r§2§l100$",false);
		inv.setItem(47, add_100);
		ItemStack add_10 = getCustomItem(Material.GOLD_INGOT,"§e§lAjouter §r§2§l10$",false);
		inv.setItem(46, add_10);
		ItemStack add_1 = getCustomItem(Material.GOLD_NUGGET,"§e§lAjouter §r§2§l1$",false);
		inv.setItem(45, add_1);
		
		ItemStack remove_100 = getCustomItem(Material.IRON_BLOCK,"§e§lRetirer §r§4§l100$",false);
		inv.setItem(51, remove_100);
		ItemStack remove_10 = getCustomItem(Material.IRON_INGOT,"§e§lRetirer §r§4§l10$",false);
		inv.setItem(52, remove_10);
		ItemStack remove_1 = getCustomItem(Material.IRON_NUGGET,"§e§lRetirer §r§4§l1$",false);
		inv.setItem(53, remove_1);
		
		ItemStack finish_accept = getCustomItem(Material.GREEN_WOOL,"§2§lAccepter l'échange",true);
		inv.setItem(48, finish_accept);
		ItemStack finish_decline = getCustomItem(Material.RED_WOOL,"§4§lRefuser l'échange",true);
		inv.setItem(50, finish_decline);
		
		if(!trades_stats.containsKey(sender.getUniqueId())) {
		trades_stats.put(sender.getUniqueId(), new TradeStats(inv, 1, null, null, 0, 0));
		}

		sender.playSound(sender.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
		sender.openInventory(inv);
		target.playSound(target.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
		target.openInventory(inv);
		
	}
	
	
	//@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
			
			if(event.getView().getTitle().equalsIgnoreCase("§e§lEchange:" + trades_player_2.get(player).getDisplayName() + "§r§6§l/§r§e§l" + trades_player.get(player).getDisplayName())) {
				
				if(trades_player_2.containsKey(player)) {
					//COTE TARGET DONC A DROITE
					short[] blacklist_case = {0,1,2,3,4, 9,10,11,12,13 , 18,19,20,21,22, 27,28,29,30,31, 36,37,38,39,40, 45,46,47,48,49,50,51,52,53};
					for(int i = 0; i < blacklist_case.length; i++) {
						ItemStack is = inv.getItem(blacklist_case[i]);
						if(current != null) {
					if(current.equals(is)) {
						event.setCancelled(true);
					}
						}
					if(event.isLeftClick() || event.isRightClick()) {
						if(event.getSlot() == blacklist_case[i]) {
							if(event.getCursor() != null)
							{
								player.getInventory().addItem(event.getCursor());
								player.setItemOnCursor(null);
								
							}
						}
					}
					if(event.isShiftClick()) {
						event.setCancelled(true);
					}
					}
					
					
					
				}else if(trades_player.containsKey(player)){
					//COTE SENDER DONC A GAUCHE
					short[] blacklist_case = {0+4,1+4,2+4,3+4,4+4, 9+4,10+4,11+4,12+4,13+4 , 18+4,19+4,20+4,21+4,22+4, 27+4,28+4,29+4,30+4,31+4, 36+4,37+4,38+4,39+4,40+4, 45,46,47,48,49,50,51,52,53};				
					
					for(int i = 0; i < blacklist_case.length; i++) {
						ItemStack is = inv.getItem(blacklist_case[i]);
						if(current != null) {
						if(current.equals(is)) {
							event.setCancelled(true);
						}
						}
						if(event.isLeftClick() || event.isRightClick()) {
							if(event.getSlot() == blacklist_case[i]) {
								if(event.getCursor() != null)
								{
									player.getInventory().addItem(event.getCursor());
									player.setItemOnCursor(null);
									
								}
							}
						}
						if(event.isShiftClick()) {
							event.setCancelled(true);
						}
					}
						
				}
			
				
			
			
		}
	}
	
	public static ItemStack getCustomItem(Material material, String customName, boolean EnchantEffect) {
	
		ItemStack item = new ItemStack(material, 1);
		ItemMeta itemMeta = item.getItemMeta();
		if(customName != null) {itemMeta.setDisplayName(customName);}
		if(EnchantEffect) {itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);}
		item.setItemMeta(itemMeta);
		
		
		return item;
		
	}

}
