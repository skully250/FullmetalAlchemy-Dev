package skully.fma.core.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.nbt.NBTTagCompound;
import skully.fma.creativetab.CreativeTabFMA;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class FMAUtils {

    public static final CreativeTabs fmaTab = new CreativeTabFMA();
    
    public static NBTTagCompound tagCompound = new NBTTagCompound();
    public static final NBThelper nbtHelper = new NBThelper();

    @Deprecated
    public static void addIcons() {

//        Resources.index.addIconToIndex("chalkStick", "chalkStick");
//        Resources.index.addIconToIndex("chalkDust", "chalkDust");
//        Resources.index.addIconToIndex("flacon", "flacon");
//        Resources.index.addIconToIndex("flask", "flask");
//        Resources.index.addIconToIndex("bloodDrop", "bloodDrop");
//        Resources.index.addIconToIndex("alchemicalWool", "alchemicalWool");
//        Resources.index.addIconToIndex("alchemicalSilk", "alchemicalSilk");
    }
}
