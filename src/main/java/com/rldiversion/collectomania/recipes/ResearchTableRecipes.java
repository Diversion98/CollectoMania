package com.rldiversion.collectomania.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import com.rldiversion.collectomania.init.BlockInit;
import com.rldiversion.collectomania.init.ItemInit;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ResearchTableRecipes {
    private static final ResearchTableRecipes INSTANCE = new ResearchTableRecipes();
    private final Map<ItemStack, ItemStack> researchList =  Maps.newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.newHashMap();
    private final Map<ItemStack, Integer> researchTimeList = Maps.newHashMap();

    public static ResearchTableRecipes getInstance()
    {
        return INSTANCE;
    }

    //add research recipes
    private ResearchTableRecipes()
    {
        //addResearchRecipe(input, output, xp, time to research)
        addResearchRecipe(new ItemStack(ItemInit.BUG_BEETLE), new ItemStack(BlockInit.RESEARCH_TABLE), 5.0F, 200);
        addResearchRecipe(new ItemStack(ItemInit.BUG_LADYBUG), new ItemStack(BlockInit.RESEARCH_TABLE), 10.0F, 100);
    }


    private void addResearchRecipe(@Nonnull ItemStack input, @Nonnull ItemStack result, float experience, int totalResearchTime)
    {
        if(getResearchResult(input) != ItemStack.EMPTY) return;
        this.researchList.put(input, result);
        this.experienceList.put(result, experience);
        this.researchTimeList.put(input, totalResearchTime);
    }

    public ItemStack getResearchResult(ItemStack input1)
    {
        for (Entry<ItemStack, ItemStack> entry : this.researchList.entrySet())
        {
            if(this.compareItemStacks(input1, entry.getKey())) {
                return entry.getValue();
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public float getSmeltingExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }
        return 0.0F;
    }

    public float getResearchTime(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Entry<ItemStack, Integer> entry : this.researchTimeList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }
        return 0;
    }
}
