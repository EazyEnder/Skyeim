package fr.eazyender.skyblock.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class IChallenge{
	
	@SuppressWarnings("unused")
	private ItemStack item;
	private int lootP;
	private int type;
	private int avancement,goal;
	private String name,objective;
	private String[] desc;
	
	private Map<ItemStack, Integer> but = new LinkedHashMap<ItemStack, Integer>();
	
	public IChallenge(String name,String objective,String[] description,int loot_money, int goal,int type_of_challenge,Map<ItemStack, Integer> but)
	{
		this.name = name;
		this.objective = objective;
		this.desc = description;
		this.lootP = loot_money;
		this.type = type_of_challenge;
		this.avancement = 0;
		this.goal = goal;
		this.but = but;
		
	}
	
	public ItemStack getItem() {
			ItemStack item;
			
			item = new ItemStack(Material.PAPER);
	        ItemMeta itemM = item.getItemMeta();
	        itemM.setDisplayName(name);
	        List<String> itemL = new ArrayList<String>();
	        switch(type) {
	        	case 1:String itemL1 = "§f§lType :§r§8" + "Récolte";
						  itemL.add(itemL1);
					break;
	        	case 2:String itemL2 = "§f§lType :§r§8" + "Crafting";
				  		  itemL.add(itemL2);
				  	break;
	        	case 3:String itemL3 = "§f§lType :§r§8" + "Combat";
				  		  itemL.add(itemL3);
				  	break;
				default: break;
	        }
			if(objective != null) {
				String itemL1 = "§f§lObjectif :§r§8";
				itemL.add(itemL1);
					Object[] entry = but.entrySet().toArray();
					for (int i = 0; i < entry.length; i++) {
					String obj = ((ItemStack)entry[i]).getItemMeta().getDisplayName() + ": " + "/" + but.get(((ItemStack)entry[i]));
					itemL.add(obj);		
					}
				
			}
			if(desc != null) {
				for(int i = 0; i < desc.length; i++) {
				itemL.add(desc[i]);
				}
			}
			
			itemL.add("§8Récompense : " + lootP);
			itemM.setLore(itemL);
			
			if(goal <= avancement) { itemM.setDisplayName("§2[§a§lV§r§2]§r"+ name);itemM.addEnchant(Enchantment.DAMAGE_ALL, 200, true);itemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);}
			
			item.setItemMeta(itemM);
	        
	        return item;
	}
	public void setItem(ItemStack item) {
		this.item = item;
	}
	public int getLootP() {
		return lootP;
	}
	public void setLootP(int lootP) {
		this.lootP = lootP;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String[] getDesc() {
		return desc;
	}
	public void setDesc(String[] desc) {
		this.desc = desc;
	}

	public int getAvancement() {
		return avancement;
	}

	public void setAvancement(int avancement) {
		this.avancement = avancement;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public  Map<ItemStack, Integer> getBut() {
		return but;
	}

	public  void setBut(Map<ItemStack, Integer> but) {
		this.but = but;
	}
	
	

}
