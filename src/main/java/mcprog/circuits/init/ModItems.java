package mcprog.circuits.init;

import mcprog.circuits.Reference;
import mcprog.circuits.item.CircuitsItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item silicon_wafer;
	public static Item phosphorus_dust;
	public static Item boron_dust;
	public static Item quartz_dust;

	public static void init() {
		silicon_wafer = new CircuitsItem("silicon_wafer");
		phosphorus_dust = new CircuitsItem("phosphorus_dust");
		boron_dust = new CircuitsItem("boron_dust");
		quartz_dust = new CircuitsItem("quartz_dust");
	}
	
	public static void register() {
		register(silicon_wafer);
		register(phosphorus_dust);
		register(boron_dust);
		register(quartz_dust);
	}
	
	private static void register(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	public static void registerRenders() {
		registerRender(silicon_wafer);
		registerRender(phosphorus_dust);
		registerRender(boron_dust);
		registerRender(quartz_dust);
	}

	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(
				Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

}
