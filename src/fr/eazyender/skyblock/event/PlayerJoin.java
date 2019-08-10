package fr.eazyender.skyblock.event;

import fr.eazyender.skyblock.island.IslandManager;
import fr.eazyender.skyblock.player.PlayerEconomy;
import fr.eazyender.skyblock.player.PlayerStats;
import fr.eazyender.skyblock.utils.GradesAChallengesUtils;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener
{
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) { 
	  
	  IslandManager.getIslandManager().loadPlayer(e.getPlayer()); 
	  PlayerEconomy.getPlayerEconomy().loadPlayer(e.getPlayer()); 
	  
	  PlayerStats ps = PlayerStats.getPlayerStats();
	  ps.loadPlayer(e.getPlayer());
	  
	  switch(ps.getGradeId(e.getPlayer())) {
	  case -1: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(-1) + e.getPlayer().getName());
		  break;
	  case 1: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(1) + e.getPlayer().getName());
		  break;
	  case 2: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(2) + e.getPlayer().getName());
		  break;
	  case 3: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(3) + e.getPlayer().getName());
		  break;
	  case 4: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(4) + e.getPlayer().getName());
		  break;
	  case 5: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(5) + e.getPlayer().getName());
		  break;
	  case 6: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(6) + e.getPlayer().getName());
		  break;
	  case 7: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(7) + e.getPlayer().getName());
	  	break;
	  case 8: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(8) + e.getPlayer().getName());
	  	break;
	  case 9: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(9) + e.getPlayer().getName());
	  	break;
	  case 10: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(10) + e.getPlayer().getName());
	  	break;
	  case 11: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(11) + e.getPlayer().getName());
	  	break;
	  case 12: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(12) + e.getPlayer().getName());
	  	break;
	  case 13: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(13) + e.getPlayer().getName());
	  	break;
	  case 14: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(14) + e.getPlayer().getName());
	  	break;
	  case 15: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(15) + e.getPlayer().getName());
	  	break;
	  case 16: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(16) + e.getPlayer().getName());
	  	break;
	  case 17: e.getPlayer().setDisplayName(GradesAChallengesUtils.getGradesIdToStr().get(17) + e.getPlayer().getName());
	  	break;
	  
	 default:
		  break;	  
	  }
	  
	  e.setJoinMessage("§e[§9§l+§r§e] §e§l" + e.getPlayer().getName() + " §r§ea §r§9rejoint§r§e ! §r§e§o(" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + ")");
  
  }
}
