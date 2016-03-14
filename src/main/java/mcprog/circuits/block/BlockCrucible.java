package mcprog.circuits.block;

import java.util.List;

import mcprog.circuits.init.ModBlocks;
import mcprog.circuits.init.ModItems;
import mcprog.circuits.lib.Maths;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrucible extends CircuitsBlock {

	private static final int MAX_LEVEL = 4;

	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, MAX_LEVEL);

	public BlockCrucible(String unlocalizedName, Material materialIn) {
		super(unlocalizedName, materialIn);
		this.setBlockBounds(0, 0, 0, 1, Maths.VOXEL_LENGTH * 12F, 1);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, Integer.valueOf(0)));

	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		}
		ItemStack itemStack = playerIn.getHeldItem();
		if (itemStack == null) {
			return true;
		}
		int level = ((Integer) state.getValue(LEVEL)).intValue();
		Item item = itemStack.getItem();
		if (item == ModItems.boron_dust || item == ModItems.phosphorus_dust) {
			if (level < MAX_LEVEL) {
				if (!playerIn.capabilities.isCreativeMode) {
					--itemStack.stackSize;
					if (itemStack.stackSize <= 0) {
						playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, (ItemStack) null);
					}
				}
				setLevel(worldIn, pos, state, level + 1);
			}
		}
		return true;
	}

	protected void setLevel(World worldIn, BlockPos pos, IBlockState state, int level) {
		worldIn.setBlockState(pos,
				state.withProperty(LEVEL, Integer.valueOf(MathHelper.clamp_int(level, 0, MAX_LEVEL))), 2);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(LEVEL, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer) state.getValue(LEVEL)).intValue();
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { LEVEL });
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isFullCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}

}
