package fr.eazyender.skyblock.player;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.eazyender.skyblock.SkyblockMain;
import fr.eazyender.skyblock.items.GemFire;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class PlayerActionBar {
	
	private static Map<Player, String> player_manaStr = new HashMap<Player, String>();
	private static Map<Player, Boolean> player_manaOn = new HashMap<Player, Boolean>();
	
	public static String defaultmessage = "§6§lMana : §r§e<====================>";
	
	public static void initBar() {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (Player ps : Bukkit.getOnlinePlayers()) {
					
					if(player_manaOn.containsKey(ps)) {
						if(player_manaOn.get(ps).equals(true)) {
					  
					  if(player_manaStr.containsKey(ps)) {
						  
					  }else {
						  player_manaStr.put(ps, "§6§lMana : §r§e<====================>");
					  }
					  ps.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(player_manaStr.get(ps)));
					  launchTimer(ps);
						}
					}
					
				}
				
			}
		}.runTaskTimer(SkyblockMain.getSkyBlock(), 0, 5);
		
	}
	
	public static void launchTimer(Player player) {
		
		switch(player_manaStr.get(player)) {
		case "§6§lMana : §r§e<====================>": player_manaStr.replace(player, "§6§lMana : §r§e<===================>"); break;
		case "§6§lMana : §r§e<===================>": player_manaStr.replace(player, "§6§lMana : §r§e<==================>"); break;
		case "§6§lMana : §r§e<==================>": player_manaStr.replace(player, "§6§lMana : §r§e<=================>"); break;
		case "§6§lMana : §r§e<=================>": player_manaStr.replace(player, "§6§lMana : §r§e<================>"); break;
		case "§6§lMana : §r§e<================>": player_manaStr.replace(player, "§6§lMana : §r§e<===============>"); break;
		case "§6§lMana : §r§e<===============>": player_manaStr.replace(player, "§6§lMana : §r§e<==============>"); break;
		case "§6§lMana : §r§e<==============>": player_manaStr.replace(player, "§6§lMana : §r§e<=============>"); break;
		case "§6§lMana : §r§e<=============>": player_manaStr.replace(player, "§6§lMana : §r§e<============>"); break;
		case "§6§lMana : §r§e<============>": player_manaStr.replace(player, "§6§lMana : §r§e<===========>"); break;
		case "§6§lMana : §r§e<===========>": player_manaStr.replace(player, "§6§lMana : §r§e<==========>"); break;
		case "§6§lMana : §r§e<==========>": player_manaStr.replace(player, "§6§lMana : §r§e<=========>"); break;
		case "§6§lMana : §r§e<=========>": player_manaStr.replace(player, "§6§lMana : §r§e<========>"); break;
		case "§6§lMana : §r§e<========>": player_manaStr.replace(player, "§6§lMana : §r§e<=======>"); break;
		case "§6§lMana : §r§e<=======>": player_manaStr.replace(player, "§6§lMana : §r§e<======>");break;
		case "§6§lMana : §r§e<======>": player_manaStr.replace(player, "§6§lMana : §r§e<=====>"); break;
		case "§6§lMana : §r§e<=====>": player_manaStr.replace(player, "§6§lMana : §r§e<====>"); break;
		case "§6§lMana : §r§e<====>": player_manaStr.replace(player, "§6§lMana : §r§e<===>"); break;
		case "§6§lMana : §r§e<===>": player_manaStr.replace(player, "§6§lMana : §r§e<==>"); break;
		case "§6§lMana : §r§e<==>": player_manaStr.replace(player, "§6§lMana : §r§e<=>"); break;
		case "§6§lMana : §r§e<=>": player_manaStr.replace(player, "§6§lMana : §r§e<>"); break;
		case "§6§lMana : §r§e<>": GemFire.cancelEffect(player,1); player_manaStr.replace(player, "§4Regen : 5"); break;
		case "§4Regen : 5": player_manaStr.replace(player, "§4Regen : 4.75"); break;
		case "§4Regen : 4.75": player_manaStr.replace(player, "§4Regen : 4.5"); break;
		case "§4Regen : 4.5": player_manaStr.replace(player, "§4Regen : 4.25"); break;
		case "§4Regen : 4.25": player_manaStr.replace(player, "§4Regen : 4"); break;
		case "§4Regen : 4": player_manaStr.replace(player, "§4Regen : 3.75"); break;
		case "§4Regen : 3.75": player_manaStr.replace(player, "§4Regen : 3.5"); break;
		case "§4Regen : 3.5": player_manaStr.replace(player, "§4Regen : 3.25"); break;
		case "§4Regen : 3.25": player_manaStr.replace(player, "§4Regen : 2"); break;
		case "§4Regen : 3": player_manaStr.replace(player, "§4Regen : 2.75"); break;
		case "§4Regen : 2.75": player_manaStr.replace(player, "§4Regen : 2.5"); break;
		case "§4Regen : 2.5": player_manaStr.replace(player, "§4Regen : 2.25"); break;
		case "§4Regen : 2.25": player_manaStr.replace(player, "§4Regen : 2"); break;
		case "§4Regen : 2": player_manaStr.replace(player, "§4Regen : 1.75"); break;
		case "§4Regen : 1.75": player_manaStr.replace(player, "§4Regen : 1.5"); break;
		case "§4Regen : 1.5": player_manaStr.replace(player, "§4Regen : 1.25"); break;
		case "§4Regen : 1.25": player_manaStr.replace(player, "§4Regen : 1"); break;
		case "§4Regen : 1": player_manaStr.replace(player, "§4Regen : 0.75"); break;
		case "§4Regen : 0.75": player_manaStr.replace(player, "§4Regen : 0.5"); break;
		case "§4Regen : 0.5": player_manaStr.replace(player, "§4Regen : 0.25"); break;
		case "§4Regen : 0.25": GemFire.cancelEffect(player,2);player_manaStr.replace(player, defaultmessage); break;
		default: player_manaStr.replace(player, "§6§lMana : §r§e<===================>"); break;
		}
		
	}
	
	public static Map<Player, String> getPlayer_manaStr() {
		return player_manaStr;
	}


	public static boolean isOnOrOff(Player player) {
		if(player_manaOn.containsKey(player)) {
		return player_manaOn.get(player);
		}else {
			return false;
		}
	}


	public static void setOnOrOff(Player player,boolean onOrOff) {
		if(player_manaOn.containsKey(player)) {
		player_manaOn.replace(player, onOrOff);
		}else if(!player_manaOn.containsKey(player)) {
		player_manaOn.put(player, onOrOff);
		}
	}
	

}
