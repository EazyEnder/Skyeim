package fr.eazyender.skyblock.event;

import fr.eazyender.skyblock.SkyblockMain;
import fr.eazyender.skyblock.island.Island;
import fr.eazyender.skyblock.island.IslandManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener
{
  @EventHandler
  public void onPlayerRespawn(PlayerRespawnEvent e) {
    Player p = e.getPlayer();
    if (p.getWorld().getName().equals((SkyblockMain.getSkyBlock()).world.getName()) && 
      IslandManager.getIslandManager().hasIsland(p)) {
      Island i = IslandManager.getIslandManager().getIsland(p);
      e.setRespawnLocation(i.getSpawnLocation());
    } 
  }
}
