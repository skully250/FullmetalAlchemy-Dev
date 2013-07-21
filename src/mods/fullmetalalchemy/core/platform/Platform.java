package mods.fullmetalalchemy.core.platform;

import mods.fullmetalalchemy.core.FullmetalAlchemy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Platform {

	public static Platform instance() {

		return FullmetalAlchemy.platform;
	}

	public void registerRenderThings() {
	}

	public void saveData(WorldServer world, NBTTagCompound tag) {

		//ApexNBTUtils.saveData(world, tag, "FMA");
	}

	public NBTTagCompound loadData(WorldServer world) {

		//return ApexNBTUtils.loadData(world, "FMA");
		return null;
	}

	public void registerKeyBinds() {

	}

	public void transmuteBlock(int x, int y, int z, int blockID, int meta, EntityPlayer player, World world) {

		player.addChatMessage("HEHE");
	}
}
