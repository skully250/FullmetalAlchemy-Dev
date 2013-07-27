package skully.fma.item.alchemical;

import java.util.List;

import skully.fma.entity.EntityKunaiFire;
import skully.fma.item.FMAItems;
import skully.fma.item.ItemFMA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemKunaiFire extends ItemFMA {

	public ItemKunaiFire(int par1) {
		super(par1);
		this.setMaxStackSize(5);
	}
	
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("With 5, will cause an explosion when thrown");
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
        else if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(FMAItems.KunaiFire, 5)))
        {

            par3EntityPlayer.inventory.consumeInventoryItem(FMAItems.KunaiFire.itemID);
            par3EntityPlayer.inventory.consumeInventoryItem(FMAItems.KunaiFire.itemID);
            par3EntityPlayer.inventory.consumeInventoryItem(FMAItems.KunaiFire.itemID);
            par3EntityPlayer.inventory.consumeInventoryItem(FMAItems.KunaiFire.itemID);
            par3EntityPlayer.inventory.consumeInventoryItem(FMAItems.KunaiFire.itemID);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(new EntityKunaiFire(par2World, par3EntityPlayer));
            }

            return par1ItemStack;
        }
        else
        {
        	return par1ItemStack;
        }
    }
}
