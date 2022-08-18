package com.rldiversion.collectomania.objects.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import com.rldiversion.collectomania.init.BlockInit;
import com.rldiversion.collectomania.init.ItemInit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ResearchTableRecipes {
    private static final ResearchTableRecipes INSTANCE = new ResearchTableRecipes();
    private final Table<ItemStack, ItemStack, ItemStack> researchList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    public static ResearchTableRecipes getInstance()
    {
        return INSTANCE;
    }

    private ResearchTableRecipes()
    {
        addResearchRecipe(new ItemStack(ItemInit.BUG_BEETLE), ItemStack.EMPTY ,new ItemStack(BlockInit.RESEARCH_TABLE), 5.0F);
    }

    public void addResearchRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience)
    {
        this.researchList.put(input1, input2, result);
        this.experienceList.put(result, Float.valueOf(experience));
    }

    public ItemStack getResearchResult(ItemStack input1)
    {
        for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.researchList.columnMap().entrySet())
        {
            if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
            {
                for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                {
                    return (ItemStack)ent.getValue();
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList()
    {
        return this.researchList;
    }

    public float getResearchExperience(ItemStack stack)
    {
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }
}
