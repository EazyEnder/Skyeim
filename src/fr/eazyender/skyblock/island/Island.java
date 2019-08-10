package fr.eazyender.skyblock.island;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.eazyender.skyblock.SkyblockMain;

import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.Blocks;
import net.minecraft.server.v1_14_R1.ChunkCoordIntPair;
import net.minecraft.server.v1_14_R1.DefinedStructure;
import net.minecraft.server.v1_14_R1.DefinedStructureInfo;
import net.minecraft.server.v1_14_R1.EnumBlockMirror;
import net.minecraft.server.v1_14_R1.EnumBlockRotation;
import net.minecraft.server.v1_14_R1.NBTCompressedStreamTools;
import net.minecraft.server.v1_14_R1.WorldServer;

public class Island
{
  private String ownerName;
  public Location loc;
  public long level;
  private Location spawn;
  public int rayon;
  
  public Island(Player owner, Location loc, long level, int rayon, boolean gen) {
    this.ownerName = owner.getName();
    this.loc = loc;
    this.level = level;
    this.rayon = rayon;
    this.spawn = loc.clone().add(4.5D, 12.0D, 6.5D);
    this.spawn.setYaw(-180.0F);
    if (gen) {
      gen();
    }
  }
  
  	private void gen() {
			
			File src = new File(SkyblockMain.getSkyBlock().getDataFolder(),"default_island" + ".nbt");
			
			try {
				insertSingleStructure(loadSingleStructure(src),loc,EnumBlockRotation.NONE);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	  
  	}
  
  /**
	  * Pastes a single structure into the world
	  * @param structure - The structure to be pasted
	  * @param startEdge - The starting corner with the lowest x, y, z coordinates
	  * @param rotation - You may rotate the structure by 90 degrees steps
	  */
	public static void insertSingleStructure(DefinedStructure structure, Location startEdge, EnumBlockRotation rotation) {
	    WorldServer world = ((CraftWorld) startEdge.getWorld()).getHandle();
	    DefinedStructureInfo structInfo = new DefinedStructureInfo()
	    		.a(EnumBlockMirror.NONE)
	    		.a(rotation)
	    		.a(false)
	    		.a((ChunkCoordIntPair) null)
	    		.c(false)
	    		.a(new Random());
	    // false sets ignore entities to false (so it does NOT ignore them)
	    // 1.0f sets the amout of to be pasted blocks to 100%
	    // mirror & rortation are self explaining
	    // the block does the thing like the block does, which can be set in the ingame structure block GUI
	    // no idea at the moment, what the coord pair, the random and the second false does
	    // If you want to find out more about it, see net.minecraft.server.v1_13_R1.TileEntityStructure.c(boolean), there mojang calls the method and compare it with the ingame GUI
	    structure.a(world, new BlockPosition(startEdge.getBlockX(), startEdge.getBlockY(), startEdge.getBlockZ()), structInfo);
	}
	
	/**
	  * Loads a single structure NBT file and creates a new structure object instance. Works only for structures created with 1.13 or later!
	  * @param source - The structure file
	  * @return DefinedStructure - The new instance
	  */
	public static DefinedStructure loadSingleStructure(File source) throws FileNotFoundException, IOException {
	    DefinedStructure structure = new DefinedStructure();
	    structure.b(NBTCompressedStreamTools.a(new FileInputStream(source)));
	    return structure;
	}
  
  public boolean isAt(Location location) {
    if (location == null) {
      return false;
    }
    int x = Math.abs(location.getBlockX() - this.loc.getBlockX());
    int z = Math.abs(location.getBlockZ() - this.loc.getBlockZ());
    return (x < this.rayon && z < this.rayon);
  }

  
  public Player getPlayer() { return Bukkit.getPlayerExact(this.ownerName); }


  
  public Location getLocation() { return this.loc; }
  
  public long getLevel() { return this.level; }
  
  public void setLevel(long lvl) { this.level = lvl; }
  
  public int getRayon() { return this.rayon; }
  
  public void setRayon(int rayon) { this.rayon = rayon; }


  
  public Location getSpawnLocation() { return this.spawn; }
}
