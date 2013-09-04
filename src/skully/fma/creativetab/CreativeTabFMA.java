package skully.fma.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import skully.fma.item.FMAItems;


/**
 * @author viper283
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CreativeTabFMA extends CreativeTabs {

    public CreativeTabFMA() {

        super(getNextID(), "FMA");
    }

    @Override
    public Item getTabIconItem() {

        return FMAItems.pStone;
    }
}
