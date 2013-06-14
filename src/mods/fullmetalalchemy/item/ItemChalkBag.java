package mods.fullmetalalchemy.item;

import java.util.List;

import mods.fullmetalalchemy.core.enums.BagEnumState;
import mods.fullmetalalchemy.core.enums.EnumState;
import mods.fullmetalalchemy.core.enums.TattooEnumState;
import mods.fullmetalalchemy.core.implement.IKeyBound;
import mods.fullmetalalchemy.core.implement.IStatedItem;
import mods.fullmetalalchemy.core.util.FMAUtils;
import mods.fullmetalalchemy.core.util.Resources;
import mods.fullmetalalchemy.crafting.ReconstructionRecipes;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemChalkBag extends ItemFMA implements IStatedItem, IKeyBound  {
	
	public int Chalk = 0;
	public int PChalk;

	public ItemChalkBag(int par1, BagEnumState defaultState) {
		super(par1);
		setMaxStackSize(1);

		if(getState() != null && !(getState().equals(""))) {

			getState();
		} else {

			setState(defaultState);
		}
	}

	private Icon icons[] = new Icon[256];

	/**
	 * 0 - Holding 1 - Taking
	 */
	private int stateFlag;

	@Override
	public String getState() {

		String state = FMAUtils.nbtHelper.readString("ChalkBagState");

		return state;
	}

	public void setState(BagEnumState defaultState) {

		FMAUtils.nbtHelper.writeString("ChalkBagState", defaultState.getName());
	}

	@Override
	public void setState(EnumState state) {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		this.icons[0] = iconRegister.registerIcon(Resources.MOD_ID
				.toLowerCase() + ":" + "ReconstructionCircleOff");
		this.icons[1] = iconRegister.registerIcon(Resources.MOD_ID
				.toLowerCase() + ":" + "ReconstructionCircleOn");
	}

	@Override
	public Icon getIconFromDamage(int par1) {

		return icons[stateFlag];
	}

	@SuppressWarnings({
		"unchecked", "rawtypes"
	})
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

		list.add("\u00a78" + getState());
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {

		boolean updateTextures = getState() != null ? true : false;

		if(updateTextures) {

			if(getState().equalsIgnoreCase("Taking")) {

				stateFlag = 0;
			} else if(getState().equalsIgnoreCase("Holding")) {

				stateFlag = 1;
			} else {

				stateFlag = 0;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {

		return true;
	}

	@Override
	public void keyBindActions() {

		if(getState().equals("Holding")) {

			setState(BagEnumState.OFF);
		} else if(getState().equals("Taking")) {

			setState(BagEnumState.ON);
			ReconstructionRecipes.initialize();
		} else {

			setState(BagEnumState.OFF);
		}
	}


	
	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
	{
		if(getState() == "Holding" && player.inventory.hasItem(FMAItems.ChalkStick.itemID)) {
			PChalk = Chalk - 1;
			player.inventory.consumeInventoryItem(FMAItems.ChalkStick.itemID);
			Chalk = Chalk + 1;
			player.inventoryContainer.detectAndSendChanges();
			player.sendChatToPlayer("you have " + PChalk + " in this bag");
			return par1ItemStack;
			
		} else if (getState() == "Taking" && Chalk >= 2) {
			player.inventory.addItemStackToInventory(new ItemStack(FMAItems.ChalkStick, 1));
			Chalk = Chalk + 1;
			player.inventoryContainer.detectAndSendChanges();
			player.sendChatToPlayer("you have " + PChalk + " remaining in this bag");
			return par1ItemStack;
		}
		return par1ItemStack;
	}
}
