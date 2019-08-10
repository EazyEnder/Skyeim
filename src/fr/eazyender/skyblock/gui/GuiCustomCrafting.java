package fr.eazyender.skyblock.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiCustomCrafting implements Listener{
	
	private static Block crafting_table;
	
	private static ItemStack font = createCustomItem("§0Font noir",null,null,Material.FEATHER,1);
	private static ItemStack fleche = createCustomItem("§0Fleche noir",null,null,Material.FEATHER,1);
	private static ItemStack actualize = getCustomItem(Material.LIME_TERRACOTTA,"§4§lPour actualiser et valider le craft",false,1);
	
	public static void createGui(Player player,Block bc){
		
		Inventory inv = Bukkit.createInventory(null, 27, "§f§lCustomCraft > §e§l" + player.getDisplayName());

		
		inv.setItem(0, font);
		inv.setItem(1, font);
		inv.setItem(10, font);
		inv.setItem(9, actualize);
		inv.setItem(18, font);
		inv.setItem(19, font);
		inv.setItem(5, font);
		inv.setItem(6, font);	
		inv.setItem(7, font);	
		inv.setItem(8, font);
		inv.setItem(5 + 9, fleche);
		inv.setItem(6 + 9, font);		
		inv.setItem(8 + 9, font);	
		inv.setItem(5 + 9*2, font);
		inv.setItem(6 + 9*2, font);	
		inv.setItem(7 + 9*2, font);	
		inv.setItem(8 + 9*2, font);	

		crafting_table = bc;
		
		player.openInventory(inv);
		
	}
	@EventHandler
	private static void onClick(InventoryClickEvent event) {
		
		
		Inventory inv = event.getInventory();
		ItemStack current = event.getCurrentItem();
		Player p = (Player) event.getWhoClicked();
		if(current != null) {
		if(event.getView().getTitle().equalsIgnoreCase("§f§lCustomCraft > §e§l" + p.getDisplayName())) {
		
			
			createCraftAstateBottle(inv, current, p);
			createCraftAstateBottleFull(inv, current, p);
			createCraftAstateSword(inv,current,p);
			createCraftAstatePickaxe(inv,current,p);
			createCraftAstatePickaxe_Stone(inv,current,p);
			createCraftDiscoverEssence(inv,current,p);
			createCraftGemEmplacement(inv,current,p);
			createCraftFireGem(inv,current,p);
			
			if(event.isShiftClick()) {
				event.setCancelled(true);
			}
		if(current.equals(font) || current.equals(fleche) || current.equals(actualize)) {
			
			event.setCancelled(true);
		}
		}
		}
		
	}
	@EventHandler
	private static void onClose(InventoryCloseEvent event) {
		
		
		Inventory inv = event.getInventory();
		ItemStack[] content = inv.getStorageContents();
		Player p = (Player) event.getPlayer();
		if(event.getView().getTitle().equalsIgnoreCase("§f§lCustomCraft > §e§l" + p.getDisplayName())) {
		
			for(int i = 0; i < content.length; i++) {
				
				ItemStack item = content[i];
				if(item != null) {
				if(item.equals(font) ||
					item.equals(fleche) ||
					item.equals(actualize)) {
					
				}else {
					p.getWorld().dropItemNaturally(p.getLocation(), item);
				}
				}
				
			}
			
		}
		
	}
	private static void createCraftFireGem(Inventory inv,ItemStack current,Player p) {
		
		if(inv.containsAtLeast(createCustomItem("§f§lEmplacement pour §8§lgem","Ressource","§f§lPeux contenir un élément.",Material.FEATHER,1),1)
				&& inv.containsAtLeast(createCustomItem("§f§lEssence de §4§lfeu","Ressource","§f§lDu feu dans une stabilité optimal !",Material.FEATHER,1),1)) {
			
			if(current.equals(actualize)) {
				int[] tab_mixte = {2,3,4,2+9*1,3+9*1,4+9*1,2+9*2,3+9*2,4+9*2};
				
				Location loc = crafting_table.getLocation();
				Location loc2 = new Location(loc.getWorld(),loc.getX(),loc.getY() + 1,loc.getZ());
				loc.getWorld().spawnParticle(Particle.REDSTONE,loc2,1,new Particle.DustOptions(Color.fromBGR(187, 239, 230), 1));
				
				int min = 0;
				
				for(int i = 0; i < tab_mixte.length;i++) {
					if(inv.getItem(tab_mixte[i]) != null) {
					if(min == 0) {
						min = inv.getItem(tab_mixte[i]).getAmount();
					}
				if(inv.getItem(tab_mixte[i]).getAmount() < min) {
					min = inv.getItem(tab_mixte[i]).getAmount();
				}
					}
				}
				
			p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
			inv.setItem(16,createCustomItem("§f§lGem de §4§lfeu","Gem","§f§lConcentré à son maximum !",Material.FEATHER,min));


			for(int i = 0; i < tab_mixte.length;i++)
				if(inv.getItem(tab_mixte[i]) != null) {
				if(inv.getItem(tab_mixte[i]).getAmount() > min) {int newA = inv.getItem(tab_mixte[i]).getAmount() - min;inv.getItem(tab_mixte[i]).setAmount(newA);}
				else {inv.clear(tab_mixte[i]);}
				
				}
				}
			
		}
		
	}
	private static void createCraftDiscoverEssence(Inventory inv,ItemStack current,Player p) {
		
		if(inv.containsAtLeast(createCustomItemWFlag("§8§lLoupe","Outil","§f§lPermet de découvrir des éléments",Material.WOODEN_SWORD,1,true),1)
				&& inv.containsAtLeast(createCustomItem("§kf§r§f§lInconnu§kf","Ressource","§f§lDécouvrez le avec une loupe.",Material.FEATHER,1),1)) {
			
			if(current.equals(actualize)) {
				int[] tab_mixte = {2,3,4,2+9*1,3+9*1,4+9*1,2+9*2,3+9*2,4+9*2};
				
				Location loc = crafting_table.getLocation();
				Location loc2 = new Location(loc.getWorld(),loc.getX(),loc.getY() + 1,loc.getZ());
				loc.getWorld().spawnParticle(Particle.REDSTONE,loc2,1,new Particle.DustOptions(Color.fromBGR(187, 239, 230), 1));
				
			p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
			Random r = new Random();
			int chance = r.nextInt(100);
			if(chance <= 33) {
			inv.setItem(16,createCustomItem("§f§lEssence de §2§lvent","Ressource","§f§lDu vent dans une stabilité optimal !",Material.FEATHER,1));
			}else if(chance > 33 && chance <= 66) {
			inv.setItem(16,createCustomItem("§f§lEssence d'§9§leau","Ressource","§f§lDe l'eau dans une stabilité optimal !",Material.FEATHER,1));	
			}else if(chance > 66) {
			inv.setItem(16,createCustomItem("§f§lEssence de §4§lfeu","Ressource","§f§lDu feu dans une stabilité optimal !",Material.FEATHER,1));	
			}
			for(int i = 0; i < tab_mixte.length;i++)
			if(inv.getItem(tab_mixte[i]) != null) {
			if(inv.getItem(tab_mixte[i]).getAmount() > 1 && !inv.getItem(tab_mixte[i]).equals(createCustomItemWFlag("§8§lLoupe","Outil","§f§lPermet de découvrir des éléments",Material.WOODEN_SWORD,1,true))) {
				int newA = inv.getItem(tab_mixte[i]).getAmount() - 1;inv.getItem(tab_mixte[i]).setAmount(newA);}
			else if(inv.getItem(tab_mixte[i]).equals(createCustomItemWFlag("§8§lLoupe","Outil","§f§lPermet de découvrir des éléments",Material.WOODEN_SWORD,1,true))){}
			
			else {
				inv.clear(tab_mixte[i]);
			}}
			}
			
		}
		
	}
	private static void createCraftAstatePickaxe_Stone(Inventory inv,ItemStack current,Player p) {
		
		if(inv.containsAtLeast(createCustomItem("§e§lPioche en §6§lAstate","Outil","§f§lGemme :§r§8Aucune",Material.DIAMOND_PICKAXE,1),1)
				&& inv.containsAtLeast(createCustomItem("§f§lPierre §8§lParfaite","Ressource","§f§lPierre aux bords parfaits",Material.STICK,1),1)) {
			
			if(current.equals(actualize)) {
				int[] tab_mixte = {2,3,4,2+9*1,3+9*1,4+9*1,2+9*2,3+9*2,4+9*2};
				
				int min = 0;
				
				for(int i = 0; i < tab_mixte.length;i++) {
					if(inv.getItem(tab_mixte[i]) != null) {
					if(min == 0) {
						min = inv.getItem(tab_mixte[i]).getAmount();
					}
				if(inv.getItem(tab_mixte[i]).getAmount() < min) {
					min = inv.getItem(tab_mixte[i]).getAmount();
				}
					}
				}
			
				Location loc = crafting_table.getLocation();
				Location loc2 = new Location(loc.getWorld(),loc.getX(),loc.getY() + 1,loc.getZ());
				loc.getWorld().spawnParticle(Particle.REDSTONE,loc2,min,new Particle.DustOptions(Color.fromBGR(187, 239, 230), 1));
				
			p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
			inv.setItem(16,createCustomItem("§e§lPioche en §6§lAstate§r§8§o Stone","Outil","§f§lGemme :§r§8Pierre",Material.DIAMOND_PICKAXE,1));
			
			for(int i = 0; i < tab_mixte.length;i++)
			if(inv.getItem(tab_mixte[i]) != null) {
			if(inv.getItem(tab_mixte[i]).getAmount() > min) {int newA = inv.getItem(tab_mixte[i]).getAmount() - min;inv.getItem(tab_mixte[i]).setAmount(newA);}
			else {inv.clear(tab_mixte[i]);}
			
			}
			}
			
		}
		
	}
	private static void createCraftGemEmplacement(Inventory inv,ItemStack current,Player p) {
		
		int[] tab_perfectS = {3,11,13,21};
		if(ifItemIsPresent(tab_perfectS,createCustomItem("§f§lPierre §8§lParfaite","Ressource","§f§lPierre aux bords parfaits",Material.STICK,1),inv)) {
			
			
			if(current.equals(actualize)) {
				int[] tab_mixte = {2,3,4,2+9*1,3+9*1,4+9*1,2+9*2,3+9*2,4+9*2};
				
				int min = 0;
				
				for(int i = 0; i < tab_mixte.length;i++) {
					if(inv.getItem(tab_mixte[i]) != null) {
					if(min == 0) {
						min = inv.getItem(tab_mixte[i]).getAmount();
					}
				if(inv.getItem(tab_mixte[i]).getAmount() < min) {
					min = inv.getItem(tab_mixte[i]).getAmount();
				}
					}
				}
			
				Location loc = crafting_table.getLocation();
				Location loc2 = new Location(loc.getWorld(),loc.getX(),loc.getY() + 1,loc.getZ());
				loc.getWorld().spawnParticle(Particle.REDSTONE,loc2,min,new Particle.DustOptions(Color.fromBGR(187, 239, 230), 1));
				
			p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
			inv.setItem(16,createCustomItem("§f§lEmplacement pour §8§lgem","Ressource","§f§lPeux contenir un élément.",Material.FEATHER,min));
			
			for(int i = 0; i < tab_mixte.length;i++)
			if(inv.getItem(tab_mixte[i]) != null) {
			if(inv.getItem(tab_mixte[i]).getAmount() > min) {int newA = inv.getItem(tab_mixte[i]).getAmount() - min;inv.getItem(tab_mixte[i]).setAmount(newA);}
			else {inv.clear(tab_mixte[i]);}
			
			}
			}
			
		}
		
	}
	private static void createCraftAstatePickaxe(Inventory inv,ItemStack current,Player p) {
		
		int[] tab_astate = {2,3,4};
		int[] tab_stick = {12,21};
		if(ifItemIsPresent(tab_astate,createCustomItem("§e§lAstate §6§lliquide","Ressource","§f§lAstate stable pouvant être utilisé.",Material.STICK,1),inv)
			&& ifItemIsPresent(tab_stick,new ItemStack(Material.STICK),inv)) {
			
			
			if(current.equals(actualize)) {
				int[] tab_mixte = {2,4,11,13,20,22,3,21,12};
			
				Location loc = crafting_table.getLocation();
				Location loc2 = new Location(loc.getWorld(),loc.getX(),loc.getY() + 2,loc.getZ());
				loc.getWorld().spawnParticle(Particle.REDSTONE,loc2,1,new Particle.DustOptions(Color.fromBGR(187, 239, 230), 1));
				
			p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
			inv.setItem(16,createCustomItem("§e§lPioche en §6§lAstate","Outil","§f§lGemme :§r§8Aucune",Material.DIAMOND_PICKAXE,1));
			
			for(int i = 0; i < tab_mixte.length;i++)
				if(inv.getItem(tab_mixte[i]) != null) {
				if(inv.getItem(tab_mixte[i]).getAmount() > 1) {int newA = inv.getItem(tab_mixte[i]).getAmount() - 1;inv.getItem(tab_mixte[i]).setAmount(newA);}
				else {inv.clear(tab_mixte[i]);}
				}
			}
			
		}
		
	}
	private static void createCraftAstateSword(Inventory inv,ItemStack current,Player p) {
		
		int[] tab_astate = {3,12};
		int[] tab_stick = {21};
		if(ifItemIsPresent(tab_astate,createCustomItem("§e§lAstate §6§lliquide","Ressource","§f§lAstate stable pouvant être utilisé.",Material.STICK,1),inv)
			&& ifItemIsPresent(tab_stick,new ItemStack(Material.STICK),inv)) {
			
			
			if(current.equals(actualize)) {
				int[] tab_mixte = {2,4,11,13,20,22,3,21,12};
			
				Location loc = crafting_table.getLocation();
				Location loc2 = new Location(loc.getWorld(),loc.getX(),loc.getY() + 2,loc.getZ());
				loc.getWorld().spawnParticle(Particle.REDSTONE,loc2,1,new Particle.DustOptions(Color.fromBGR(187, 239, 230), 1));
				
			p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
			inv.setItem(16,createCustomItem("§e§lEpee en §6§lAstate","Arme","§f§lGemme :§r§8Aucune",Material.DIAMOND_SWORD,1));
			
			for(int i = 0; i < tab_mixte.length;i++)
				if(inv.getItem(tab_mixte[i]) != null) {
				if(inv.getItem(tab_mixte[i]).getAmount() > 1) {int newA = inv.getItem(tab_mixte[i]).getAmount() - 1;inv.getItem(tab_mixte[i]).setAmount(newA);}
				else {inv.clear(tab_mixte[i]);}
				}
			}
			
		}
		
	}
	
	private static void createCraftAstateBottleFull(Inventory inv,ItemStack current,Player p) {
		
		if(inv.containsAtLeast(createCustomItem("§f§lBocal a §e§lAstate","Ressource","§f§lParfait pour stabiliser l'astate",Material.STICK,0),1)
				&& inv.containsAtLeast(createCustomItem("§f§lPoudre d'§e§lAstate","Ressource","§f§lElément rare se trouvant dans le donjon",Material.STICK,0),1)) {
			
			if(current.equals(actualize)) {
				int[] tab_mixte = {2,3,4,2+9*1,3+9*1,4+9*1,2+9*2,3+9*2,4+9*2};
				
				int min = 0;
				
				for(int i = 0; i < tab_mixte.length;i++) {
					if(inv.getItem(tab_mixte[i]) != null) {
					if(min == 0) {
						min = inv.getItem(tab_mixte[i]).getAmount();
					}
				if(inv.getItem(tab_mixte[i]).getAmount() < min) {
					min = inv.getItem(tab_mixte[i]).getAmount();
				}
					}
				}
			
				Location loc = crafting_table.getLocation();
				Location loc2 = new Location(loc.getWorld(),loc.getX(),loc.getY() + 1,loc.getZ());
				loc.getWorld().spawnParticle(Particle.REDSTONE,loc2,min,new Particle.DustOptions(Color.fromBGR(187, 239, 230), 1));
				
			p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
			inv.setItem(16,createCustomItem("§e§lAstate §6§lliquide","Ressource","§f§lAstate stable pouvant être utilisé.",Material.STICK,min));
			
			for(int i = 0; i < tab_mixte.length;i++)
			if(inv.getItem(tab_mixte[i]) != null) {
			if(inv.getItem(tab_mixte[i]).getAmount() > min) {int newA = inv.getItem(tab_mixte[i]).getAmount() - min;inv.getItem(tab_mixte[i]).setAmount(newA);}
			else {inv.clear(tab_mixte[i]);}
			
			}
			}
			
		}
		
	}
	
	private static void createCraftAstateBottle(Inventory inv,ItemStack current,Player p) {
		
		int[] tab_pierre = {2,4,11,13,20,22};
		int[] tab_glass = {3,21};
		int[] tab_bucket = {12};
		if(ifItemIsPresent(tab_pierre,createCustomItem("§f§lPierre §8§lParfaite","Ressource","§f§lPierre aux bords parfaits",Material.STICK,1),inv)
			&& ifItemIsPresent(tab_glass,new ItemStack(Material.GLASS_PANE),inv)
			&& ifItemIsPresent(tab_bucket,new ItemStack(Material.BUCKET),inv)) {
			
			
			if(current.equals(actualize)) {
				int[] tab_mixte = {2,4,11,13,20,22,3,21,12};
				
				int min = 0;
				for(int i = 0; i < tab_mixte.length;i++) {
					if(min == 0) {
						min = inv.getItem(tab_mixte[i]).getAmount();
					}
				if(inv.getItem(tab_mixte[i]).getAmount() < min) {
					min = inv.getItem(tab_mixte[i]).getAmount();
				}
				}
			
				Location loc = crafting_table.getLocation();
				Location loc2 = new Location(loc.getWorld(),loc.getX(),loc.getY() + 1,loc.getZ());
				loc.getWorld().spawnParticle(Particle.REDSTONE,loc2,min,new Particle.DustOptions(Color.fromBGR(187, 239, 230), 1));
				
			p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
			inv.setItem(16,createCustomItem("§f§lBocal a §e§lAstate","Ressource","§f§lParfait pour stabiliser l'astate",Material.STICK,min));
			
			for(int i = 0; i < tab_mixte.length;i++)
				if(inv.getItem(tab_mixte[i]).getAmount() > min) {int newA = inv.getItem(tab_mixte[i]).getAmount() - min;inv.getItem(tab_mixte[i]).setAmount(newA);}
				else {inv.clear(tab_mixte[i]);}
			}
			
		}
		
	}
	
	private static boolean ifItemIsPresent(int[] inv,ItemStack item,Inventory in) {
			
		int imin = 0;
		int imax = inv.length;
		
		for(int i = 0; i < inv.length;i++) {
			if(in.getItem(inv[i]) != null) {
		if(in.getItem(inv[i]).getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())
				&& in.getItem(inv[i]).getType().equals(item.getType())) {
			imin++;
		}
			}
		}
		
		if(imin == imax) {
			return true;
		}else {
			return false;
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
	
	private static ItemStack createCustomItemWFlag(String name,String type,String lore,Material material,int nbr,boolean flag) {
		
		ItemStack item = new ItemStack(material,nbr);
        ItemMeta itemM = item.getItemMeta();
        
        itemM.setDisplayName(name);
        itemM.setUnbreakable(true);
        if(flag) {
        itemM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
        }
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
