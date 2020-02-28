package jaredbgreat.climaticbiomes.generation.biome;

import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

import java.util.StringTokenizer;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public class NoiseDoubleBiome extends AbstractTerminalSpecifier {
    private final long a, b;
    private final int boundary;


    public NoiseDoubleBiome(long a, int boundary, long b) {
        this.a = a;
        this.b = b;
        this.boundary = boundary;
    }


    public NoiseDoubleBiome(Biome a, int boundary, Biome b) {
        this.a = getIdForBiome(a);
        this.b = getIdForBiome(b);
        this.boundary = boundary;
    }


    public NoiseDoubleBiome(String a, int boundary, String b, IForgeRegistry biomeReg) {
        this.a = getBiomeNumber(a, biomeReg);
        this.b = getBiomeNumber(b, biomeReg);
        this.boundary = boundary;
    }


    @Override
    public long getBiome(ChunkTile tile) {
        if(tile.getNoise() < boundary) {
            return a;
        } else {
            return b;
        }
    }


    @Override
    public boolean isEmpty() {
        return ((a < 0) || (b < 0));
    }


    public void listOut() {
        System.err.println("Biome a: " + a + "; biome b: " + b);
    }

}