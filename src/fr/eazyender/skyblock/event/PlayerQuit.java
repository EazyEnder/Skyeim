package fr.eazyender.skyblock.event;


import fr.eazyender.skyblock.island.IslandManager;
import fr.eazyender.skyblock.player.PlayerEconomy;
import fr.eazyender.skyblock.player.PlayerStats;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener
{
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent e) {
	  
	  PlayerEconomy.getPlayerEconomy().unloadPlayer(e.getPlayer());
	  IslandManager.getIslandManager().unloadPlayer(e.getPlayer()); 
	  PlayerStats.getPlayerStats().unloadPlayer(e.getPlayer());
	  e.setQuitMessage("§e[§4§l-§r§e] §e§l" + e.getPlayer().getName() + " §r§ea §r§4quitté§r§e ! §r§e§o(" + (Bukkit.getOnlinePlayers().size() - 1) + "/" + Bukkit.getMaxPlayers() + ")");
  
  }
}

