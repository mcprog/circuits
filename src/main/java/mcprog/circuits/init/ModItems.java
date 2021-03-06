package mcprog.circuits.init;

import mcprog.circuits.Reference;
import mcprog.circuits.item.CircuitsItem;
import mcprog.circuits.item.ItemChemical;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item silicon_wafer;
	//public static Item phosphorus_dust;
	//public static Item boron_dust;
	public static Item quartz_dust;
	public static Item mono_silicon_ingot;

	public static void init() {
		silicon_wafer = new CircuitsItem("silicon_wafer");
		//phosphorus_dust = new ItemCrucibleble("phosphorus_dust");
		//boron_dust = new ItemCrucibleble("boron_dust");
		quartz_dust = new ItemChemical("quartz_dust");
		mono_silicon_ingot = new ItemChemical("mono_silicon_ingot");
	}
	
	public static void register() {
		register(silicon_wafer);
		//register(phosphorus_dust);
		//register(boron_dust);
		register(quartz_dust);
		register(mono_silicon_ingot);
	}
	
	private static void register(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	public static void registerRenders() {
		registerRender(silicon_wafer);
		//registerRender(phosphorus_dust);
		//registerRender(boron_dust);
		registerRender(quartz_dust);
		registerRender(mono_silicon_ingot);
	}

	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(
				Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

}
