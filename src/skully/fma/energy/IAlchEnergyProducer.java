package skully.fma.energy;

public interface IAlchEnergyProducer {
	
	public void produceEnergy(int energy);
	
	public int produceDecay(int energyProduced, int percentOfDecay);
	
}
