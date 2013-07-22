package skully.fma.item;

import java.util.List;

import skully.fma.core.enums.BagEnumState;
import skully.fma.core.enums.EnumState;
import skully.fma.core.enums.TattooEnumState;
import skully.fma.core.implement.IKeyBound;
import skully.fma.core.implement.IStatedItem;
import skully.fma.core.util.ChatUtils;
import skully.fma.core.util.FMAUtils;
import skully.fma.core.util.Resources;
import skully.fma.crafting.ReconstructionRecipes;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
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

			//			setState(defaultState);
		}
	}

	private Icon icons[] = new Icon[256];

	/**
	 * 0 - Holding 1 - Taking
	 */
	private int stateFlag;

	@Override
	public String getState() {

		//String state = FMAUtils.nbtHelper.getString(null, "ChalkBagState");
		String state = "Test";

		return state;
	}

	public void setState(BagEnumState defaultState) {

		//FMAUtils.nbtHelper.setString(null, "ChalkBagState", defaultState.getName());
	}

	@Override
	public void setState(EnumState state) {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		this.icons[0] = iconRegister.registerIcon(Resources.MOD_ID
				.toLowerCase() + ":" + "ChalkBagOff");
		this.icons[1] = iconRegister.registerIcon(Resources.MOD_ID
				.toLowerCase() + ":" + "ChalkBagOn");
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
		if (player.isSneaking())
			//list.add("\u00a78" + getState());
			list.add("Testing Things");
		else
			list.add("Hold <Shift> for more info");
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
		if(par2World.isRemote)
			if(getState() == "Holding" && player.inventory.hasItem(FMAItems.ChalkStick.itemID)) {
				player.inventory.consumeInventoryItem(FMAItems.ChalkStick.itemID);
				Chalk++;
				player.inventoryContainer.detectAndSendChanges();
				player.sendChatToPlayer(ChatUtils.toChatComponent("you have " + Chalk + " in this bag"));
				return par1ItemStack;

			} else if (getState() == "Taking" && Chalk > 0) {
				player.inventory.addItemStackToInventory(new ItemStack(FMAItems.ChalkStick, 1));
				Chalk--;
				player.inventoryContainer.detectAndSendChanges();
				player.sendChatToPlayer(ChatUtils.toChatComponent("you have " + (Chalk - 1) + " remaining in this bag"));
				return par1ItemStack;
			}
		return par1ItemStack;
	}
}
