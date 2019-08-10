package fr.eazyender.skyblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.eazyender.skyblock.player.PlayerEconomy;

public class CommandMoney implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
					
					if(args.length != 0) {
						if(player.isOp() && args[0] != null) {
						PlayerEconomy.getPlayerEconomy().setMoney(player, Long.parseLong(args[0]));
						PlayerEconomy.getPlayerEconomy().onDisable();
						player.sendMessage("§r§eVous vous êtes set à " + args[0]);
						}else if(args[0] != null) {
							player.sendMessage("Vous devez être OP pour vous give de la money");
						}
						return true;
					}else if(args.length == 0){
						
						player.sendMessage("§f§l[§r§eInfo§r§f§l]§r §eVous avez : §6§l" + PlayerEconomy.getPlayerEconomy().getMoney(player));
						return true;
					}
					
					
			}
		
		return false;
		
	}

}
