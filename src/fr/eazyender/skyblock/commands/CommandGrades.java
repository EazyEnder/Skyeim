package fr.eazyender.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.eazyender.skyblock.player.PlayerStats;
import fr.eazyender.skyblock.utils.GradesAChallengesUtils;

public class CommandGrades implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			PlayerStats ps = PlayerStats.getPlayerStats();
			Player player = (Player) sender;
		if(player.isOp()) {
			if(args[0] != null) {
				if(args[1] != null) {
			
					Player target = Bukkit.getServer().getPlayer(args[1]);
					if(target != null) {
						
						target.setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(Integer.parseInt(args[0])) + target.getName());
						ps.setGradeId(target, Integer.parseInt(args[0]));
						player.sendMessage("§eSon grade est maintenant : " + GradesAChallengesUtils.getGradesIdToStr().get(Integer.parseInt(args[0])));
						
					}else{
						player.sendMessage("§eJoueur §4§lNON VALABLE");
					}
				}else {
					player.sendMessage("§eUsage : /grades <ID> §4§l<Player>");
				}
			}else {
				player.sendMessage("§eUsage : /grades §4§l<ID> <Player>");
			}
			
			return true;
		}else {
			
		}
		}
		
		return false;
	}

}
