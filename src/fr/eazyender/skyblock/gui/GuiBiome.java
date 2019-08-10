package fr.eazyender.skyblock.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.eazyender.skyblock.island.IslandManager;
import fr.eazyender.skyblock.player.PlayerEconomy;


public class GuiBiome implements Listener {
	
	private static Map<Biome, Long> biome_price = new HashMap<Biome, Long>();
	private static boolean initMapIsOn = false;
	
	private static void initMap() {
		
		biome_price.put(Biome.PLAINS, (long) 50);
		biome_price.put(Biome.RIVER, (long) 50);
		biome_price.put(Biome.DESERT, (long) 75);
		biome_price.put(Biome.ICE_SPIKES, (long) 80);
		biome_price.put(Biome.NETHER, (long) 150);
		biome_price.put(Biome.THE_END, (long) 300);
		
	}
	
	public static void createGui(Player player){
		
		
		if(!initMapIsOn) {
			initMap();
			initMapIsOn = true;
		}
		
		Inventory inv = Bukkit.createInventory(null, 27, "§e§lBoutique de biomes");
		ItemStack glass = getCustomItem(Material.YELLOW_STAINED_GLASS_PANE,"§e§lUne simple bordure",false,1);
		
		for(int i = 0; i < 9; i++) {
		inv.setItem(i, glass);
		inv.setItem(i + 18, glass);
		}
		
		ItemStack leave = getCustomItem(Material.BARRIER,"§4§lQuitter",true,1);
		inv.setItem(22, leave);
		
		List<String> str_plains = new ArrayList<String>();
		str_plains.add("§a§lTransformer votre zone en plaine!");
		ItemStack plains = getCustomItemWithLore(Material.GRASS_BLOCK,"§2§lPlaine",true,1,str_plains);
		inv.setItem(10, plains);
		
		List<String> str_river = new ArrayList<String>();
		str_river.add("§1§lTransformer votre zone en rivière!");
		ItemStack river = getCustomItemWithLore(Material.LIGHT_BLUE_TERRACOTTA,"§9§lRivière",true,1,str_river);
		inv.setItem(11, river);
		
		List<String> str_desert = new ArrayList<String>();
		str_desert.add("§e§lTransformer votre zone en désert!");
		ItemStack desert = getCustomItemWithLore(Material.SAND,"§6§lDésert",true,1,str_desert);
		inv.setItem(12, desert);
		
		List<String> str_snow = new ArrayList<String>();
		str_snow.add("§7§lTransformer votre zone en zone de neige!");
		ItemStack snow = getCustomItemWithLore(Material.SNOW_BLOCK,"§f§lNeige",true,1,str_snow);
		inv.setItem(13, snow);
		
		List<String> str_nether = new ArrayList<String>();
		str_nether.add("§c§lTransformer votre zone en nether!");
		ItemStack nether = getCustomItemWithLore(Material.NETHERRACK,"§4§lNether",true,1,str_nether);
		inv.setItem(14, nether);
		
		List<String> str_end = new ArrayList<String>();
		str_end.add("§8§lTransformer votre zone en end!");
		ItemStack end = getCustomItemWithLore(Material.END_STONE,"§0§lEnd",true,1,str_end);
		inv.setItem(15, end);
		
		player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
		player.openInventory(inv);
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(current != null) {
			
			if(event.getView().getTitle().equalsIgnoreCase("§e§lBoutique de biomes")) {
				
				if(current.getType() == Material.GRASS_BLOCK) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createBiomeGui(player,Biome.PLAINS,"§fBiome : §r§2§lPlaine");
				}
				else if(current.getType() == Material.LIGHT_BLUE_TERRACOTTA) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createBiomeGui(player,Biome.RIVER,"§fBiome : §r§9§lRivière");
				}
				else if(current.getType() == Material.SAND) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createBiomeGui(player,Biome.DESERT,"§fBiome : §r§6§lDesert");
				}
				else if(current.getType() == Material.SNOW_BLOCK) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createBiomeGui(player,Biome.ICE_SPIKES,"§fBiome : §r§f§lNeige");
				}
				else if(current.getType() == Material.NETHERRACK) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createBiomeGui(player,Biome.NETHER,"§fBiome : §r§4§lNether");
				}
				else if(current.getType() == Material.END_STONE) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createBiomeGui(player,Biome.THE_END,"§fBiome : §r§8§lEnd");
				}
				else if(current.getType() == Material.BARRIER) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					player.closeInventory();
				}
			
			event.setCancelled(true);
			
			}else if(event.getView().getTitle().equalsIgnoreCase("§fBiome : §r§2§lPlaine")) {
				generateClickEvent(event,Biome.PLAINS);
			}else if(event.getView().getTitle().equalsIgnoreCase("§fBiome : §r§9§lRivière")) {
				generateClickEvent(event,Biome.RIVER);
			}else if(event.getView().getTitle().equalsIgnoreCase("§fBiome : §r§6§lDesert")) {
				generateClickEvent(event,Biome.DESERT);
			}else if(event.getView().getTitle().equalsIgnoreCase("§fBiome : §r§f§lNeige")) {
				generateClickEvent(event,Biome.ICE_SPIKES);
			}else if(event.getView().getTitle().equalsIgnoreCase("§fBiome : §r§4§lNether")) {
				generateClickEvent(event,Biome.NETHER);
			}else if(event.getView().getTitle().equalsIgnoreCase("§fBiome : §r§8§lEnd")) {
				generateClickEvent(event,Biome.THE_END);
			}
			
		}
		
		
	}
	
	private static void generateClickEvent(InventoryClickEvent event,Biome biome) {
		ItemStack current = event.getCurrentItem();
		Player p = (Player) event.getWhoClicked();
		
		event.setCancelled(true);
		
		if(current.getType() == Material.COAL) {
			int value =  2;
			PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
			IslandManager im = IslandManager.getIslandManager();
			if(im.getIsland(p) != null) {
			if(im.getIsland(p).isAt(p.getLocation())) {
			if(pe.getMoney(p) >= biome_price.get(biome) * (2*2)) {
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				for(int x = -value ; x < value; x++) {
					for(int z = -value ; z < value; z++) {
						Location lc_p = p.getLocation();
						Location lc = new Location(lc_p.getWorld(),(int)lc_p.getX() + x,0,(int)lc_p.getZ() + z);
				        Block block = lc.getBlock();
					block.setBiome(biome);
					}}
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez acheté : §6§l" + biome.toString() + "§r§e sur une surface de §6§l" + value + "§r§e blocks².");
				pe.setMoney(p, pe.getMoney(p) - biome_price.get(biome) * (value*value));
				p.sendMessage("§4§l[§r§cInfo§r§4§l]§r §c§lReconnectez vous pour voir les changements.");
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas assez d'argent.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'êtes pas sur votre île.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas d'île.");
			}
		}else if(current.getType() == Material.IRON_NUGGET) {
			int value =  3;
			PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
			IslandManager im = IslandManager.getIslandManager();
			if(im.getIsland(p) != null) {
			if(im.getIsland(p).isAt(p.getLocation())) {
			if(pe.getMoney(p) >= biome_price.get(biome) * (value*value)) {
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				for(int x = -value ; x < value; x++) {
					for(int z = -value ; z < value; z++) {
						Location lc_p = p.getLocation();
						Location lc = new Location(lc_p.getWorld(),(int)lc_p.getX() + x,0,(int)lc_p.getZ() + z);
				        Block block = lc.getBlock();
					block.setBiome(biome);
					
					}}
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez acheté : §6§l" + biome.toString() + "§r§e sur une surface de §6§l" + value + "§r§e blocks².");
				pe.setMoney(p, pe.getMoney(p) - biome_price.get(biome) * (value*value));
				p.sendMessage("§4§l[§r§cInfo§r§4§l]§r §c§lReconnectez vous pour voir les changements.");
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas assez d'argent.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'êtes pas sur votre île.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas d'île.");
			}
		}else if(current.getType() == Material.IRON_INGOT) {
			int value =  5;
			PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
			IslandManager im = IslandManager.getIslandManager();
			if(im.getIsland(p) != null) {
			if(im.getIsland(p).isAt(p.getLocation())) {
			if(pe.getMoney(p) >= biome_price.get(biome) * (value*value)) {
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				for(int x = -value ; x < value; x++) {
					for(int z = -value ; z < value; z++) {
						Location lc_p = p.getLocation();
						Location lc = new Location(lc_p.getWorld(),(int)lc_p.getX() + x,0,(int)lc_p.getZ() + z);
				        Block block = lc.getBlock();
					block.setBiome(biome);
					}}
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez acheté : §6§l" + biome.toString() + "§r§e sur une surface de §6§l" + value + "§r§e blocks².");
				pe.setMoney(p, pe.getMoney(p) - biome_price.get(biome) * (value*value));
				p.sendMessage("§4§l[§r§cInfo§r§4§l]§r §c§lReconnectez vous pour voir les changements.");
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas assez d'argent.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'êtes pas sur votre île.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas d'île.");
			}
		}else if(current.getType() == Material.GOLD_NUGGET) {
			int value =  7;
			PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
			IslandManager im = IslandManager.getIslandManager();
			if(im.getIsland(p) != null) {
			if(im.getIsland(p).isAt(p.getLocation())) {
			if(pe.getMoney(p) >= biome_price.get(biome) * (value*value)) {
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				for(int x = -value ; x < value; x++) {
					for(int z = -value ; z < value; z++) {
						Location lc_p = p.getLocation();
						Location lc = new Location(lc_p.getWorld(),(int)lc_p.getX() + x,0,(int)lc_p.getZ() + z);
				        Block block = lc.getBlock();
					block.setBiome(biome);
					}}
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez acheté : §6§l" + biome.toString() + "§r§e sur une surface de §6§l" + value + "§r§e blocks².");
				pe.setMoney(p, pe.getMoney(p) - biome_price.get(biome) * (value*value));
				p.sendMessage("§4§l[§r§cInfo§r§4§l]§r §c§lReconnectez vous pour voir les changements.");
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas assez d'argent.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'êtes pas sur votre île.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas d'île.");
			}
		}
		else if(current.getType() == Material.GOLD_INGOT) {
			int value =  9;
			PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
			IslandManager im = IslandManager.getIslandManager();
			if(im.getIsland(p) != null) {
			if(im.getIsland(p).isAt(p.getLocation())) {
			if(pe.getMoney(p) >= biome_price.get(biome) * (value*value)) {
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				for(int x = -value ; x < value; x++) {
					for(int z = -value ; z < value; z++) {
						Location lc_p = p.getLocation();
						Location lc = new Location(lc_p.getWorld(),(int)lc_p.getX() + x,0,(int)lc_p.getZ() + z);
				        Block block = lc.getBlock();
					block.setBiome(biome);
					}}
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez acheté : §6§l" + biome.toString() + "§r§e sur une surface de §6§l" + value + "§r§e blocks².");
				pe.setMoney(p, pe.getMoney(p) - biome_price.get(biome) * (value*value));
				p.sendMessage("§4§l[§r§cInfo§r§4§l]§r §c§lReconnectez vous pour voir les changements.");
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas assez d'argent.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'êtes pas sur votre île.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas d'île.");
			}
		}else if(current.getType() == Material.GOLD_BLOCK) {
			int value =  12;
			PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
			IslandManager im = IslandManager.getIslandManager();
			if(im.getIsland(p) != null) {
			if(im.getIsland(p).isAt(p.getLocation())) {
			if(pe.getMoney(p) >= biome_price.get(biome) * (value*value)) {
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				for(int x = -value ; x < value; x++) {
					for(int z = -value ; z < value; z++) {
						Location lc_p = p.getLocation();
						Location lc = new Location(lc_p.getWorld(),(int)lc_p.getX() + x,0,(int)lc_p.getZ() + z);
				        Block block = lc.getBlock();
					block.setBiome(biome);
					}}
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez acheté : §6§l" + biome.toString() + "§r§e sur une surface de §6§l" + value + "§r§e blocks².");
				p.sendMessage("§4§l[§r§cInfo§r§4§l]§r §c§lReconnectez vous pour voir les changements.");
				pe.setMoney(p, pe.getMoney(p) - biome_price.get(biome) * (value*value));
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas assez d'argent.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'êtes pas sur votre île.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas d'île.");
			}
		}else if(current.getType() == Material.DIAMOND) {
			int value =  16;
			PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
			IslandManager im = IslandManager.getIslandManager();
			if(im.getIsland(p) != null) {
			if(im.getIsland(p).isAt(p.getLocation())) {
			if(pe.getMoney(p) >= biome_price.get(biome) * (value*value)) {
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				for(int x = -value ; x < value; x++) {
					for(int z = -value ; z < value; z++) {
						Location lc_p = p.getLocation();
						Location lc = new Location(lc_p.getWorld(),(int)lc_p.getX() + x,0,(int)lc_p.getZ() + z);
				        Block block = lc.getBlock();
					block.setBiome(biome);
					}}
				p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez acheté : §6§l" + biome.toString() + "§r§e sur une surface de §6§l" + value + "§r§e blocks².");
				pe.setMoney(p, pe.getMoney(p) - biome_price.get(biome) * (value*value));
				p.sendMessage("§4§l[§r§cInfo§r§4§l]§r §c§lReconnectez vous pour voir les changements.");
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas assez d'argent.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'êtes pas sur votre île.");
			}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
				p.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas d'île.");
			}
		}
		
	}
	
	public static void createBiomeGui(Player player,Biome biome,String title){
		
		Inventory inv = Bukkit.createInventory(null, 27,title);
		ItemStack glass = getCustomItem(Material.YELLOW_STAINED_GLASS_PANE,"§e§lUne simple bordure",false,1);
		
		for(int i = 0; i < 9; i++) {
		inv.setItem(i, glass);
		inv.setItem(i + 18, glass);
		}
		
		ItemStack leave = getCustomItem(Material.BARRIER,"§4§lQuitter",true,1);
		inv.setItem(22, leave);
		
		List<String> str_biome_x2 = new ArrayList<String>();
		str_biome_x2.add("§f§lTransformer votre zone en " + biome.toString());
		str_biome_x2.add("§f§lLe prix : §6§l" + biome_price.get(biome) * (2*2));
		ItemStack x2 = getCustomItemWithLore(Material.COAL,"§f§lZone de §4§l2x2",true,1,str_biome_x2);
		inv.setItem(10, x2);
		
		List<String> str_biome_x3 = new ArrayList<String>();
		str_biome_x3.add("§f§lTransformer votre zone en " + biome.toString());
		str_biome_x3.add("§f§lLe prix : §6§l" + biome_price.get(biome) * (3*3));
		ItemStack x3 = getCustomItemWithLore(Material.IRON_NUGGET,"§f§lZone de §4§l3x3",true,1,str_biome_x3);
		inv.setItem(11, x3);
		
		List<String> str_biome_x5 = new ArrayList<String>();
		str_biome_x5.add("§f§lTransformer votre zone en " + biome.toString());
		str_biome_x5.add("§f§lLe prix : §6§l" + biome_price.get(biome) * (5*5));
		ItemStack x5 = getCustomItemWithLore(Material.IRON_INGOT,"§f§lZone de §4§l5x5",true,1,str_biome_x5);
		inv.setItem(12, x5);
		
		List<String> str_biome_x7 = new ArrayList<String>();
		str_biome_x7.add("§f§lTransformer votre zone en " + biome.toString());
		str_biome_x7.add("§f§lLe prix : §6§l" + biome_price.get(biome) * (7*7));
		ItemStack x7 = getCustomItemWithLore(Material.GOLD_NUGGET,"§f§lZone de §4§l7x7",true,1,str_biome_x7);
		inv.setItem(13, x7);
		
		List<String> str_biome_x9 = new ArrayList<String>();
		str_biome_x9.add("§f§lTransformer votre zone en " + biome.toString());
		str_biome_x9.add("§f§lLe prix : §6§l" + biome_price.get(biome) * (9*9));
		ItemStack x9 = getCustomItemWithLore(Material.GOLD_INGOT,"§f§lZone de §4§l9x9",true,1,str_biome_x9);
		inv.setItem(14, x9);
		
		List<String> str_biome_x12 = new ArrayList<String>();
		str_biome_x12.add("§f§lTransformer votre zone en " + biome.toString());
		str_biome_x12.add("§f§lLe prix : §6§l" + biome_price.get(biome) * (12*12));
		ItemStack x12 = getCustomItemWithLore(Material.GOLD_BLOCK,"§f§lZone de §4§l12x12",true,1,str_biome_x12);
		inv.setItem(15, x12);
		
		List<String> str_biome_x16 = new ArrayList<String>();
		str_biome_x16.add("§f§lTransformer votre zone en " + biome.toString());
		str_biome_x16.add("§f§lLe prix : §6§l" + biome_price.get(biome) * (16*16));
		ItemStack x16 = getCustomItemWithLore(Material.DIAMOND,"§f§lZone de §4§l16x16",true,1,str_biome_x16);
		inv.setItem(16, x16);

		
		player.openInventory(inv);
	
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
