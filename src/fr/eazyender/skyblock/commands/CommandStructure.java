package fr.eazyender.skyblock.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.entity.Player;

import fr.eazyender.skyblock.SkyblockMain;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.Blocks;
import net.minecraft.server.v1_14_R1.ChunkCoordIntPair;
import net.minecraft.server.v1_14_R1.DefinedStructure;
import net.minecraft.server.v1_14_R1.DefinedStructureInfo;
import net.minecraft.server.v1_14_R1.EnumBlockMirror;
import net.minecraft.server.v1_14_R1.EnumBlockRotation;
import net.minecraft.server.v1_14_R1.NBTCompressedStreamTools;
import net.minecraft.server.v1_14_R1.NBTTagCompound;
import net.minecraft.server.v1_14_R1.WorldServer;

public class CommandStructure implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) 
	{
		
	if(cmd.getName().equalsIgnoreCase("structure")) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			player.sendMessage("Usage : <save/load> <coo> <name>");
			
			if(args[0].equalsIgnoreCase("save")) {
				
				player.sendMessage("Usage : save <posXMin> <posYMin> <posZMin> <posXMax> <posYMax> <posZMax> <name>");
				
				if(args[1] != null && args[2] != null && args[3] != null && args[4] != null && args[5] != null && args[6] != null && args[7] != null) {
					
					double posX_min = Double.parseDouble(args[1]);
					double posY_min = Double.parseDouble(args[2]);
					double posZ_min = Double.parseDouble(args[3]);
					
					double posX_max = Double.parseDouble(args[4]);
					double posY_max = Double.parseDouble(args[5]);
					double posZ_max = Double.parseDouble(args[6]);
					
					Location[] location = {null,null};
					
					Location posMin = new Location(player.getWorld(), posX_min, posY_min, posZ_min);
					Location posMax = new Location(player.getWorld(), posX_max, posY_max, posZ_max);
					
					location[0] = posMin; location[1] = posMax;
					
					File rep = new File(SkyblockMain.getSkyBlock().getDataFolder(),args[7]);
					
					try {
						saveSingleStructure(createSingleStructure(location, sender.getName()),rep);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					player.sendMessage("§c[§4§lConsole§r§c]: La structure :§4§l"
							+ args[7] 
							+ "§r§c de coordonnées : §4§l" 
							+ posX_min + "§r§c,§4§l" + posY_min + "§r§c,§4§l" + posZ_min + " §r§cet §4§l" + posX_max + "§r§c,§4§l" + posY_max + "§r§c,§4§l" + posZ_max
							+ "§r§ca été sauvegardé avec succès.");
					
					
					
				}
				
			}
			else if(args[0].equalsIgnoreCase("load")) {
				
				player.sendMessage("Usage : load <posX> <posY> <posZ> <name>");
				
				if(args[1] != null && args[2] != null && args[3] != null && args[4] != null) {
					
					double posX = Double.parseDouble(args[1]);
					double posY = Double.parseDouble(args[2]);
					double posZ = Double.parseDouble(args[3]);
					
					Location posMin = new Location(player.getWorld(), posX, posY, posZ);
					
					File src = new File(SkyblockMain.getSkyBlock().getDataFolder(),args[4] + ".nbt");
					
					try {
						insertSingleStructure(loadSingleStructure(src),posMin,EnumBlockRotation.NONE);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					player.sendMessage("§c[§4§lConsole§r§c]: La structure :§4§l"
							+ args[4] 
							+ "§r§c a été crée aux coordonnées : §4§l" 
							+ posX + "§r§c,§4§l" + posY + "§r§c,§4§l" + posZ + " §r§cet §4§l");
					
				}
				
			}
			
			return true;
		}
	}
		
		return false;
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
	
	/**
	  * Saves a structure NBT file to a given destination file
	  * @param structure - The structure to be saved
	  * @param destination - The NBT file to be created
	  */
	public static void saveSingleStructure(DefinedStructure structure, File destination) throws FileNotFoundException, IOException {
	    NBTTagCompound fileTag = new NBTTagCompound();
	    fileTag = structure.a(fileTag);
	    NBTCompressedStreamTools.a(fileTag, new FileOutputStream(new File(destination + ".nbt")));
	}
	
	/**
	   * Creates a single structure of maximum 32x32x32 blocks.
	    * @param corners - The edges of the are (order doesn't matter)
	    * @param author - The listed author of the structure
	    * @return DefinedStructure - The new structure instance
	    */
	  
	  public static DefinedStructure createSingleStructure(Location[] corners, String author) {
		    if (corners.length != 2) throw new IllegalArgumentException("An area needs to be set up by exactly 2 opposite edges!");
		    Location[] normalized = normalizeEdges(corners[0], corners[1]); 
		    // ^^ This is juggling the coordinates, so the first is the Corner with lowest x, y, z and the second has the highest x, y, z.
		    WorldServer world = ((CraftWorld) normalized[0].getWorld()).getHandle();
		    int[] dimensions = getDimensions(normalized);
		    if (dimensions[0] > 32 || dimensions[1] > 32 || dimensions[2] > 32) throw new IllegalArgumentException("A single structure can only be 32x32x32!");
		    DefinedStructure structure = new DefinedStructure();
		    structure.a(world, new BlockPosition(normalized[0].getBlockX(), normalized[0].getBlockY(), normalized[0].getBlockZ()), new BlockPosition(dimensions[0], dimensions[1], dimensions[2]), true, Blocks.STRUCTURE_VOID);
		    structure.a(author); // may not be saved to file anymore since 1.13
		    return structure;
		}
	  
	  private static Location[] normalizeEdges(Location startBlock, Location endBlock) {
	      int xMin, xMax, yMin, yMax, zMin, zMax;
	      if (startBlock.getBlockX() <= endBlock.getBlockX()) {
	          xMin = startBlock.getBlockX();
	          xMax = endBlock.getBlockX();
	      } else {
	          xMin = endBlock.getBlockX();
	          xMax = startBlock.getBlockX();
	      }
	      if (startBlock.getBlockY() <= endBlock.getBlockY()) {
	          yMin = startBlock.getBlockY();
	          yMax = endBlock.getBlockY();
	      } else {
	          yMin = endBlock.getBlockY();
	          yMax = startBlock.getBlockY();
	      }
	      if (startBlock.getBlockZ() <= endBlock.getBlockZ()) {
	          zMin = startBlock.getBlockZ();
	          zMax = endBlock.getBlockZ();
	      } else {
	          zMin = endBlock.getBlockZ();
	          zMax = startBlock.getBlockZ();
	      }
	      return new Location[]{new Location(startBlock.getWorld(), xMin, yMin, zMin), new Location(startBlock.getWorld(), xMax, yMax, zMax)};
	  }
	  
	  private static int[] getDimensions(Location[] corners) {
	      if (corners.length != 2)
	          throw new IllegalArgumentException("An area needs to be set up by exactly 2 opposite edges!");
	      return new int[]{corners[1].getBlockX() - corners[0].getBlockX() + 1, corners[1].getBlockY() - corners[0].getBlockY() + 1, corners[1].getBlockZ() - corners[0].getBlockZ() + 1};
	  }

}
