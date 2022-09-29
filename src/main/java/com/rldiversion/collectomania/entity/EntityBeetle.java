package com.rldiversion.collectomania.entity;

import com.rldiversion.collectomania.util.handlers.LootTableHandler;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBeetle extends EntityBug {

    @Override
    public boolean canTriggerWalking()
    {
        return true;
    }

    protected float getSoundVolume()
    {
        return 0.0F;
    }

    public EntityBeetle(World worldIn)
    {
        super(worldIn);
        this.setSize(0.7F, 0.7F);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(2, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.10D);
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.BEETLE;
    }
/*
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundsHandler.ENTITY_CENTAUR_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return SoundsHandler.ENTITY_CENTAUR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundsHandler.ENTITY_CENTAUR_DEATH;
    }*/
}
