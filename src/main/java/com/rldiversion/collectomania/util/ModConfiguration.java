package com.rldiversion.collectomania.util;

import java.io.File;

import com.rldiversion.collectomania.Main;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfiguration
{
    public static Configuration config;
    public static int ENTITY_BEETLE_ID;
    public static int GUI_RESEARCH_TABLE_ID;
    public static int ENTITY_LADYBUG_ID;

    public static boolean All_Bugs_Spawn;
    public static boolean Beetle_Spawn;
    public static boolean Ladybug_Spawn;

    public static void init(File file)
    {
        config = new Configuration(file);

        String category;

        category = "Entity IDs";
        config.addCustomCategoryComment(category, "Set the ID's for the entities to ensure that they don't clash with other mod's ids");
        ENTITY_BEETLE_ID = config.getInt("ENTITY_BEETLE_ID", category, 201, 201, 999, "Entity IDs below 201 are used by Minecraft");
        ENTITY_LADYBUG_ID = config.getInt("ENTITY_LADYBUG_ID", category, 202, 201, 999, "Entity IDs below 201 are used by Minecraft");

        category = "GUI IDs";
        config.addCustomCategoryComment(category, "Set the ID's for the GUI's to ensure that they don't clash with other mod's ids");
        GUI_RESEARCH_TABLE_ID = config.getInt("GUI_RESEARCH_TABLE_ID", category, 1, 1, 999, "Set the ID for the Research Table");

        category = "Bug Spawning";
        config.addCustomCategoryComment(category, "Set if entities from this mod should spawn or not");
        All_Bugs_Spawn = config.getBoolean("Spawn All bugs: ", category, true, "Spawn all entities of this mod or not");
        Beetle_Spawn = config.getBoolean("Spawn Beetles: ", category, true, "Spawn beetles or not");
        Ladybug_Spawn = config.getBoolean("Spawn Ladybugs: ", category, true, "Spawn ladybugs or not");

        config.save();
    }

    public static void registerConfig(FMLPreInitializationEvent event)
    {
        Main.config = new File(event.getModConfigurationDirectory() + "/");
        init(new File(Main.config.getPath(), Reference.MODID + ".cfg"));
    }
}
