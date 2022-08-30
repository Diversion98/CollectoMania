package com.rldiversion.collectomania.objects.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.entity.EntityBug;
import com.rldiversion.collectomania.init.ItemInit;
import com.rldiversion.collectomania.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ToolNet extends ItemTool implements IHasModel
{
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();
    private String name;

    public ToolNet(String name, ToolMaterial material, CreativeTabs tab)
    {
        super(material, EFFECTIVE_ON);

        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ItemInit.ITEMS.add(this);
    }
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target.getEntityWorld().isRemote) return false;
        if (target instanceof EntityPlayer || !target.isNonBoss() || !target.isEntityAlive()) return false;
        if(target instanceof EntityBug) {
            name = "collectomania:BUG_" + String.valueOf(target.getDisplayName().getUnformattedText()).toUpperCase();
            ItemStack item = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(name)));
            playerIn.swingArm(hand);
            target.setDead();
            playerIn.inventory.addItemStackToInventory(item);
            return true;
        }
        return false;
    }

    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
