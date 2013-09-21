package skully.fma.energy;

public interface IAlchEnergyRequester {
	
	public void requestEnergy(int amount, boolean decayIncrease);
	
	/*
	 * if a block or item slowly disperses energy into the environment 
	 * or another block this method will be used
	 */
	public void disperseEnergy(int amount);
	
	/*
	 * theres a secret in here :P 
	 */
	public void useEnergy(int amount, boolean usesDecay);
	
}
