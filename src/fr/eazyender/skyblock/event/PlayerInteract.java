package fr.eazyender.skyblock.event;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.eazyender.skyblock.SkyblockMain;
import fr.eazyender.skyblock.commands.CommandTempoInvite;
import fr.eazyender.skyblock.gui.GuiCustomCrafting;
import fr.eazyender.skyblock.island.Island;
import fr.eazyender.skyblock.island.IslandManager;

public class PlayerInteract  implements Listener
{
	  @EventHandler
	  public void onPlayerInteract(PlayerInteractEvent e) {
		  
		  	Player p = e.getPlayer();
		  	
		  	if(e.getClickedBlock() != null) {
		  	if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		  		if(e.getMaterial().equals(Material.TNT)) {
		  			p.sendMessage("§4§lVous ne pouvez pas poser ce block.");
		  			e.setCancelled(true);
		  		}
		  	}

		  if(!p.isOp() && !e.getClickedBlock().getType().equals(Material.DEAD_BRAIN_CORAL_BLOCK)) {
		    if (p.getWorld().equals((SkyblockMain.getSkyBlock()).world)) {
		    	 Island i;
		    	if(IslandManager.getIslandManager().hasIsland(p)) {
		      i = IslandManager.getIslandManager().getIsland(p);  
		    	}else {
		    i = null;
		    	}
		      Island i2;
		      if(CommandTempoInvite.perms.containsKey(p)) {
		    	  i2 = IslandManager.getIslandManager().getIsland( CommandTempoInvite.perms.get(p));
		      }
		      else {
		    	  i2 = i;
		      }
		     
		      if(i != null ) {
		      if(i.isAt(p.getLocation()) || i2.isAt(p.getLocation())) {
		    	 
		    	  if(e.getItem() != null) {
		    	  if(e.getItem().getType().equals(Material.BUCKET)) {
		    		  if(e.getClickedBlock().getType().equals(Material.OBSIDIAN)) {
		    			  e.getClickedBlock().setType(Material.AIR);
		    			  p.setItemInHand(new ItemStack(Material.LAVA_BUCKET,1));
		    		  }
		    	  }
		    	  }
		    	  
		    	  
		      }else if(!i.isAt(p.getLocation())) {
		    
		    	  
		    	  e.setCancelled(true);
		    	  
		      }
		      }
		      
		    } else if(!p.getWorld().getName().equals((SkyblockMain.getSkyBlock()).world.getName())) {
		    	e.setCancelled(true);
		    }
		  }
		  		}
		  
	  }
	  
	  @EventHandler
	  public void onPlayerInteractWithCraftTable(PlayerInteractEvent e) {
		  
		  	Player p = e.getPlayer();
		  	if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		  		if(e.hasBlock() && e.hasItem()) {
		  		if(e.getClickedBlock().getType().equals(Material.CRAFTING_TABLE)) {
		  			if(e.getItem().equals(createCustomItem("§8§lMarteau","Outil","§f§lClique droit sur une table craft.",Material.WOODEN_SWORD,1))) {
		  				
		  				e.setCancelled(true);
		  				GuiCustomCrafting.createGui(p,e.getClickedBlock());
		  			}
		  		}
		  		}
		  	}
	  }
	  
	  @EventHandler
	  public void interactWithArmorStand(PlayerArmorStandManipulateEvent e)
	  {
	          if(!e.getRightClicked().isVisible())
	          {
	              e.setCancelled(true);
	          }
	  }

	  private ItemStack createCustomItem(String name,String type,String lore,Material material,int nbr) {
			
			ItemStack item = new ItemStack(material,nbr);
	        ItemMeta itemM = item.getItemMeta();
	        
	        itemM.setDisplayName(name);
	        itemM.setUnbreakable(true);
	        itemM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
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
