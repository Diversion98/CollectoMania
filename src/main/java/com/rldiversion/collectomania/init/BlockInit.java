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

    public static final Block BUG_BEETLE = new Block3DModelBase("bug_beetle", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_ANT = new Block3DModelBase("bug_ant", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_BUMBLE_BEE = new Block3DModelBase("bug_bumble_bee", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_BUTTERFLY_RED_ADMIRAL = new Block3DModelBase("bug_butterfly_red_admiral", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_HOUSE_FLY= new Block3DModelBase("bug_house_fly", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_LADYBUG = new Block3DModelBase("bug_ladybug", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_REDBACK_SPIDER = new Block3DModelBase("bug_redback_spider", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_SILVERFISH = new Block3DModelBase("bug_silverfish", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_SPIDER = new Block3DModelBase("bug_spider", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_TARANTULA_BLUE = new Block3DModelBase("bug_tarantula_blue", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_TARANTULA_ORANGE = new Block3DModelBase("bug_tarantula_orange", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_WORM= new Block3DModelBase("bug_worm", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
    public static final Block BUG_CENTEPEDE= new Block3DModelBase("bug_centepede", Material.CLOTH, Main.COLLECTOMANIA, 0.1875D, 0, 0.1875D, 0.8125D, 0.8125D, 0.8125D);




    public static final Block RESEARCH_TABLE = new BlockResearchTable("research_table");
}
