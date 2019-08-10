package fr.eazyender.skyblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.eazyender.skyblock.gui.GuiBiome;

public class CommandeBiomes implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) 
	{
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			GuiBiome.createGui(player);
			
			return true;
		}
		
		return false;
	}

}
