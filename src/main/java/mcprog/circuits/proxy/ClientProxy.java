package mcprog.circuits.proxy;

import mcprog.circuits.init.ModBlocks;
import mcprog.circuits.init.ModItems;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenders () {
		ModItems.registerRenders();
		ModBlocks.registerRenders();
	}

}
