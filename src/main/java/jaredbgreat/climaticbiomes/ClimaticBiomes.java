package jaredbgreat.climaticbiomes;

import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.generation.ClimaticWorldType;
import jaredbgreat.climaticbiomes.proxy.ClientProxy;
import jaredbgreat.climaticbiomes.testing.TestWorldType;
import jaredbgreat.climaticbiomes.util.Externalizer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Info.ID)
public class ClimaticBiomes {
    private static final Logger LOGGER = LogManager.getLogger();
    private final File CONF_DIR;
    public static ClimaticBiomes instance;

    // TODO: Delete this when ready to create the Climatic World Type
    //public static final WorldType testWorlds = new TestWorldType();
    public static final WorldType climatic = new ClimaticWorldType();


    public ClimaticBiomes() {
        instance = this;
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::fowardToClientProxy);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        CONF_DIR = new File(FMLPaths.CONFIGDIR.get().toFile() + File.separator + Info.DIR);
    }


    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Starting Climatic Biomes");
        Externalizer externalizer = new Externalizer();
        externalizer.copyOut(CONF_DIR);
        DefReader.init(GameRegistry.findRegistry(Biome.class), CONF_DIR);
    }


    private void fowardToClientProxy(final FMLClientSetupEvent event) {
        ClientProxy.getProxy().acceptEventForward(event);
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {/*Do Nothing -- for now...*/}


    public static Logger getLogger() {
        return LOGGER;
    }


}
