package skully.mod.fma.common.crafting;

import java.util.Comparator;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

public class RecipeSorterFMA implements Comparator {
	
	final CraftingManagerFMA craftingManager;
	
	RecipeSorterFMA(CraftingManagerFMA craftingmanager) {
		craftingManager = craftingmanager;
	}

	@Override
	public int compare(Object obj, Object obj1) {
		return compareRecipes((IRecipe)obj, (IRecipe)obj1);
	}
	
	public int compareRecipes(IRecipe recipe, IRecipe recipe1) {
		if (recipe instanceof ShapelessRecipes && recipe1 instanceof ShapedRecipes) {
			return 1;
		}
		if (recipe1 instanceof ShapelessRecipes && recipe instanceof ShapedRecipes) {
			return -1;
		}
		if (recipe1.getRecipeSize() < recipe.getRecipeSize()) {
			return -1;
		}
		return recipe1.getRecipeSize() <= recipe.getRecipeSize() ? 0 : 1;
	}

}
