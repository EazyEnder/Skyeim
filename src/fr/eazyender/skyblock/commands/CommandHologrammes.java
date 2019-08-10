package fr.eazyender.skyblock.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CommandHologrammes implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
					
					if(args[0] != null) {
						
						
						if(args[0].equals("1")) {
							
							Location lc = player.getLocation();
							createHologram(lc,"§r§f§l§kKKK§6§l§nSkyeim§r§f§l§kKKK");
							Location lc2 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.3,
									player.getLocation().getZ());
							createHologram(lc2,"§r§e§lUn serveur §r§6§lskyblock RPG§r§e§l !");
							Location lc3 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.6,
									player.getLocation().getZ());
							createHologram(lc3,"§e§lExplorez des §r§6§ldonjons§r§e§l et fondez votre §r§6§lguilde§r§e§l...");
							Location lc4 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.9,
									player.getLocation().getZ());
							createHologram(lc4,"§e§lTout en améliorant votre §r§6§lîle§r§e§l.");
							Location lc5 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 1.2,
									player.getLocation().getZ());
							createHologram(lc5,"§r§6§l§kKKK§r§f§lAtteignez le top des aventuriers !§r§6§l§kKKK");
							
						}else if(args[0].equals("2")) {
							
							Location lc2 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.3,
									player.getLocation().getZ());
							createHologram(lc2,"§r§e§lVous voulez commencer votre §r§6§laventure§r§e§l ?");
							Location lc3 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.6,
									player.getLocation().getZ());
							createHologram(lc3,"§e§lRien de plus simple : faites §r§6§l/is§r§e§l puis allez sur §r§6§lvotre île§r§e§l");
							Location lc4 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.9,
									player.getLocation().getZ());
							createHologram(lc4,"§e§lEt voici les §r§6§lcommandes§r§e§l qui vous seront utiles :");
							Location lc5 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 1.2,
									player.getLocation().getZ());
							createHologram(lc5,"§e§l§r§6§l/shop§r§e§l : pour la §r§6§lboutique§r§e§l");
							Location lc6 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 1.4,
									player.getLocation().getZ());
							createHologram(lc6,"§e§l§r§6§l/challenge§r§e§l : pour vos §r§6§lobjectifs§r§e§l");
							Location lc7 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 1.6,
									player.getLocation().getZ());
							createHologram(lc7,"§e§l§r§6§l/donjons§r§e§l : pour explorer les §r§6§ldonjons§r§e§l");
							Location lc8 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 1.8,
									player.getLocation().getZ());
							createHologram(lc8,"§e§lEt pour les autres §r§6§l/aide§r§e§l");
							Location lc9 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 2.1,
									player.getLocation().getZ());
							createHologram(lc9,"§r§f§l§kKKK§e§lBonne aventure !§r§f§l§kKKK");
							
							
						}else if(args[0].equals("3")) {
							Location lcL = player.getLocation();
							createHologram(lcL,"§r§f§l§kKKK§6§l§nSkyeim§r§f§l§kKKK");
							Location lc2L = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.3,
									player.getLocation().getZ());
							createHologram(lc2L,"§r§e§lUn serveur §r§6§lskyblock RPG§r§e§l !");
							Location lc3L = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.6,
									player.getLocation().getZ());
							createHologram(lc3L,"§e§lExplorez des §r§6§ldonjons§r§e§l et fondez votre §r§6§lguilde§r§e§l...");
							Location lc4L = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.9,
									player.getLocation().getZ());
							createHologram(lc4L,"§e§lTout en améliorant votre §r§6§lîle§r§e§l.");
							Location lc5L = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 1.2,
									player.getLocation().getZ());
							createHologram(lc5L,"§r§6§l§kKKK§r§f§lAtteignez le top des aventuriers !§r§6§l§kKKK");
							Location lc2 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.3- 1.2,
									player.getLocation().getZ());
							createHologram(lc2,"§r§e§lVous voulez commencer votre §r§6§laventure§r§e§l ?");
							Location lc3 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.6- 1.2,
									player.getLocation().getZ());
							createHologram(lc3,"§e§lRien de plus simple : faites §r§6§l/is§r§e§l puis allez sur §r§6§lvotre île§r§e§l");
							Location lc4 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 0.9- 1.2,
									player.getLocation().getZ());
							createHologram(lc4,"§e§lEt voici les §r§6§lcommandes§r§e§l qui vous seront utiles :");
							Location lc5 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 1.2- 1.2,
									player.getLocation().getZ());
							createHologram(lc5,"§e§l§r§6§l/shop§r§e§l : pour la §r§6§lboutique§r§e§l");
							Location lc6 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 1.4- 1.2,
									player.getLocation().getZ());
							createHologram(lc6,"§e§l§r§6§l/challenge§r§e§l : pour vos §r§6§lobjectifs§r§e§l");
							Location lc7 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 1.6- 1.2,
									player.getLocation().getZ());
							createHologram(lc7,"§e§l§r§6§l/donjons§r§e§l : pour explorer les §r§6§ldonjons§r§e§l");
							Location lc8 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 1.8- 1.2,
									player.getLocation().getZ());
							createHologram(lc8,"§e§lEt pour les autres §r§6§l/aide§r§e§l");
							Location lc9 = new Location(
									player.getWorld(),
									player.getLocation().getX(),
									player.getLocation().getY() - 2.1- 1.2,
									player.getLocation().getZ());
							createHologram(lc9,"§r§f§l§kKKK§e§lBonne aventure !§r§f§l§kKKK");
							
							
						}
					
				}
					
					return true;
			}
		
		return false;
		
	}
	
	private static void createHologram(Location lc, String txt) {
		
		
		ArmorStand as = (ArmorStand) lc.getWorld().spawnEntity(lc, EntityType.ARMOR_STAND); //Spawn the ArmorStand
		as.setGravity(false); //Make sure it doesn't fall
		as.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
		as.setCustomName(txt); //Set this to the text you want
		as.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
		as.setVisible(false); //Makes the ArmorStand invisible
		
	}

}
