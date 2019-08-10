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

public class HDVFiles {
//
//	public static HDVFiles HDVFiles;
//	
//	 private static Map<UUID, HDVCustomItem[]> items = new HashMap<UUID, HDVCustomItem[]>();
//	
//	  private static File HDVFile;
//	  private static FileConfiguration HDVConfig;
//	
//	  public HDVFiles() {
//		  HDVFiles = this;
//		    registerFile();
//		    for (Player ps : Bukkit.getOnlinePlayers()) {
//		      loadPlayer(ps);
//		    }
//	  }
//	  
//	  public void onDisable() {
//		  for (Player ps : Bukkit.getOnlinePlayers()) {
//		  ConfigurationSection s = HDVConfig.getConfigurationSection(ps.getUniqueId().toString());
//		  s.set("items", items.get(ps.getUniqueId()));
//		  }
//		    saveFile();
//		  }
//	  
//	  public static void createItemsList(Player p) {
//		  HDVCustomItem[] itemsP = {null,null,null,null,null};
//		    items.put(p.getUniqueId(), itemsP);
//		    ConfigurationSection s = HDVConfig.createSection(p.getUniqueId().toString());
//		    s.set("items",  itemsP.toString());
//		    saveFile();
//		  }
//	  
//	  
//	 private void registerFile() {
//		 HDVFile = new File(SkyblockMain.getSkyBlock().getDataFolder(), "EconomyPlayer.yml");
//		 HDVConfig = YamlConfiguration.loadConfiguration(HDVFile);
//		    saveFile();
//		  }
//	 
//	 private static void saveFile() {
//		    try {		    
//		    	HDVConfig.save(HDVFile);
//		    } catch (IOException iOException) {}
//		  }
//	 
//	 public void loadPlayer(Player p) {
//		    if (HDVConfig.contains(p.getUniqueId().toString())) {
//		      ConfigurationSection s = HDVConfig.getConfigurationSection(p.getUniqueId().toString());
//		      long money = s.getLong("money");
//		      items.put(p.getUniqueId(), money);
//		    } else {
//		    	createItemsList(p);
//		    }
//		  }
//	 
//	 public long getMoney(Player p) {
//		    if (playerExist(p)) {
//		      return items.get(p.getUniqueId());
//		    }else {
//		    	System.out.println("Player doesn't exist");
//		    }
//		    return 0;
//		  }
//	 
//	 public void setMoney(Player p, long newValue) {
//		    if (playerExist(p)) {
//		    	items.replace(p.getUniqueId(), newValue);
//		    }else {
//		    	System.out.println("Player doesn't exist");
//		    }
//		  }
//	 
//	 public void addMoney(Player p, long value) {
//		    if (playerExist(p)) {
//		    	items.replace(p.getUniqueId(), (items.get(p.getUniqueId()) + value));
//		    }else {
//		    	System.out.println("Player doesn't exist");
//		    }
//		  }
//	 
//	 public void unloadPlayer(Player p) {
//		 	onDisable();
//		    if (playerExist(p)) {
//		    	items.remove(p.getUniqueId());
//		    }
//		  }
//	 
//	 public static HDVFiles getHDVFiles() { return HDVFiles;  }
//	 
//	 public boolean playerExist(Player p) { return items.containsKey(p.getUniqueId()); }
//	
}
