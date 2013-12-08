package skully.fma.item.alchemical;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import skully.fma.item.ItemFMA;


public class ItemIceGauntlet extends ItemFMA {

	public ItemIceGauntlet(int par1) {
		super(par1);
		setMaxStackSize(1);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode)) {
			list.add("if snowball is in the inventory");
			list.add("Then will freeze 3x3 to ice");
		} else {
			list.add("Hold Shift for more info");
		}
	}


	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if(par7 == 0) {
			--par5;
		}

		if(par7 == 1) {
			++par5;
		}

		if(par7 == 2) {
			--par6;
		}

		if(par7 == 3) {
			++par6;
		}

		if(par7 == 4) {
			--par4;
		}

		if(par7 == 5) {
			++par4;
		}

		if(!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
			return false;
		} else {
			for(int i = 0; i < par2EntityPlayer.inventory.mainInventory.length; i++) {
				if(par2EntityPlayer.inventory.mainInventory[i] != null) {
					if(par2EntityPlayer.inventory.mainInventory[i].getItem() == Item.snowball) {
						{
							//if (par3World.isBlockFreezable(par4, par5 + 1, par6)) {
								par2EntityPlayer.inventory.consumeInventoryItem(Item.snowball.itemID);
								par2EntityPlayer.inventory.inventoryChanged = true;
								par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "FingerClick", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
								par3World.setBlock(par4, par5, par6, Block.ice.blockID);
								par3World.setBlock(par4 + 1, par5, par6 - 1, Block.ice.blockID);
								par3World.setBlock(par4 - 1, par5, par6 + 1, Block.ice.blockID);
								par3World.setBlock(par4 + 1, par5, par6 + 1, Block.ice.blockID);
								par3World.setBlock(par4 - 1, par5, par6 - 1, Block.ice.blockID);
								par3World.setBlock(par4 + 1, par5, par6, Block.ice.blockID);
								par3World.setBlock(par4, par5, par6 + 1, Block.ice.blockID);
								par3World.setBlock(par4 - 1, par5, par6, Block.ice.blockID);
								par3World.setBlock(par4, par5, par6 - 1, Block.ice.blockID);
							//}
						}
						break;
					}
				}
			}
			return true;
		}
	}

}
