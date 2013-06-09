package mods.fullmetalalchemy.block;

import mods.fullmetalalchemy.item.FMAItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockCircles extends BlockFMA 
{
	public BlockCircles(int par1) {
		super(par1, Material.snow);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player,int par6, float par7, float par8, float par9)
	{
		boolean First = true;
		boolean Second = true;
		boolean Third = true;
		boolean Fourth = true;
		if (world.isRemote)
		{
			return true;
		}
		else
		{
			if (player.getCurrentEquippedItem() != null)
				if (player.getCurrentEquippedItem().getItem() == Item.enderPearl && player.inventory.hasItem(FMAItems.Kunai.itemID)) {
					int i1 = world.getBlockMetadata(par2, par3, par4);
					int k1 = 8 - (i1 & 8);
					player.inventory.consumeInventoryItem(Item.enderPearl.itemID);
					player.inventory.consumeInventoryItem(FMAItems.Kunai.itemID);
					world.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "Transmute", 0.3F, k1 > 0 ? 0.6F : 0.5F);
					player.inventoryContainer.detectAndSendChanges();
					player.inventory.addItemStackToInventory(new ItemStack(FMAItems.KunaiEnder));
				}


				else if (player.getCurrentEquippedItem().getItem() ==  Item.blazePowder && player.inventory.hasItem(FMAItems.Kunai.itemID)) {
					int waitTime = 30; // 1.5 seconds
					while(waitTime != 0) 
					{
						waitTime--;
					}
					player.inventory.consumeInventoryItem(Item.blazePowder.itemID);
					long time = world.getWorldTime();
					player.inventory.consumeInventoryItem(FMAItems.Kunai.itemID);
					player.inventoryContainer.detectAndSendChanges();
					player.inventory.addItemStackToInventory(new ItemStack(FMAItems.KunaiFire));
				}
		}
		return true;
	}


	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return 0;
	}
}
