package com.rldiversion.collectomania.objects.tools;

import java.util.Objects;
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

import javax.annotation.Nonnull;

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
    @Override
    public boolean itemInteractionForEntity(@Nonnull ItemStack stack, @Nonnull EntityPlayer playerIn, @Nonnull EntityLivingBase target, @Nonnull EnumHand hand) {
        if(!target.getEntityWorld().isRemote && target instanceof EntityBug) {
            String name = "collectomania:bug_" + target.getDisplayName().getUnformattedText();
            ItemStack item = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(name))));
            target.setDead();
            playerIn.inventory.addItemStackToInventory(item);
            return true;
        }
        playerIn.swingArm(hand);
        return false;
    }

    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
