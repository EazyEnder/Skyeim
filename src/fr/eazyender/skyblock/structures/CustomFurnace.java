package fr.eazyender.skyblock.structures;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.eazyender.skyblock.gui.structure.GuiCustomFurnace;
import fr.eazyender.skyblock.island.Island;
import fr.eazyender.skyblock.island.IslandManager;

public class CustomFurnace implements Listener {
		
		@EventHandler
		  public void onPlayerInteract(PlayerInteractEvent e) {
			
			Player p = e.getPlayer();
			if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				Island i;
		    	if(IslandManager.getIslandManager().hasIsland(p)) {
		      i = IslandManager.getIslandManager().getIsland(p);  
		    	}else {
		    i = null;
		    return;
		    	}
		    	
		    	if(i.isAt(p.getLocation()) || p.isOp()) {
		    		
		    		Block clicked = e.getClickedBlock();
		    		if(clicked.getType().equals(Material.SEA_LANTERN)) {
		    			Location loc = clicked.getLocation();
    					Location loc_plat1 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY()-1, loc.getBlockZ() -1);
    					Location loc_plat2 = new Location(loc.getWorld(),loc.getBlockX() , loc.getBlockY()-1, loc.getBlockZ() -1);
    					Location loc_plat3 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY()-1, loc.getBlockZ() -1);
    					Location loc_plat4 = new Location(loc.getWorld(),loc.getBlockX() , loc.getBlockY()-1, loc.getBlockZ() );
    					Location loc_plat5 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY()-1, loc.getBlockZ());
    					Location loc_plat6 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY()-1, loc.getBlockZ());
    					Location loc_plat7 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY()-1, loc.getBlockZ() +1);
    					Location loc_plat8 = new Location(loc.getWorld(),loc.getBlockX() , loc.getBlockY()-1, loc.getBlockZ() +1);
    					Location loc_plat9 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY()-1, loc.getBlockZ() +1);
    					if(loc_plat1.getBlock().getType().equals(Material.POLISHED_ANDESITE)
    							&& loc_plat2.getBlock().getType().equals(Material.COBBLESTONE)
    							&& loc_plat3.getBlock().getType().equals(Material.POLISHED_ANDESITE)
    							&& loc_plat4.getBlock().getType().equals(Material.POLISHED_ANDESITE)
    							&& loc_plat5.getBlock().getType().equals(Material.COBBLESTONE)
    							&& loc_plat6.getBlock().getType().equals(Material.COBBLESTONE)
    							&& loc_plat7.getBlock().getType().equals(Material.POLISHED_ANDESITE)
    							&& loc_plat8.getBlock().getType().equals(Material.COBBLESTONE)
    							&& loc_plat9.getBlock().getType().equals(Material.POLISHED_ANDESITE)){
    						Location loc_plat21 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY(), loc.getBlockZ() -1);
        					Location loc_plat22 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY(), loc.getBlockZ() -1);
        					Location loc_plat23 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY(), loc.getBlockZ() +1);
        					Location loc_plat24 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY(), loc.getBlockZ() +1);
        					if(loc_plat21.getBlock().getType().equals(Material.COBBLESTONE)
        							&& loc_plat22.getBlock().getType().equals(Material.COBBLESTONE)
        							&& loc_plat23.getBlock().getType().equals(Material.COBBLESTONE)
        							&& loc_plat24.getBlock().getType().equals(Material.COBBLESTONE)) {
        						Location loc_plat31 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY()+1, loc.getBlockZ() -1);
            					Location loc_plat32 = new Location(loc.getWorld(),loc.getBlockX() , loc.getBlockY()+1, loc.getBlockZ() -1);
            					Location loc_plat33 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY()+1, loc.getBlockZ() -1);
            					Location loc_plat39 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY()+1, loc.getBlockZ()+1);
            					Location loc_plat35 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY()+1, loc.getBlockZ());
            					Location loc_plat36 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY()+1, loc.getBlockZ());
            					Location loc_plat37 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY()+1, loc.getBlockZ() +1);
            					Location loc_plat38 = new Location(loc.getWorld(),loc.getBlockX() , loc.getBlockY()+1, loc.getBlockZ() +1);
            					if(loc_plat31.getBlock().getType().equals(Material.POLISHED_ANDESITE_STAIRS)
            							&& loc_plat32.getBlock().getType().equals(Material.COBBLESTONE)
            							&& loc_plat33.getBlock().getType().equals(Material.POLISHED_ANDESITE_STAIRS)
            							&& loc_plat39.getBlock().getType().equals(Material.POLISHED_ANDESITE_STAIRS)
            							&& loc_plat35.getBlock().getType().equals(Material.COBBLESTONE)
            							&& loc_plat36.getBlock().getType().equals(Material.COBBLESTONE)
            							&& loc_plat37.getBlock().getType().equals(Material.POLISHED_ANDESITE_STAIRS)
            							&& loc_plat38.getBlock().getType().equals(Material.COBBLESTONE)) {
            						Location loc_plat41 = new Location(loc.getWorld(),loc.getBlockX() , loc.getBlockY()+2, loc.getBlockZ() -1);
                					Location loc_plat42 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY()+2, loc.getBlockZ() );
                					Location loc_plat43 = new Location(loc.getWorld(),loc.getBlockX() , loc.getBlockY()+2, loc.getBlockZ() +1);
                					Location loc_plat44 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY()+2, loc.getBlockZ() );
                					if(loc_plat41.getBlock().getType().equals(Material.POLISHED_ANDESITE_STAIRS)
                							&& loc_plat42.getBlock().getType().equals(Material.POLISHED_ANDESITE_STAIRS)
                							&& loc_plat43.getBlock().getType().equals(Material.POLISHED_ANDESITE_STAIRS)
                							&& loc_plat44.getBlock().getType().equals(Material.POLISHED_ANDESITE_STAIRS)) {
                						e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
                						GuiCustomFurnace.createGui(e.getPlayer());
                					}
            					}
        					}
        					
    					}
		    		}
		    	}
			}
		}
}
