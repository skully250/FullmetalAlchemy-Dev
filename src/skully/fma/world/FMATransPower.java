package skully.fma.world;

import net.minecraft.nbt.NBTTagCompound;

public class FMATransPower {
	static NBTTagCompound compound = new NBTTagCompound();
	public static double transEnergy = compound.getDouble("Alchemical Energy");
	public static int transDecay = compound.getInteger("Alchemical Decay");

	public static void initialize() {
		if (compound.getBoolean("run") == true) {
			compound.getDouble("Alchemical Energy");
			compound.getInteger("Alchemical Decay");
		} else {
			compound.setDouble("Alchemical Energy", 100);
			compound.setInteger("Alchemical Decay", 0);
			compound.setBoolean("run", true);
		}
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
		compound.setDouble("Alchemical Energy", amount);
	}
}
