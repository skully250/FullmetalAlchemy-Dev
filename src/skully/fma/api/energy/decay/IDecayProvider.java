package skully.fma.api.energy.decay;

import skully.fma.api.energy.IAlchEnergyProvider;

public interface IDecayProvider extends IAlchEnergyProvider {
	
	public int provideDecay(int amount);
	
}
