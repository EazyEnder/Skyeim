package fr.eazyender.skyblock.island;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import fr.eazyender.skyblock.SkyblockMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class IslandManager
{
  private static IslandManager islandManager;
  private static Map<UUID, Island> islands = new HashMap<UUID, Island>();
  
  private static double x = -1000000.0D; private static double z = -1000000.0D; private static double offset = 1000.0D;
  private static Location nextLocation = new Location((SkyblockMain.getSkyBlock()).world, x, 72.0D, z);
  
  public Block block1;
  
  public Block block2;
  
  File file;
  
  FileConfiguration config;
  private static File islandFile;
  private static FileConfiguration islandConfig;
  private static int totalIslands = 0;
  
  public IslandManager() {
    islandManager = this;
    registerFile();
    registerIslandFile();
    loadStats();
    loadNextLocation();
    for (Player ps : Bukkit.getOnlinePlayers()) {
      loadPlayer(ps);
    }
  }

  
  private void loadStats() { totalIslands = this.config.getInt("total-islands"); }
  
  public void onDisable() {
    this.config.set("total-islands", Integer.valueOf(totalIslands));
    ConfigurationSection s = this.config.getConfigurationSection("nextLocation");
    s.set("x", Double.valueOf(nextLocation.getX()));
    s.set("z", Double.valueOf(nextLocation.getZ()));
    for (Player ps : Bukkit.getOnlinePlayers()) {
    	if(this.hasIsland(ps)) {
    	ConfigurationSection si = islandConfig.getConfigurationSection(ps.getUniqueId().toString());
    	si.set("level", getIsland(ps).getLevel());
    	si.set("rayon", getIsland(ps).getRayon());
    	}
    }
    saveIslands();
    saveFile();
    for (Player ps : Bukkit.getOnlinePlayers()) {
      unloadPlayer(ps);
    }
  }
  
  public static void createIsland(Player p) {
    totalIslands++;
    double x = nextLocation.getX() + offset;
    double z = nextLocation.getZ();
    if (x > Math.abs(IslandManager.x)) {
      z += offset;
      nextLocation.setX(IslandManager.x);
      x = nextLocation.getX() + offset;
      nextLocation.setZ(z);
    } 
    Math.abs(IslandManager.z);
    
    Location loc = new Location(Bukkit.getWorld((SkyblockMain.getSkyBlock()).worldName), x, 75.0D, z);
    Location playerLoc = loc.clone().add(4.5D, 10.0D, 6.5D);
    playerLoc.setYaw(-180.0F);
    Island il = new Island(p, loc, 0, 50, true);
    p.teleport(playerLoc);
    islands.put(p.getUniqueId(), il);
    nextLocation = il.getLocation();
    ConfigurationSection s = islandConfig.createSection(p.getUniqueId().toString());
    s.set("name", p.getName());
    s.set("level", il.getLevel());
    s.set("rayon", il.getRayon());
    s.set("x", Double.valueOf(il.getLocation().getX()));
    s.set("z", Double.valueOf(il.getLocation().getZ()));
    saveIslands();
  }
  
  public void deleteIsland(Player p) {
    p.teleport(((World)Bukkit.getWorlds().get(0)).getSpawnLocation());
    islands.remove(p.getUniqueId());
    islandConfig.set(p.getUniqueId().toString(), null);
    saveIslands();
  }

  
  public static IslandManager getIslandManager() { return islandManager; }

  
  private void registerFile() {
    this.file = new File(SkyblockMain.getSkyBlock().getDataFolder(), "IslandManager.yml");
    this.config = YamlConfiguration.loadConfiguration(this.file);
    saveFile();
  }
  
  private void saveFile() {
    try {
      this.config.save(this.file);
    } catch (IOException iOException) {}
  }


  
  private void registerIslandFile() {
    islandFile = new File(SkyblockMain.getSkyBlock().getDataFolder(), "islands.yml");
    islandConfig = YamlConfiguration.loadConfiguration(islandFile);
    saveIslands();
  }
  
  private static void saveIslands() {
    try {
      islandConfig.save(islandFile);
    } catch (IOException iOException) {}
  }


  
  private void loadNextLocation() {
    if (this.config.contains("nextLocation")) {
      ConfigurationSection s = this.config.getConfigurationSection("nextLocation");
      double x = s.getDouble("x");
      double z = s.getDouble("z");
      nextLocation = new Location(Bukkit.getWorld((SkyblockMain.getSkyBlock()).worldName), x, 72.0D, z);
    } else {
      ConfigurationSection s = this.config.createSection("nextLocation");
      s.set("x", Double.valueOf(nextLocation.getX()));
      s.set("z", Double.valueOf(nextLocation.getZ()));
      saveFile();
    } 
  }
  
  public void loadPlayer(Player p) {
	  
    if (islandConfig.contains(p.getUniqueId().toString())) {
      ConfigurationSection s = islandConfig.getConfigurationSection(p.getUniqueId().toString());
      double x = s.getDouble("x");
      double z = s.getDouble("z");
      long level = s.getLong("level");
      int rayon = s.getInt("rayon");
    	  if(!s.contains("rayon")) {
    		  s.set("rayon", 50);
    	  }
    	  if(rayon < 50) {
    		  rayon = 50;
          }
      Island i = new Island(p, new Location(Bukkit.getWorld((SkyblockMain.getSkyBlock()).worldName), x, 72.0D, z),level,rayon, false);
      islands.put(p.getUniqueId(), i);
    } 
  }
  
  public void unloadPlayer(Player p) {
	    saveIslands();
    if (hasIsland(p)) {
    	ConfigurationSection si = islandConfig.getConfigurationSection(p.getUniqueId().toString());
	    si.set("level", getIsland(p).getLevel());
	    si.set("rayon", getIsland(p).getRayon());
      islands.remove(p.getUniqueId());
    }
  }
  
  public Island getIsland(Player p) {
    if (hasIsland(p)) {
      return (Island)islands.get(p.getUniqueId());
    }
    return null;
  }
  
  public void setLevel(Player p,long lvl) {
	    if (hasIsland(p)) {
	    	Island i = getIsland(p);
	    	i.setLevel(lvl);
	    	islands.put(p.getUniqueId(),i);
	    }
	  }
  
  public void setRayon(Player p,int rayon) {
	    if (hasIsland(p)) {
	    	Island i = getIsland(p);
	    	i.setRayon(rayon);
	    	islands.put(p.getUniqueId(),i);
	    }
	  }

  
  public void sendHome(Player p) { p.teleport(getIsland(p).getSpawnLocation()); }


  
  public boolean hasIsland(Player p) { return islands.containsKey(p.getUniqueId()); }


  
  public int getTotalIslands() { return totalIslands; }


  
  public int getTotalIslandsInUse() { return islandConfig.getKeys(false).size(); }
}
