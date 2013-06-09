package mods.fullmetalalchemy.crafting;

import static mods.fullmetalalchemy.item.FMAItems.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class AlchIceRecipes {

	public static void initialize() {
		GameRegistry.addRecipe(new ItemStack(IceGauntlet), "XYX", "XZX", " X ", 'X', Item.ingotIron, 'Y', Block.ice, 'Z', ChalkCircle);

	}
}
