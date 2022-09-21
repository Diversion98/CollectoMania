package com.rldiversion.collectomania.rendering;

import com.rldiversion.collectomania.entity.EntityRoach;
import com.rldiversion.collectomania.rendering.models.entities.ModelRoach;
import com.rldiversion.collectomania.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRoach extends RenderLiving<EntityRoach>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/bug_roach.png");

    public RenderRoach(RenderManager manager)
    {
        super(manager, new ModelRoach(), 0.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityRoach entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityRoach entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
