package com.rldiversion.collectomania.objects.blocks.container;

import com.rldiversion.collectomania.objects.blocks.recipes.ResearchTableRecipes;
import com.rldiversion.collectomania.objects.blocks.tileentities.TileEntityResearchTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerResearchTable extends Container
{
    private final TileEntityResearchTable tileEntity;
    private int researchTime;
    private int totalResearchTime;

    public ContainerResearchTable(InventoryPlayer player, TileEntityResearchTable tileEntity)
    {
        this.tileEntity = tileEntity;
        IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotItemHandler(handler, 0, 48, 35));
        this.addSlotToContainer(new SlotItemHandler(handler, 1, 124, 35));

        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {
                this.addSlotToContainer(new Slot(player, x + y*9 + 9, 8 + x*18, 84 + y*18));
            }
        }

        for(int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(player, x, 8 + x*18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileEntity.isUsableByPlayer(playerIn);
    }

    @Override
    public void updateProgressBar(int id, int data)
    {
        this.tileEntity.setField(id, data);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (IContainerListener listener : this.listeners) {
            if(this.researchTime != this.tileEntity.getField(0)) listener.sendWindowProperty(this, 0, this.tileEntity.getField(0));
            if(this.totalResearchTime != this.tileEntity.getField(1)) listener.sendWindowProperty(this, 1, this.tileEntity.getField(1));
        }

        this.researchTime = this.tileEntity.getField(0);
        this.totalResearchTime = this.tileEntity.getField(1);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();

            if(index != 1 && index != 0)
            {
                Slot slot1 = this.inventorySlots.get(index + 1);

                if(!ResearchTableRecipes.getInstance().getResearchResult(slot1.getStack()).isEmpty())
                {
                    if(!this.mergeItemStack(stack1, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                    else if(index < 31)
                    {
                        if(!this.mergeItemStack(stack1, 31, 37, false)) return ItemStack.EMPTY;
                    }
                    else if(index < 37 && !this.mergeItemStack(stack1, 2, 31, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }
            else if(!this.mergeItemStack(stack1, 2, 37, false))
            {
                return ItemStack.EMPTY;
            }
            if(stack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();

            }
            if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
            slot.onTake(playerIn, stack1);
        }
        return stack;
    }
}
