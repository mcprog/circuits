package mcprog.circuits.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemChemical extends CircuitsItem {
	
	
	public ItemChemical(String unlocalizedName) {
		super(unlocalizedName);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(StatCollector.translateToLocal("tooltip.circuits:" + getUnlocalizedName() + ".name"));
		tooltip.add(StatCollector.translateToLocal("tooltip.circuits:" + getUnlocalizedName() + ".formula"));
	}

}
