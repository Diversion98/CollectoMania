package com.rldiversion.collectomania.init;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.entity.EntityBeetle;
import com.rldiversion.collectomania.util.ModConfiguration;
import com.rldiversion.collectomania.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{
    public static void registerEntities()
    {
        registerEntity("beetle", EntityBeetle.class, Main.COLLECTOMANIA, ModConfiguration.ENTITY_BEETLE_ID, 50, 5059661, 000000);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, CreativeTabs tab, int id, int range, int color1, int color2)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
    }
}
