package com.rldiversion.collectomania.rendering;

import com.rldiversion.collectomania.entity.EntityHouseFly;
import com.rldiversion.collectomania.rendering.models.entities.ModelHouseFly;
import com.rldiversion.collectomania.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHouseFly extends RenderLiving<EntityHouseFly>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/bug_house_fly.png");

    public RenderHouseFly(RenderManager manager)
    {
        super(manager, new ModelHouseFly(), 0.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityHouseFly entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityHouseFly entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
