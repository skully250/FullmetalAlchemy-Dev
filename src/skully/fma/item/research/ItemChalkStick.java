package skully.fma.item.research;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import skully.fma.block.FMABlocks;
import skully.fma.core.enums.EnumState;
import skully.fma.core.implement.IKeyBound;
import skully.fma.core.implement.IStatedItem;
import skully.fma.item.ItemFMA;


public class ItemChalkStick extends ItemFMA implements IKeyBound, IStatedItem
{

	private int chalkState;

	public ItemChalkStick(int par1) {
		super(par1);
		setMaxStackSize(1);
		chalkState = 0;
	}

	@Override
	public boolean shouldRotateAroundWhenRendering()
	{
		if (chalkState == 0) {
			return false;
		} else if (chalkState == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (chalkState == 0 && Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode))
			list.add("Drawing crafting circle");
		else if (chalkState == 1 && Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode))
			list.add("Drawing research circle");
		 else 
		list.add("Hold Shift for more info");
}

@Override
public String getState() {
	return null;
}

@Override
public void setState(EnumState state) {

}

@Override
public void keyBindActions() {
	if (chalkState == 0)
		chalkState = 1;
	else if (chalkState == 1)
		chalkState = 0;
	else
		chalkState = 1;
}

/**
 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
 */
@Override
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
		if (chalkState == 0) {
			int i1 = par3World.getBlockId(par4, par5, par6);

			if (i1 == 0)
			{
				par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "ChalkDraw", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				par3World.setBlock(par4, par5, par6, FMABlocks.craftingCircle.blockID);
				--par1ItemStack.stackSize;
			}
		} else if (chalkState == 1) {
			int i1 = par3World.getBlockId(par4, par5, par6);

			if (i1 == 0)
			{
				par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "ChalkDraw", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				par3World.setBlock(par4, par5, par6, FMABlocks.researchCircle.blockID);
				--par1ItemStack.stackSize;
			}
		}
		return true;
	}
}
}
