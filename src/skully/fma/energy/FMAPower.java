package skully.fma.energy;

import net.minecraft.nbt.NBTTagCompound;
import skully.fma.core.util.NBThelper;


public class FMAPower {
	static NBThelper helper = new NBThelper();
	static NBTTagCompound compound =  new NBTTagCompound();
	public static double transEnergy = compound.getDouble("Alchemical Energy");
	public static int transDecay = compound.getInteger("Alchemical Decay");
	
	public FMAPower() {
		setEnergy(100);
	}

	public static void modifyEnergy(double amount) {
		transEnergy += amount;
		if (transEnergy < 0)
			transEnergy = 0;
	}

	public static void modifyDecay(int amount) {
		transDecay += amount;
		if (transDecay < 0)
			transDecay = 0;
	}

	public static String decayDisplay() {
		if (transDecay <= 25)
			return "no Alchemical Decay";
		else if (transDecay <= 50)
			return "Alchemical Decay Moderate";
		else if (transDecay <= 75)
			return "Alchemical Decay High";
		else
			return "Alchemical Decay Extremely Dangerous";
	}

	public static void setEnergy(double amount) {
		if (compound.getDouble("Alchemical Energy") > -1)
			compound.getDouble("Alchemical Energy");
		else
		compound.setDouble("Alchemical Energy", amount);
	}
}
