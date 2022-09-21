package com.rldiversion.collectomania.init;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.objects.items.ItemBase;
import com.rldiversion.collectomania.objects.items.food.ItemCustomFood;
import com.rldiversion.collectomania.objects.tools.ToolNet;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<>();

    //*********************************
    //              TOOLS
    //*********************************

    public static final Item.ToolMaterial TOOL_BUGNET = EnumHelper.addToolMaterial("tool_net", 0, 59, 2.0F, 0.0F, 15);
    public static final Item.ToolMaterial TOOL_BRUSH = EnumHelper.addToolMaterial("tool_net", 0, 59, 2.0F, 0.0F, 15);

    //public static final Item BUGNET = new ToolNet("tool_name", TOOL_NAME, Main.COLLECTOMANIA);
    public static final Item BUGNET = new ToolNet("tool_bugnet", TOOL_BUGNET, Main.COLLECTOMANIA);
    public static final Item BRUSH = new ToolNet("tool_brush", TOOL_BRUSH, Main.COLLECTOMANIA);

    //*********************************
    //              BUGS
    //*********************************

    public static final Item BUG_BEETLE = new ItemBase("bug_beetle", Main.COLLECTOMANIA);
    public static final Item COOKED_BEETLE = new ItemCustomFood("cooked_beetle", 4, false);

    public static final Item BUG_LADYBUG = new ItemBase("bug_ladybug", Main.COLLECTOMANIA);
    public static final Item COOKED_LADYBUG = new ItemCustomFood("cooked_ladybug", 4, false);

    public static final Item BUG_BUTTERFLY = new ItemBase("bug_butterfly_red_admiral", Main.COLLECTOMANIA);
    public static final Item COOKED_BUTTERFLY = new ItemCustomFood("cooked_butterfly_red_admiral", 4, false);

    public static final Item BUG_FLY = new ItemBase("bug_house_fly", Main.COLLECTOMANIA);
    public static final Item COOKED_FLY = new ItemCustomFood("cooked_house_fly", 4, false);

    public static final Item BUG_ROACH = new ItemBase("bug_roach", Main.COLLECTOMANIA);
    public static final Item COOKED_ROACH = new ItemCustomFood("cooked_roach", 4, false);

    public static final Item BUG_WORM = new ItemBase("bug_worm", Main.COLLECTOMANIA);
    public static final Item COOKED_WORM = new ItemCustomFood("cooked_worm", 4, false);
}
