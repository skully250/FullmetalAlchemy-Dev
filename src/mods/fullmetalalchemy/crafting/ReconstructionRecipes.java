package mods.fullmetalalchemy.crafting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import static mods.fullmetalalchemy.item.FMAItems.*;
import mods.fullmetalalchemy.crafting.*;

public class ReconstructionRecipes {
	public static void initialize() {
		GameRegistry.addShapelessRecipe(new ItemStack(ChalkStick), new Object[] {
			new ItemStack(metaTest, 1, 0), new ItemStack(metaTest, 1, 0), new ItemStack(metaTest, 1, 0),
			new ItemStack(ReconstructionCircle),
		});
		GameRegistry.addShapelessRecipe(new ItemStack(metaTest, 3, 1), new Object[] {
			new ItemStack(ChalkStick), new ItemStack(ReconstructionCircle),
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.lightStoneDust, 2), new Object[] {
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
