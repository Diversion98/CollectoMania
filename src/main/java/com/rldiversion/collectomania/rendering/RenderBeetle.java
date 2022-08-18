package com.rldiversion.collectomania.rendering;

import com.rldiversion.collectomania.entity.EntityBeetle;
import com.rldiversion.collectomania.rendering.models.entities.ModelBeetle;
import com.rldiversion.collectomania.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBeetle extends RenderLiving<EntityBeetle>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/bug_beetle.png");

    public RenderBeetle(RenderManager manager)
    {
        super(manager, new ModelBeetle(), 0.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBeetle entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityBeetle entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
