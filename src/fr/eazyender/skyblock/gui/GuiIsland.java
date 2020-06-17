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

import fr.eazyender.skyblock.island.IslandManager;
import fr.eazyender.skyblock.player.PlayerEconomy;

public class GuiIsland implements Listener{
	
	public static int price_basique_t = 500;
	
	public static void createGui(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 27, "§e§l Gestion de son île");
		ItemStack glass = getCustomItem(Material.YELLOW_STAINED_GLASS_PANE,"§e§lUne simple bordure",false);
		
		IslandManager im = IslandManager.getIslandManager();
		
		for(int i = 0; i < 9; i++) {
		inv.setItem(i, glass);
		inv.setItem(i + 18, glass);
		}
		
		ItemStack doorHome = getCustomItem(Material.OAK_DOOR,"§f§lAller sur votre île",true);
		inv.setItem(22, doorHome);
		
		ItemStack deleteIsland = getCustomItem(Material.TNT,"§4§lSupprimer §r§fvotre île",true);
		inv.setItem(10, deleteIsland);
		
		List<String> lore = new ArrayList<String>();
		String str_lore;
		if(im.hasIsland(player)) {
		str_lore = "§f§lTaille actuelle :§r§6 " + im.getIsland(player).getRayon();
		}else {
		str_lore = "§f§lTaille actuelle :§r§6 " + "Pas d'île";	
		}
		lore.add(str_lore);
		ItemStack rayonShop = getCustomItemWithLore(Material.CARTOGRAPHY_TABLE,"§r§fAgrandir son §r§6§lîle",true,1,lore);
		inv.setItem(11, rayonShop);
		
		
		
		player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
		player.openInventory(inv);
		
	}
	
	private static void createGuiDelete(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 9, "§e§l Gestion de son île > §4§lDelete");
		ItemStack glass = getCustomItem(Material.BLACK_STAINED_GLASS_PANE,"§e§lUne simple bordure",false);
		
		IslandManager im = IslandManager.getIslandManager();
		
		inv.setItem(0, glass);
		inv.setItem(1, glass);
		inv.setItem(4, glass);
		inv.setItem(3, glass);
		inv.setItem(5, glass);
		inv.setItem(7, glass);
		inv.setItem(8, glass);
		
		ItemStack VALIDATE = getCustomItem(Material.GREEN_CONCRETE,"§a§lValider et supprimer son île",true);
		inv.setItem(6, VALIDATE);
		
		ItemStack REFUSE = getCustomItem(Material.RED_CONCRETE,"§4§lRefuser , c'était une erreur !",true);
		inv.setItem(2, REFUSE);
		
		
		
		player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
		player.openInventory(inv);
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(current != null) {
			
			if(event.getView().getTitle().equalsIgnoreCase("§e§l Gestion de son île > §4§lDelete")) {
				IslandManager im = IslandManager.getIslandManager();
				if(current.getType() == Material.GREEN_CONCRETE) {
					player.sendMessage("§e§lVotre île a été supprimé.");
		              im.deleteIsland(player);
				}else if(current.getType().equals(Material.RED_CONCRETE)){
					player.closeInventory();
				}
				
				event.setCancelled(true);
			}
			else if(event.getView().getTitle().equalsIgnoreCase("§e§l Gestion de son île")) {
				
				
				event.setCancelled(true);
				
				if(current.getType() == Material.OAK_DOOR) {
					 IslandManager im = IslandManager.getIslandManager();
					if (!im.hasIsland(player)) {
						player.sendMessage("§e§lCréation de l'île...");
			            IslandManager.createIsland(player);
			          } else if(im.hasIsland(player)){
			        	  player.sendMessage("§e§lTéléportation...");
			              im.sendHome(player);
			          }
					
				}else if(current.getType() == Material.TNT) {
					
					 IslandManager im = IslandManager.getIslandManager();
						if (!im.hasIsland(player)) {
							player.sendMessage("§e§lVous ne possédez aucune île.");
				          } else if(im.hasIsland(player)){
				        	  createGuiDelete(player);
				          }		
				}else if(current.getType().equals(Material.CARTOGRAPHY_TABLE)) {
					
					createRayonShopGui(player);
					
				}
				
				
			}else if(event.getView().getTitle().equalsIgnoreCase("§e§lGestion de son île > §r§6§lTaille")) {
				
				
				if(current.getType().equals(Material.IRON_NUGGET)) {
					int coef = 1;
					IslandManager im = IslandManager.getIslandManager();
					PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
					if (!im.hasIsland(player)) {
						player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eSi vous n'avez pas d'île vous ne pouvez pas augmenter sa taille...");
			          } else if(im.hasIsland(player)){
			              if(im.getIsland(player).getRayon() <= (250 - coef)) {
			            	  if(pe.getMoney(player) >= (price_basique_t * coef)) {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez agrandis votre île de " + coef + " bloc pour " + (price_basique_t * coef));
			            	  pe.setMoney(player, pe.getMoney(player) - (price_basique_t * coef));
			            	  im.setRayon(player, im.getIsland(player).getRayon() + coef);
			            	  }else {
			            		  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas l'argent pour.");
			            	  }
			              }else {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVotre île est déja au max de sa taille(250blocks de rayon).");  
			              }
			          }
				}else if(current.getType().equals(Material.IRON_INGOT)) {
					int coef = 2;
					IslandManager im = IslandManager.getIslandManager();
					PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
					if (!im.hasIsland(player)) {
						player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eSi vous n'avez pas d'île vous ne pouvez pas augmenter sa taille...");
			          } else if(im.hasIsland(player)){
			              if(im.getIsland(player).getRayon() <= (250 - coef)) {
			            	  if(pe.getMoney(player) >= (price_basique_t * coef)) {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez agrandis votre île de " + coef + " bloc pour " + (price_basique_t * coef));
			            	  pe.setMoney(player, pe.getMoney(player) - (price_basique_t * coef));
			            	  im.setRayon(player, im.getIsland(player).getRayon() + coef);
			            	  }else {
			            		  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas l'argent pour.");
			            	  }
			              }else {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVotre île est déja au max de sa taille(250blocks de rayon).");  
			              }
			          }
				}else if(current.getType().equals(Material.IRON_BLOCK)) {
					int coef = 5;
					IslandManager im = IslandManager.getIslandManager();
					PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
					if (!im.hasIsland(player)) {
						player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eSi vous n'avez pas d'île vous ne pouvez pas augmenter sa taille...");
			          } else if(im.hasIsland(player)){
			              if(im.getIsland(player).getRayon() <= (250 - coef)) {
			            	  if(pe.getMoney(player) >= (price_basique_t * coef)) {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez agrandis votre île de " + coef + " bloc pour " + (price_basique_t * coef));
			            	  pe.setMoney(player, pe.getMoney(player) - (price_basique_t * coef));
			            	  im.setRayon(player, im.getIsland(player).getRayon() + coef);
			            	  }else {
			            		  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas l'argent pour.");
			            	  }
			              }else {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVotre île est déja au max de sa taille(250blocks de rayon).");  
			              }
			          }
				}else if(current.getType().equals(Material.GOLD_NUGGET)) {
					int coef = 10;
					IslandManager im = IslandManager.getIslandManager();
					PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
					if (!im.hasIsland(player)) {
						player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eSi vous n'avez pas d'île vous ne pouvez pas augmenter sa taille...");
			          } else if(im.hasIsland(player)){
			              if(im.getIsland(player).getRayon() <= (250 - coef)) {
			            	  if(pe.getMoney(player) >= (price_basique_t * coef)) {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez agrandis votre île de " + coef + " bloc pour " + (price_basique_t * coef));
			            	  pe.setMoney(player, pe.getMoney(player) - (price_basique_t * coef));
			            	  im.setRayon(player, im.getIsland(player).getRayon() + coef);
			            	  }else {
			            		  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas l'argent pour.");
			            	  }
			              }else {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVotre île est déja au max de sa taille(250blocks de rayon).");  
			              }
			          }
				}else if(current.getType().equals(Material.GOLD_INGOT)) {
					int coef = 25;
					IslandManager im = IslandManager.getIslandManager();
					PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
					if (!im.hasIsland(player)) {
						player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eSi vous n'avez pas d'île vous ne pouvez pas augmenter sa taille...");
			          } else if(im.hasIsland(player)){
			              if(im.getIsland(player).getRayon() <= (250 - coef)) {
			            	  if(pe.getMoney(player) >= (price_basique_t * coef)) {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez agrandis votre île de " + coef + " bloc pour " + (price_basique_t * coef));
			            	  pe.setMoney(player, pe.getMoney(player) - (price_basique_t * coef));
			            	  im.setRayon(player, im.getIsland(player).getRayon() + coef);
			            	  }else {
			            		  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas l'argent pour.");
			            	  }
			              }else {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVotre île est déja au max de sa taille(250blocks de rayon).");  
			              }
			          }
				}else if(current.getType().equals(Material.GOLD_BLOCK)) {
					int coef = 50;
					IslandManager im = IslandManager.getIslandManager();
					PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
					if (!im.hasIsland(player)) {
						player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eSi vous n'avez pas d'île vous ne pouvez pas augmenter sa taille...");
			          } else if(im.hasIsland(player)){
			              if(im.getIsland(player).getRayon() <= (250 - coef)) {
			            	  if(pe.getMoney(player) >= (price_basique_t * coef)) {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez agrandis votre île de " + coef + " bloc pour " + (price_basique_t * coef));
			            	  pe.setMoney(player, pe.getMoney(player) - (price_basique_t * coef));
			            	  im.setRayon(player, im.getIsland(player).getRayon() + coef);
			            	  }else {
			            		  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas l'argent pour.");
			            	  }
			              }else {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVotre île est déja au max de sa taille(250blocks de rayon).");  
			              }
			          }
				}else if(current.getType().equals(Material.DIAMOND)) {
					int coef = 100;
					IslandManager im = IslandManager.getIslandManager();
					PlayerEconomy pe = PlayerEconomy.getPlayerEconomy();
					if (!im.hasIsland(player)) {
						player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eSi vous n'avez pas d'île vous ne pouvez pas augmenter sa taille...");
			          } else if(im.hasIsland(player)){
			              if(im.getIsland(player).getRayon() <= (250 - coef)) {
			            	  if(pe.getMoney(player) >= (price_basique_t * coef)) {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez agrandis votre île de " + coef + " bloc pour " + (price_basique_t * coef));
			            	  pe.setMoney(player, pe.getMoney(player) - (price_basique_t * coef));
			            	  im.setRayon(player, im.getIsland(player).getRayon() + coef);
			            	  }else {
			            		  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas l'argent pour.");
			            	  }
			              }else {
			            	  player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVotre île est déja au max de sa taille(250blocks de rayon).");  
			              }
			          }
				}
				
				event.setCancelled(true);
				
			}
			
		}
		
			
	}
	
	private static void createRayonShopGui(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 27, "§e§lGestion de son île > §r§6§lTaille");
		ItemStack glass = getCustomItem(Material.YELLOW_STAINED_GLASS_PANE,"§e§lUne simple bordure",false);
		
		for(int i = 0; i < 9; i++) {
		inv.setItem(i, glass);
		inv.setItem(i + 18, glass);
		}
		
		List<String> lore_b1 = new ArrayList<String>();
		String priceStr_b1 = "§f§lPrix :§r§6 " + price_basique_t * 1;
		lore_b1.add(priceStr_b1);
		ItemStack b1b = getCustomItemWithLore(Material.IRON_NUGGET,"§r§fAgrandir de §r§6§l1 block",true,1,lore_b1);
		inv.setItem(10, b1b);
		
		List<String> lore_b2 = new ArrayList<String>();
		String priceStr_b2 = "§f§lPrix :§r§6 " + price_basique_t * 2;
		lore_b2.add(priceStr_b2);
		ItemStack b2b = getCustomItemWithLore(Material.IRON_INGOT,"§r§fAgrandir de §r§6§l2 block",true,1,lore_b2);
		inv.setItem(11, b2b);
		
		List<String> lore_b5 = new ArrayList<String>();
		String priceStr_b5 = "§f§lPrix :§r§6 " + price_basique_t * 5;
		lore_b5.add(priceStr_b5);
		ItemStack b5b = getCustomItemWithLore(Material.IRON_BLOCK,"§r§fAgrandir de §r§6§l5 block",true,1,lore_b5);
		inv.setItem(12, b5b);
		
		List<String> lore_b10 = new ArrayList<String>();
		String priceStr_b10 = "§f§lPrix :§r§6 " + price_basique_t * 10;
		lore_b10.add(priceStr_b10);
		ItemStack b10b = getCustomItemWithLore(Material.GOLD_NUGGET,"§r§fAgrandir de §r§6§l10 block",true,1,lore_b10);
		inv.setItem(13, b10b);
		
		List<String> lore_b25 = new ArrayList<String>();
		String priceStr_b25 = "§f§lPrix :§r§6 " + price_basique_t * 25;
		lore_b25.add(priceStr_b25);
		ItemStack b25b = getCustomItemWithLore(Material.GOLD_INGOT,"§r§fAgrandir de §r§6§l25 block",true,1,lore_b25);
		inv.setItem(14, b25b);
		
		List<String> lore_b50 = new ArrayList<String>();
		String priceStr_b50 = "§f§lPrix :§r§6 " + price_basique_t * 50;
		lore_b50.add(priceStr_b50);
		ItemStack b50b = getCustomItemWithLore(Material.GOLD_BLOCK,"§r§fAgrandir de §r§6§l50 block",true,1,lore_b50);
		inv.setItem(15, b50b);
		
		List<String> lore_b100 = new ArrayList<String>();
		String priceStr_b100 = "§f§lPrix :§r§6 " + price_basique_t * 100;
		lore_b100.add(priceStr_b100);
		ItemStack b100b = getCustomItemWithLore(Material.DIAMOND,"§r§fAgrandir de §r§6§l100 block",true,1,lore_b100);
		inv.setItem(16, b100b);
		
		player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
		player.openInventory(inv);
		
	}
	
	public static ItemStack getCustomItem(Material material, String customName, boolean EnchantEffect) {
		
		ItemStack item = new ItemStack(material, 1);
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
