package skully.fma.tileEntity;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import skully.fma.energy.IAlchEnergyProvider;
import skully.fma.item.FMAItems;
import skully.fma.item.alchemical.ItemPStone;

public class TileEntityInfuser extends TileEntity implements IAlchEnergyProvider {

	private ItemStack[] infuserStacks = new ItemStack[10];

	public int craftTime = 300;
	public int addTime = 20;
	public EntityPlayer player = Minecraft.getMinecraft().thePlayer;

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
		
		List playerEntities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(
				this.xCoord - 1, this.yCoord, this.zCoord - 1,
				this.xCoord + 1, this.yCoord + 1, this.zCoord + 1));
		
		while (craftTime >= 0) {
			craftTime--;
		}
		if (craftTime == 0) {
			completeCraft();
		}
		
		for (Object obj : playerEntities) {
			EntityPlayer player = (EntityPlayer) obj;
			if (player.inventory.getCurrentItem() != null)
				if (player.inventory.getCurrentItem().getItem() == FMAItems.pStone) {
					addTime--;
					if (addTime == 0) { 
						ItemPStone.power2 += 2;
						addTime = 20;
					}
				}
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

	@Override
	public int provideEnergy(int amount) {
		return amount;
	}

	@Override
	public int increaseDecay(int amount) {
		return amount;
	}
}