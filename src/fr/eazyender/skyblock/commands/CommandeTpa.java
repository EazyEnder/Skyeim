package fr.eazyender.skyblock.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.eazyender.skyblock.SkyblockMain;

public class CommandeTpa implements CommandExecutor {

private static Map<Player, Player> tpa = new HashMap<Player, Player>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) 
	{
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
		
			if(args[0] != null) {
			
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(target == null) {
					if(args[0].equals("accept")) {
						if(tpa.containsKey(player)) {
							

							tpa.get(player).teleport(player.getLocation());
							
							tpa.get(player).sendMessage("§f§l[§r§eTpa§r§f§l]§r" + "§r§e Votre §r§6§ltpa§r§e ,vers §6§l§n" + player.getDisplayName() + "§r§e, a été accepté.");
							player.sendMessage("§f§l[§r§eTpa§r§f§l]§r" + "§r§e Vous avez accepté  un §r§6§ltpa§r§e venant de §6§l§n" + tpa.get(player).getDisplayName());
							tpa.remove(player);
						}else {
							player.sendMessage("§f§l[§r§eTpa§r§f§l]§r §eVous n'avez pas de demande");
						}
					}else if(args[0].equals("refuse")) {
						if(tpa.containsKey(player)) {
							
							player.sendMessage("§f§l[§r§eTpa§r§f§l]§r" + "§r§e Vous avez refusé  un §r§6§ltpa§r§e venant de §6§l§n" + tpa.get(player).getDisplayName());
							tpa.get(player).sendMessage("§f§l[§r§eTpa§r§f§l]§r §6§l§n" + player.getDisplayName() + "§r§e a refusé votre demande.");
							tpa.remove(player);
						}else {
							player.sendMessage("§f§l[§r§eTpa§r§f§l]§r §eVous n'avez pas de demande à refusé.");
						}
					}else {
						player.sendMessage("Usage : /tpa <player/accept/refuse>");
					}
				}else if(target != player){
					if(!tpa.containsKey(target)) {
						tpa.put(target, player);
					
					new BukkitRunnable() {
						
						@Override
						public void run() {
							
							if(tpa.containsKey(target)) {
								tpa.remove(target);
							}
							
						}
						
					}.runTaskLater(SkyblockMain.getSkyBlock(), 20*60);
					
					player.sendMessage("§f§l[§r§eTpa§r§f§l] §eVous avez envoyé un tpa à§6§l " + target.getDisplayName());
					target.sendMessage("§f§l[§r§eTpa§r§f§l]§r §6§l§n" + player.getDisplayName() + "§r§e vous a demandé un §r§6§ltpa §r§e, vous avez §r§6§l60§r§e secondes pour y répondre(/tpa <accept/refuse>).");
					}else {
						player.sendMessage("§f§l[§r§eTpa§r§f§l]§r §eCette personne a déja une demande , veuillez attendre qu'elle la refuse.");
					}
				}else {
					player.sendMessage("§4§lVous ne pouvez pas vous tp à vous même");
				}
				
				
			return true;
			}else {
				player.sendMessage("Usage : /tp <player/accept/refuse>");
			}
		}
		
		return false;
	}

}
