package mcprog.circuits.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockHeatedStone extends CircuitsBlock {

	private static final int COLD_MAX = 15;

	public static final PropertyInteger COLDNESS = PropertyInteger.create("coldness", 0, COLD_MAX);

	public BlockHeatedStone() {
		super("heated_stone", Material.rock);
		this.setTickRandomly(true);
		this.setDefaultState(getDefaultState().withProperty(COLDNESS, 0));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);

		int coldness = state.getValue(COLDNESS).intValue();

		if (coldness < COLD_MAX) {
			boolean colder = rand.nextBoolean();
			if (colder) {
				worldIn.setBlockState(pos, state.withProperty(COLDNESS, coldness));
			}
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(COLDNESS).intValue();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(COLDNESS, meta);
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { COLDNESS });
	}

}
