package com.rldiversion.collectomania.objects.blocks.tileentities;

import com.rldiversion.collectomania.objects.blocks.BlockResearchTable;
import com.rldiversion.collectomania.objects.blocks.container.ContainerResearchTable;
import com.rldiversion.collectomania.recipes.ResearchTableRecipes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

public class TileEntityResearchTable extends TileEntityLockable implements ITickable, ISidedInventory
{
    private static final int[] slotsTop = Slots.INPUT_SLOT.slots();
    private static final int[] slotsBottom = Slots.OUTPUT_SLOT.slots();
    private final IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
    private final IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);

    private String researchCustomName;

    private NonNullList<ItemStack> researchItemStacks = NonNullList.withSize(2, ItemStack.EMPTY);
    private int researchTime = 0;
    private int totalResearchTime;
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
    public void setInventorySlotContents(int index, @NotNull ItemStack stack)
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
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
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
            if (!this.researchItemStacks.get(0).isEmpty() && this.canResearch())
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

        if (flag1)
        {
            this.markDirty();
        }
    }

    public void ResearchItem() {
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

    private boolean canResearch()
    {
        ItemStack input = this.researchItemStacks.get(0);
        ItemStack result = ResearchTableRecipes.getInstance().getResearchResult(input);
        ItemStack output = this.researchItemStacks.get(Slots.OUTPUT_SLOT.slots[0]);
        int totalAmount = output.getCount() + result.getCount();

        if (input.isEmpty() || result.isEmpty())
            return false;

        if (output.isEmpty() || output.isItemEqual(result))
            return true;

        else
            return (totalAmount <= this.getInventoryStackLimit() && totalAmount <= output.getMaxStackSize());
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
            return slotsBottom;
        }
        else
            return slotsTop;
    }

    @Override
    public boolean canInsertItem(int index, @Nonnull ItemStack itemStackIn, @Nonnull EnumFacing direction)
    {
        return isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, @Nonnull ItemStack stack, @Nonnull EnumFacing direction)
    {
        return Slots.OUTPUT_SLOT.contains(index);
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

    @Override
    public void clear()
    {
        this.researchItemStacks.clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T> T getCapability(@Nonnull net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else
                return (T) handlerTop;
        return super.getCapability(capability, facing);
    }

    public enum Slots {
        INPUT_SLOT(0), OUTPUT_SLOT(1);

        private final int[] slots;

        Slots(int... slots)
        {
            this.slots = slots;
        }

        public int[] slots()
        {
            return Arrays.copyOf(slots, slots.length);
        }

        public boolean contains(int i)
        {
            return ArrayUtils.contains(slots, i);
        }
    }

    //---------------------------------------------------------------------------------------
    //Handle Client/Server Syncing
    //sync process when notifyBlockUpdate is called
    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound compound = new NBTTagCompound();
        writeToNBT(compound);
        return new SPacketUpdateTileEntity(this.getPos(), 0, compound);
    }

    @Override
    public void onDataPacket(@Nonnull NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.getNbtCompound());
    }

    public void sendUpdate()
    {
        if (world != null)
        {
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 8);
            markDirty();
        }
    }
}
