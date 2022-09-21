package com.rldiversion.collectomania.util.handlers;

import com.rldiversion.collectomania.entity.*;
import com.rldiversion.collectomania.rendering.*;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
    public static void registerEntityRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, manager -> new RenderBeetle(manager));

        RenderingRegistry.registerEntityRenderingHandler(EntityLadybug.class, manager -> new RenderLadybug(manager));

        RenderingRegistry.registerEntityRenderingHandler(EntityButterflyRedAdmiral.class, manager -> new RenderButterflyRedAdmiral(manager));

        RenderingRegistry.registerEntityRenderingHandler(EntityHouseFly.class, manager -> new RenderHouseFly(manager));

        RenderingRegistry.registerEntityRenderingHandler(EntityRoach.class, manager -> new RenderRoach(manager));

        RenderingRegistry.registerEntityRenderingHandler(EntityWorm.class, manager -> new RenderWorm(manager));
    }
}
