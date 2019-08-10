package fr.eazyender.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandeChatCanals implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 0 || args.length == 1) {
					player.sendMessage("§eUsage : /canal <trade/coop/report> <message>");
				}
				else if(args.length >= 2) {
					
					if(args[0].equals("trade")) {
						StringBuilder sb = new StringBuilder();
						for(String part : args) {
							sb.append(part + " ");
						}
						String message = sb.toString();
						message = message.substring(6);
					
						Bukkit.broadcastMessage("§f§l[§r§bTrade§r§f§l]§r§f" + player.getDisplayName() + "§r§f:§b§l " + message);
					}else if(args[0].equals("coop")) {
						StringBuilder sb = new StringBuilder();
						for(String part : args) {
							sb.append(part + " ");
						}
						String message = sb.toString();
						message = message.substring(5);
					
						Bukkit.broadcastMessage("§f§l[§r§9Coop§r§f§l]§r§f" + player.getDisplayName() + "§r§f:§9 " + message);
					}else if(args[0].equals("report")) {
						StringBuilder sb = new StringBuilder();
						for(String part : args) {
							sb.append(part + " ");
						}
						String message = sb.toString();
						message = message.substring(7);
					
						Bukkit.broadcastMessage("§f§l[§r§cReport§r§f§l]§r§f" + player.getDisplayName() + "§r§f:§c " + message);
					}else {
						player.sendMessage("§eUsage : /canal " + args[0] +  " <message>");
					}
				}
				
				return true;
			}
		
		return false;
		
	}

}
