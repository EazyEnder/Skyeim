package fr.eazyender.skyblock.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import fr.eazyender.skyblock.player.PlayerStats;
import fr.eazyender.skyblock.utils.GradesAChallengesUtils;
import fr.eazyender.skyblock.utils.IChallenge;

public class GuiChallengesMain implements Listener {
	
	private static ItemStack voyageur = createCustomItem("§8Voyageur","§fGrade","§fCommencez l'aventure §8§laccompagné§r§f !",null,Material.FEATHER,1);
	private static ItemStack novice = createCustomItem("§8Novice","§fGrade","§fS'habituer aux §8§lmonstres§r",null,Material.FEATHER,1);
	private static ItemStack adepte = createCustomItem("§8Adepte","§fGrade","§fPrendre goût à la §8§lpoudre d'astate§r",null,Material.FEATHER,1);
	
	private static ItemStack aventurier_all = createCustomItem("§8Aventurier","§fEnsemble de Grades",null,null,Material.FEATHER,1);
	
	private static ItemStack aventurier_prc = createCustomItem("§0Fleche noir","",null,null,Material.FEATHER,1);
	private static ItemStack aventurier_cui = createCustomItem("§0Fleche noir","",null,null,Material.FEATHER,1);
	private static ItemStack aventurier_fer = createCustomItem("§0Fleche noir","",null,null,Material.FEATHER,1);
	private static ItemStack aventurier_bro = createCustomItem("§0Fleche noir","",null,null,Material.FEATHER,1);
	private static ItemStack aventurier_arg = createCustomItem("§0Fleche noir","",null,null,Material.FEATHER,1);
	private static ItemStack aventurier_or = createCustomItem("§0Fleche noir","",null,null,Material.FEATHER,1);
	
	public static List<IChallenge> challenge_voyageur = new ArrayList<IChallenge>();
	
	public static void init() {
		initVoyageur();
	}
	
	private static void initVoyageur() {
		Map<ItemStack, Integer> but1 = new HashMap<ItemStack, Integer>();
		String[] descr1 = {"Se faire une armure en bois pour se protéger"};
		but1.put(createCustomItemLess("§8§lCasque en §6§lbois","Armure",null,Material.CHAINMAIL_HELMET,1), 1);
		but1.put(createCustomItemLess("§8§lPlastron en §6§lbois","Armure","§f§lGemme :§r§8Aucune",Material.CHAINMAIL_CHESTPLATE,1), 1);
		but1.put(createCustomItemLess("§8§lJambiere en §6§lbois","Armure",null,Material.CHAINMAIL_LEGGINGS,1), 1);
		but1.put(createCustomItemLess("§8§lBottes en §6§lbois","Armure",null,Material.CHAINMAIL_BOOTS,1), 1);
		
		challenge_voyageur.add(new IChallenge("Première protection", "Craft une Armure en bois", descr1 ,1, 0, 2, but1));
	}
	
	public static void createGui(Player player,int type){
		
		Inventory inv = null;
		if(type == 1) {
			inv = Bukkit.createInventory(null, 9, "§f§lChallenges > §e§l" + player.getDisplayName());
			inv.setItem(0, voyageur);
			inv.setItem(1, novice);
			inv.setItem(2, adepte);
			inv.setItem(3, aventurier_all);
		player.openInventory(inv);
		}else if(type == -1) {
			inv = Bukkit.createInventory(null, 18, "§f§lChallenges(Aventurier) > §e§l" + player.getDisplayName());
			inv.setItem(0, aventurier_prc);
			inv.setItem(0, aventurier_cui);
			inv.setItem(0, aventurier_fer);
			inv.setItem(0, aventurier_bro);
			inv.setItem(0, aventurier_arg);
			inv.setItem(0, aventurier_or);
		}else if(type == 2) {
			inv = Bukkit.createInventory(null, 9, "§f§lChallenges[Voyageur] > §e§l" + player.getDisplayName());
			inv.setItem(0, challenge_voyageur.get(0).getItem());
		}
		
		if(inv != null)
		player.openInventory(inv);
		
	}
	
	@EventHandler
	private static void onClick(InventoryClickEvent event) {
		
		
		Inventory inv = event.getInventory();
		ItemStack current = event.getCurrentItem();
		Player p = (Player) event.getWhoClicked();
		if(current != null) {
		if(event.getView().getTitle().equalsIgnoreCase("§f§lChallenges > §e§l" + p.getDisplayName())) {
		
			PlayerStats ps = PlayerStats.getPlayerStats();
			
			if(current.equals(voyageur)) {
				if(ps.getGradeId(p) >= 1 || ps.getGradeId(p) < 0) {
					createGui(p,2);
				}else {
					p.sendMessage("§cVous n'êtes que " + GradesAChallengesUtils.getGradesIdToStr().get(ps.getGradeId(p)) );
				}
				
			}else if(current.equals(novice)) {
				
				if(ps.getGradeId(p) >= 2 || ps.getGradeId(p) < 0) {
					
				}else {
					p.sendMessage("§cVous n'êtes que " + GradesAChallengesUtils.getGradesIdToStr().get(ps.getGradeId(p)) );
				}
				
			}else if(current.equals(adepte)) {
				
				if(ps.getGradeId(p) >= 3 || ps.getGradeId(p) < 0) {
					
				}else {
					p.sendMessage("§cVous n'êtes que " + GradesAChallengesUtils.getGradesIdToStr().get(ps.getGradeId(p)) );
				}
				
			}else if(current.equals(aventurier_all)) {
				createGui(p,-1);
			}
			
			event.setCancelled(true);
		}else if(event.getView().getTitle().equalsIgnoreCase("§f§lChallenges(Aventurier) > §e§l" + p.getDisplayName())) {
			
			PlayerStats ps = PlayerStats.getPlayerStats();
			
			if(current.equals(aventurier_prc)) {
				if(ps.getGradeId(p) >= 4) {
					
				}else {
					p.sendMessage("§cVous n'êtes que " + GradesAChallengesUtils.getGradesIdToStr().get(ps.getGradeId(p)) );
				}
			}else if(current.equals(aventurier_cui)) {
				if(ps.getGradeId(p) >= 5) {
					
				}else {
					p.sendMessage("§cVous n'êtes que " + GradesAChallengesUtils.getGradesIdToStr().get(ps.getGradeId(p)) );
				}
			}else if(current.equals(aventurier_fer)) {
				if(ps.getGradeId(p) >= 6) {
					
				}else {
					p.sendMessage("§cVous n'êtes que " + GradesAChallengesUtils.getGradesIdToStr().get(ps.getGradeId(p)) );
				}
			}else if(current.equals(aventurier_bro)) {
				if(ps.getGradeId(p) >= 7) {
					
				}else {
					p.sendMessage("§cVous n'êtes que " + GradesAChallengesUtils.getGradesIdToStr().get(ps.getGradeId(p)) );
				}
			}
			
			event.setCancelled(true);
		}
		
		event.setCancelled(true);
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
	
	private static ItemStack createCustomItem(String name,String type,String lore,String description,Material material,int nbr) {
		
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
		if(description != null) {
			itemL.add(description);
		}
		itemM.setLore(itemL);
		item.setItemMeta(itemM);
        
        return item;
		
	}
	
	private static ItemStack createCustomItemLess(String name,String type,String lore,Material material,int nbr) {
		
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
