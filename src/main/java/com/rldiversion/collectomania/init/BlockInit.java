package com.rldiversion.collectomania.init;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.objects.blocks.BlockResearchTable;
import com.rldiversion.collectomania.objects.blocks.Block3DModelBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final Block RESEARCH_TABLE = new BlockResearchTable("research_table");
}
