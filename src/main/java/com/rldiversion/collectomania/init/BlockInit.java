package com.rldiversion.collectomania.init;

import com.rldiversion.collectomania.objects.blocks.BlockResearchTable;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<>();

    //public static final Block NAME_OF_BLOCK_IN_CAPS = new BlockResearchTable("name_of_block_lowercase");
    public static final Block RESEARCH_TABLE = new BlockResearchTable("research_table");
}
