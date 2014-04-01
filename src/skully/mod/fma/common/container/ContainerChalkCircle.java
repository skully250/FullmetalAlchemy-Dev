package skully.mod.fma.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerChalkCircle extends Container {
	
	public ContainerChalkCircle(InventoryPlayer playerInv) {
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
}
