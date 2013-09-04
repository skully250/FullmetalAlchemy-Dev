package skully.fma.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import skully.fma.core.util.Resources;

import java.util.List;


/**
 * @author viper283
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemFMAMeta extends ItemFMA {

    private String[] names;
    private Icon[] icons;

    public ItemFMAMeta(int par1, String[] names, Icon[] icons) {

        super(par1);
        assert names.length == icons.length;
        this.names = names;
        this.icons = icons;
        setHasSubtypes(true);
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

        icons[0] = ir.registerIcon(Resources.MOD_ID + ":meta/ChalkDust");
        icons[1] = ir.registerIcon(Resources.MOD_ID + ":meta/Flacon");
        icons[2] = ir.registerIcon(Resources.MOD_ID + ":meta/Flask");
        icons[3] = ir.registerIcon(Resources.MOD_ID + ":meta/BloodDrop");
        icons[4] = ir.registerIcon(Resources.MOD_ID + ":meta/AlchemicalWool");
        icons[5] = ir.registerIcon(Resources.MOD_ID + ":meta/AlchemicalSilk");
        icons[6] = ir.registerIcon(Resources.MOD_ID + ":meta/EStone");
        icons[7] = ir.registerIcon(Resources.MOD_ID + ":meta/GStone");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {

        for(int j = 0; j < names.length; ++j) {

            par3List.add(new ItemStack(par1, 1, j));
        }
    }
}
