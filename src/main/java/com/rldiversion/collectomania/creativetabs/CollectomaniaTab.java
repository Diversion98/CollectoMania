package com.rldiversion.collectomania.creativetabs;

import com.rldiversion.collectomania.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CollectomaniaTab extends CreativeTabs{
    public CollectomaniaTab()
    {
        super("collectomania");
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(BlockInit.RESEARCH_TABLE);
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}
