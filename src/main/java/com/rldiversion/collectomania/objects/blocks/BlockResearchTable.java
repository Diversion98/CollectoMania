package com.rldiversion.collectomania.objects.blocks;

import com.rldiversion.collectomania.Main;
import com.rldiversion.collectomania.init.BlockInit;
import com.rldiversion.collectomania.objects.blocks.tileentities.BlockTileEntity;
import com.rldiversion.collectomania.objects.blocks.tileentities.TileEntityResearchTable;
import com.rldiversion.collectomania.util.ModConfiguration;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BlockResearchTable extends BlockTileEntity<TileEntityResearchTable>
{
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool RESEARCHING = PropertyBool.create("researching");
    private static boolean keepInventory;

    public BlockResearchTable(String name)
    {
        super(Material.WOOD, name, Main.COLLECTOMANIA);
        setSoundType(SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(RESEARCHING, false));
    }

    @Override
    public Class<TileEntityResearchTable> getTileEntityClass() {
        return TileEntityResearchTable.class;
    }

    @Override
    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState state,@Nonnull Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockInit.RESEARCH_TABLE);
    }

    @Override
    public boolean onBlockActivated(World worldIn,@Nonnull BlockPos pos,@Nonnull IBlockState state,@Nonnull EntityPlayer playerIn,@Nonnull EnumHand hand,@Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityResearchTable) {
                playerIn.openGui(Main.instance, ModConfiguration.GUI_RESEARCH_TABLE_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }

        }
        return true;
    }

    public void onBlockAdded(@Nonnull World worldIn,@Nonnull BlockPos pos,@Nonnull IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState state = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (active) worldIn.setBlockState(pos, BlockInit.RESEARCH_TABLE.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(RESEARCHING, true), 3);
        else worldIn.setBlockState(pos, BlockInit.RESEARCH_TABLE.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(RESEARCHING, false), 3);

        keepInventory = false;

        if(tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntityResearchTable createTileEntity(World world, IBlockState state)
    {
        return new TileEntityResearchTable();
    }

    @Override
    @Nonnull
    public IBlockState getStateForPlacement(@Nonnull World world,@Nonnull BlockPos pos,@Nonnull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer,@Nonnull EnumHand hand)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(@Nonnull World worldIn,@Nonnull BlockPos pos,@Nonnull IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityResearchTable)
            {
                ((TileEntityResearchTable)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }

    public void breakBlock(@Nonnull World worldIn,@Nonnull BlockPos pos, @Nonnull IBlockState state)
    {
        if (!keepInventory)
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityResearchTable)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos,(TileEntityResearchTable)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, RESEARCHING, FACING);
    }


    @Override
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(FACING)).getIndex();
    }
}
