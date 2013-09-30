package skully.fma.api.energy;

public interface IAlchEnergyRequester {
	
	public int requestEnergy(int amount, boolean decayIncrease);
	
	/*
	 * if a block or item slowly disperses energy into the environment 
	 * or another block this method will be used
	 */
	public int disperseEnergy(int amount);
	
	/*
	 * theres a secret in here :P 
	 */
	public int useEnergy(int amount, boolean usesDecay);
	
}
