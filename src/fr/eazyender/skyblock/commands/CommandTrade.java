package fr.eazyender.skyblock.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.eazyender.skyblock.SkyblockMain;
import fr.eazyender.skyblock.gui.GuiTrade;

public class CommandTrade implements CommandExecutor{

	private static Map<Player, Player> trades = new HashMap<Player, Player>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) 
	{
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
		
			if(args[0] != null) {
			
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(target == null) {
					if(args[0].equals("accept")) {
						if(trades.containsKey(player)) {
							
							GuiTrade.createGui(trades.get(player), player);
							trades.get(player).sendMessage("§f§l[§r§eEchanges§r§f§l]§r" + "§r§e Votre §r§6§léchange§r§e ,vers §6§l§n" + player.getDisplayName() + "§r§e, a été accepté.");
							player.sendMessage("§f§l[§r§eEchanges§r§f§l]§r" + "§r§e Vous avez accepté  un §r§6§léchange§r§e venant de §6§l§n" + trades.get(player).getDisplayName());
							trades.remove(player);
						}else {
							player.sendMessage("§f§l[§r§eEchanges§r§f§l]§r §eVous n'avez pas de demande");
						}
					}else if(args[0].equals("refuse")) {
						if(trades.containsKey(player)) {
							
							player.sendMessage("§f§l[§r§eEchanges§r§f§l]§r" + "§r§e Vous avez refusé  un §r§6§léchange§r§e venant de §6§l§n" + trades.get(player).getDisplayName());
							trades.get(player).sendMessage("§f§l[§r§eEchanges§r§f§l]§r §6§l§n" + player.getDisplayName() + "§r§e a refusé votre demande.");
							trades.remove(player);
						}else {
							player.sendMessage("§f§l[§r§eEchanges§r§f§l]§r §eVous n'avez pas de demande à refusé.");
						}
					}else {
						player.sendMessage("Usage : /trade <player/accept/refuse>");
					}
				}else if(target != player){
					if(!trades.containsKey(target)) {
					trades.put(target, player);
					
					new BukkitRunnable() {
						
						@Override
						public void run() {
							
							if(trades.containsKey(target)) {
							trades.remove(target);
							}
							
						}
						
					}.runTaskLater(SkyblockMain.getSkyBlock(), 20*60);
					
					target.sendMessage("§f§l[§r§eEchanges§r§f§l]§r §6§l§n" + player.getDisplayName() + "§r§e vous a demandé un §r§6§léchange §r§e, vous avez §r§6§l60§r§e secondes pour y répondre(/trade <accept/refuse>).");
					}else {
						player.sendMessage("§f§l[§r§eEchanges§r§f§l]§r §eCette personne a déja une demande , veuillez attendre qu'elle la refuse.");
					}
				}else {
					player.sendMessage("§4§lVous ne pouvez pas demander un échange à vous même");
				}
				
				
			return true;
			}else {
				player.sendMessage("Usage : /trade <player/accept/refuse>");
			}
		}
		
		return false;
	}

}
