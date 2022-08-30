package com.rldiversion.collectomania.objects.blocks.tileentities;

import com.rldiversion.collectomania.objects.blocks.BlockResearchTable;
import com.rldiversion.collectomania.objects.blocks.recipes.ResearchTableRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityResearchTable extends TileEntity implements ITickable
{
    int tick;
    public ItemStackHandler handler = new ItemStackHandler(2);
    private String customName;
    public int researchTime;
    private ItemStack researching = ItemStack.EMPTY;

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
        return super.getCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setTag("Inventory", this.handler.serializeNBT());
        compound.setInteger("ResearchTime", researchTime);
        compound.setString("Name", getDisplayName().toString());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.researchTime = compound.getInteger("ResearchTime");
        if(compound.hasKey("Name")) this.customName = compound.getString("Name");
    }

    @Override
    public void update()
    {
        tick++;
        if(tick > 20) tick = 0;

        ItemStack[] inputs = new ItemStack[] {handler.getStackInSlot(0)};


        if(researchTime > 0)
        {
            researchTime++;
            BlockResearchTable.setState(true, world, pos);
            if(researchTime == 100)
            {
                if(handler.getStackInSlot(1).getCount() > 0)
                {
                    handler.getStackInSlot(1).grow(1);
                }
                else
                {
                    handler.insertItem(1, researching, false);
                }
                researching = ItemStack.EMPTY;
                researchTime = 0;
            }
        }
        else
        {
            if(!inputs[0].isEmpty())
            {
                ItemStack output = ResearchTableRecipes.getInstance().getResearchResult(inputs[0]);
                if(!output.isEmpty())
                {
                    researching = output;
                    researchTime++;
                    inputs[0].shrink(1);
                    handler.setStackInSlot(0, inputs[0]);
                }
            }
        }
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation("container.research_table");
    }

    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public int getField(int id)
    {
        switch(id)
        {
            case 0:
                return this.researchTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch(id)
        {
            case 0:
                this.researchTime = value;
                break;
        }
    }
}
