package skully.fma.tileEntity;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import org.lwjgl.opengl.GL11;

import skully.fma.api.blocks.IInfuser;
import skully.fma.fx.FXChargingBeam;
import skully.fma.item.FMAItems;
import skully.fma.item.alchemical.ItemPStone;
import skully.fma.item.energy.ItemEnergyStore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityInfuser extends TileEntity implements IInfuser {

	private ItemStack[] infuserStacks = new ItemStack[10];
	public int addTime = 20;
	private ItemStack energyStore = new ItemStack(FMAItems.EnergyStore);

	public int decay;

	public TileEntityInfuser() {
		decay = 0;
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
					if (decay > 10) {
						FXChargingBeam fx = new FXChargingBeam(this.worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, player.posX,
								player.posY - 0.1, player.posZ, 10);
						Minecraft.getMinecraft().effectRenderer.addEffect(fx);
						ItemEnergyStore.setEnergy(player.inventory.getCurrentItem(), ItemEnergyStore.getEnergy(energyStore) - 10);
						decay -= 10;
						System.out.println(xCoord + " " + yCoord + " " + zCoord + " " + decay);
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
		decay = compound.getInteger("Decay");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("Decay", this.decay);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int provideEnergy(int amount, int decayIncrease) {
		if (this.worldObj.isRemote) {
		int finalDecay = amount / decayIncrease;
		decay += finalDecay;
		System.out.println(xCoord + " " + yCoord + " " + zCoord + " " + decay);
		return amount;
		}
		return 0;
	}

	@Override
	public int increaseDecay(int amount) {
		return amount;
	}

	@Override
	public int provideDecay(int amount)  {
		return amount;
	}

	@Override
	public int getDecay() {
		return decay;
	}

	@Override
	public void setDecay(int decayAmount) {
		decay = decayAmount;
	}
}