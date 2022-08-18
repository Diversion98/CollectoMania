package com.rldiversion.collectomania.util.handlers;

import com.rldiversion.collectomania.objects.blocks.tileentities.TileEntityResearchTable;
import com.rldiversion.collectomania.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityResearchTable.class, new ResourceLocation(Reference.MODID + ":research_table"));
    }
}
