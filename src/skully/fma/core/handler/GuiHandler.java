package skully.fma.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import skully.fma.container.ContainerInfuser;
import skully.fma.core.lib.GuiIDs;
import skully.fma.gui.GuiInfuser;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if  (ID == GuiIDs.INFUSER) {
			return new ContainerInfuser(player.inventory, world, x, y, z);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == GuiIDs.INFUSER) {
			return new GuiInfuser(player.inventory, world, x, y, z);
		}
		return null;
	}

}
