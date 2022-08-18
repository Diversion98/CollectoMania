package com.rldiversion.collectomania.entity;

import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBug extends EntityAmbientCreature {
    private BlockPos spawnPosition;
    public EntityBug(World worldIn)
    {
        super(worldIn);
        this.setSize(0.5F, 0.9F);
    }
}
