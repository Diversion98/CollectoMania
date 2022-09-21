package com.rldiversion.collectomania.recipes;

import com.rldiversion.collectomania.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingRecipes {
    public static void init()
    {
        GameRegistry.addSmelting(ItemInit.BUG_BEETLE, new ItemStack(ItemInit.COOKED_BEETLE), 0.5F);
        GameRegistry.addSmelting(ItemInit.BUG_LADYBUG, new ItemStack(ItemInit.COOKED_LADYBUG), 0.5F);
        GameRegistry.addSmelting(ItemInit.BUG_BUTTERFLY, new ItemStack(ItemInit.COOKED_BUTTERFLY), 0.5F);
        GameRegistry.addSmelting(ItemInit.BUG_FLY, new ItemStack(ItemInit.COOKED_FLY), 0.5F);
        GameRegistry.addSmelting(ItemInit.BUG_ROACH, new ItemStack(ItemInit.COOKED_ROACH), 0.5F);
        GameRegistry.addSmelting(ItemInit.BUG_WORM, new ItemStack(ItemInit.COOKED_WORM), 0.5F);
    }
}
