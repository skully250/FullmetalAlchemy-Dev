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
	public ItemStack transferStackInSlot(EntityPlayer player, int clickedIndex) {
		ItemStack var2 = null;
		Slot grabbedSlot = (Slot)this.inventorySlots.get(clickedIndex);

		if (grabbedSlot != null && grabbedSlot.getHasStack()) {
			ItemStack var4 = grabbedSlot.getStack();
			var2 = var4.copy();
			
			if (clickedIndex < 10) {
				if (!this.mergeItemStack(var4, 10, 36, true)) {
					return null;
				}
			} else if (clickedIndex > 10 && clickedIndex < 37) {
				if (!this.mergeItemStack(var4, 0, 9, true)) {
					return null;
				}
			} else if (clickedIndex >= 37 && clickedIndex < 62) {
				if (!this.mergeItemStack(var4, 0, 36, true)) {
					return null;
				}
			}

			if (var4.stackSize == 0) {
				grabbedSlot.putStack((ItemStack)null);
			} else {
				grabbedSlot.onSlotChanged();
			}
			if (var4.stackSize == var2.stackSize) {
				return null;
			}
			
			grabbedSlot.onPickupFromSlot(player, var4);
		}
		this.onCraftMatrixChanged(this.craftMatrix);
		return var2;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
}
