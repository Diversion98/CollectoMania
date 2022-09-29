package com.rldiversion.collectomania.objects.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.init.ItemInit;
import com.rldiversion.collectomania.util.IHasModel;

public class ItemBase extends Item implements IHasModel {
    public ItemBase(String name, CreativeTabs tab) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ItemInit.ITEMS.add(this);
    }

    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
