package fr.eazyender.skyblock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import fr.eazyender.skyblock.commands.CommandAno;
import fr.eazyender.skyblock.commands.CommandCHelp;
import fr.eazyender.skyblock.commands.CommandCustomGive;
import fr.eazyender.skyblock.commands.CommandHdv;
import fr.eazyender.skyblock.commands.CommandHologrammes;
import fr.eazyender.skyblock.commands.CommandIsland;
import fr.eazyender.skyblock.commands.CommandLevel;
import fr.eazyender.skyblock.commands.CommandMinions;
import fr.eazyender.skyblock.commands.CommandMoney;
import fr.eazyender.skyblock.commands.CommandShop;
import fr.eazyender.skyblock.commands.CommandSpawn;
import fr.eazyender.skyblock.commands.CommandStructure;
import fr.eazyender.skyblock.commands.CommandTempoInvite;
import fr.eazyender.skyblock.commands.CommandTrade;
import fr.eazyender.skyblock.commands.CommandeBiomes;
import fr.eazyender.skyblock.commands.CommandeChatCanals;
import fr.eazyender.skyblock.commands.CommandeDonate;
import fr.eazyender.skyblock.commands.CommandeTpa;
import fr.eazyender.skyblock.event.EntityInteract;
import fr.eazyender.skyblock.event.PlayerBreakBlock;
import fr.eazyender.skyblock.event.PlayerInteract;
import fr.eazyender.skyblock.event.PlayerJoin;
import fr.eazyender.skyblock.event.PlayerMove;
import fr.eazyender.skyblock.event.PlayerPerformCommand;
import fr.eazyender.skyblock.event.PlayerQuit;
import fr.eazyender.skyblock.event.PlayerRespawn;
import fr.eazyender.skyblock.gui.GuiBiome;
import fr.eazyender.skyblock.gui.GuiCustomCrafting;
import fr.eazyender.skyblock.gui.GuiIsland;
import fr.eazyender.skyblock.gui.GuiMinions;
import fr.eazyender.skyblock.gui.GuiShop;
import fr.eazyender.skyblock.gui.GuiTrade;
import fr.eazyender.skyblock.island.IslandManager;
import fr.eazyender.skyblock.items.GemFire;
import fr.eazyender.skyblock.player.CraftCustom;
import fr.eazyender.skyblock.player.PlayerActionBar;
import fr.eazyender.skyblock.player.PlayerEconomy;
import fr.eazyender.skyblock.player.PlayerStats;
import fr.eazyender.skyblock.utils.GradesAChallengesUtils;
import fr.eazyender.skyblock.world.SkyBlockGen;
import net.minecraft.server.v1_14_R1.WorldType;



public class SkyblockMain extends JavaPlugin 
{
	
		public String worldName = "world_skyblock";
	  
	  public World world;
	  
	  private static SkyblockMain skyblock;
	
	@Override
	public void onEnable() 
	{
		
		skyblock = this;
		
		/* Commande permettant de mettre une annonce en rouge */
		getCommand("annonce").setExecutor(new CommandAno());
		/* Commande permettant de save/load une structure */
		getCommand("structure").setExecutor(new CommandStructure());
		/* Commande pour aller au spawn*/
		getCommand("spawn").setExecutor(new CommandSpawn());
		/* Commande pour ouvrir la boutique */
		getCommand("shop").setExecutor(new CommandShop());
		/* Commande pour les admins pour se give de la money */
		getCommand("money").setExecutor(new CommandMoney());
		/* Commande pour les hologrammes */
		getCommand("holo").setExecutor(new CommandHologrammes());
		/* Commande pour les trades */
		getCommand("trade").setExecutor(new CommandTrade());
		/* Commande pour les minions */
		getCommand("minions").setExecutor(new CommandMinions());
		/* Commande pour afficher l'aide */
		getCommand("aide").setExecutor(new CommandCHelp());
		/* Commande pour afficher l'HDV */
		//getCommand("hdv").setExecutor(new CommandHdv());
		/* Commande pour donner de l'argent */
		getCommand("don").setExecutor(new CommandeDonate());
		/* Commande pour se tpa */
		getCommand("tpa").setExecutor(new CommandeTpa());
		/* Commande pour les canaux de tchat */
		getCommand("canal").setExecutor(new CommandeChatCanals());
		/* Commande pour changer le biome d'une zone */
		getCommand("biome").setExecutor(new CommandeBiomes());
		/* Commande pour se give les items customs */
		getCommand("customgive").setExecutor(new CommandCustomGive());
		
		getCommand("invite").setExecutor(new CommandTempoInvite());
		
		PluginManager pm = getServer().getPluginManager();
		
		/* Les events : */
		pm.registerEvents(new GuiBiome(), this);	
		pm.registerEvents(new GuiIsland(), this);	
		pm.registerEvents(new GuiMinions(), this);	
		pm.registerEvents(new GuiShop(), this);
		pm.registerEvents(new GuiTrade(), this);
		pm.registerEvents(new GuiCustomCrafting(), this);
		pm.registerEvents(new MainEventsHandler(), this);
		pm.registerEvents(new MainScoreboard(this), this);
		makeWorld();
		
		IslandManager im = new IslandManager();
		PlayerEconomy pe = new PlayerEconomy();
		PlayerStats ps = new PlayerStats();
		
		GradesAChallengesUtils.initClass();
		
		/* Commande pour les levels */
		getCommand("level").setExecutor(new CommandLevel());
		/* Commande permettant d'accéder à tt les options de son île */
		getCommand("island").setExecutor(new CommandIsland());
	     //registerPermissions();
	      registerListeners();
	      registerPermissions();
	      PlayerActionBar.initBar();
	     // MainScoreboard.createScoreboard();
	      
	      CraftCustom cc = new CraftCustom();
	      cc.initCraft();
	
	}
	
	@Override
	public void onDisable() 
	{
	
		 IslandManager.getIslandManager().onDisable();
		 PlayerEconomy.getPlayerEconomy().onDisable();
		 PlayerStats.getPlayerStats().onDisable();
		 
	}
	
	 private void registerPermissions() {
		    PluginManager pm = Bukkit.getPluginManager();
		    Permission p = new Permission("skyblock.player");
		    p.setDefault(PermissionDefault.TRUE);
		    pm.addPermission(p);
		  }
	  
	  private void registerListeners() {
	    PluginManager pm = Bukkit.getPluginManager();
	    pm.registerEvents(new EntityInteract(), this);
	    pm.registerEvents(new PlayerJoin(), this);
	    pm.registerEvents(new PlayerQuit(), this);
	    pm.registerEvents(new PlayerMove(), this);
	    pm.registerEvents(new PlayerRespawn(), this);
	    pm.registerEvents(new PlayerInteract(), this);
	    pm.registerEvents(new PlayerPerformCommand(), this);
	    pm.registerEvents(new PlayerBreakBlock(), this);
	    
	    pm.registerEvents(new GemFire(), this);
	  }
	
	private void makeWorld() {
	    if (Bukkit.getWorld(this.worldName) == null) {
	      sendMessage("Generating world: '" + this.worldName + "'");
	      WorldCreator wc = new WorldCreator(this.worldName);
	      wc.generateStructures(false);
	      wc.generator(new SkyBlockGen());
	      Bukkit.getServer().createWorld(wc);
	    } 
	    sendMessage("Loaded world: '" + this.worldName + "'");
	    this.world = Bukkit.getWorld(this.worldName);
	    this.world.setDifficulty(Difficulty.NORMAL);
	  }
	
	public static SkyblockMain getSkyBlock() { return skyblock; }


	  
	 public void sendMessage(String message) { Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "(Skyeim) " + ChatColor.RED + message); }


}
