package mods.fullmetalalchemy.creativetab;

import mods.fullmetalalchemy.item.FMAItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CreativeTabFMA extends CreativeTabs {

    public CreativeTabFMA() {

        super(getNextID(), "FMA");
    }

    @Override
    public Item getTabIconItem() {

        return FMAItems.metaTest;
    }
}
