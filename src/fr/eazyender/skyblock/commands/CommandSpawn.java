package fr.eazyender.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
					
					player.teleport(((World)Bukkit.getWorlds().get(0)).getSpawnLocation());
					player.sendMessage("§r§eVous avez été téléporté au §6§lspawn");
					
					return true;
			}
		
		return false;
		
	}

}
