package com.rldiversion.collectomania.objects.blocks.container;

import com.rldiversion.collectomania.objects.blocks.tileentities.TileEntityResearchTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ContainerResearchTable extends Container
{
    private final IInventory tileResearchTable;
    private int researchTime;
    private int totalResearchTime;

    public ContainerResearchTable(InventoryPlayer playerInventory, IInventory researchInventory)
    {
        this.tileResearchTable = researchInventory;
        this.addSlotToContainer(new Slot(researchInventory, 0, 48, 35));
        this.addSlotToContainer(new SlotResearchTableOutput(playerInventory.player, researchInventory, 1, 124, 35));

        //player inventory slots
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        //player hotbar slots
        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    public void addListener(@Nonnull IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileResearchTable);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (IContainerListener icontainerlistener : this.listeners) {
            if (this.researchTime != this.tileResearchTable.getField(0)) {
                icontainerlistener.sendWindowProperty(this, 0, this.tileResearchTable.getField(0));
            }

            if (this.totalResearchTime != this.tileResearchTable.getField(1)) {
                icontainerlistener.sendWindowProperty(this, 1, this.tileResearchTable.getField(1));
            }
        }

        this.researchTime = this.tileResearchTable.getField(0);
        this.totalResearchTime = this.tileResearchTable.getField(1);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileResearchTable.setField(id, data);
    }

    public boolean canInteractWith(@Nonnull EntityPlayer playerIn)
    {
        return this.tileResearchTable.isUsableByPlayer(playerIn);
    }

    @Override
    @Nonnull
    public ItemStack transferStackInSlot(@Nonnull EntityPlayer player, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();

            if (index < containerSlots)
            {
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true))
                    return ItemStack.EMPTY;

                if (TileEntityResearchTable.Slots.OUTPUT_SLOT.contains(index))
                    slot.onTake(player, itemstack);
            }
            else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false))
                return ItemStack.EMPTY;

            if (itemstack1.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }
}
