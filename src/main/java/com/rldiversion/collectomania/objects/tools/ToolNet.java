package com.rldiversion.collectomania.objects.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.init.ItemInit;
import com.rldiversion.collectomania.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ToolNet extends ItemTool implements IHasModel
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();

    public ToolNet(String name, ToolMaterial material, CreativeTabs tab)
    {
        super(material, EFFECTIVE_ON);

        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ItemInit.ITEMS.add(this);
    }

    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
    }

    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
