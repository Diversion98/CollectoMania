package com.rldiversion.collectomania.objects.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import com.rldiversion.collectomania.init.BlockInit;
import com.rldiversion.collectomania.init.ItemInit;
import net.minecraft.item.ItemStack;

public class ResearchTableRecipes {
    private static final ResearchTableRecipes INSTANCE = new ResearchTableRecipes();
    private final Map<ItemStack, ItemStack> researchList = Maps.newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.newHashMap();

    public static ResearchTableRecipes getInstance()
    {
        return INSTANCE;
    }

    private ResearchTableRecipes()
    {
        addResearchRecipe(new ItemStack(ItemInit.BUG_BEETLE), new ItemStack(BlockInit.RESEARCH_TABLE), 5.0F);
        addResearchRecipe(new ItemStack(ItemInit.BUG_LADYBUG), new ItemStack(BlockInit.RESEARCH_TABLE), 5.0F);
    }


    public void addResearchRecipe(ItemStack input1, ItemStack result, float experience)
    {
        if(getResearchResult(input1) != ItemStack.EMPTY) return;
        this.researchList.put(input1, result);
        this.experienceList.put(result, Float.valueOf(experience));
    }

    public ItemStack getResearchResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.researchList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }

        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public float getResearchExperience(ItemStack stack)
    {
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if(this.compareItemStacks(stack, entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }
}
