package com.rldiversion.collectomania.util.handlers;

import com.rldiversion.collectomania.entity.*;
import com.rldiversion.collectomania.rendering.*;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
    public static void registerEntityRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, RenderBeetle::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityLadybug.class, RenderLadybug::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityButterflyRedAdmiral.class, RenderButterflyRedAdmiral::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityHouseFly.class, RenderHouseFly::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRoach.class, RenderRoach::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityWorm.class, RenderWorm::new);
    }
}
