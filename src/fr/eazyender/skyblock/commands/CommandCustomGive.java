package fr.eazyender.skyblock.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandCustomGive implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player)sender;
			
			if(args != null) {
				
				switch(args[0]) {
				case "impulsions_bottes":
					 	ItemStack impulsions_bottes = new ItemStack(Material.STICK, 1);
				        ItemMeta impulsions_bottesmeta = impulsions_bottes.getItemMeta();
				        impulsions_bottesmeta.setDisplayName("§6§lBottes d'impulsions");
				        List<String> impulsions_botteslore = new ArrayList<String>();
						String impulsions_bottesloreStr = "§f§lType :§r§eRelique";
						String impulsions_bottesloreStr2 = "§f§lRareté :§r§eRare";
						String impulsions_bottesloreStr3 = "§f§lMana:§r§a§10";
						impulsions_botteslore.add(impulsions_bottesloreStr);
						impulsions_botteslore.add(impulsions_bottesloreStr2);
						impulsions_botteslore.add(impulsions_bottesloreStr3);
						impulsions_bottesmeta.setLore(impulsions_botteslore);
						impulsions_bottesmeta.setUnbreakable(true);
						impulsions_bottesmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS);
				        impulsions_bottes.setItemMeta(impulsions_bottesmeta);
				        
				        p.getInventory().addItem(impulsions_bottes);
					break;
				case "astate_chestplate":
			        
			        p.getInventory().addItem(createCustomItem("§e§lPlastron en §6§lAstate","Armure","§f§lGemme :§r§8Aucune",Material.DIAMOND_CHESTPLATE,1));
					
					break;
				case "astate_helmet":
			        
			        p.getInventory().addItem((createCustomItem("§e§lCasque en §6§lAstate","Armure",null,Material.DIAMOND_HELMET,1)));
					
					break;
				case "astate_leggings":
			        
			        p.getInventory().addItem((createCustomItem("§e§lJambiere en §6§lAstate","Armure",null,Material.DIAMOND_LEGGINGS,1)));
					
					break;
				case "astate_boots":
    
					p.getInventory().addItem((createCustomItem("§e§lBottes en §6§lAstate","Armure",null,Material.DIAMOND_BOOTS,1)));
	
    				break;
				case "wood_chestplate":
			        
			        p.getInventory().addItem(createCustomItem("§8§lPlastron en §6§lbois","Armure","§f§lGemme :§r§8Aucune",Material.CHAINMAIL_CHESTPLATE,1));
					
					break;
				case "wood_helmet":
			        
			        p.getInventory().addItem((createCustomItem("§8§lCasque en §6§lbois","Armure",null,Material.CHAINMAIL_HELMET,1)));
					
					break;
				case "wood_leggings":
			        
			        p.getInventory().addItem((createCustomItem("§8§lJambiere en §6§lbois","Armure",null,Material.CHAINMAIL_LEGGINGS,1)));
					
					break;
				case "wood_boots":
    
					p.getInventory().addItem((createCustomItem("§8§lBottes en §6§lbois","Armure",null,Material.CHAINMAIL_BOOTS,1)));
	
    				break;
				case "astate_sword":
				    
					p.getInventory().addItem((createCustomItem("§e§lEpee en §6§lAstate","Arme","§f§lGemme :§r§8Aucune",Material.DIAMOND_SWORD,1)));
	
    				break;
				case "astate_powder":
				    
					p.getInventory().addItem((createCustomItem("§f§lPoudre d'§e§lAstate","Ressource","§f§lElément rare se trouvant dans le donjon",Material.STICK,1)));
	
    				break;
				case "perfect_stone":
				    
					p.getInventory().addItem((createCustomItem("§f§lPierre §8§lParfaite","Ressource","§f§lPierre aux bords parfaits",Material.STICK,1)));
	
    				break;
				case "astate_c_e":
	
					p.getInventory().addItem((createCustomItem("§f§lBocal a §e§lAstate","Ressource","§f§lParfait pour stabiliser l'astate",Material.STICK,1)));
	
    				break;
				case "astate_c_f":
    
					p.getInventory().addItem((createCustomItem("§e§lAstate §6§lliquide","Ressource","§f§lAstate stable pouvant être utilisé.",Material.STICK,1)));

					break;
				case "hammer_tool":
				    
					p.getInventory().addItem((createCustomItemWFlag("§8§lMarteau","Outil","§f§lClique droit sur une table craft.",Material.WOODEN_SWORD,1,true)));

					break;
				case "astate_pickaxe_stone":
				    
					p.getInventory().addItem((createCustomItem("§e§lPioche en §6§lAstate§r§8§o Stone","Outil","§f§lGemme :§r§8Pierre",Material.DIAMOND_PICKAXE,1)));

					break;
				case "astate_pickaxe":
    
					p.getInventory().addItem((createCustomItem("§e§lPioche en §6§lAstate","Outil","§f§lGemme :§r§8Aucune",Material.DIAMOND_PICKAXE,1)));

					break;
				case "loupe":
				    
					p.getInventory().addItem((createCustomItemWFlag("§8§lLoupe","Outil","§f§lPermet de découvrir des éléments",Material.WOODEN_SWORD,1,true)));

					break;
				case "unknown":
				    
					p.getInventory().addItem((createCustomItem("§kf§r§f§lInconnu§kf","Ressource","§f§lDécouvrez le avec une loupe.",Material.FEATHER,1)));

					break;
				case "fire_element":
				    
					p.getInventory().addItem((createCustomItem("§f§lEssence de §4§lfeu","Ressource","§f§lDu feu dans une stabilité optimal !",Material.FEATHER,1)));

					break;
				case "water_element":
				    
					p.getInventory().addItem((createCustomItem("§f§lEssence d'§9§leau","Ressource","§f§lDe l'eau dans une stabilité optimal !",Material.FEATHER,1)));

					break;
				case "wind_element":
				    
					p.getInventory().addItem((createCustomItem("§f§lEssence de §2§lvent","Ressource","§f§lDu vent dans une stabilité optimal !",Material.FEATHER,1)));

					break;
				case "fire_gem":
				    
					p.getInventory().addItem((createCustomItem("§f§lGem de §4§lfeu","Gem","§f§lConcentré à son maximum !",Material.FEATHER,1)));

					break;
				case "water_gem":
				    
					p.getInventory().addItem((createCustomItem("§f§lGem d'§9§leau","Gem","§f§lConcentré à son maximum !",Material.FEATHER,1)));

					break;
				case "wind_gem":
				    
					p.getInventory().addItem((createCustomItem("§f§lGem de §2§lvent","Gem","§f§lConcentré à son maximum !",Material.FEATHER,1)));

					break;
				case "emplacement_gem":
				    
					p.getInventory().addItem((createCustomItem("§f§lEmplacement pour §8§lgem","Ressource","§f§lPeux contenir un élément.",Material.FEATHER,1)));

					break;
				default: p.sendMessage("§eCet ID n'existe pas.");
					break;
				}
				
			}else {
				p.sendMessage("§eVous devez rentrer l'ID de l'item après.");
			}
			
			return true;
		}
		
		return false;
	}
	
	private ItemStack createCustomItem(String name,String type,String lore,Material material,int nbr) {
		
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
	
	private ItemStack createCustomItemWFlag(String name,String type,String lore,Material material,int nbr,boolean flag) {
		
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
