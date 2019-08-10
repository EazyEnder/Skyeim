package fr.eazyender.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAno implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("annonce")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				
				if(args.length == 0) {
					player.sendMessage("§r§cMettez le texte après pour faire votre §4§lannonce.");
				}
				if(args.length >= 1) {
					
					StringBuilder sb = new StringBuilder();
					for(String part : args) {
						sb.append(part + " ");
					}
					
					Bukkit.broadcastMessage("§r§c§l[§r§4Annonce§r§c§l]§r§c " + sb.toString());
					
				}
				
				return true;
			}
		}
		
		return false;
		
	}

}
