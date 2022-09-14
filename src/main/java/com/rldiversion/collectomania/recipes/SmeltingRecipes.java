package com.rldiversion.collectomania.recipes;

import com.rldiversion.collectomania.init.BlockInit;
import com.rldiversion.collectomania.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingRecipes {
    public static void init()
    {
        GameRegistry.addSmelting(ItemInit.BUG_BEETLE, new ItemStack(ItemInit.COOKED_BEETLE), 0.5F);
    }
}
