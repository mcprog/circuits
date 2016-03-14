package mcprog.circuits.creativetab;

import mcprog.circuits.Reference;
import mcprog.circuits.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CircuitsTab extends CreativeTabs {

	private Item icon;
	
	public CircuitsTab() {
		super(Reference.MOD_ID);
	}
	
	public void setTabIconItem(Item icon) {
		this.icon = icon;
	}

	@Override
	public Item getTabIconItem() {
		return icon;
	}

}
