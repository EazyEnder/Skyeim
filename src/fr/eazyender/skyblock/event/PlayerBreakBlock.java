package fr.eazyender.skyblock.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.eazyender.skyblock.SkyblockMain;

public class PlayerBreakBlock implements Listener {
	
	@EventHandler
	private static void onBlockBreak(BlockBreakEvent event) {
		
		Player p = event.getPlayer();
		
		if(event.getBlock() != null) {
		if(event.getBlock().getType().equals(Material.COBBLESTONE) || event.getBlock().getType().equals(Material.STONE)) {
			
			Random r = new Random();
			int chance = r.nextInt(100);
			if(chance >= 2) {
				
			}else {
				event.getBlock().setType(Material.AIR);
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), createCustomItem("§f§lPierre §8§lParfaite","Ressource","§f§lPierre aux bords parfaits",Material.STICK,1));
			}
			
			
		}if(event.getBlock().getType().equals(Material.COBBLESTONE) || event.getBlock().getType().equals(Material.ANDESITE)) {
			
			Random r = new Random();
			int chance = r.nextInt(100);
			if(chance >= 2) {
				
			}else {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), createCustomItem("§f§lMinerais §8§lImpur","Ressource","§f§lPierre avec des morceaux de minérais à l'intérieur",Material.STICK,1));
				
			}
			
			
		}
		if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§e§lPioche en §6§lAstate§r§8§o Stone")) {
			
			Random r = new Random();
			int chance = r.nextInt(400);
			if(chance >= 2) {
				
			}else {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), createCustomItem("§kf§r§f§lInconnu§kf","Ressource","§f§lDécouvrez le avec une loupe.",Material.FEATHER,1));
				
			}
			
		}
		
		if(event.getBlock().getType().equals(Material.DEAD_BRAIN_CORAL_BLOCK)) {
			if(event.getPlayer().getItemInHand() != null) {
				if(!event.getPlayer().isOp()) {
			if(event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§e§lPioche en §6§lAstate") || event.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)) {
				
				Location loc = event.getBlock().getLocation();
				loc.getBlock().setType(Material.AIR);
				loc.getWorld().playSound(loc, Sound.BLOCK_CORAL_BLOCK_BREAK, 1, 1);
				loc.getWorld().dropItemNaturally(loc, createCustomItem("§f§lFragment d'§e§lAstate","Ressource","§f§lElément chargé en magie",Material.STICK,1));
				loc.getWorld().spawnParticle(Particle.REDSTONE,new Location(loc.getWorld(),loc.getX() + 0.5, loc.getBlockY() + 1.5, loc.getZ() +0.5),5,new Particle.DustOptions(Color.fromBGR(187, 239, 230), 100));
				event.setCancelled(true);
				
				new BukkitRunnable() {

					@Override
					public void run() {
						loc.getBlock().setType(Material.DEAD_BRAIN_CORAL_BLOCK);
					}
				}.runTaskLater(SkyblockMain.getSkyBlock(), 20*2);
				
				
			}else {
				event.getPlayer().sendMessage("§eVous ne pouvez pas §6miner ce block§e avec votre pioche actuelle !");
				event.setCancelled(true);
			}
				}
			}
		}
		}
		
	}
	
	private static ItemStack createCustomItem(String name,String type,String lore,Material material,int nbr) {
		
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
