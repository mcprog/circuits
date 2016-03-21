package mcprog.circuits.init;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	public static void init () {
		GameRegistry.addSmelting(Blocks.stone, new ItemStack(ModBlocks.heated_stone), 0);
	}

}
