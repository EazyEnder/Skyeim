package fr.eazyender.skyblock.world;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class SkyBlockGen extends ChunkGenerator
{
	 public List<BlockPopulator> getDefaultPopulators(World world) {
         return Arrays.asList(new BlockPopulator[0]);
     }

     public boolean canSpawn(World world, int x, int z) {
         return true;
     }

     public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
         return new byte[32768];
     }
     
     @Override
     public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
       
         return createChunkData(world);
     }
   

}
