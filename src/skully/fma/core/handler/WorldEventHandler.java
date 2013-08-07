package skully.fma.core.handler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.WorldEvent;
import skully.fma.core.helper.NBThelper;
import skully.fma.item.FMAItems;
import skully.fma.item.alchemical.ItemReconstructionCircle;

public class WorldEventHandler {

	public void onWorldLoad(WorldEvent.Load world) {
		NBThelper.getNBTCompoundForItemStack(new ItemStack(FMAItems.ReconstructionCircle));
	}
	
	public void onWorldSave(WorldEvent.Save world) {
		NBThelper.setInteger(new ItemStack(FMAItems.ReconstructionCircle), "Transmutations", ItemReconstructionCircle.trans);
	}
}