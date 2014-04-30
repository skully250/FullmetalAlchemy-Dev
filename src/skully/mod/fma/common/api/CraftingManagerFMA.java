package skully.mod.fma.common.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

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
				s = new StringBuilder().append(s).append(s2).toString();
			}
		} else {
			while (obj[i] instanceof String) {
				String s1 = (String)obj[i++];
				k++;
				j = s1.length();
				s = new StringBuilder().append(s).append(s1).toString();
			}
		}
		HashMap hashmap = new HashMap();
		for (; i < obj.length; i += 2) {
			Character character = (Character)obj[i];
			ItemStack itemStack1 = null;
			if (obj[i+1] instanceof Item) {
				itemStack1 = new ItemStack((Item)obj[i+1]);
			} else if (obj[i+1] instanceof Block) {
				itemStack1 = new ItemStack((Block)obj[i+1], 1, -1);
			} else if (obj[i+1] instanceof ItemStack) {
				itemStack1 = (ItemStack)obj[i+1];
			}
			hashmap.put(character, itemStack1);
		}

		ItemStack itemStack[] = new ItemStack[j*k];
		for (int i1 = 0;i1 < j*k; i1++) {
			char c = s.charAt(i1);
			if (hashmap.containsKey(Character.valueOf(c))) {
				itemStack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c))).copy();
			} else {
				itemStack[i1] = null;
			}
		}
		recipes.add(new ShapedRecipesFMA(j, k, itemStack, stack));
	}

	public void addShapelessRecipe(ItemStack itemstack, Object aobj[])
	{
		ArrayList arraylist = new ArrayList();
		Object aobj1[] = aobj;
		int i = aobj1.length;
		for (int j = 0; j < i; j++)
		{
			Object obj = aobj1[j];
			if (obj instanceof ItemStack)
			{
				arraylist.add(((ItemStack)obj).copy());
				continue;
			}
			if (obj instanceof Item)
			{
				arraylist.add(new ItemStack((Item)obj));
				continue;
			}
			if (obj instanceof Block)
			{
				arraylist.add(new ItemStack((Block)obj));
			}
			else
			{
				throw new RuntimeException("Invalid shapeless recipy!");
			}
		}

		recipes.add(new ShapelessRecipesFMA(itemstack, arraylist));
	}

	public ItemStack findMatchingRecipe(InventoryCrafting inventorycrafting, World world)
	{
		int i = 0;
		ItemStack itemstack = null;
		ItemStack itemstack1 = null;
		for (int j = 0; j < inventorycrafting.getSizeInventory(); j++)
		{
			ItemStack itemstack2 = inventorycrafting.getStackInSlot(j);
			if (itemstack2 == null)
			{
				continue;
			}
			if (i == 0)
			{
				itemstack = itemstack2;
			}
			if (i == 1)
			{
				itemstack1 = itemstack2;
			}
			i++;
		}
		for (int k = 0; k < recipes.size(); k++)
		{
			IRecipe irecipe = (IRecipe)recipes.get(k);
			if (irecipe.matches(inventorycrafting, world))
			{
				return irecipe.getCraftingResult(inventorycrafting);
			}
		}

		return null;
	}

	public List getRecipeList()
	{
		return recipes;
	}
}