package skully.fma.api;

import net.minecraft.creativetab.CreativeTabs;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CoreApi {

    private static CoreApi api = new CoreApi();

    private Class<?> FMAUtils;

    private static final String FMA_CLASS_LOCATION = "skully.fma.core.util.FMAUtils";

    public static CoreApi getInstance() {

        return api;
    }

    public CreativeTabs fmaTab(CreativeTabs alt) {

        try {

            if(FMAUtils == null) {

                FMAUtils = Class.forName(FMA_CLASS_LOCATION);
            }

            Object ret = FMAUtils.getField("fmaTab").get(null);

            if(ret instanceof CreativeTabs) {

                return (CreativeTabs) ret;
            } else {

                return alt;
            }

        } catch(Exception e) {

            e.printStackTrace();

            return alt;
        }
    }
}