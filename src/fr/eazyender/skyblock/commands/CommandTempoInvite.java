package fr.eazyender.skyblock.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTempoInvite implements CommandExecutor{

public static Map<Player, Player> perms = new HashMap<Player, Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) 
	{
	
	if(sender instanceof Player) {
		Player player = (Player) sender;
		
		if(args[0] != null) {
		Player target = Bukkit.getServer().getPlayer(args[0]);
		
		if(args[1].equalsIgnoreCase("invite")) {
			
			target.sendMessage("�e" + player.getDisplayName() + " vous a autoris� � build sur son �le. Pour y acc�der faites /tpa");
			player.sendMessage("�eVous avez donn� les permissions � " + target.getDisplayName() + " d'int�ragir avec votre �le. Pour l'enlever , faites /invite reset.");
			
			perms.put(target, player);
					
		}else if(args[1].equalsIgnoreCase("reset")) {
			if(perms.containsKey(target)) {
				player.sendMessage("�eVous avez reset les permissions.");
				
				perms.remove(target);
			}else {
				player.sendMessage("�eVous devez invit� une personne avant.");
			}
		}else {
			player.sendMessage("�eUsage : /invite <Pseudo> <invite/reset>");
		}
		
	}else {
		player.sendMessage("�eUsage : /invite <Pseudo> <invite/reset>");
	}
	return true;
	}
	
	return false;
	
	}
}
