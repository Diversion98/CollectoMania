package com.rldiversion.collectomania.objects.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.entity.EntityBug;
import com.rldiversion.collectomania.init.BlockInit;
import com.rldiversion.collectomania.init.ItemInit;
import com.rldiversion.collectomania.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

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
