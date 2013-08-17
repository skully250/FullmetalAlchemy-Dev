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
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir) {
		this.icons[0] = ir.registerIcon(Resources.MOD_ID + ":" + "ChalkDust");
		this.icons[1] = ir.registerIcon(Resources.MOD_ID + ":" + "flacon");
		this.icons[2] = ir.registerIcon(Resources.MOD_ID + ":" + "flask");
		this.icons[3] = ir.registerIcon(Resources.MOD_ID + ":" + "bloodDrop");
		this.icons[4] = ir.registerIcon(Resources.MOD_ID + ":" + "alchemicalWool");
		this.icons[5] = ir.registerIcon(Resources.MOD_ID + ":" + "alchemicalSilk");
		this.icons[6] = ir.registerIcon(Resources.MOD_ID + ":" + "EStone");
		this.icons[7] = ir.registerIcon(Resources.MOD_ID + ":" + "GStone");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + names[stack.getItemDamage()];
	}

	/*@Override
	public Icon getIconFromDamage(int meta) {
		return this.icons[meta];
	}*/

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {

		for (int j = 0; j < names.length; ++j){

			par3List.add(new ItemStack(par1, 1, j));
		}
	}
}
