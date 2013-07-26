package skully.fma.world;

public class FMATransPower {
	public static int transEnergy;
	public static int transDecay;
	
	public static void modifyEnergy(int amount) {
		transEnergy += amount;
	}
	
	public static void modifyDecay(int amount) {
		transDecay += amount;
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
	
	public static void alchemicalSideEffects() {
		if (transDecay >= 25) {
			//code here
		}
		else if (transDecay >= 50) {
			//code here
		}
		else if (transDecay >= 75) {
			//code here
		}
		else if (transDecay >= 100) {
			//code here
		}
	}
}
