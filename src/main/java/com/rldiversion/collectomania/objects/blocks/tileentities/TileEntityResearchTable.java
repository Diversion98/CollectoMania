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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Objects;

public class TileEntityResearchTable extends TileEntity implements ITickable
{
    private String customName;
    private ItemStackHandler handler = new ItemStackHandler(2);
    private int researchTime;
    private int totalResearchTime = 100;
    private ItemStack researching = ItemStack.EMPTY;

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        else return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
        return super.getCapability(capability, facing);
    }

    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation("container.research_table");
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.researchTime = compound.getInteger("ResearchTime");
        this.totalResearchTime = compound.getInteger("totalResearchTime");
        if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("researchTime", (short)this.researchTime);
        compound.setInteger("totalResearchTime", (short)this.totalResearchTime);
        compound.setTag("Inventory", this.handler.serializeNBT());

        if(this.hasCustomName()) compound.setString("CustomName", this.customName);
        return compound;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isResearching(TileEntityResearchTable te)
    {
        return te.getField(0) > 0;
    }

    @Override
    public void update()
    {

        //declare input to itemstack
        ItemStack[] inputs = new ItemStack[] {handler.getStackInSlot(0)};

        //if research started, set blockstate true and start research count with additional checks
        if(researchTime >= 1)
        {
            researchTime++;

            //if research is done
            if(researchTime == totalResearchTime)
            {
                System.out.println("Research Done");
                //if output slot is not empty, add 1 to the stack example: if there is allready 1 bug, add another 1 instead of making a new one
                if(handler.getStackInSlot(1).getCount() > 0)
                {
                    System.out.println("allready has output");
                    handler.getStackInSlot(1).grow(1);
                }
                //else make a new item in output slot corresponding to the input slot research recipe
                else
                {
                    System.out.println("new output: " + researching.getDisplayName());
                    handler.insertItem(1, researching, false);
                }
                //reset research
                //researching = ItemStack.EMPTY;
                researchTime = 0;
                BlockResearchTable.setState(false, world, pos);
                System.out.println("Research resetted. researchTime: " + researchTime);
            }
        }
        //if researchTime equals 0
        else
        {
            //if input is not empty, set the output slot to the result of the recipe from input
            if(canResearch())
            {
                ItemStack result = ResearchTableRecipes.getInstance().getResearchResult(inputs[0]);
                //if output is not null, get the output result from the input, add 1 to researchTime so it starts the if at the start of this update method, decrease the input slot by 1
                System.out.println("result research: " + ResearchTableRecipes.getInstance().getResearchResult(inputs[0]).getDisplayName());
                if(!result.isEmpty())
                {
                    researching = result;
                    researchTime++;
                    BlockResearchTable.setState(true, world, pos);
                    System.out.println("Research started, researchTime: " + researchTime);
                    inputs[0].shrink(1);
                    handler.setStackInSlot(0, inputs[0]);
                }
            }
        }
    }

    private boolean canResearch()
    {
        if(!handler.getStackInSlot(0).isEmpty())
        {
            System.out.println("has item in input: " + handler.getStackInSlot(0).getDisplayName());
            ItemStack result = ResearchTableRecipes.getInstance().getResearchResult(this.handler.getStackInSlot(0));
            if(result.isEmpty()) {
                System.out.println("No Research Result: " + result.getDisplayName());
                return false;
            }
            ItemStack output = this.handler.getStackInSlot(1);
            if(output.isEmpty()) {
                System.out.println("output empty, can research");
                return true;
            }
            else if(output.isItemEqual(result)) {
                System.out.println("output not empty, can  research because of same output result");
                return true;
            }
            int res = output.getCount() + result.getCount();
            return res <= 64 && res <= output.getMaxStackSize();
        }
        System.out.println("Input empty: " + handler.getStackInSlot(0).getDisplayName());
        return false;
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
