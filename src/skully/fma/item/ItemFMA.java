package skully.fma.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import skully.fma.core.util.Resources;


/**
 * @author viper283
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemFMA extends Item {

    /**
     * @param par1
     */
    public ItemFMA(int par1) {

        super(par1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon(Resources.MOD_ID.toLowerCase()
                + ":"
                + this.getUnlocalizedName().substring(
                this.getUnlocalizedName().indexOf(".") + 1));
    }
}
