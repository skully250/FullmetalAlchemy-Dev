package skully.fma.tileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCircle extends TileEntity {
	
	public static int energy;
	
	@Override
	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setInteger("Transmutational Energy", energy);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		TileEntityCircle.energy = par1.getInteger("Transmutational Energy");
	}
}
