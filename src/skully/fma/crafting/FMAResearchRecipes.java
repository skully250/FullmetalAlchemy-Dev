package skully.fma.crafting;

import static skully.fma.item.FMAItems.ChalkCircle;
import static skully.fma.item.FMAItems.ChalkStick;
import static skully.fma.item.FMAItems.FireCircle;
import static skully.fma.item.FMAItems.FireGlove;
import static skully.fma.item.FMAItems.IceGauntlet;
import static skully.fma.item.FMAItems.ReconstructionCircle;
import static skully.fma.item.FMAItems.metaTest;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class FMAResearchRecipes {

	static FMACraftingManager recipe = new FMACraftingManager();
	
	public static void initializeFire() {
		GameRegistry.addRecipe(new ItemStack(FireGlove), 
				"XXX", 
				"YZY", 
				" Y ", 
				'X', new ItemStack(metaTest, 1, 6), 'Y', new ItemStack(metaTest, 1, 5), 'Z', FireCircle);
	}
	
	public static void initializeIce() {
		GameRegistry.addRecipe(new ItemStack(IceGauntlet), 
				"XYX", 
				"XZX", 
				" X ", 
				'X', Item.ingotIron, 'Y', Block.ice, 'Z', ChalkCircle);
	}
	
	public static void initializeRec() {
		GameRegistry.addRecipe(new ItemStack(ReconstructionCircle), 
				"XYX", 
				"XZX", 
				"LXK", 
				'X', Item.ingotGold, 'Y', Block.blockDiamond, 'Z', ChalkCircle, 'L', 
				new ItemStack(Item.dyePowder, 1, 0), 'K', new ItemStack(Item.dyePowder, 1, 15));
	}
	
	public static void initializeAlchemy() {
		GameRegistry.addShapelessRecipe(new ItemStack(ChalkStick), new Object[] {
			new ItemStack(metaTest, 1, 0), new ItemStack(metaTest, 1, 0), 
			new ItemStack(metaTest, 1, 0), new ItemStack(ReconstructionCircle),
		});
		GameRegistry.addShapelessRecipe(new ItemStack(metaTest, 3, 1), new Object[] {
			new ItemStack(ChalkStick), new ItemStack(ReconstructionCircle),
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.glowstone, 2), new Object[] {
			new ItemStack(Item.redstone), new ItemStack(Item.redstone), 
			new ItemStack(Item.redstone), new ItemStack(Item.redstone), 
			new ItemStack(ReconstructionCircle),
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.coal), new Object[] {
			new ItemStack(Item.coal, 1, 1), new ItemStack(Item.coal, 1, 1),
			new ItemStack(ReconstructionCircle),
		});
	}
}
