package com.rldiversion.collectomania.init;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.objects.items.ItemBase;
import com.rldiversion.collectomania.objects.tools.ToolNet;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<>();

    //tools
    //public static final Item.ToolMaterial TOOL_NAME = EnumHelper.addToolMaterial("tool_name", int harvestLevel, int maxUses, float efficiency, float damage, int enchantability);
    public static final Item.ToolMaterial TOOL_BUGNET = EnumHelper.addToolMaterial("tool_net", 0, 59, 2.0F, 0.0F, 15);
    public static final Item.ToolMaterial TOOL_BRUSH = EnumHelper.addToolMaterial("tool_net", 0, 59, 2.0F, 0.0F, 15);

    //public static final Item BUGNET = new ToolNet("tool_name", TOOL_NAME, Main.COLLECTOMANIA);
    public static final Item BUGNET = new ToolNet("tool_bugnet", TOOL_BUGNET, Main.COLLECTOMANIA);
    public static final Item BRUSH = new ToolNet("tool_brush", TOOL_BRUSH, Main.COLLECTOMANIA);

    //bugs
    //public static final Item BUG_NAME = new ItemBase("bug_name", Main.COLLECTOMANIA);
    public static final Item BUG_BEETLE = new ItemBase("bug_beetle", Main.COLLECTOMANIA);
    public static final Item BUG_LADYBUG = new ItemBase("bug_ladybug", Main.COLLECTOMANIA);

}
