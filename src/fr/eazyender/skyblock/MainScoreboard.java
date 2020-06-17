package fr.eazyender.skyblock;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.eazyender.skyblock.island.Island;
import fr.eazyender.skyblock.island.IslandManager;
import fr.eazyender.skyblock.player.PlayerEconomy;

public class MainScoreboard implements Listener {
	
	public static String name = "§f§l<§e§lSkyeim§f§l>";
	public static int stape = 0;
	public static boolean up = true;
	
	 private static Map<UUID, String[]> scores = new HashMap<UUID, String[]>();
	
	private static ScoreboardManager sb = Bukkit.getScoreboardManager();
	private static Scoreboard board;
	private static Objective obj;
	private static Score space2,title_server,onlinePlayers,version,ram,ramused,space,title_player,player_grade,moneyS,space3,title_island,island_width,island_lvl;
	
	private static String name_onlinePlayers,name_ramused,name_player_grade,name_moneyS,name_island_width,name_island_lvl;
	
	static String[] tab = {name_onlinePlayers,name_ramused,name_player_grade,name_moneyS,name_island_width,name_island_lvl};
	
	static SkyblockMain skyblock;
	
	private static long ramLB =  (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000;
	private static long ramLM =  Runtime.getRuntime().totalMemory() / 1000000;
	private static long onlineP = Bukkit.getOnlinePlayers().size();
	
	public MainScoreboard(SkyblockMain skyblock) {this.skyblock = skyblock;}
	
	private static void changeValue() {
		
		ramLB =  (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000;
		ramLM =  Runtime.getRuntime().totalMemory() / 1000000;
		
	}
	
	private static void initScoreboard() {
		
		sb = Bukkit.getScoreboardManager();
		board = sb.getNewScoreboard();
				
		obj = board.registerNewObjective("Main", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(name);
		
		space2 = obj.getScore("§r   ");
		space2.setScore(14);
		
		title_server = obj.getScore("§6§l§nServeur§r§f");
		title_server.setScore(13);
		
		
		version = obj.getScore("§e§lVersion: §r§cAlpha 1");
		version.setScore(11);
		
		ram = obj.getScore("§e§lMachine: §r§f");
		ram.setScore(10);
		
		space = obj.getScore("§r  ");
		space.setScore(8);
		
		title_player = obj.getScore("§6§l§nJoueur§r§f");
		title_player.setScore(7);
		
		space3 = obj.getScore("§r ");
		space3.setScore(4);
		
		title_island = obj.getScore("§6§l§nSon île§r§f");
		title_island.setScore(3);

		
	}
	
	public static void createScoreboard() {
		
		initScoreboard();
		
		 new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for (Player ps : Bukkit.getOnlinePlayers()) {
			
					for(OfflinePlayer i : ps.getScoreboard().getPlayers()) {
						ps.getScoreboard().resetScores(i);
					}
				
					changeValue();
					
			space2 = obj.getScore("§r   ");
			space2.setScore(14);
			
			title_server = obj.getScore("§6§l§nServeur§r§f");
			title_server.setScore(13);
			
			
			version = obj.getScore("§e§lVersion: §r§cAlpha 1");
			version.setScore(11);
			
			ram = obj.getScore("§e§lMachine: §r§f");
			ram.setScore(10);
			
			space = obj.getScore("§r  ");
			space.setScore(8);
			
			title_player = obj.getScore("§6§l§nJoueur§r§f");
			title_player.setScore(7);
			
			space3 = obj.getScore("§r ");
			space3.setScore(4);
			
			title_island = obj.getScore("§6§l§nSon île§r§f");
			title_island.setScore(3);
		
		name_onlinePlayers = "§e§lConnectés: §r§f" + onlineP;
		onlinePlayers = obj.getScore(name_onlinePlayers);
		onlinePlayers.setScore(12);
		name_ramused = "§f" + ramLB + "MB §e§l/§r§f" + ramLM + "MB";
		ramused = obj.getScore(name_ramused);
		ramused.setScore(9);
		name_player_grade = "§e§lGrade: §r§f";
		player_grade = obj.getScore("§e§lGrade: §r§f");
		player_grade.setScore(6);
		name_moneyS = "§e§lArgent: §r§f" + PlayerEconomy.getPlayerEconomy().getMoney(ps);
		moneyS = obj.getScore("§e§lArgent: §r§f" + moneyS);
		moneyS.setScore(5);
		
		if(IslandManager.getIslandManager().hasIsland(ps)) {
		name_island_width = "§e§lTaille: §r§f" + IslandManager.getIslandManager().getIsland(ps).getRayon() * 2 + "B";
		island_width = obj.getScore(name_island_width);
		island_width.setScore(2);
		}else {
		name_island_width = "§e§lTaille: §r§f" + "Pas d'île...";
		island_width = obj.getScore(name_island_width);
		island_width.setScore(2);
		}
		if(IslandManager.getIslandManager().hasIsland(ps)) {
		name_island_lvl = "§e§lLevel: §r§f" + IslandManager.getIslandManager().getIsland(ps).getLevel();
		island_lvl = obj.getScore(name_island_lvl);
		island_lvl.setScore(1);
		}else {
		name_island_lvl = "§e§lLevel: §r§f" + "Pas d'île...";
		island_lvl = obj.getScore(name_island_lvl);
		island_lvl.setScore(1);
		}
		
		tab[0] = name_onlinePlayers;
		tab[1] = name_ramused;
		tab[2] = name_player_grade;
		tab[3] = name_moneyS;
		tab[4] = name_island_width;
		tab[5] = name_island_lvl;
		if(!scores.containsKey(ps.getUniqueId())) {
		scores.put(ps.getUniqueId(), tab);
		}else {
		scores.replace(ps.getUniqueId(), tab);
		}
		
		ps.setScoreboard(board);
		
				}
			}
			
		 }.runTaskTimer(skyblock, 0, 40);
				
				

			new BukkitRunnable() {
				
				@Override
				public void run() {	
					
					
					obj.setDisplayName(name);
					
		if(up) {
			switch(stape) {
			
			case 0: 
			case 1: 
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12: name = "§f§l<§e§lSkyeim§f§l>";stape++;
				break;
			case 13: name = "§f§l<§e§lkyei§f§l>";stape++;
				break;
			case 14: name = "§f§l<§e§lye§f§l>";stape++;
				break;
			case 15: name = "§f§l<>"; up = false;
				break;
			}
		}else if(!up) {
				switch(stape) {
				
				case 0: up = true;
					break;
				case 1: 
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
				case 12: name = "§f§l<§e§lSkyeim§f§l>";stape--;
					break;
				case 13: name = "§f§l<§e§lkyei§f§l>";stape--;
					break;
				case 14: name = "§f§l<§e§lye§f§l>";stape--;
					break;
				case 15: name = "§f§l<>";stape--;
					break;
				}
			}
			}
			
	}.runTaskTimer(skyblock, 0, 2);
		
		
	}
	
	

}
