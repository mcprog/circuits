package mcprog.circuits.item;

import mcprog.circuits.CircuitsMod;
import mcprog.circuits.creativetab.CircuitsTab;
import mcprog.circuits.creativetab.CircuitsTabs;
import net.minecraft.item.Item;

public class CircuitsItem extends Item {

	public CircuitsItem(String unlocalizedName) {
		setUnlocalizedName(unlocalizedName);
		setCreativeTab(CircuitsTabs.TAB_CORE);
	}

}
