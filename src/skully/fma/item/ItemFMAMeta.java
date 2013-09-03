package skully.fma.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import skully.fma.core.util.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemFMAMeta extends ItemFMA {

	private String[] names;
	private Icon[] icons;

	public ItemFMAMeta(int par1, String[] names, Icon[] icons) {

		super(par1);
		//this.setMaxDamage(0);
		assert names.length == icons.length;
		setHasSubtypes(true);
		this.names = names;
		this.icons = icons;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + names[stack.getItemDamage()];
	}

	@Override
	public Icon getIconFromDamage(int meta) {
		return this.icons[meta];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir) {
		icons = new Icon[8];
		
		for (int i = 0; i < icons.length; i++) {
			icons[i] = ir.registerIcon(Resources.MOD_ID + ":meta/" + (this.getUnlocalizedName().substring(5)) + i);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {

		for (int j = 0; j < names.length; ++j){

			par3List.add(new ItemStack(par1, 1, j));
		}
	}
}
