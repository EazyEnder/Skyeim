package fr.eazyender.skyblock.player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.eazyender.skyblock.SkyblockMain;

public class PlayerEconomy {
	
	public static PlayerEconomy PlayerEconomy;
	
	 private static Map<UUID, Long> moneyPlayer = new HashMap<UUID, Long>();
	
	  private static File MoneyFile;
	  private static FileConfiguration MoneyConfig;
	
	  public PlayerEconomy() {
		  PlayerEconomy = this;
		    registerFile();
		    for (Player ps : Bukkit.getOnlinePlayers()) {
		      loadPlayer(ps);
		    }
	  }
	  
	  public void onDisable() {
		  for (Player ps : Bukkit.getOnlinePlayers()) {
		  ConfigurationSection s = MoneyConfig.getConfigurationSection(ps.getUniqueId().toString());
		  s.set("money", moneyPlayer.get(ps.getUniqueId()));
		  }
		    saveFile();
		  }
	  
	  public static void createPlayerEconomy(Player p) {
		    long money = 0;
		    moneyPlayer.put(p.getUniqueId(), money);
		    ConfigurationSection s = MoneyConfig.createSection(p.getUniqueId().toString());
		    s.set("money", Long.valueOf(0));
		    saveFile();
		  }
	  
	  
	 private void registerFile() {
		    MoneyFile = new File(SkyblockMain.getSkyBlock().getDataFolder(), "EconomyPlayer.yml");
		    MoneyConfig = YamlConfiguration.loadConfiguration(MoneyFile);
		    saveFile();
		  }
	 
	 private static void saveFile() {
		    try {		    
		      MoneyConfig.save(MoneyFile);
		    } catch (IOException iOException) {}
		  }
	 
	 public void loadPlayer(Player p) {
		    if (MoneyConfig.contains(p.getUniqueId().toString())) {
		      ConfigurationSection s = MoneyConfig.getConfigurationSection(p.getUniqueId().toString());
		      long money = s.getLong("money");
		      moneyPlayer.put(p.getUniqueId(), money);
		    } else {
		    	createPlayerEconomy(p);
		    }
		  }
	 
	 public long getMoney(Player p) {
		    if (playerExist(p)) {
		      return moneyPlayer.get(p.getUniqueId());
		    }else {
		    	System.out.println("Player doesn't exist");
		    }
		    return 0;
		  }
	 
	 public void setMoney(Player p, long newValue) {
		    if (playerExist(p)) {
		      moneyPlayer.replace(p.getUniqueId(), newValue);
		    }else {
		    	System.out.println("Player doesn't exist");
		    }
		  }
	 
	 public void addMoney(Player p, long value) {
		    if (playerExist(p)) {
		      moneyPlayer.replace(p.getUniqueId(), (moneyPlayer.get(p.getUniqueId()) + value));
		    }else {
		    	System.out.println("Player doesn't exist");
		    }
		  }
	 
	 public void unloadPlayer(Player p) {
		 	onDisable();
		    if (playerExist(p)) {
		    	moneyPlayer.remove(p.getUniqueId());
		    }
		  }
	 
	 public static PlayerEconomy getPlayerEconomy() { return PlayerEconomy;  }
	 
	 public boolean playerExist(Player p) { return moneyPlayer.containsKey(p.getUniqueId()); }

}
