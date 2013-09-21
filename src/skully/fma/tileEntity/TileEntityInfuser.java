package skully.fma.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInfuser extends TileEntity {

	private ItemStack[] infuserStacks = new ItemStack[10];

	public int craftTime = 300;

	public TileEntityInfuser() {

	}

	public ItemStack getStackInSlotOnClosing(int par1)
	{
		return null;
	}

	public void completeCraft() {

	}

	@Override
	public void updateEntity() {
		while (craftTime >= 0) {
			craftTime--;
		}
		if (craftTime == 0) {
			completeCraft();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

	}
}