package mods.fullmetalalchemy.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;


/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemFMAMeta extends Item {
	
	public ItemFMAMeta(int id) {
		super(id);
		
		maxStackSize = 64;
		setCreativeTab(CreativeTabs.tabMaterials);
		setUnlocalizedName("MetaTest");
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public Icon getIconFromDamage(Icon i) {
		return i;
	}
	
	@Override
	public String getItemNameIS(ItemStack stack) {
		return names[stack.getItemDamage()];
	}
}
