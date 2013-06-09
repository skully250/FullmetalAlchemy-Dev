package mods.fullmetalalchemy.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockOres extends BlockFMA {

    public BlockOres(int id) {

        super(id, Material.rock);
    }

    public BlockOres(int id, Item blockDrops, int amount) {

        this(id, new ItemStack(blockDrops, amount));
    }

    public BlockOres(int id, ItemStack blockDrops) {

        this(id);
        this.blockDrops = blockDrops;
    }

    public BlockOres(int id, Block blockDrops, int amount) {

        this(id, new ItemStack(blockDrops, amount));
    }
}
