package fr.eazyender.skyblock.player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.eazyender.skyblock.SkyblockMain;

public class PlayerStats {
	
	public static PlayerStats PlayerStats;
	
	 private static Map<UUID, PlayerStatsObject> statsPlayer = new HashMap<UUID, PlayerStatsObject>();
	
	  private static File StatsFile;
	  private static FileConfiguration StatsConfig;
	
	  public PlayerStats() {
		  PlayerStats = this;
		    registerFile();
		    for (Player ps : Bukkit.getOnlinePlayers()) {
		      loadPlayer(ps);
		    }
	  }
	  
	  public void onDisable() {
		  for (Player ps : Bukkit.getOnlinePlayers()) {
		  ConfigurationSection s = StatsConfig.getConfigurationSection(ps.getUniqueId().toString());
		  s.set("challenge", statsPlayer.get(ps.getUniqueId()).getChallenges());
		  s.set("grade", statsPlayer.get(ps.getUniqueId()).getGrade_ID());
		  }
		    saveFile();
		  }
	  
	  public static void createPlayerStats(Player p) {
		    PlayerStatsObject pso = new PlayerStatsObject(p,0,new ArrayList<Integer>());
		    
		    List<Integer> list = new ArrayList<Integer>();
		    int numberOfChallenges = 9;
		    for(int i = 0; i < numberOfChallenges; i++) {
		    	list.add(0);
		    }
		    pso.setChallenges(list);
		    
		    statsPlayer.put(p.getUniqueId(), pso);
		    ConfigurationSection s = StatsConfig.createSection(p.getUniqueId().toString());
		    s.set("challenge", pso.getChallenges());
			s.set("grade", pso.getGrade_ID());
		    saveFile();
		  }
	  
	  
	 private void registerFile() {
		 	StatsFile = new File(SkyblockMain.getSkyBlock().getDataFolder(), "PlayerStats.yml");
		 	StatsConfig = YamlConfiguration.loadConfiguration(StatsFile);
		    saveFile();
		  }
	 
	 private static void saveFile() {
		    try {		    
		    	StatsConfig.save(StatsFile);
		    } catch (IOException iOException) {}
		  }
	 
	 public void loadPlayer(Player p) {
		    if (StatsConfig.contains(p.getUniqueId().toString())) {
		      ConfigurationSection s = StatsConfig.getConfigurationSection(p.getUniqueId().toString());
		      int grade_Id = s.getInt("grade");
		      List<Integer> challenges = s.getIntegerList("challenge");
		      PlayerStatsObject pso = new PlayerStatsObject(p,grade_Id,challenges);
		      statsPlayer.put(p.getUniqueId(), pso);
		    } else {
		    	createPlayerStats(p);
		    }
		  }
	 
	 public int getGradeId(Player p) {
		    if (playerExist(p)) {
		      return statsPlayer.get(p.getUniqueId()).getGrade_ID();
		    }else {
		    	System.out.println("Player doesn't exist");
		    }
		    return 0;
		  }
	 
	 public void setChallengeById(Player p, int ID, int newValue) {
		    if (playerExist(p)) {
		    	statsPlayer.get(p.getUniqueId()).getChallenges().set(newValue, ID);
		    }else {
		    	System.out.println("Player doesn't exist");
		    }
		  }
	 
	 public int getChallengeById(Player p,int ID) {
		    if (playerExist(p)) {
		      return statsPlayer.get(p.getUniqueId()).getChallenges().get(ID);
		    }else {
		    	System.out.println("Player doesn't exist");
		    }
		    return 0;
		  }
	 
	 public void setGradeId(Player p, int newValue) {
		    if (playerExist(p)) {
		    	statsPlayer.get(p.getUniqueId()).setGrade_ID(newValue);
		    }else {
		    	System.out.println("Player doesn't exist");
		    }
		  }
	 
	 public List<Integer> getChallengesList(Player p) {
		    if (playerExist(p)) {
		      return statsPlayer.get(p.getUniqueId()).getChallenges();
		    }else {
		    	System.out.println("Player doesn't exist");
		    }
		    return null;
		  }
	 
	 public void setChallengeList(Player p, List<Integer> newValue) {
		    if (playerExist(p)) {
		    	statsPlayer.get(p.getUniqueId()).setChallenges(newValue);;
		    }else {
		    	System.out.println("Player doesn't exist");
		    }
		  }
	 
	 
	 public void unloadPlayer(Player p) {
		 	onDisable();
		    if (playerExist(p)) {
		    	statsPlayer.remove(p.getUniqueId());
		    }
		  }
	 
	 public static PlayerStats getPlayerStats() { return PlayerStats;  }
	 
	 public boolean playerExist(Player p) { return statsPlayer.containsKey(p.getUniqueId()); }

}