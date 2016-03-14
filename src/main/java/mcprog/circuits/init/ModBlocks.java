package mcprog.circuits.init;

import mcprog.circuits.Reference;
import mcprog.circuits.block.BlockCrucible;
import mcprog.circuits.block.CircuitsBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static Block quartz_crucible;
	//public static Block quartz_crystal;

	public static void init() {
		quartz_crucible = new BlockCrucible("quartz_crucible", Material.rock);
		//quartz_crystal = new BlockCrucible("quartz_crystal", Material.glass);
	}

	public static void register() {
		register(quartz_crucible);
		//register(quartz_crystal);
	}
	
	private static void register(Block block) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}

	public static void registerRenders() {
		registerRender(quartz_crucible);
		//registerRender(quartz_crystal);
	}
	
	private static void registerRender(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(
				Reference.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}

}
