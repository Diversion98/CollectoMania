package com.rldiversion.collectomania.rendering;

import com.rldiversion.collectomania.entity.EntityButterflyRedAdmiral;
import com.rldiversion.collectomania.rendering.models.entities.ModelButterflyRedAdmiral;
import com.rldiversion.collectomania.util.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderButterflyRedAdmiral extends RenderLiving<EntityButterflyRedAdmiral>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/bug_butterfly_red_admiral.png");

    public RenderButterflyRedAdmiral(RenderManager manager)
    {
        super(manager, new ModelButterflyRedAdmiral(), 0.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityButterflyRedAdmiral entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityButterflyRedAdmiral entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
