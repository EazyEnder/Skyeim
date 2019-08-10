package fr.eazyender.skyblock.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.eazyender.skyblock.SkyblockMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class PlayerActionBar {
	
	public static void initBar() {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (Player ps : Bukkit.getOnlinePlayers()) {
					
					  String message = "§6§lMana : §r§e<====================>";
					  ps.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
					
				}
				
			}
		}.runTaskTimer(SkyblockMain.getSkyBlock(), 0, 2);
		
	}

}
