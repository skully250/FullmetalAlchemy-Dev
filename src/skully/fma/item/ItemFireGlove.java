package skully.fma.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFireGlove extends ItemFMA {

	public ItemFireGlove(int par1) {
		super(par1);
        setMaxStackSize(1);
	}



	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par7 == 0)
		{
			--par5;
		}

		if (par7 == 1)
		{
			++par5;
		}

		if (par7 == 2)
		{
			--par6;
		}

		if (par7 == 3)
		{
			++par6;
		}

		if (par7 == 4)
		{
			--par4;
		}

		if (par7 == 5)
		{
			++par4;
		}

		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
		{
			return false;
		}
		else
		{
			if(par2EntityPlayer.getCurrentEquippedItem() !=null) {
				if(par2EntityPlayer.inventory.hasItem(Item.flint.itemID)) {
					{
						par3World.playSoundEffect((double)par4 + 0.5D, (double)par5 + 0.5D, (double)par6 + 0.5D, "FingerClick", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
						par3World.setBlock(par4, par5, par6, Block.fire.blockID) ;
						par3World.setBlock(par4 + 1, par5, par6 - 1, Block.fire.blockID);
						par3World.setBlock(par4 - 1, par5, par6 + 1, Block.fire.blockID);
						par3World.setBlock(par4 + 1, par5, par6 + 1, Block.fire.blockID);
						par3World.setBlock(par4 - 1, par5, par6 - 1, Block.fire.blockID);
						par3World.setBlock(par4 + 1, par5, par6, Block.fire.blockID);
						par3World.setBlock(par4, par5, par6 + 1, Block.fire.blockID);
						par3World.setBlock(par4 - 1, par5, par6, Block.fire.blockID);
						par3World.setBlock(par4, par5, par6 - 1, Block.fire.blockID);
					}
				}
			}
		}
		return true;
	}
}