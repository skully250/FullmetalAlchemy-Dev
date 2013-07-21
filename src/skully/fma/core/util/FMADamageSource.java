package skully.fma.core.util;

import net.minecraft.util.DamageSource;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class FMADamageSource extends DamageSource {

    public static DamageSource crimsonAlchemy = new FMADamageSource(
            "crimsonAlchemy").setFireDamage();

    public FMADamageSource(String par1Str) {

        super(par1Str);
    }
}
