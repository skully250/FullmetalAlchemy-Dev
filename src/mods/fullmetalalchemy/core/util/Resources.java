package mods.fullmetalalchemy.core.util;

import net.minecraft.nbt.NBTTagCompound;
import apexapi.common.utils.ApexNBTHelper;
import apexapi.common.utils.ApexTextureIndexBase;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Resources {

    public static final String MOD_ID = "FullmetalAlchemy";
    public static final String MOD_NAME = "Fullmetal Alchemy";
    public static final String MOD_VERSION = "0.0.5";
    public static final String CLIENT_PLATFORM_LOCATION = "mods.fullmetalalchemy.core.platform.ClientPlatform";
    public static final String PLATFORM_LOCATION = "mods.fullmetalalchemy.core.platform.Platform";
    public static boolean debugMode = false;
    public static ApexTextureIndexBase index = new ApexTextureIndexBase("fullmetalalchemy");
    public static NBTTagCompound tagCompound = new NBTTagCompound();
    
    public static ApexNBTHelper nbtHelper = new ApexNBTHelper(tagCompound);
}