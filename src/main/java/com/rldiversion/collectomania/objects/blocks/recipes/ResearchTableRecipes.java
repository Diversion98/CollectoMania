package com.rldiversion.collectomania.objects.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import com.rldiversion.collectomania.init.BlockInit;
import com.rldiversion.collectomania.init.ItemInit;
import net.minecraft.item.ItemStack;

public class ResearchTableRecipes {
    private static final ResearchTableRecipes INSTANCE = new ResearchTableRecipes();
    private final Map<ItemStack, ItemStack> researchList =  Maps.newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.newHashMap();

    public static ResearchTableRecipes getInstance()
    {
        return INSTANCE;
    }

    //add research recipes
    private ResearchTableRecipes()
    {
        //addResearchRecipe(input, output, xp, time to research)
        addResearchRecipe(new ItemStack(ItemInit.BUG_BEETLE), new ItemStack(BlockInit.RESEARCH_TABLE), 5.0F, 200);
        addResearchRecipe(new ItemStack(ItemInit.BUG_LADYBUG), new ItemStack(BlockInit.RESEARCH_TABLE), 5.0F, 100);
    }


    public void addResearchRecipe(ItemStack input1, ItemStack result, float experience, int timeToResearch)
    {
        if(getResearchResult(/*input1*/) != ItemStack.EMPTY) return;
        this.researchList.put(input1, result);
        this.experienceList.put(result, experience);
    }

    public ItemStack getResearchResult()
    {
        for (Entry<ItemStack, ItemStack> entry : this.researchList.entrySet())
        {
            return entry.getValue();
        }

        return ItemStack.EMPTY;
    }

    public float getResearchExperience()
    {
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            return entry.getValue();
        }
        return 0.0F;
    }
}
