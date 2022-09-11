package com.rldiversion.collectomania.objects.blocks.tileentities;

import com.rldiversion.collectomania.objects.blocks.BlockResearchTable;
import com.rldiversion.collectomania.objects.blocks.container.ContainerResearchTable;
import com.rldiversion.collectomania.objects.blocks.recipes.ResearchTableRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;

public class TileEntityResearchTable extends TileEntityLockable implements ITickable, ISidedInventory
{
    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {1};
    private String researchCustomName;
    private NonNullList<ItemStack> researchItemStacks = NonNullList.withSize(2, ItemStack.EMPTY);
    private int researchTime;
    private int totalResearchTime = 100;
    public int getSizeInventory()
    {
        return this.researchItemStacks.size();
    }

    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.researchItemStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int index)
    {
        return this.researchItemStacks.get(index);
    }

    @Override
    @Nonnull
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.researchItemStacks, index, count);
    }

    @Override
    @Nonnull
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.researchItemStacks, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = this.researchItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.researchItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !flag)
        {
            this.totalResearchTime = this.getResearchTime(stack);
            this.researchTime = 0;
            this.markDirty();
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.hasCustomName() ? this.researchCustomName : "container.research_table";
    }

    public boolean hasCustomName()
    {
        return this.researchCustomName != null && !this.researchCustomName.isEmpty();
    }

    public void setCustomName(String customName)
    {
        this.researchCustomName = customName;
    }

    @Override
    @Nonnull
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation("container.research_table");
    }

    @Override
    public void readFromNBT(@Nonnull NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.researchItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.researchItemStacks);
        this.researchTime = compound.getInteger("researchTime");
        this.totalResearchTime = compound.getInteger("totalResearchTime");
        if (compound.hasKey("researchCustomName", 8))
        {
            this.researchCustomName = compound.getString("researchCustomName");
        }
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("researchTime", (short)this.researchTime);
        compound.setInteger("totalResearchTime", (short)this.totalResearchTime);
        ItemStackHelper.saveAllItems(compound, this.researchItemStacks);

        if (this.hasCustomName())
        {
            compound.setString("researchCustomName", this.researchCustomName);
        }
        return compound;
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void update()
    {
        boolean flag1 = false;

        if (!this.world.isRemote)
        {
            if (!this.researchItemStacks.get(0).isEmpty())
            {
                if (this.canResearch())
                {
                    ++this.researchTime;

                    if (this.researchTime == this.totalResearchTime)
                    {
                        this.researchTime = 0;
                        this.totalResearchTime = this.getResearchTime(this.researchItemStacks.get(0));
                        this.ResearchItem();
                        flag1 = true;
                        BlockResearchTable.setState(true, this.world, this.pos);
                    }
                }
                else
                {
                    this.researchTime = 0;
                }
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    public void ResearchItem() {
        if (this.canResearch()) {
            ItemStack itemstack = this.researchItemStacks.get(0);
            ItemStack itemstack1 = ResearchTableRecipes.getInstance().getResearchResult(itemstack);
            ItemStack itemstack2 = this.researchItemStacks.get(1);

            if (itemstack2.isEmpty())
            {
                this.researchItemStacks.set(1, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(itemstack1.getCount());
            }

            itemstack.shrink(1);
        }
    }

    private boolean canResearch()
    {
        if (this.world.isRemote) return true;
        if ((this.researchItemStacks.get(0)).isEmpty())
        {
            return false;
        }
        else
        {
            ItemStack itemstack = ResearchTableRecipes.getInstance().getResearchResult(this.researchItemStacks.get(0));

            if (itemstack.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack itemstack1 = this.researchItemStacks.get(1);

                if (itemstack1.isEmpty())
                {
                    return true;
                }
                else if (!itemstack1.isItemEqual(itemstack))
                {
                    return false;
                }
                else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize())  // Forge fix: make furnace respect stack sizes in furnace recipes
                {
                    return true;
                }
                else
                {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        }
    }

    public int getResearchTime(ItemStack stack)
    {
        return (int) ResearchTableRecipes.getInstance().getResearchTime(stack);
    }

    public boolean isUsableByPlayer(@Nonnull EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void openInventory(@Nonnull EntityPlayer player) {

    }

    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {

    }

    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack)
    {
        return index != 1;
    }

    @Override
    @Nonnull
    public int[] getSlotsForFace(@Nonnull EnumFacing side) {
        if (side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        else
            return SLOTS_TOP;
    }

    public boolean canInsertItem(int index,@Nonnull ItemStack itemStackIn,@Nonnull EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    public boolean canExtractItem(int index,@Nonnull ItemStack stack, @Nonnull EnumFacing direction)
    {
        return direction == EnumFacing.DOWN && index == 1;
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.researchTime;
            case 1:
                return this.totalResearchTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        if (value != 0) value = (int) ((value / ResearchTableRecipes.getInstance().getResearchTime(this.researchItemStacks.get(0))) * 100);
        switch (id)
        {
            case 0:
                this.researchTime = value;
                break;
            case 1:
                this.totalResearchTime = value;
        }
    }

    @Override
    @Nonnull
    public Container createContainer(@Nonnull InventoryPlayer playerInventory, @Nonnull EntityPlayer playerIn) {
        return new ContainerResearchTable(playerInventory, this);
    }

    @Nonnull
    public String getGuiID()
    {
        return "collectomania:research_table";
    }

    public int getFieldCount()
    {
        return 2;
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);

    @Override
    public void clear()
    {
        this.researchItemStacks.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    @javax.annotation.Nullable
    public <T> T getCapability(@Nonnull net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else
                return (T) handlerTop;
        return super.getCapability(capability, facing);
    }
}
