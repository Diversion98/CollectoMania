package com.rldiversion.collectomania.util.handlers;

import com.rldiversion.collectomania.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {
    public static final ResourceLocation BEETLE = LootTableList.register(new ResourceLocation(Reference.MODID, "beetle"));
}
