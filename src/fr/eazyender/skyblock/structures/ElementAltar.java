package fr.eazyender.skyblock.structures;


import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.eazyender.skyblock.island.Island;
import fr.eazyender.skyblock.island.IslandManager;

public class ElementAltar implements Listener {
	
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
	    		if(clicked.getType().equals(Material.LECTERN)) {
	    			Location loc = clicked.getLocation();
	    					Location loc2 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY()-1, loc.getBlockZ() -1);
	    					if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)) {
	    						Location loc3 = new Location(loc.getWorld(),loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ() -1);
		    				if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)) {
		    					Location loc4 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY()-1, loc.getBlockZ() -1);
		    					
			    			if(loc4.getBlock().getType().equals(Material.STONE_BRICKS)) {
			    				Location loc5 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY()-1, loc.getBlockZ() +1);
				    		if(loc5.getBlock().getType().equals(Material.STONE_BRICKS)) {
				    			Location loc6 = new Location(loc.getWorld(),loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ() +1);
					    	if(loc6.getBlock().getType().equals(Material.STONE_BRICKS)) {
					    		Location loc7 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY()-1, loc.getBlockZ() +1);
					    		
						    if(loc7.getBlock().getType().equals(Material.STONE_BRICKS)) {
						    	Location loc8 = new Location(loc.getWorld(),loc.getBlockX() -1, loc.getBlockY()-1, loc.getBlockZ());
						    if(loc8.getBlock().getType().equals(Material.STONE_BRICKS)) {
						    	Location loc9 = new Location(loc.getWorld(),loc.getBlockX() +1, loc.getBlockY()-1, loc.getBlockZ());
							if(loc9.getBlock().getType().equals(Material.STONE_BRICKS)) {
						    		
								Location loc10 = new Location(loc.getWorld(),loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ());
								if(loc10.getBlock().getType().equals(Material.REDSTONE_BLOCK)) {
									
									Location Pillar1 = new Location(loc.getWorld(),loc.getBlockX() - 2, loc.getBlockY()-1, loc.getBlockZ() + 2);
									Location Pillar2 = new Location(loc.getWorld(),loc.getBlockX() + 2, loc.getBlockY()-1, loc.getBlockZ() - 2);
									Location Pillar3 = new Location(loc.getWorld(),loc.getBlockX() - 2, loc.getBlockY()-1, loc.getBlockZ() - 2);
									Location Pillar4 = new Location(loc.getWorld(),loc.getBlockX() + 2, loc.getBlockY()-1, loc.getBlockZ() + 2);
									
									if(getPillar(Pillar1,Material.IRON_BLOCK)
											&& getPillar(Pillar2,Material.IRON_BLOCK) 
											&& getPillar(Pillar3,Material.IRON_BLOCK)
											&& getPillar(Pillar4,Material.IRON_BLOCK)) {
										p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1);
										loc.getWorld().spawnParticle(Particle.REDSTONE,new Location(loc.getWorld(),loc.getX() + 0.5, loc.getBlockY() + 1.5, loc.getZ() +0.5),5,new Particle.DustOptions(Color.fromBGR(187, 239, 230), 100));
									}
								}
							}}}}}}}}
						    					
	    		}
	    		
	    	}
	    	
		}
		
	}
	
	private static boolean getPillar(Location origin,Material block) {
		Location Pillar1 = new Location(origin.getWorld(),origin.getBlockX(), origin.getBlockY(), origin.getBlockZ());
		if(Pillar1.getBlock().getType().equals(Material.STONE_BRICKS)) {
			Location Pillar1b = new Location(origin.getWorld(),origin.getBlockX(), origin.getBlockY() + 1, origin.getBlockZ());
			if(Pillar1b.getBlock().getType().equals(Material.STONE_BRICKS)) {
				Location Pillar1c = new Location(origin.getWorld(),origin.getBlockX(), origin.getBlockY() + 2, origin.getBlockZ());
				if(Pillar1c.getBlock().getType().equals(block)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
