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

import fr.eazyender.skyblock.player.PlayerEconomy;

public class GuiShop implements Listener{
	
	static int[][] prices = {{400,300,150,75,50,50,25,10   ,250,200,100,50,60,35,35,20}
	,{5,10,15,15,15,15,15,30,50,75,50,150,100,25,20}
	,{10,15,15,20,30,30,10,40,20,5,7,10,10,10,15,15,15,20,10,10,10,10,200}
	,{40,40,75,75,50,50,50,50,50,75,50,50,75,75,100,700,150,50,30}
	,{15,20,20,20,30,100,150,20,10000}};
	static Material[][] itemSell = {{Material.NETHER_QUARTZ_ORE,Material.EMERALD_ORE,Material.DIAMOND_ORE,Material.REDSTONE_ORE,Material.GOLD_ORE,Material.LAPIS_ORE,Material.IRON_ORE,Material.COAL_ORE,
									Material.QUARTZ,Material.EMERALD,Material.DIAMOND,Material.REDSTONE,Material.GOLD_INGOT,Material.LAPIS_LAZULI,Material.IRON_INGOT,Material.COAL}
									,{Material.COBBLESTONE,Material.STONE,Material.GRAVEL,Material.ANDESITE,Material.DIORITE,Material.GRANITE,Material.CLAY,Material.SAND,Material.DIRT,Material.GRASS_BLOCK,Material.NETHERRACK,Material.SOUL_SAND,Material.GLOWSTONE,Material.PRISMARINE,Material.SEA_LANTERN}
									,{Material.OAK_SAPLING,Material.BIRCH_SAPLING,Material.SPRUCE_SAPLING,Material.ACACIA_SAPLING,Material.DARK_OAK_SAPLING,Material.JUNGLE_SAPLING,Material.OAK_LOG,Material.LAVA_BUCKET,Material.WATER_BUCKET,Material.LILY_PAD
										,Material.WHEAT_SEEDS,Material.PUMPKIN_SEEDS,Material.MELON_SEEDS,Material.POTATO,Material.CARROT,Material.BEETROOT_SEEDS,Material.SWEET_BERRIES,Material.SUGAR_CANE,Material.VINE,Material.BAMBOO,Material.CACTUS,Material.NETHER_WART}
									,{Material.PIG_SPAWN_EGG,Material.RABBIT_SPAWN_EGG,Material.COW_SPAWN_EGG,Material.CHICKEN_SPAWN_EGG,Material.COD_SPAWN_EGG,Material.TROPICAL_FISH_SPAWN_EGG,Material.SALMON_SPAWN_EGG,Material.CAT_SPAWN_EGG,Material.SQUID_SPAWN_EGG,Material.HORSE_SPAWN_EGG,Material.LLAMA_SPAWN_EGG,Material.TURTLE_EGG,Material.PUFFERFISH_SPAWN_EGG,Material.SHEEP_SPAWN_EGG,Material.MOOSHROOM_SPAWN_EGG,Material.VILLAGER_SPAWN_EGG,Material.ZOMBIE_SPAWN_EGG,Material.FOX_SPAWN_EGG,Material.NAME_TAG}
									,{Material.ROTTEN_FLESH,Material.STRING,Material.GUNPOWDER,Material.BONE,Material.SLIME_BALL,Material.BLAZE_ROD,Material.GHAST_TEAR,Material.SPIDER_EYE,Material.NETHER_STAR}};
	
	private static double coefsell = 5;
	
	public static void createGui(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 27, "§e§l Magasin");
		ItemStack glass = getCustomItem(Material.YELLOW_STAINED_GLASS_PANE,"§e§lUne simple bordure",false,1);
		
		for(int i = 0 + 9; i < 9 + 9; i++) {
			inv.setItem(i, glass);
		}
		
		ItemStack shop_minerais = getCustomItem(Material.GOLD_ORE,"§f§lMinérais / Achat",false,1);
		inv.setItem(0, shop_minerais);
		ItemStack shop_mobs = getCustomItem(Material.NETHERRACK,"§f§lMobs / Achat",false,1);
		inv.setItem(1, shop_mobs);
		ItemStack shop_vegetaux = getCustomItem(Material.OAK_LEAVES,"§f§lVégétaux / Achat",false,1);
		inv.setItem(2, shop_vegetaux);
		ItemStack shop_blocks = getCustomItem(Material.DIRT,"§f§lBlocs / Achat",false,1);
		inv.setItem(3, shop_blocks);
		ItemStack shop_loots = getCustomItem(Material.BONE_BLOCK,"§f§lLoots / Achat",false,1);
		inv.setItem(4, shop_loots);
		
		ItemStack shop_minerais_vente = getCustomItem(Material.GOLD_ORE,"§f§lMinérais / Vente",false,2);
		inv.setItem(18, shop_minerais_vente);
		ItemStack shop_mobs_vente = getCustomItem(Material.NETHERRACK,"§f§lMobs / Vente",false,2);
		inv.setItem(19, shop_mobs_vente);
		ItemStack shop_vegetaux_vente = getCustomItem(Material.OAK_LEAVES,"§f§lVégétaux / Vente",false,2);
		inv.setItem(20, shop_vegetaux_vente);
		ItemStack shop_blocks_vente = getCustomItem(Material.DIRT,"§f§lBlocs / Vente",false,2);
		inv.setItem(21, shop_blocks_vente);
		ItemStack shop_loots_vente = getCustomItem(Material.BONE_BLOCK,"§f§lLoots / Vente",false,2);
		inv.setItem(22, shop_loots_vente);
		
		player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
		player.openInventory(inv);
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(current != null) {
			
			if(event.getView().getTitle().equalsIgnoreCase("§e§l Magasin")) {
				
				
				event.setCancelled(true);
				
				if(current.getType() == Material.GOLD_ORE) {
					 
					if(current.getAmount() == 1) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGuiMinerais(player,false);}
					else if(current.getAmount() == 2){
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGuiMinerais(player,true);
					}
					
			   }
				
				else if(current.getType() == Material.DIRT) {
					 
					if(current.getAmount() == 1) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGuiBlocks(player,false);
					}else if(current.getAmount() == 2) {
						player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
						createGuiBlocks(player,true);	
					}
					
			   }
				
				else if(current.getType() == Material.OAK_LEAVES) {
					 
					if(current.getAmount() == 1) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGuiVegetals(player,false);
					}else if(current.getAmount() == 2) {
						player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
						createGuiVegetals(player,true);	
					}
					
			   }
				
				else if(current.getType() == Material.NETHERRACK) {
					 
					if(current.getAmount() == 1) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGuiMobsEggs(player,false);
					}else if(current.getAmount() == 2) {
						player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
						createGuiMobsEggs(player,true);	
					}
					
			   }else if(current.getType() == Material.BONE_BLOCK) {
					 
					if(current.getAmount() == 1) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGuiLoots(player,false);
					}else if(current.getAmount() == 2) {
						player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
						createGuiLoots(player,true);	
					}
					
			   }
				
			}
			else if(event.getView().getTitle().equalsIgnoreCase("§e§l Magasin : Minérais / Achat")) {
				
				event.setCancelled(true);
				
				for(int i = 0;i < 8;i++) {
					if(current.getType() == itemSell[0][i] && current.getAmount() == 1) {createBuyingItem(prices[0][i],player,new ItemStack(itemSell[0][i] , 1));}
				}
				
				for(int i = 0;i < 8;i++) {
					if(current.getType() == itemSell[0][i+8] && current.getAmount() == 1) {createBuyingItem(prices[0][i+8],player,new ItemStack(itemSell[0][i+8] , 1));}
				}
				for(int i = 0;i < 8;i++) {
					if(current.getType() == itemSell[0][i] && current.getAmount() == 64) {createBuyingItem((int) (prices[0][i] * 64),player,new ItemStack(itemSell[0][i] , 64));}
				}
				for(int i = 0;i < 8;i++) {
					if(current.getType() == itemSell[0][i+8] && current.getAmount() == 64) {createBuyingItem((int) (prices[0][i + 8] * 64),player,new ItemStack(itemSell[0][i+8] , 64));}
				}
				
				if(current.getType() == Material.BARRIER) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGui(player);
				}
				
			}else if(event.getView().getTitle().equalsIgnoreCase("§e§l Magasin : Minérais / Vente")) {
				
				event.setCancelled(true);
				
				for(int i = 0;i < 8;i++) {
					if(current.getType() == itemSell[0][i] && current.getAmount() == 1) {createSellingItem((int) (prices[0][i] / coefsell),player,new ItemStack(itemSell[0][i] , 1));}
				}
				
				for(int i = 0;i < 8;i++) {
					if(current.getType() == itemSell[0][i+8] && current.getAmount() == 1) {createSellingItem((int) (prices[0][i+8] / coefsell),player,new ItemStack(itemSell[0][i+8] , 1));}
				}
				for(int i = 0;i < 8;i++) {
					if(current.getType() == itemSell[0][i] && current.getAmount() == 64) {createSellingItem((int) (prices[0][i] * 64 / coefsell),player,new ItemStack(itemSell[0][i] , 64));}
				}
				for(int i = 0;i < 8;i++) {
					if(current.getType() == itemSell[0][i+8] && current.getAmount() == 64) {createSellingItem((int) (prices[0][i] * 64 / coefsell),player,new ItemStack(itemSell[0][i+8] , 64));}
				}
				
				if(current.getType() == Material.BARRIER) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGui(player);
				}
				
			}
			
			else if(event.getView().getTitle().equalsIgnoreCase("§e§l Magasin : Blocs / Achat")) {
				
				event.setCancelled(true);
				
				for(int i = 0;i < itemSell[1].length;i++) {
					if(current.getType() == itemSell[1][i] && current.getAmount() == 1) {createBuyingItem(prices[1][i],player,new ItemStack(itemSell[1][i] , 1));}
				}
				for(int i = 0;i < itemSell[1].length;i++) {
					if(current.getType() == itemSell[1][i] && current.getAmount() == 64) {createBuyingItem(prices[1][i] * 64,player,new ItemStack(itemSell[1][i] , 64));}
				}
				
				if(current.getType() == Material.BARRIER) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGui(player);
				}
				
			}else if(event.getView().getTitle().equalsIgnoreCase("§e§l Magasin : Blocs / Vente")) {
				
				event.setCancelled(true);
				
				for(int i = 0;i < itemSell[1].length;i++) {
					if(current.getType() == itemSell[1][i] && current.getAmount() == 1) {createSellingItem((int) (prices[1][i] / coefsell),player,new ItemStack(itemSell[1][i] , 1));}
				}
				for(int i = 0;i < itemSell[1].length;i++) {
					if(current.getType() == itemSell[1][i] && current.getAmount() == 64) {createSellingItem((int) (prices[1][i] * 64 / coefsell),player,new ItemStack(itemSell[1][i] , 64));}
				}
				
				if(current.getType() == Material.BARRIER) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGui(player);
				}
				
			}else if(event.getView().getTitle().equalsIgnoreCase("§e§l Magasin : Végétaux / Achat")) {
				
				event.setCancelled(true);
				
				for(int i = 0;i < itemSell[2].length;i++) {
					if(current.getType() == itemSell[2][i] && current.getAmount() == 1) {createBuyingItem(prices[2][i],player,new ItemStack(itemSell[2][i] , 1));}
				}
				for(int i = 0;i < itemSell[2].length;i++) {
					if(current.getType() == itemSell[2][i] && current.getAmount() == 64) {createBuyingItem((int) (prices[2][i] * 64),player,new ItemStack(itemSell[2][i] , 64));}
				}
				
				if(current.getType() == Material.BARRIER) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGui(player);
				}
				
			}else if(event.getView().getTitle().equalsIgnoreCase("§e§l Magasin : Végétaux / Vente")) {
				
				event.setCancelled(true);
				
				for(int i = 0;i < itemSell[2].length;i++) {
					if(current.getType() == itemSell[2][i] && current.getAmount() == 1) {createSellingItem((int) (prices[2][i] / coefsell),player,new ItemStack(itemSell[2][i] , 1));}
				}
				for(int i = 0;i < itemSell[2].length;i++) {
					if(current.getType() == itemSell[2][i] && current.getAmount() == 64) {createSellingItem((int) (prices[2][i] * 64 / coefsell),player,new ItemStack(itemSell[2][i] , 64));}
				}
				
				if(current.getType() == Material.BARRIER) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGui(player);
				}
				
			}else if(event.getView().getTitle().equalsIgnoreCase("§e§l Magasin : Mobs / Achat")) {
				
				event.setCancelled(true);
				
				for(int i = 0;i < itemSell[3].length;i++) {
					if(current.getType() == itemSell[3][i] && current.getAmount() == 1) {createBuyingItem(prices[3][i],player,new ItemStack(itemSell[3][i] , 1));}
				}
				for(int i = 0;i < itemSell[3].length;i++) {
					if(current.getType() == itemSell[3][i] && current.getAmount() == 64) {createBuyingItem((int) (prices[3][i] * 64),player,new ItemStack(itemSell[3][i] , 64));}
				}
				
				if(current.getType() == Material.BARRIER) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGui(player);
				}
				
			}else if(event.getView().getTitle().equalsIgnoreCase("§e§l Magasin : Mobs / Vente")) {
				
				event.setCancelled(true);
				
				for(int i = 0;i < itemSell[3].length;i++) {
					if(current.getType() == itemSell[3][i] && current.getAmount() == 1) {createSellingItem((int) (prices[3][i] / coefsell),player,new ItemStack(itemSell[3][i] , 1));}
				}
				for(int i = 0;i < itemSell[3].length;i++) {
					if(current.getType() == itemSell[3][i] && current.getAmount() == 64) {createSellingItem((int) (prices[3][i] * 64 / coefsell),player,new ItemStack(itemSell[3][i] , 64));}
				}
				
				if(current.getType() == Material.BARRIER) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGui(player);
				}
				
			}else if(event.getView().getTitle().equalsIgnoreCase("§e§l Magasin : Loots / Achat")) {
				
				event.setCancelled(true);
				
				for(int i = 0;i < itemSell[4].length;i++) {
					if(current.getType() == itemSell[4][i] && current.getAmount() == 1) {createBuyingItem(prices[4][i],player,new ItemStack(itemSell[4][i] , 1));}
				}
				for(int i = 0;i < itemSell[4].length;i++) {
					if(current.getType() == itemSell[4][i] && current.getAmount() == 64) {createBuyingItem((int) (prices[4][i] * 64),player,new ItemStack(itemSell[4][i] , 64));}
				}
				
				if(current.getType() == Material.BARRIER) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGui(player);
				}
				
			}else if(event.getView().getTitle().equalsIgnoreCase("§e§l Magasin : Loots / Vente")) {
				
				event.setCancelled(true);
				
				for(int i = 0;i < itemSell[4].length;i++) {
					if(current.getType() == itemSell[4][i] && current.getAmount() == 1) {createSellingItem((int) (prices[4][i] / coefsell),player,new ItemStack(itemSell[4][i] , 1));}
				}
				for(int i = 0;i < itemSell[4].length;i++) {
					if(current.getType() == itemSell[4][i] && current.getAmount() == 64) {createSellingItem((int) (prices[4][i] * 64 / coefsell),player,new ItemStack(itemSell[4][i] , 64));}
				}
				
				if(current.getType() == Material.BARRIER) {
					player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
					createGui(player);
				}
				
			}
			
		}
		
			
	}
	
	private static void createGuiBlocks(Player player,boolean isSell) {
		
		if(!isSell) {
		Inventory inv = Bukkit.createInventory(null, 45, "§e§l Magasin : Blocs / Achat");
		
		for(int i = 0;i < itemSell[1].length;i++) {
		List<String> lore = new ArrayList<String>();
		String priceStr = "§f§lPrix :§r§6 " + prices[1][i];
		lore.add(priceStr);
		ItemStack shop_block = getCustomItemWithLore(itemSell[1][i],"§e§l§n" + itemSell[1][i].name(),false,1,lore);
		inv.setItem(i, shop_block);
		}
		
		for(int i = 0;i < itemSell[1].length;i++) {
			List<String> lore = new ArrayList<String>();
			String priceStr = "§f§lPrix :§r§6 " + prices[1][i] * 64;
			lore.add(priceStr);
			ItemStack shop_block = getCustomItemWithLore(itemSell[1][i],"§e§l§n" + itemSell[1][i].name(),false,64,lore);
			inv.setItem(i + 9*2, shop_block);
			}

		ItemStack return_shop = getCustomItem(Material.BARRIER,"§4§lRetour",false,1);
		inv.setItem(36, return_shop);
		
		player.openInventory(inv);
		}else {
			Inventory inv = Bukkit.createInventory(null, 45, "§e§l Magasin : Blocs / Vente");
			
			for(int i = 0;i < itemSell[1].length;i++) {
			List<String> lore = new ArrayList<String>();
			String priceStr = "§f§lPrix :§r§6 " + ((int)(prices[1][i] / coefsell));
			lore.add(priceStr);
			ItemStack shop_block = getCustomItemWithLore(itemSell[1][i],"§e§l§n" + itemSell[1][i].name(),false,1,lore);
			inv.setItem(i, shop_block);
			}
			
			for(int i = 0;i < itemSell[1].length;i++) {
				List<String> lore = new ArrayList<String>();
				String priceStr = "§f§lPrix :§r§6 " + prices[1][i] * 64 / coefsell;
				lore.add(priceStr);
				ItemStack shop_block = getCustomItemWithLore(itemSell[1][i],"§e§l§n" + itemSell[1][i].name(),false,64,lore);
				inv.setItem(i + 9*2, shop_block);
				}

			ItemStack return_shop = getCustomItem(Material.BARRIER,"§4§lRetour",false,1);
			inv.setItem(36, return_shop);
			
			player.openInventory(inv);
		}
	}
	
	private static void createGuiVegetals(Player player,boolean isSell) {
		
		if(!isSell) {
		Inventory inv = Bukkit.createInventory(null, 54, "§e§l Magasin : Végétaux / Achat");
		
		for(int i = 0;i < itemSell[2].length;i++) {
		List<String> lore = new ArrayList<String>();
		String priceStr = "§f§lPrix :§r§6 " + prices[2][i];
		lore.add(priceStr);
		ItemStack shop_block = getCustomItemWithLore(itemSell[2][i],"§e§l§n" + itemSell[2][i].name(),false,1,lore);
		inv.setItem(i, shop_block);
		}
		
		for(int i = 0;i < itemSell[2].length;i++) {
			List<String> lore = new ArrayList<String>();
			String priceStr = "§f§lPrix :§r§6 " + prices[2][i] * 64;
			lore.add(priceStr);
			ItemStack shop_block = getCustomItemWithLore(itemSell[2][i],"§e§l§n" + itemSell[2][i].name(),false,64,lore);
			inv.setItem(i + 9*3, shop_block);
			}

		ItemStack return_shop = getCustomItem(Material.BARRIER,"§4§lRetour",false,1);
		inv.setItem(53, return_shop);
		
		player.openInventory(inv);
		}else {
			Inventory inv = Bukkit.createInventory(null, 54, "§e§l Magasin : Végétaux / Vente");
			
			for(int i = 0;i < itemSell[2].length;i++) {
			List<String> lore = new ArrayList<String>();
			String priceStr = "§f§lPrix :§r§6 " + ((int)(prices[2][i] / coefsell));
			lore.add(priceStr);
			ItemStack shop_block = getCustomItemWithLore(itemSell[2][i],"§e§l§n" + itemSell[2][i].name(),false,1,lore);
			inv.setItem(i, shop_block);
			}
			
			for(int i = 0;i < itemSell[2].length;i++) {
				List<String> lore = new ArrayList<String>();
				String priceStr = "§f§lPrix :§r§6 " + prices[2][i] * 64 / coefsell;
				lore.add(priceStr);
				ItemStack shop_block = getCustomItemWithLore(itemSell[2][i],"§e§l§n" + itemSell[2][i].name(),false,64,lore);
				inv.setItem(i + 9*3, shop_block);
				}

			ItemStack return_shop = getCustomItem(Material.BARRIER,"§4§lRetour",false,1);
			inv.setItem(53, return_shop);
			
			player.openInventory(inv);
		}
	}
	
	private static void createGuiMobsEggs(Player player,boolean isSell) {
		
		if(!isSell) {
		Inventory inv = Bukkit.createInventory(null, 45, "§e§l Magasin : Mobs / Achat");
		
		for(int i = 0;i < itemSell[3].length;i++) {
		List<String> lore = new ArrayList<String>();
		String priceStr = "§f§lPrix :§r§6 " + prices[3][i];
		lore.add(priceStr);
		ItemStack shop_block = getCustomItemWithLore(itemSell[3][i],"§e§l§n" + itemSell[3][i].name(),false,1,lore);
		inv.setItem(i, shop_block);
		}
		
		for(int i = 0;i < itemSell[3].length;i++) {
			List<String> lore = new ArrayList<String>();
			String priceStr = "§f§lPrix :§r§6 " + prices[3][i] * 64;
			lore.add(priceStr);
			ItemStack shop_block = getCustomItemWithLore(itemSell[3][i],"§e§l§n" + itemSell[3][i].name(),false,64,lore);
			inv.setItem(i + 9*2, shop_block);
			}

		ItemStack return_shop = getCustomItem(Material.BARRIER,"§4§lRetour",false,1);
		inv.setItem(36, return_shop);
		
		player.openInventory(inv);
		}else {
			Inventory inv = Bukkit.createInventory(null, 45, "§e§l Magasin : Mobs / Vente");
			
			for(int i = 0;i < itemSell[3].length;i++) {
			List<String> lore = new ArrayList<String>();
			String priceStr = "§f§lPrix :§r§6 " + ((int)(prices[3][i] / coefsell));
			lore.add(priceStr);
			ItemStack shop_block = getCustomItemWithLore(itemSell[3][i],"§e§l§n" + itemSell[3][i].name(),false,1,lore);
			inv.setItem(i, shop_block);
			}
			
			for(int i = 0;i < itemSell[3].length;i++) {
				List<String> lore = new ArrayList<String>();
				String priceStr = "§f§lPrix :§r§6 " + prices[3][i] * 64 / coefsell;
				lore.add(priceStr);
				ItemStack shop_block = getCustomItemWithLore(itemSell[3][i],"§e§l§n" + itemSell[3][i].name(),false,64,lore);
				inv.setItem(i + 9*2, shop_block);
				}

			ItemStack return_shop = getCustomItem(Material.BARRIER,"§4§lRetour",false,1);
			inv.setItem(36, return_shop);
			
			player.openInventory(inv);
		}
	}
	
	private static void createGuiLoots(Player player,boolean isSell) {
		
		if(!isSell) {
		Inventory inv = Bukkit.createInventory(null, 27, "§e§l Magasin : Loots / Achat");
		
		for(int i = 0;i < itemSell[4].length;i++) {
		List<String> lore = new ArrayList<String>();
		String priceStr = "§f§lPrix :§r§6 " + prices[4][i];
		lore.add(priceStr);
		ItemStack shop_block = getCustomItemWithLore(itemSell[4][i],"§e§l§n" + itemSell[4][i].name(),false,1,lore);
		inv.setItem(i, shop_block);
		}
		
		for(int i = 0;i < itemSell[4].length;i++) {
			List<String> lore = new ArrayList<String>();
			String priceStr = "§f§lPrix :§r§6 " + prices[4][i] * 64;
			lore.add(priceStr);
			ItemStack shop_block = getCustomItemWithLore(itemSell[4][i],"§e§l§n" + itemSell[4][i].name(),false,64,lore);
			inv.setItem(i + 9, shop_block);
			}

		ItemStack return_shop = getCustomItem(Material.BARRIER,"§4§lRetour",false,1);
		inv.setItem(18, return_shop);
		
		player.openInventory(inv);
		}else {
			Inventory inv = Bukkit.createInventory(null, 27, "§e§l Magasin : Loots / Vente");
			
			for(int i = 0;i < itemSell[4].length;i++) {
			List<String> lore = new ArrayList<String>();
			String priceStr = "§f§lPrix :§r§6 " + ((int)(prices[4][i] / coefsell));
			lore.add(priceStr);
			ItemStack shop_block = getCustomItemWithLore(itemSell[4][i],"§e§l§n" + itemSell[4][i].name(),false,1,lore);
			inv.setItem(i, shop_block);
			}
			
			for(int i = 0;i < itemSell[4].length;i++) {
				List<String> lore = new ArrayList<String>();
				String priceStr = "§f§lPrix :§r§6 " + prices[4][i] * 64 / coefsell;
				lore.add(priceStr);
				ItemStack shop_block = getCustomItemWithLore(itemSell[4][i],"§e§l§n" + itemSell[4][i].name(),false,64,lore);
				inv.setItem(i + 9, shop_block);
				}

			ItemStack return_shop = getCustomItem(Material.BARRIER,"§4§lRetour",false,1);
			inv.setItem(18, return_shop);
			
			player.openInventory(inv);
		}
	}
	
	private static void createGuiMinerais(Player player,boolean isSell) {
		
		if(!isSell) {
		Inventory inv = Bukkit.createInventory(null, 45, "§e§l Magasin : Minérais / Achat");
		
		for(int i = 0;i < 8;i++) {
		List<String> lore = new ArrayList<String>();
		String priceStr = "§f§lPrix :§r§6 " + prices[0][i];
		lore.add(priceStr);
		ItemStack shop_block = getCustomItemWithLore(itemSell[0][i],"§e§l§n" + itemSell[0][i].name(),false,1,lore);
		inv.setItem(i, shop_block);
		}
		
		for(int i = 0;i < 8;i++) {
			List<String> lore = new ArrayList<String>();
			String priceStr = "§f§lPrix :§r§6 " + prices[0][i + 8];
			lore.add(priceStr);
			ItemStack shop_block = getCustomItemWithLore(itemSell[0][i + 8],"§e§l§n" + itemSell[0][i + 8].name(),false,1,lore);
			inv.setItem(i + 9, shop_block);
		}
		for(int i = 0;i < 8;i++) {
			List<String> lore = new ArrayList<String>();
			String priceStr = "§f§lPrix :§r§6 " + prices[0][i] * 64;
			lore.add(priceStr);
			ItemStack shop_block = getCustomItemWithLore(itemSell[0][i],"§e§l§n" + itemSell[0][i].name(),false,64,lore);
			inv.setItem(i + 9 * 2, shop_block);
			}
			
			for(int i = 0;i < 8;i++) {
				List<String> lore = new ArrayList<String>();
				String priceStr = "§f§lPrix :§r§6 " + prices[0][i + 8] * 64;
				lore.add(priceStr);
				ItemStack shop_block = getCustomItemWithLore(itemSell[0][i + 8],"§e§l§n" + itemSell[0][i + 8].name(),false,64,lore);
				inv.setItem(i + 9 * 3, shop_block);
			}
		
		ItemStack return_shop = getCustomItem(Material.BARRIER,"§4§lRetour",false,1);
		inv.setItem(36, return_shop);

		player.openInventory(inv);
		}else {
			Inventory inv = Bukkit.createInventory(null, 45, "§e§l Magasin : Minérais / Vente");
			
			for(int i = 0;i < 8;i++) {
			List<String> lore = new ArrayList<String>();
			String priceStr = "§f§lPrix :§r§6 " + ((int)(prices[0][i] / coefsell));
			lore.add(priceStr);
			ItemStack shop_block = getCustomItemWithLore(itemSell[0][i],"§e§l§n" + itemSell[0][i].name(),false,1,lore);
			inv.setItem(i, shop_block);
			}
			
			for(int i = 0;i < 8;i++) {
				List<String> lore = new ArrayList<String>();
				String priceStr = "§f§lPrix :§r§6 " + ((int)(prices[0][i + 8] / coefsell));
				lore.add(priceStr);
				ItemStack shop_block = getCustomItemWithLore(itemSell[0][i + 8],"§e§l§n" + itemSell[0][i + 8].name(),false,1,lore);
				inv.setItem(i + 9, shop_block);
			}
			for(int i = 0;i < 8;i++) {
				List<String> lore = new ArrayList<String>();
				String priceStr = "§f§lPrix :§r§6 " + prices[0][i] * 64 / coefsell;
				lore.add(priceStr);
				ItemStack shop_block = getCustomItemWithLore(itemSell[0][i],"§e§l§n" + itemSell[0][i].name(),false,64,lore);
				inv.setItem(i + 9 * 2, shop_block);
				}
				
				for(int i = 0;i < 8;i++) {
					List<String> lore = new ArrayList<String>();
					String priceStr = "§f§lPrix :§r§6 " + prices[0][i + 8] * 64 / coefsell;
					lore.add(priceStr);
					ItemStack shop_block = getCustomItemWithLore(itemSell[0][i + 8],"§e§l§n" + itemSell[0][i + 8].name(),false,64,lore);
					inv.setItem(i + 9 * 3, shop_block);
				}
			
			ItemStack return_shop = getCustomItem(Material.BARRIER,"§4§lRetour",false,1);
			inv.setItem(36, return_shop);

			player.openInventory(inv);
		}
	}
	
	private static void createBuyingItem(int price,Player player,ItemStack item) {
		
		if(PlayerEconomy.getPlayerEconomy().getMoney(player) >= price) {
			
			PlayerEconomy.getPlayerEconomy().setMoney(player, PlayerEconomy.getPlayerEconomy().getMoney(player) - (price));
			player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
			player.getInventory().addItem(item);
			player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez acheté : §f§l" + item.getType().toString() + "§r§e à un prix de : §r§f§l" + price);
			
		}else {
			player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
			player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas assez d'argent pour acheter : §f§l" + item.getType().toString() + "§r§e , il vous manque : §r§f§l" + ((PlayerEconomy.getPlayerEconomy().getMoney(player) - price) * -1));
		}
		
	}
	
	private static void createSellingItem(int price,Player player,ItemStack item) {
		 
		if(player.getInventory().contains(item.getType(),item.getAmount())) {
			
			PlayerEconomy.getPlayerEconomy().addMoney(player,price);
			player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
			player.getInventory().removeItem(item);
			player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez vendu : §f§l" + item.getType().toString() + "§r§e à un prix de : §r§f§l" + price);
			
		}else {
			player.playSound(player.getLocation(), Sound.ENTITY_CAT_HURT, 1, 1);
			player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas l'objet : §f§l" + item.getType().toString());
		}
		
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
