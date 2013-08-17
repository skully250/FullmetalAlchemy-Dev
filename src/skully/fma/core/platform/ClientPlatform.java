package skully.fma.core.platform;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import skully.fma.client.render.RenderPlayerFMA;
import skully.fma.core.FullmetalAlchemy;
import skully.fma.core.client.ClientKeybindHandler;
import skully.fma.core.util.registers.FMAEventRegister;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class ClientPlatform extends Platform {

	public static Platform instance() {

		return FullmetalAlchemy.platform;
	}
	
	@Override
	public void registerHandlers() {
		FMAEventRegister.registerOverlays();
	}

	@Override
	public void registerRenderThings() {
	    RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderPlayerFMA());
		//RenderingRegistry.registerEntityRenderingHandler(EntityKunaiEnder.class, new RenderKunaiEnder(FMAItems.KunaiEnder));
	}

	@Override
	public void registerKeyBinds() {

		KeyBindingRegistry.registerKeyBinding(new ClientKeybindHandler());
	}

	@Override
	public void transmuteBlock(int x, int y, int z, int blockID, int meta, EntityPlayer player, World world) {

		world.setBlock(x, y, z, blockID);
	}
}
