package skully.fma.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemMeta extends ItemFMAMeta {
	@SideOnly(Side.CLIENT)
	private Icon[] icons;

    public ItemMeta(int par1) {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1) {
        int j = MathHelper.clamp_int(par1, 0, 7);
    	return this.icons[j];
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < 8; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }
}