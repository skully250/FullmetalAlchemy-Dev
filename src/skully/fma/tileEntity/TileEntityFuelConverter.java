package skully.fma.tileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFuelConverter extends TileEntity {
	
	
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("Trans Energy", 0);
	}
	
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		compound.getInteger("Trans Energy");
	}
}
