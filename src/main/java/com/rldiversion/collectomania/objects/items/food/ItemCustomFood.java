package com.rldiversion.collectomania.objects.items.food;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.init.ItemInit;
import com.rldiversion.collectomania.util.IHasModel;
import net.minecraft.item.ItemFood;

public class ItemCustomFood extends ItemFood implements IHasModel
{
    public ItemCustomFood(String name, int amount, boolean isWolfFood){
        super(amount, isWolfFood);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(Main.COLLECTOMANIA);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
