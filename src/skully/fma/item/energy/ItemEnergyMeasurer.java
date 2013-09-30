package skully.fma.item.energy;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import skully.fma.api.energy.IAlchEnergyRequester;
import skully.fma.item.ItemFMA;
import skully.fma.tileEntity.TileEntityCircle;


public class ItemEnergyMeasurer extends ItemFMA implements IAlchEnergyRequester {

    public ItemEnergyMeasurer(int par1) {
        super(par1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
    }

    public void onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int x, int y, int z) {
    	
    }

	@Override
	public int requestEnergy(int amount, boolean decayIncrease) {
		return amount;
	}

	@Override
	public int disperseEnergy(int amount) {
		return amount;
	}

	@Override
	public int useEnergy(int amount, boolean usesDecay) {
		return amount;
	}
}
