package com.rldiversion.collectomania.util.handlers;

import com.rldiversion.collectomania.entity.EntityBeetle;
import com.rldiversion.collectomania.entity.EntityLadybug;
import com.rldiversion.collectomania.rendering.RenderBeetle;
import com.rldiversion.collectomania.rendering.RenderLadybug;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
    public static void registerEntityRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, new IRenderFactory<EntityBeetle>()
        {
            @Override
            public Render<? super EntityBeetle> createRenderFor(RenderManager manager)
            {
                return new RenderBeetle(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityLadybug.class, new IRenderFactory<EntityLadybug>()
        {
            @Override
            public Render<? super EntityLadybug> createRenderFor(RenderManager manager)
            {
                return new RenderLadybug(manager);
            }
        });
    }
}
