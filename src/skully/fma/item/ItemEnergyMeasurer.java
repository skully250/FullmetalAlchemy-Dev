package skully.fma.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import skully.fma.world.FMATransPower;

public class ItemEnergyMeasurer extends ItemFMA {
	
	public ItemEnergyMeasurer(int par1) {
		super(par1);
	}

	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		//if (player.isSneaking()){
			list.add("Trans Energy: " + FMATransPower.transEnergy);
			list.add("Trans Decay: " + FMATransPower.transDecay);
			list.add(FMATransPower.decayDisplay());
		//} else {
			//list.add("Hold shift for more info");
		//}
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (par3EntityPlayer.isSneaking()) {
			FMATransPower.modifyDecay(25);
			return par1ItemStack;
		} else {
		FMATransPower.modifyDecay(-25);
		return par1ItemStack;
		}
	}
}
