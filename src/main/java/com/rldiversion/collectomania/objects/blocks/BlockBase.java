package com.rldiversion.collectomania.objects.blocks;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.init.BlockInit;
import com.rldiversion.collectomania.init.ItemInit;
import com.rldiversion.collectomania.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel
{
    public BlockBase(String name, Material material, CreativeTabs tab)
    {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public void registerModels()
    {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
