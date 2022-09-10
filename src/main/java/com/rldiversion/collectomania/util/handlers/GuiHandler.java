package com.rldiversion.collectomania.util.handlers;

import com.rldiversion.collectomania.objects.blocks.container.ContainerResearchTable;
import com.rldiversion.collectomania.objects.blocks.guis.GUIResearchTable;
import com.rldiversion.collectomania.objects.blocks.tileentities.TileEntityResearchTable;
import com.rldiversion.collectomania.util.ModConfiguration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.inventory.IInventory;

import javax.annotation.Nullable;
public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == ModConfiguration.GUI_RESEARCH_TABLE_ID) return new ContainerResearchTable(player.inventory, (TileEntityResearchTable)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == ModConfiguration.GUI_RESEARCH_TABLE_ID) return new GUIResearchTable(player.inventory, (TileEntityResearchTable)world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}