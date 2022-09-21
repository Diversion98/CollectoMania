package com.rldiversion.collectomania.objects.blocks.container;

import com.rldiversion.collectomania.recipes.ResearchTableRecipes;
import com.rldiversion.collectomania.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nonnull;

public class SlotResearchTableOutput extends Slot {

    private final EntityPlayer player;

    public SlotResearchTableOutput(EntityPlayer player, IInventory inventory, int index, int xPos, int yPos)
    {
        super(inventory, index, xPos, yPos);
        this.player = player;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack)
    {
        return false;
    }

    @Nonnull
    @Override
    public ItemStack onTake(@Nonnull EntityPlayer thePlayer, @Nonnull ItemStack stack)
    {
        onCrafting(stack);
        return super.onTake(thePlayer, stack);
    }

    @Override
    protected void onCrafting(@Nonnull ItemStack output)
    {
        if (!player.world.isRemote)
        {
            int i = output.getCount();
            output.onCrafting(player.world, player, i);
            Utils.giveExperience(player, i * ResearchTableRecipes.getInstance().getResearchExperience(output));
        }
        FMLCommonHandler.instance().firePlayerSmeltedEvent(player, output);
    }
}
