package mods.fullmetalalchemy.crafting;

import static mods.fullmetalalchemy.item.FMAItems.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class AlchFireRecipes {

	public static void initialize() {
		GameRegistry.addRecipe(new ItemStack(FireGlove), "XXX", "YZY", " Y ", 'X', new ItemStack(metaTest, 1, 6), 'Y', new ItemStack(metaTest, 1, 5), 'Z', FireCircle);

	}
}
