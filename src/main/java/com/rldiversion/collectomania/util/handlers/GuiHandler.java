package com.rldiversion.collectomania.util.handlers;

import com.rldiversion.collectomania.objects.blocks.container.ContainerResearchTable;
import com.rldiversion.collectomania.objects.blocks.guis.GUIResearchTable;
import com.rldiversion.collectomania.objects.blocks.tileentities.TileEntityResearchTable;
import com.rldiversion.collectomania.util.ModConfiguration;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

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