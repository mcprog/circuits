package mcprog.circuits.block;

import java.util.Random;

import mcprog.circuits.init.ModItems;
import mcprog.circuits.lib.Maths;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrucible extends CircuitsBlock {

	private static final int MAX_LEVEL = 7;
	private static final float HEIGHT = Maths.VOXEL_LENGTH * 12f;
	
	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, MAX_LEVEL);
	public static final PropertyBool MELTED = PropertyBool.create("melted");
	
	

	public BlockCrucible(String unlocalizedName, Material materialIn) {
		super(unlocalizedName, materialIn);
		this.setBlockBounds(0, 0, 0, 1, HEIGHT, 1);
		this.setTickRandomly(true);
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
		if (item == ModItems.quartz_dust) {
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
		return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
	}
	

	protected void setLevel(World worldIn, BlockPos pos, IBlockState state, int level) {
		worldIn.setBlockState(pos,
				state.withProperty(LEVEL, Integer.valueOf(MathHelper.clamp_int(level, 0, MAX_LEVEL))), 2);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		
		Block below = worldIn.getBlockState(pos.down()).getBlock();
		if (below == Blocks.lava) {
			
			boolean melted = ((Boolean) state.getValue(MELTED)).booleanValue();
			
			if (!melted) {
				boolean melt = rand.nextBoolean();
				if (melt) {
					int level = ((Integer) state.getValue(LEVEL)).intValue();
					worldIn.setBlockState(pos, state.withProperty(MELTED, true), 2);
				}
			}
			
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		Block below = worldIn.getBlockState(pos.down()).getBlock();
		if (below == Blocks.lava) {
			final double border = Maths.VOXEL_LENGTH * 2.0;
			final double range = 1 - (border * 2);
			double dX = rand.nextDouble() * range + border;
			double dZ = rand.nextDouble() * range + border;
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + dX, pos.getY() + HEIGHT, pos.getZ() + dZ, 0, 0, 0, new int[0]);
		}
	}
	

	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean melted = false;
		if (((meta & 15) >> 3) != 0) {
			melted = true;
		}
		return this.getDefaultState().withProperty(LEVEL, Integer.valueOf(meta & 3)).withProperty(MELTED, melted);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;// 0000
		i |= ((Integer) state.getValue(LEVEL)).intValue();// 0000-0111
		if (((Boolean) state.getValue(MELTED)).booleanValue()) {
			i |= 1 << 4;// 1000-1111
		}
		return i;
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { LEVEL, MELTED });
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
