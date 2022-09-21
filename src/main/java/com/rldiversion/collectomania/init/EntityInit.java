package com.rldiversion.collectomania.init;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.entity.*;
import com.rldiversion.collectomania.util.ModConfiguration;
import com.rldiversion.collectomania.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import static net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity;

public class EntityInit
{
    public static void registerEntities()
    {
        //registerEntity("name_of_bug", EntityName.class, ModConfiguration.ENTITY_NAME_ID, 50, color spawn egg 1, color spawn egg 2);
        registerEntity("beetle", EntityBeetle.class, ModConfiguration.ENTITY_BEETLE_ID, 50, 5059661, 0);
        registerEntity("ladybug", EntityLadybug.class, ModConfiguration.ENTITY_LADYBUG_ID, 50, 16711680, 0);
        registerEntity("butterfly_red_admiral", EntityButterflyRedAdmiral.class, ModConfiguration.ENTITY_BUTTERFLY_ID, 50, 12204575, 7092767);
        registerEntity("house_fly", EntityHouseFly.class, ModConfiguration.ENTITY_FLY_ID, 50, 5059661, 16711680);
        registerEntity("roach", EntityRoach.class, ModConfiguration.ENTITY_ROACH_ID, 50, 7092767, 0);
        registerEntity("worm", EntityWorm.class, ModConfiguration.ENTITY_WORM_ID, 50, 8540476, 0);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
    {
        registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
    }
}
