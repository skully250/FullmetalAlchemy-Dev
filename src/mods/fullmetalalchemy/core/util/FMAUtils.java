package mods.fullmetalalchemy.core.util;

import mods.fullmetalalchemy.creativetab.CreativeTabFMA;
import net.minecraft.creativetab.CreativeTabs;
import apexapi.common.utils.ApexNBTHelper;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class FMAUtils {

    public static final CreativeTabs fmaTab = new CreativeTabFMA();
    public static final ApexNBTHelper nbtHelper = new ApexNBTHelper(Resources.tagCompound);

    public static void addIcons() {

        Resources.index.addIconToIndex("chalkStick", "chalkStick");
        Resources.index.addIconToIndex("chalkDust", "chalkDust");
        Resources.index.addIconToIndex("flacon", "flacon");
        Resources.index.addIconToIndex("flask", "flask");
        Resources.index.addIconToIndex("bloodDrop", "bloodDrop");
        Resources.index.addIconToIndex("alchemicalWool", "alchemicalWool");
        Resources.index.addIconToIndex("alchemicalSilk", "alchemicalSilk");
    }
}
