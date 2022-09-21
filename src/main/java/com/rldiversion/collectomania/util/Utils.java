package com.rldiversion.collectomania.util;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;

public class Utils {
    public static void giveExperience(EntityPlayer player, float experience)
    {
        int intExp = (int) experience;
        float fractional = experience - intExp;
        if (fractional > 0.0F && (float) Math.random() < fractional)
        {
            intExp++;
        }
        while (intExp > 0)
        {
            int j = EntityXPOrb.getXPSplit(intExp);
            intExp -= j;
            player.world.spawnEntity(new EntityXPOrb(player.world, player.posX, player.posY + 0.5D, player.posZ + 0.5D, j));
        }

    }
}
