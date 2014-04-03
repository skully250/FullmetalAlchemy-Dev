package skully.mod.fma.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import skully.mod.fma.common.items.FMAItems;

public class ContainerChalkCircle extends Container {

	/**
	 * The crafting matrix inventory (3x3).
	 */
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 5, 5);
	public IInventory craftResult = new InventoryCraftResult();
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;

	public ContainerChalkCircle(InventoryPlayer par1InventoryPlayer, ItemStack stack, World par2World, int par3, int par4, int par5)
	{
		this.worldObj = par2World;
		this.posX = par3;
		this.posY = par4;
		this.posZ = par5;
		this.addSlotToContainer(new SlotCrafting(par1InventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 128, 44));

		for (int i = 0; i < 25; i++) {
			if (stack != null) {
				craftMatrix.setInventorySlotContents(i, stack.copy());
			}
		}

		for (int l = 0; l < 3; ++l)
		{
			for (int i1 = 0; i1 < 9; ++i1)
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, i1 + l * 9 + 9, 8 + i1 * 18, 102 + l * 18));
			}
		}

		for (int l1 = 0; l1 < 9; ++l1)
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, l1, 8 + l1 * 18, 160));
		}
		this.onCraftMatrixChanged(this.craftMatrix);
	}

	/**
	 * Callback for when the crafting matrix is changed.
	 */
	 public void onCraftMatrixChanged(IInventory par1IInventory)
	{
		 this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
}
