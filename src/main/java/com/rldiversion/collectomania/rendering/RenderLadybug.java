package com.rldiversion.collectomania.rendering;

import com.rldiversion.collectomania.entity.EntityLadybug;
import com.rldiversion.collectomania.rendering.models.entities.ModelLadybug;
import com.rldiversion.collectomania.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLadybug extends RenderLiving<EntityLadybug>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/bug_ladybug.png");

    public RenderLadybug(RenderManager manager)
    {
        super(manager, new ModelLadybug(), 0.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLadybug entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityLadybug entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
