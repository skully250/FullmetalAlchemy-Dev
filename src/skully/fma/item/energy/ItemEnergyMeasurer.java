package skully.fma.item.energy;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import skully.fma.energy.IAlchEnergyRequester;
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
        TileEntityCircle tile = (TileEntityCircle)par2World.getBlockTileEntity(x, y, z);
        int l = TileEntityCircle.energy;
        System.out.println(l);
        par3EntityPlayer.addChatMessage("Energy: " + l);
    }

	@Override
	public void requestEnergy(int amount, boolean decayIncrease) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disperseEnergy(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useEnergy(int amount, boolean usesDecay) {
		// TODO Auto-generated method stub
		
	}
}
