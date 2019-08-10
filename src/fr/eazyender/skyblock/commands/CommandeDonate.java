package fr.eazyender.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.eazyender.skyblock.player.PlayerEconomy;

public class CommandeDonate implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) 
	{
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
		
			if(args[0] != null) {
			
				Player target = Bukkit.getServer().getPlayer(args[0]);
				
				if(target != null) {
					
					if(args[1] != null) {
						if(PlayerEconomy.getPlayerEconomy().getMoney(player) >= Long.parseLong(args[1])) {
							if(Long.parseLong(args[1]) >= 0) {
						PlayerEconomy.getPlayerEconomy().addMoney(target, Long.parseLong(args[1]));
						PlayerEconomy.getPlayerEconomy().setMoney(player, PlayerEconomy.getPlayerEconomy().getMoney(player) - Long.parseLong(args[1]));
						PlayerEconomy.getPlayerEconomy().onDisable();
						player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez donné :§6§l "+ args[1] + "§e à §6§l " + target.getDisplayName() + "§e.");
						target.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez reçu :§6§l "+ args[1] + "§e de la part de §6§l " + player.getDisplayName() + "§e.");
							}else{
								player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous ne pouvez pas mettre une valeur négative.");
							}
						}else {
							player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous n'avez pas assez d'argent.");
						}
					}else {
						player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous devez spécifier le montant que vous voulez donner.");
					}
					
				}else {
					player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eLa cible doit être un joueur valide.");
				}
				
			}else {
				player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous devez spécifier le joueur puis le montant");
			}
			
			return true;
			
		}
		
		return false;
	}

}
