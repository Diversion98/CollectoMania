package com.rldiversion.collectomania;

import com.rldiversion.collectomania.creativetabs.CollectomaniaTab;
import com.rldiversion.collectomania.entity.EntityBeetle;
import com.rldiversion.collectomania.entity.EntityLadybug;
import com.rldiversion.collectomania.proxy.CommonProxy;
import com.rldiversion.collectomania.util.Reference;
import com.rldiversion.collectomania.util.handlers.RegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.io.File;
import java.util.Set;

import static com.rldiversion.collectomania.util.ModConfiguration.*;

@Mod(
        modid = Reference.MODID,
        name = Reference.NAME,
        version = Reference.VERSION
)
public class Main {
    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
    public static CommonProxy proxy;

    public static final CreativeTabs COLLECTOMANIA = new CollectomaniaTab();
    public static File config;
    static { FluidRegistry.enableUniversalBucket(); }

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        RegistryHandler.preInitRegistries(event);
    }

    @EventHandler
    public static void init(FMLInitializationEvent event)
    {
        RegistryHandler.initRegistries(event);
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
        RegistryHandler.postInitRegistries(event);
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event)
    {
        RegistryHandler.serverRegistries(event);

        Set<Biome> biomeList = BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST);
        Biome[] biomes = biomeList.toArray(new Biome[biomeList.size()]);

        if(All_Bugs_Spawn) {
            if(Beetle_Spawn) EntityRegistry.addSpawn(EntityBeetle.class, 10, 8, 12, EnumCreatureType.AMBIENT, biomes);
            if(Ladybug_Spawn) EntityRegistry.addSpawn(EntityLadybug.class, 10, 8, 12, EnumCreatureType.AMBIENT, biomes);
        }
    }
}
