package mods.fullmetalalchemy.core.platform;

import java.io.File;

import mods.fullmetalalchemy.core.FullmetalAlchemy;
import mods.fullmetalalchemy.core.client.ClientKeybindHandler;
import mods.fullmetalalchemy.core.renderer.RenderKunaiEnder;
import mods.fullmetalalchemy.entities.EntityKunaiEnder;
import mods.fullmetalalchemy.item.FMAItems;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
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
@SuppressWarnings("static-access")
public class ClientPlatform extends Platform {

	private File moduleDir = new File(getFMADir(), "modules");

	public static Platform instance() {

		return FullmetalAlchemy.platform;
	}

	@Override
	public void registerRenderThings() {
		RenderingRegistry.registerEntityRenderingHandler(EntityKunaiEnder.class, new RenderKunaiEnder(FMAItems.KunaiEnder));
	}

	@Override
	public File getFMADir() {

		return new File(FMLClientHandler.instance().getClient()
				.getMinecraftDir().getAbsolutePath(), "FullmetalAlchemy");
	}

	@Override
	public File getModuleDir() {

		return moduleDir;
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
