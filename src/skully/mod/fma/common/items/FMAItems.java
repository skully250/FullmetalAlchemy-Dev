package skully.mod.fma.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class FMAItems {
	
	public static Item philStone;
	public static Item chalkStick;
	
	public static void init() {
		philStone = new ItemPhilosophersStone();
		chalkStick = new ItemChalkStick();
		
		GameRegistry.registerItem(philStone, philStone.getUnlocalizedName());
		GameRegistry.registerItem(chalkStick, chalkStick.getUnlocalizedName());
	}
}
