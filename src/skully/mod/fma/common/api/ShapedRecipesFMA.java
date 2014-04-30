package skully.mod.fma.common.api;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ShapedRecipesFMA implements IRecipe {
	
	
	private int recipeWidth;
	private int recipeHeight;
	private ItemStack recipeItems[];
	private ItemStack recipeOutput;

	public ShapedRecipesFMA(int i, int j, ItemStack aitemstack[], ItemStack itemstack)
	{
		recipeWidth = i;
		recipeHeight = j;
		recipeItems = aitemstack;
		recipeOutput = itemstack;
	}

	private boolean compare(InventoryCrafting inventorycrafting, int i, int j, boolean flag)
	{
		for (int k = 0; k < 5; k++)
		{
			for (int l = 0; l < 5; l++)
			{
				int i1 = k - i;
				int j1 = l - j;
				ItemStack recipeIS = null;
				if (i1 >= 0 && j1 >= 0 && i1 < recipeWidth && j1 < recipeHeight)
				{
					if (flag)
					{
						recipeIS = recipeItems[recipeWidth - i1 - 1 + j1 * recipeWidth];
					}
					else
					{
						recipeIS = recipeItems[i1 + j1 * recipeWidth];
					}
				}
				ItemStack inputIS = inventorycrafting.getStackInRowAndColumn(k, l);
				if (inputIS == null && recipeIS == null)
				{
					continue;
				}
				if (inputIS == null && recipeIS != null || inputIS != null && recipeIS == null)
				{
					return false;
				}
				if (recipeIS.getItem() != inputIS.getItem())
				{
					return false;
				}
				if (recipeIS.getItemDamage() != 32767 && recipeIS.getItemDamage() != inputIS.getItemDamage())
				{
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventorycrafting)
	{
		return new ItemStack(recipeOutput.getItem(), recipeOutput.stackSize, recipeOutput.getItemDamage());
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return recipeOutput;
	}

	@Override
	public int getRecipeSize()
	{
		return recipeWidth * recipeHeight;
	}

	@Override
	public boolean matches(InventoryCrafting inventorycrafting, World world)
	{
		for (int i = 0; i <= 5 - recipeWidth; i++)
		{
			for (int j = 0; j <= 5 - recipeHeight; j++)
			{
				if (compare(inventorycrafting, i, j, true))
				{
					return true;
				}
				if (compare(inventorycrafting, i, j, false))
				{
					return true;
				}
			}
		}

		return false;
	}
}
