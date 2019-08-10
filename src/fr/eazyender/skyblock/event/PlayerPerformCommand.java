package fr.eazyender.skyblock.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerPerformCommand implements Listener {
	
	@EventHandler
	  public void onPlayerPerformCommand(PlayerCommandPreprocessEvent e) { 
		
		if(e.getMessage().contains("//calc") || e.getMessage().contains("/calc")) {
			
			e.setCancelled(true);
			e.getPlayer().kickPlayer("§4§l§nSuper ! C'est cool d'essayer de crash un serveur ? (:");
			
		}else if(e.getMessage().contains("/pl")) {
			
			e.setCancelled(true);
			e.getPlayer().sendMessage("§r§fPlugins (1): §aSkyeim");;
			
		}else if(e.getMessage().contains("/help")) {
			
			e.setMessage("/aide");
			
		}
		
	}
	

	@EventHandler
	  public void onPlayerSendMessage(PlayerChatEvent e) { 
		
		if(e.getPlayer() != null) {
			Player p = e.getPlayer();
			
			e.setCancelled(true);
			Bukkit.broadcastMessage("§f§l[§r§aDiscussion§r§f§l]§r§f" + p.getDisplayName() + "§r§f:§a " + e.getMessage());
		}
		
	}

}
