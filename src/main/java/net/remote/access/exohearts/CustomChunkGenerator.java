package net.remote.access.exohearts;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {
    // Remember this
    int currentHeight = 50;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        int CurrentHeight = 60;
        for (int X = 0; X < 16; X++)
            for (int Z = 0; Z < 16; Z++) {
                chunk.setBlock(X,CurrentHeight,Z, Material.AIR);
            }
        return chunk;
    }
}