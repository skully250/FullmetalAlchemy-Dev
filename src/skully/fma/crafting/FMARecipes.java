package skully.fma.crafting;

import static skully.fma.item.FMAItems.AlchNotebook;
import static skully.fma.item.FMAItems.AlchNotes;
import static skully.fma.item.FMAItems.ChalkCircle;
import static skully.fma.item.FMAItems.ChalkPyramid;
import static skully.fma.item.FMAItems.ChalkStick;
import static skully.fma.item.FMAItems.FireCircle;
import static skully.fma.item.FMAItems.Kunai;
import static skully.fma.item.FMAItems.ash;
import static skully.fma.item.FMAItems.eash;
import static skully.fma.item.FMAItems.gash;
import static skully.fma.item.FMAItems.metaTest;
import static skully.fma.item.FMAItems.stone0;
import static skully.fma.item.FMAItems.stone1;
import static skully.fma.item.FMAItems.stone2;
import skully.fma.item.FMAItems;
import skully.fma.item.ItemFMAMeta;
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
			new ItemStack(Item.bone), new ItemStack(Item.rottenFlesh), 
			new ItemStack(Item.enderPearl), new ItemStack(Item.slimeBall), 
			new ItemStack(Item.gunpowder), new ItemStack(Item.blazePowder),
			new ItemStack(Item.ghastTear), new ItemStack(Item.spiderEye), new ItemStack(ash)
		});	
		recipe.addShapelessRecipe(new ItemStack(gash), new Object [] {
			new ItemStack(Item.beefCooked), new ItemStack(Item.chickenCooked), 
			new ItemStack(Item.porkCooked), new ItemStack(Item.wheat), 
			new ItemStack(Item.bakedPotato), new ItemStack(Item.melon), 
			new ItemStack(Item.feather), new ItemStack(Item.leather), new ItemStack(ash)
		});
		recipe.addShapelessRecipe(new ItemStack(AlchNotes), new Object[] {
			new ItemStack(Item.paper), new ItemStack(Item.feather), new ItemStack(Item.dyePowder, 1, 0)
		});
		//Shapeless
		GameRegistry.addRecipe(new ItemStack(FMAItems.metaTest, 1, 7), 
				"XXX", 
				"XYX", 
				"XXX", 
				'X', gash, 'Y', stone0);
		GameRegistry.addRecipe(new ItemStack(FMAItems.metaTest, 1, 6), 
				"XXX", 
				"XYX", 
				"XXX", 
				'X', eash, 'Y', stone0);
		GameRegistry.addRecipe(new ItemStack(stone0), 
				"XYX", 
				"YZY", 
				"XYX", 
				'X', Block.stone, 'Y', Item.ingotGold, 'Z', Item.diamond);
		//GameRegistry.addRecipe(new ItemStack(pStone), "   ", "XYZ", "   ", 'X', stone1, 'Y', Item.netherStar, 'Z', stone2);
		GameRegistry.addRecipe(new ItemStack(ChalkPyramid), 
				"X X", 
				" X ", 
				"X X", 
				'X', ChalkStick);
		GameRegistry.addRecipe(new ItemStack(ChalkCircle), 
				"XXX", 
				"X X", 
				"XXX", 
				'X', ChalkStick);
		GameRegistry.addRecipe(new ItemStack(FireCircle), 
				"X", 
				"Y", 
				'X', ChalkPyramid, 'Y', ChalkCircle);
		GameRegistry.addRecipe(new ItemStack(metaTest, 4, 6), 
				"X X", 
				" Y ", 
				"X X", 
				'X', Item.silk, 'Y', Item.enderPearl);
		GameRegistry.addRecipe(new ItemStack(metaTest, 1, 4), 
				"XX ", 
				"XX ", 
				'X', new ItemStack(metaTest, 1, 5));
		GameRegistry.addRecipe(new ItemStack(AlchNotebook), 
				"XYZ", 
				"ZZZ", 
				"   ", 
				'X', Item.leather, 'Y', Item.silk, 'Z', AlchNotes);
		GameRegistry.addRecipe(new ItemStack(Kunai, 2), 
				" X ", 
				"YXY", 
				"ZG ", 
				'X', Item.ingotIron, 'Y', Item.silk, 'Z', new ItemStack(Item.dyePowder, 1, 9), 'G', Item.feather);
		//shaped
		FurnaceRecipes.smelting().addSmelting(Item.coal.itemID, 1, new ItemStack(ash), 1);
		//Furnace
	}




}