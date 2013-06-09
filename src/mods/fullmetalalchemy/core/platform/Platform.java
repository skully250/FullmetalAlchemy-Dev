package mods.fullmetalalchemy.core.platform;

import java.io.File;

import cpw.mods.fml.client.registry.RenderingRegistry;

import mods.fullmetalalchemy.core.FullmetalAlchemy;
import mods.fullmetalalchemy.core.renderer.RenderKunaiEnder;
import mods.fullmetalalchemy.entities.EntityKunaiEnder;
import mods.fullmetalalchemy.item.FMAItems;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import apexapi.common.utils.ApexNBTUtils;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Platform {

	private File moduleDir = new File(getFMADir(), "modules");

	public static Platform instance() {

		return FullmetalAlchemy.platform;
	}

	public void registerRenderThings() {
	}

	public File getFMADir() {

		return new File(System.getProperty("user.dir"), "FullmetalAlchemy");
	}

	public File getModuleDir() {

		return moduleDir;
	}

	public void makeModules() {

		try {

			boolean created = getModuleDir().mkdirs();
			if(created) {

				FullmetalAlchemy.logger.debug("Modules directory created at "
						+ getModuleDir().getAbsolutePath().replace("\\.", ""));
			} else if(getModuleDir().exists()) {

				FullmetalAlchemy.logger.debug("Modules directory exits at "
						+ getModuleDir().getAbsolutePath().replace("\\.", ""));
			}
		} catch(Exception e) {

			throw new RuntimeException(
					"Error: Modules directory not found or created, please do a clean re-installation, reason :"
							+ e);
		}
	}

	public void saveData(WorldServer world, NBTTagCompound tag) {

		ApexNBTUtils.saveData(world, tag, "FMA");
	}

	public NBTTagCompound loadData(WorldServer world) {

		return ApexNBTUtils.loadData(world, "FMA");
	}

	public void registerKeyBinds() {

	}

	public void transmuteBlock(int x, int y, int z, int blockID, int meta, EntityPlayer player, World world) {

		player.sendChatToPlayer("HEHE");
	}
}
