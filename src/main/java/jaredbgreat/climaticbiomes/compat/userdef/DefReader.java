package jaredbgreat.climaticbiomes.compat.userdef;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.util.Logging;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

// FIXME: Change mods supported; 1.14.4 doesn't have the same mods availebale
public final class DefReader {
    private static DefReader vanilla;
    private static DefReader BoP;
    private static DefReader traverse;
    private static DefReader nt;
    private static DefReader abyssal;
    private static DefReader auxiliary;
    private static DefReader environs;
    private static DefReader pvj;
    private static DefReader byg;
    private static DefReader defiled;
    private static DefReader redwoods;
    private static DefReader zoestria;
    private static DefReader special;
    private static DefReader custom;
    private BiomeParser parser;


    public DefReader(IForgeRegistry reg, File dir, String sub) {
        parser = new BiomeParser(reg, dir, sub);
    }


    public void readBiomeDataList(BiomeList list, String filename) {
        parser.makeBiomeList(list, filename);
    }


    public static void readBiomeData(BiomeList list, String filename) {
        vanilla.parser.makeBiomeList(list, filename);
//        BoP.parser.makeBiomeList(list, filename);
//        traverse.parser.makeBiomeList(list, filename);
//        nt.parser.makeBiomeList(list, filename);
//        abyssal.parser.makeBiomeList(list, filename);
//        auxiliary.parser.makeBiomeList(list, filename);
//        environs.parser.makeBiomeList(list, filename);
//        pvj.parser.makeBiomeList(list, filename);
//        byg.parser.makeBiomeList(list, filename);
//        defiled.parser.makeBiomeList(list, filename);
//        redwoods.parser.makeBiomeList(list, filename);
//        zoestria.parser.makeBiomeList(list, filename);
        custom.parser.makeBiomeList(list, filename);
        special.parser.addSpecialBiomes(list, filename);
    }


    public static void init(IForgeRegistry reg, File dir) {
        vanilla = new DefReader(reg, dir, "Minecraft");
        BoP = new DefReader(reg, dir, "BiomeOPlenty");
        traverse = new DefReader(reg, dir, "Traverse");
        nt = new DefReader(reg, dir, "NovamTerram");
        abyssal = new DefReader(reg, dir, "AbyssalCraft");
        auxiliary = new DefReader(reg, dir, "AuxiliaryBiomes");
        environs = new DefReader(reg, dir, "Environs");
        pvj = new DefReader(reg, dir, "PVJ");
        byg = new DefReader(reg, dir, "BYG");
        defiled = new DefReader(reg, dir, "DefiledLands");
        redwoods = new DefReader(reg, dir, "Redwoods");
        zoestria = new DefReader(reg, dir, "Zoesteria");
        special = new DefReader(reg, dir, "special");
        custom = new DefReader(reg, dir, "custom");
    }


    public static void writeList(File confdir) {
        File file = new File(confdir.toString() + File.separator + "BiomeList.txt");
        List<Biome> biomes = (List<Biome>)ForgeRegistries.BIOMES.getValues();
        try {
            BufferedWriter fs = new BufferedWriter(new FileWriter(file));
            for(Biome biome : biomes) {
                fs.write(biome.getRegistryName().toString()
                        /*+ " (" + Biome.getIdForBiome(biome) + ")"*/);
                fs.newLine();
            }
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeWTList(File confdir) {
        File file = new File(confdir.toString() + File.separator + "WorldTypeList.txt");
        try {
            BufferedWriter fs = new BufferedWriter(new FileWriter(file));
            for(int i = 0; i < WorldType.WORLD_TYPES.length; i++) {
                if((WorldType.WORLD_TYPES[i] != null)) {
                    fs.write(WorldType.WORLD_TYPES[i].getName());
                    fs.newLine();
                }
            }
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
