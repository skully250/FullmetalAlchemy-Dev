package skully.mod.fma.common.crafting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

public class CraftingManagerFMA {
	private static final CraftingManagerFMA instance = new CraftingManagerFMA();
	public static final CraftingManagerFMA getInstance() {
		return instance;
	}
	
	private List recipes;
	
	private CraftingManagerFMA() {
		recipes = new ArrayList();
		Collections.sort(recipes, new RecipeSorterFMA(this));
	}
	
	public void addRecipe(ItemStack stack, Object obj[]) {
		String s = "";
		int i = 0, j = 0, k = 0;
		if (obj[i] instanceof String[]) {
			String as[] = (String[])obj[i++];
			for (int l = 0; l < as.length; l++) {
				String s2 = as[l];
				k++;
				j = s2.length();
			}
		}
	}
}
