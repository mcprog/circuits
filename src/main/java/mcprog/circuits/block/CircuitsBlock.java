package mcprog.circuits.block;

import mcprog.circuits.creativetab.CircuitsTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CircuitsBlock extends Block {

	public CircuitsBlock(String unlocalizedName, Material materialIn) {
		super(materialIn);
		setUnlocalizedName(unlocalizedName);
		setCreativeTab(CircuitsTabs.TAB_CORE);
	}

}
