package skully.fma.core.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.nbt.NBTTagCompound;
import skully.fma.core.helper.NBThelper;
import skully.fma.creativetab.CreativeTabFMA;


/**
 * @author viper283
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class FMAUtils {

    public static final CreativeTabs fmaTab = new CreativeTabFMA();

    public static NBTTagCompound tagCompound = new NBTTagCompound();
    public static final NBThelper nbtHelper = new NBThelper();
}
