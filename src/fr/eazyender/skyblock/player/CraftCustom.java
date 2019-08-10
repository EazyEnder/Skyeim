package fr.eazyender.skyblock.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import fr.eazyender.skyblock.SkyblockMain;

public class CraftCustom {
	
	private Plugin pl = SkyblockMain.getPlugin(SkyblockMain.class);
	
	public void initCraft() {
		
		ItemStack hammer = createCustomItem("§8§lMarteau","Outil","§f§lClique droit sur une table craft.",Material.WOODEN_SWORD,1,true);
		 
		ShapedRecipe craft_hammer = new ShapedRecipe(hammer);
		 
		craft_hammer.shape("***","***"," y ");
		 
		craft_hammer.setIngredient('*', Material.IRON_INGOT);
		craft_hammer.setIngredient('y', Material.STICK);
		 
		pl.getServer().addRecipe(craft_hammer);
		
		ItemStack loupe = createCustomItem("§8§lLoupe","Outil","§f§lPermet de découvrir des élements.",Material.WOODEN_SWORD,1,true);
		 
		ShapedRecipe craft_loupe = new ShapedRecipe(loupe);
		 
		craft_loupe.shape(" * ","*x*","y* ");
		 
		craft_loupe.setIngredient('*', Material.IRON_INGOT);
		craft_loupe.setIngredient('y', Material.STICK);
		craft_loupe.setIngredient('x', Material.GLASS_PANE);
		 
		pl.getServer().addRecipe(craft_loupe);
		
		ItemStack wood_boots = createCustomItem("§8§lBottes en §6§lbois","Armure",null,Material.CHAINMAIL_BOOTS,1,false);
		 
		ShapedRecipe craft_wood_boots = new ShapedRecipe(wood_boots);
		 
		craft_wood_boots.shape("   ","* *","x x");
		 
		craft_wood_boots.setIngredient('*', Material.IRON_INGOT);
		craft_wood_boots.setIngredient('x', Material.OAK_WOOD);
		 
		pl.getServer().addRecipe(craft_wood_boots);
		
		ItemStack wood_helmet = createCustomItem("§8§lCasque en §6§lbois","Armure",null,Material.CHAINMAIL_HELMET,1,false);
		 
		ShapedRecipe craft_wood_helmet = new ShapedRecipe(wood_helmet);
		 
		craft_wood_helmet.shape("*x*","x x","   ");
		 
		craft_wood_helmet.setIngredient('*', Material.IRON_INGOT);
		craft_wood_helmet.setIngredient('x', Material.OAK_WOOD);
		 
		pl.getServer().addRecipe(craft_wood_helmet);
		
		ItemStack wood_chestplate = createCustomItem("§8§lPlastron en §6§lbois","Armure","§f§lGemme :§r§8Aucune",Material.CHAINMAIL_CHESTPLATE,1,false);
		 
		ShapedRecipe craft_wood_chestplate = new ShapedRecipe(wood_chestplate);
		 
		craft_wood_chestplate.shape("x x","xyx","xxx");
		 
		craft_wood_chestplate.setIngredient('x', Material.OAK_WOOD);
		craft_wood_chestplate.setIngredient('y', Material.SHIELD);
		 
		pl.getServer().addRecipe(craft_wood_chestplate);
		
		ItemStack wood_leggings = createCustomItem("§8§lJambiere en §6§lbois","Armure",null,Material.CHAINMAIL_LEGGINGS,1,false);
		 
		ShapedRecipe craft_wood_leggings = new ShapedRecipe(wood_leggings);
		 
		craft_wood_leggings.shape("x*x","* *","x x");
		 
		craft_wood_leggings.setIngredient('x', Material.OAK_WOOD);
		craft_wood_leggings.setIngredient('*', Material.IRON_INGOT);
		 
		pl.getServer().addRecipe(craft_wood_leggings);
		
	}
	
	private ItemStack createCustomItem(String name,String type,String lore,Material material,int nbr,boolean flag) {
		
		ItemStack item = new ItemStack(material,nbr);
        ItemMeta itemM = item.getItemMeta();
        
        itemM.setDisplayName(name);
        if(flag) {
        itemM.setUnbreakable(true);
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
