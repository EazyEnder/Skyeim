package fr.eazyender.skyblock.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.eazyender.skyblock.SkyblockMain;
import fr.eazyender.skyblock.island.Island;
import fr.eazyender.skyblock.island.IslandManager;
import net.minecraft.server.v1_14_R1.Block;

public class CommandLevel implements CommandExecutor{

		private boolean sync = false;
	
	 private static Map<Material, Long> blocksValue = new HashMap<Material, Long>();
	 
	 private static Map<Player, Boolean> timerValue = new HashMap<Player, Boolean>();
	 
	 private static Material[] blocksList = {Material.COBBLESTONE
			 							, Material.STONE
			 							, Material.DIRT
			 							, Material.GRASS_BLOCK
			 							, Material.SAND
			 							, Material.CLAY
			 							, Material.NETHERRACK
			 							, Material.SOUL_SAND
			 							, Material.GRAVEL
			 							, Material.GLOWSTONE
			 							, Material.QUARTZ_BLOCK
			 							, Material.EMERALD_BLOCK
			 							, Material.DIAMOND_BLOCK
			 							, Material.REDSTONE_BLOCK
			 							, Material.GOLD_BLOCK	
			 							, Material.IRON_BLOCK
			 							, Material.LAPIS_BLOCK
			 							, Material.COAL_BLOCK
			 							, Material.OAK_WOOD
			 							, Material.OAK_LOG
			 							, Material.STONE_BRICKS};
	 
	 
	 private static long[] blocksListValue = {1,1,10,10,4,3,10,15,4,20
			 									,100,100,75,25,25,10,15,10
			 									,2,2,5};
	 
	 
	 private static void syncMap() {
		 
		 for(int i = 0; i < blocksList.length; i++) {
			 blocksValue.put(blocksList[i], blocksListValue[i]);
		 }
		 
	 }
	 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
			if(!sync) {syncMap();sync = true;}
		
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
					
				if(!timerValue.containsKey(player)) {
					Island is = IslandManager.getIslandManager().getIsland(player);
					Location is_loc = is.getLocation();
					
					long level = 0;
					long level_points = 0;
					
					for(int x = -is.getRayon(); x <= is.getRayon(); x++) {
						for(int z = -is.getRayon(); z <= is.getRayon(); z++) {
							for(int y = 0; y <= 255; y++) {
								
								Location loc = new Location(is_loc.getWorld(),x + is_loc.getX(),y,z + is_loc.getZ());
								if(loc.getBlock().getType() != Material.AIR && loc.getBlock() != null) {
									Material bc = loc.getBlock().getType();
									
									
									if(blocksValue.containsKey(bc)) {
										level_points = level_points + blocksValue.get(bc);
									}
									
								}
								
							}
						}
					}
					
					if(level_points != 0) {
					double level_d = Math.sqrt(level_points / 10);
					level = (long) level_d;
					}
					
					
					is.setLevel(level);
					timerValue.put(player,true);
					
					new BukkitRunnable() {
						
						@Override
						public void run() {
							
							if(timerValue.containsKey(player)) {
								timerValue.remove(player);
							}
							
						}
						
					}.runTaskLater(SkyblockMain.getSkyBlock(), 20*60);
					
				
					player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVotre île est au level : §f§l§n" + level + "§r§e avec un total de point de :§f§l" + level_points);
					
					return true;
				}else {player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous devez attendre §r§6§l60 secondes§r§e avant de pouvoir réutiliser la commande.");}
			}
		
		return false;
		
	}

}
