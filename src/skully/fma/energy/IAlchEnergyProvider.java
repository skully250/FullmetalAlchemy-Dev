package skully.fma.energy;

public interface IAlchEnergyProvider {
	
	/*
	 * says how much energy the item or block can give
	 */
	public int provideEnergy(int amount, int decayIncrease);
	
	/*
	 * decay will play a larger role later :)
	 */
	public int increaseDecay(int amount);
	
}
