package fr.eazyender.skyblock.event;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockPlacedNaturaly implements Listener {
	
	@EventHandler
	public void onFromTo(BlockFromToEvent event){
		if(event.getBlock() != null) {
	    Material type = event.getBlock().getType();
	    if (type == Material.WATER || type == Material.WATER || type == Material.LAVA || type == Material.LAVA){
	        Block b = event.getToBlock();
	        if (b.getType() == Material.AIR){
	            if (generatesCobble(type, b)){
	            	Random r = new Random();
	    			int chance = r.nextInt(150);
	    			if(chance >= 30) {
	    				
	    			}else if(chance < 40 && chance >= 20){
	    				event.setCancelled(true);
	    				b.setType(Material.ANDESITE);
	    			}else if(chance < 20 && chance >= 10){
	                	event.setCancelled(true);
		                b.setType(Material.DIORITE);	
	                }else {
	                	event.setCancelled(true);
		                b.setType(Material.COAL_ORE);
	                }
	    			}
	            }
	        }
	    }
	}

	private final BlockFace[] faces = new BlockFace[]{
	        BlockFace.SELF,
	        BlockFace.UP,
	        BlockFace.DOWN,
	        BlockFace.NORTH,
	        BlockFace.EAST,
	        BlockFace.SOUTH,
	        BlockFace.WEST
	    };

	public boolean generatesCobble(Material type, Block b){
	    Material mirrorID1 = (type == Material.WATER || type == Material.WATER ? Material.LAVA : Material.WATER);
	    Material mirrorID2 = (type == Material.WATER || type == Material.WATER ? Material.LAVA : Material.WATER);
	    for (BlockFace face : faces){
	        Block r = b.getRelative(face, 1);
	        if (r.getType() == mirrorID1 || r.getType() == mirrorID2){
	            return true;
	        }
	    }
	    return false;
	}

}
