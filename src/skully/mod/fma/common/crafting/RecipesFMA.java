package skully.mod.fma.common.crafting;

import net.minecraft.item.ItemStack;
import skully.mod.fma.common.api.CraftingManagerFMA;
import skully.mod.fma.common.items.FMAItems;

public class RecipesFMA {
	
	private static final CraftingManagerFMA CMFMA = CraftingManagerFMA.getInstance();
	
	public static void registerRecipes() {
		registerCircles();
	}
	
	/*
	 * Function for registering the circle
	 * drawing recipes to be added
	 * (Items for now, blocks later ;-;)
	 */
	public static void registerCircles() {
		CMFMA.addRecipe(new ItemStack(FMAItems.philStone), new Object[] { "XXXXX", 
			"XXXXX", 
			"XXXXX", 
			"     ", 
			"     ",
			"     ",
			Character.valueOf('X'), FMAItems.chalkStick});
	}
}
