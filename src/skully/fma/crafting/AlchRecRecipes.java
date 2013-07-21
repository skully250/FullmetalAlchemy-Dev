package skully.fma.crafting;

import static skully.fma.item.FMAItems.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class AlchRecRecipes {

	public static void initialize() {
		GameRegistry.addRecipe(new ItemStack(ReconstructionCircle), "XYX", "XZX", "LXK", 'X', Item.ingotGold, 'Y', Block.blockDiamond, 'Z', ChalkCircle, 'L', new ItemStack(Item.dyePowder, 1, 0), 'K', new ItemStack(Item.dyePowder, 1, 15));
	}

}
