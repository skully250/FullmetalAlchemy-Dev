package skully.fma.api.blocks;

import skully.fma.api.energy.decay.IDecayProvider;


public interface IInfuser extends IDecayProvider {
	
	//Decay Methods
	public int getDecay();
	public void setDecay(int decayAmount);
}