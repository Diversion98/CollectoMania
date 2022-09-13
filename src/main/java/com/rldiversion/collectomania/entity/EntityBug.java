package com.rldiversion.collectomania.entity;

import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.world.World;

public class EntityBug extends EntityAmbientCreature {
    public EntityBug(World worldIn)
    {
        super(worldIn);
        this.setSize(0.5F, 0.9F);
    }
}
