package skully.mod.fma.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class FMAItems {
	
	public static Item philStone;
	
	public static void init() {
		philStone = new ItemPhilosophersStone();
		
		GameRegistry.registerItem(philStone, philStone.getUnlocalizedName());
	}
}
