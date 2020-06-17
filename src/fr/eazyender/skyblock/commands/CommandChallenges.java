package fr.eazyender.skyblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.eazyender.skyblock.gui.GuiChallengesMain;
import fr.eazyender.skyblock.gui.GuiShop;

public class CommandChallenges implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) 
	{
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			GuiChallengesMain.init();
			GuiChallengesMain.createGui(player,1);
			
			return true;
		}
		
		return false;
	}

}
