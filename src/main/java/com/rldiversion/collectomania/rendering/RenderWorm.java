package com.rldiversion.collectomania.rendering;

import com.rldiversion.collectomania.entity.EntityWorm;
import com.rldiversion.collectomania.rendering.models.entities.ModelWorm;
import com.rldiversion.collectomania.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWorm extends RenderLiving<EntityWorm>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/bug_worm.png");

    public RenderWorm(RenderManager manager)
    {
        super(manager, new ModelWorm(), 0.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWorm entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityWorm entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
