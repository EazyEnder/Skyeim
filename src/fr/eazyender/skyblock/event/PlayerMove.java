package fr.eazyender.skyblock.event;


import fr.eazyender.skyblock.SkyblockMain;
import fr.eazyender.skyblock.island.Island;
import fr.eazyender.skyblock.island.IslandManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class PlayerMove implements Listener
{
  @EventHandler
  public void onPlayerMove(PlayerMoveEvent e) {
    Player p = e.getPlayer();
    if (p.getWorld().getName().equals((SkyblockMain.getSkyBlock()).world.getName())) {
      Island i = IslandManager.getIslandManager().getIsland(p);
      if (i != null && 
        i.isAt(e.getFrom()) && !i.isAt(e.getTo())) {
        e.setCancelled(true);
        p.sendMessage("§4§lTu ne peux pas quitter ton île !");
      } 
    } 
  }
}
