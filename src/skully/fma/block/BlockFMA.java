package skully.fma.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import skully.fma.core.util.Resources;

import java.util.Random;


/**
 * @author viper283
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockFMA extends Block {

    protected ItemStack blockDrops;

    public BlockFMA(int par1, Material par2Material) {
        super(par1, par2Material);
    }

    public BlockFMA(int par1, Material par2Material, ItemStack blockDrops) {
        super(par1, par2Material);
        this.blockDrops = blockDrops;
    }

    public BlockFMA(int par1, Material par2Material, Block blockDrops, int amount) {
        this(par1, par2Material, new ItemStack(blockDrops, amount));
    }

    public BlockFMA(int par1, Material par2Material, Item blockDrops, int amount) {
        this(par1, par2Material, new ItemStack(blockDrops, amount));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Resources.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return blockDrops != null ? blockDrops.itemID : 0;
    }

    @Override
    public int quantityDropped(Random rand) {
        return this.blockDrops.stackSize;
    }
}
