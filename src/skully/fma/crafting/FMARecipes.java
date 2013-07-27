package skully.fma.crafting;

import static skully.fma.item.FMAItems.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import cpw.mods.fml.common.registry.GameRegistry;

public class FMARecipes
{
	public static void initialize() {
		GameRegistry.registerCraftingHandler(new FMACraftingHandler());
		FMACraftingManager recipe = new FMACraftingManager();
		
		recipe.addShapelessRecipe(new ItemStack(eash), new Object[] {
			new ItemStack(Item.bone), new ItemStack(Item.rottenFlesh), new ItemStack(Item.enderPearl),
			new ItemStack(Item.slimeBall), new ItemStack(Item.gunpowder), new ItemStack(Item.blazePowder),
			new ItemStack(Item.ghastTear), new ItemStack(Item.spiderEye), new ItemStack(ash)
		});	
		recipe.addShapelessRecipe(new ItemStack(gash), new Object [] {
			new ItemStack(Item.beefCooked), new ItemStack(Item.chickenCooked), new ItemStack(Item.porkCooked),
			new ItemStack(Item.wheat), new ItemStack(Item.bakedPotato), new ItemStack(Item.melon),
			new ItemStack(Item.feather), new ItemStack(Item.leather), new ItemStack(ash)
		});
		recipe.addShapelessRecipe(new ItemStack(AlchNotes), new Object[] {
			new ItemStack(Item.paper), new ItemStack(Item.feather), new ItemStack(Item.dyePowder, 1, 0)
		});
		//Shapeless
		recipe.addRecipe(new ItemStack(stone1), "XXX", "XYX", "XXX", 'X', eash, 'Y', stone0);
		recipe.addRecipe(new ItemStack(stone2), "XXX", "XYX", "XXX", 'X', gash, 'Y', stone0);
		recipe.addRecipe(new ItemStack(stone0), "XYX", "YZY", "XYX", 'X', Block.stone, 'Y', Item.ingotGold, 'Z', Item.diamond);
		//recipe.addRecipe(new ItemStack(pStone), "   ", "XYZ", "   ", 'X', stone1, 'Y', Item.netherStar, 'Z', stone2);
		recipe.addRecipe(new ItemStack(ChalkPyramid), "X X", " X ", "X X", 'X', ChalkStick);
		recipe.addRecipe(new ItemStack(ChalkCircle), "XXX", "X X", "XXX", 'X', ChalkStick);
		recipe.addRecipe(new ItemStack(FireCircle), "X", "Y", 'X', ChalkPyramid, 'Y', ChalkCircle);
		recipe.addRecipe(new ItemStack(metaTest, 4, 6), "X X", " Y ", "X X", 'X', Item.silk, 'Y', Item.enderPearl);
		recipe.addRecipe(new ItemStack(metaTest, 1, 4), "XX ", "XX ", 'X', new ItemStack(metaTest, 1, 5));
		recipe.addRecipe(new ItemStack(AlchNotebook), "XYZ", "ZZZ", "   ", 'X', Item.leather, 'Y', Item.silk, 'Z', AlchNotes);
		recipe.addRecipe(new ItemStack(Kunai, 2), " X ", "YXY", "ZG ", 'X', Item.ingotIron, 'Y', Item.silk, 'Z', new ItemStack(Item.dyePowder, 1, 9), 'G', Item.feather);
		//shaped
		FurnaceRecipes.smelting().addSmelting(Item.coal.itemID, 1, new ItemStack(ash), 1);
		//Furnace
	}




}