package skully.fma.tileEntity;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import skully.fma.energy.IAlchEnergyProvider;
import skully.fma.energy.decay.IDecayProvider;
import skully.fma.fx.FXChargingBeam;
import skully.fma.item.FMAItems;
import skully.fma.item.alchemical.ItemPStone;
import skully.fma.item.energy.ItemEnergyStore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityInfuser extends TileEntity implements IAlchEnergyProvider, IDecayProvider {

	private ItemStack[] infuserStacks = new ItemStack[10];
	public int addTime = 20;

	public static int decay = 0;

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
		providePStoneEnergy();
		provideDecay();
	}

	@SideOnly(Side.CLIENT)
	public void provideDecay() {
		List playerEntities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(
				this.xCoord - 1.5, this.yCoord, this.zCoord - 1.5,
				this.xCoord + 1.5, this.yCoord + 1, this.zCoord + 1.5));

		for (Object obj : playerEntities) {
			EntityPlayer player = (EntityPlayer) obj;
			if (player.inventory.getCurrentItem() != null)
				if (player.inventory.getCurrentItem().getItem() == FMAItems.EnergyStore) {
					while (ItemEnergyStore.energy > 0) {
						if (decay > 10) {
							FXChargingBeam fx = new FXChargingBeam(this.worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, player.posX,
									player.posY - 0.1, player.posZ, 10);
							Minecraft.getMinecraft().effectRenderer.addEffect(fx);
							ItemEnergyStore.energy -= 10;
							decay -= 10;
							System.out.println(decay);
						}
					}
				}
		}
	}

	@SideOnly(Side.CLIENT)
	public void providePStoneEnergy() {

		List playerEntities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(
				this.xCoord - 1.5, this.yCoord, this.zCoord - 1.5,
				this.xCoord + 1.5, this.yCoord + 1, this.zCoord + 1.5));

		for (Object obj : playerEntities) {
			EntityPlayer player = (EntityPlayer) obj;
			if (player.inventory.getCurrentItem() != null)
				if (player.inventory.getCurrentItem().getItem() == FMAItems.pStone) {
					ItemPStone.isGettingCharged = true;
					FXChargingBeam fx = new FXChargingBeam(this.worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, player.posX,
							player.posY - 0.1, player.posZ, 10);
					Minecraft.getMinecraft().effectRenderer.addEffect(fx);
					addTime--;
					if (addTime == 0) { 
						ItemPStone.PStoneEnergy += provideEnergy(5, 2);
						addTime = 20;
					}
				}
		}
		ItemPStone.isGettingCharged = false;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int provideEnergy(int amount, int decayIncrease) {
		int finalDecay = amount / decayIncrease;
		this.decay += (int)finalDecay;
		System.out.println(this.decay);
		return amount;
	}

	@Override
	public int increaseDecay(int amount) {
		return amount;
	}

	@Override
	public int provideDecay(int amount)  {
		return amount;
	}
}