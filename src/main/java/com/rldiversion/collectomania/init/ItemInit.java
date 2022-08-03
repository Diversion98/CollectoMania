package com.rldiversion.collectomania.init;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.objects.items.ItemBase;
import com.rldiversion.collectomania.objects.tools.ToolNet;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item.ToolMaterial TOOL_BUGNET = EnumHelper.addToolMaterial("tool_net", 0, 59, 2.0F, 0.0F, 15);

    //items
    //public static final Item BUG_BEETLE = new ItemBase("bug_beetle");

    //tools
    public static final Item BUGNET = new ToolNet("tool_bugnet", TOOL_BUGNET, Main.COLLECTOMANIA);
}
