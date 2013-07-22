package skully.fma.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import skully.fma.item.FMAItems;
import cpw.mods.fml.common.ICraftingHandler;

public class FMACraftingHandler implements ICraftingHandler {

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
		
		for(int i = 0; i < craftMatrix.getSizeInventory(); i++) {
			
			if(craftMatrix.getStackInSlot(i) != null) {
				ItemStack j = craftMatrix.getStackInSlot(i);
				
				if(j.getItem() != null && j.getItem() == FMAItems.ReconstructionCircle) {
					
					ItemStack k = new ItemStack(FMAItems.ReconstructionCircle, 2);
					player.inventory.setInventorySlotContents(0, k);
				}
			}
		}

	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {

	}
}