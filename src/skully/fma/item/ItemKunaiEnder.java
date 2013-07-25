package skully.fma.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import skully.fma.entities.EntityKunaiEnder;

public class ItemKunaiEnder extends ItemFMA {

	public ItemKunaiEnder(int par1) 
	{
		super(par1);
		this.setMaxStackSize(5);
	}

	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("With 5, will teleport player to location");
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par3EntityPlayer.capabilities.isCreativeMode)
		{
			return par1ItemStack;
		}
		else if (par3EntityPlayer.ridingEntity != null)
		{
			return par1ItemStack;
		}
		else if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(FMAItems.KunaiEnder, 5)));
		{
			par3EntityPlayer.inventory.consumeInventoryItem(FMAItems.KunaiEnder.itemID);
			par3EntityPlayer.inventory.consumeInventoryItem(FMAItems.KunaiEnder.itemID);
			par3EntityPlayer.inventory.consumeInventoryItem(FMAItems.KunaiEnder.itemID);
			par3EntityPlayer.inventory.consumeInventoryItem(FMAItems.KunaiEnder.itemID);
			par3EntityPlayer.inventory.consumeInventoryItem(FMAItems.KunaiEnder.itemID);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(new EntityKunaiEnder(par2World, par3EntityPlayer));
			}
			return par1ItemStack;
		}
	}
}
