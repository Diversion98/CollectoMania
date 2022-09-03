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

import java.util.Objects;

public class TileEntityResearchTable extends TileEntity implements ITickable
{
    int tick;
    public ItemStackHandler handler = new ItemStackHandler(2);
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
        compound.setString("Name", Objects.requireNonNull(getDisplayName()).toString());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.researchTime = compound.getInteger("ResearchTime");
        //String customName;
        //if(compound.hasKey("Name")) customName = compound.getString("Name");
    }

    @Override
    public void update()
    {
        tick++;
        if(tick > 19) tick = 0;

        //declare input to itemstack
        ItemStack[] inputs = new ItemStack[] {handler.getStackInSlot(0)};

        //if research started, set blockstate true and start research count with additional checks
        if(researchTime > 0)
        {
            researchTime++;
            BlockResearchTable.setState(true, world, pos);

            //if research is done
            if(researchTime == 100)
            {
                //if output slot is not empty, add 1 to the stack example: if there is allready 1 bug, add another 1 instead of making a new one
                if(handler.getStackInSlot(1).getCount() > 0)
                {
                    handler.getStackInSlot(1).grow(1);
                }
                //else make a new item in output slot corresponding to the input slot research recipe
                else
                {
                    handler.insertItem(1, researching, false);
                }
                //reset research
                researching = ItemStack.EMPTY;
                researchTime = 0;
            }
        }
        //if researchTime equals 0
        else
        {
            //if input is not empty, set the output slot to the result of the recipe from input
            if(!inputs[0].isEmpty())
            {
                ItemStack output = ResearchTableRecipes.getInstance().getResearchResult(/*inputs[0]*/);
                //if output is not empty, researching the same as the output, add 1 to researchTime so it starts the if at the start of this update method, decrease the input slot by 1
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
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public int getField(int id)
    {
        if (id == 0) {
            return this.researchTime;
        }
        return 0;
    }

    public void setField(int id, int value)
    {
        if (id == 0) {
            this.researchTime = value;
        }
    }
}
