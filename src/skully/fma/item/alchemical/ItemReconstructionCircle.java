package skully.fma.item.alchemical;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import skully.fma.core.enums.EnumState;
import skully.fma.core.enums.TattooEnumState;
import skully.fma.core.implement.IKeyBound;
import skully.fma.core.implement.IStatedItem;
import skully.fma.core.packet.PacketManager;
import skully.fma.core.util.ConvertUtil;
import skully.fma.core.util.Resources;
import skully.fma.item.ItemFMA;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemReconstructionCircle extends ItemFMA implements IStatedItem, IKeyBound  {

	private static final EntityPlayerMP par2EntityPlayer = null;
	NBTTagCompound compound;
	public static int trans;
	public static String oState;
	public PacketManager manager;

	public ItemReconstructionCircle(int par1, TattooEnumState defaultState) {
		super(par1);
		setMaxStackSize(1);
		setMaxDamage(1024);

		if(getState() != null && !(getState().equals("")))
			getState();
		else
			setState(defaultState);
	}

	private Icon icons[] = new Icon[256];

	/**
	 * 0 - Deconstruction 1 - Reconstruction
	 */
	private int stateFlag;
	private int state;

	@Override
	public String getState() {

		//String state = FMAUtils.nbtHelper.getString(new ItemStack(FMAItems.ReconstructionCircle), "ReconstructionCircleState");
		if (state == 0)
			oState = "Construct";
		else if (state == 1)
			oState = "Dismantle";

		return oState;
	}

	public void setState(TattooEnumState defaultState) {

		//FMAUtils.nbtHelper.setString(new ItemStack(FMAItems.ReconstructionCircle), "ReconstructionCircleState", defaultState.getName());
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
		if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode)) {
			switch(state) {
			case 0 :
				list.add("Reconstruction");
				list.add("Will Reconstruct 3x3x3 of dirt where clicked");
				break;
			case 1 :
				list.add("Deconstruction");
				list.add("Will Deconstruct 3x3x3 where clicked");
				break;
			}
		} else
			list.add("Hold Shift for more info");
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {

		boolean updateTextures = getState() != null ? true : false;

		if(updateTextures) {

			if(state == 0) {

				stateFlag = 0;
			} else if(state == 1) {

				stateFlag = 1;
			} else {

				stateFlag = 0;
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public void keyBindActions() {

		if (state == 0)
			state = 1;
		else if (state == 1)
			state = 0;
		else
			state = 0;
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

		switch(state) {
		case 0 :
			if (par3World.isRemote) {
				if (trans > 0) {
					par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "EarthMoving", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);

					trans += -1;
					EntityPlayerMP player = (EntityPlayerMP) par2EntityPlayer;
					//System.out.println(Trans);
					/*par3World.setBlock(par4, par5, par6, Block.dirt.blockID) ;
					par3World.setBlock(par4 + 1, par5, par6, Block.dirt.blockID);
					par3World.setBlock(par4 - 1, par5, par6, Block.dirt.blockID);
					par3World.setBlock(par4, par5 + 1, par6, Block.dirt.blockID);
					par3World.setBlock(par4 + 1, par5 + 1, par6, Block.dirt.blockID);
					par3World.setBlock(par4 - 1, par5 + 1, par6, Block.dirt.blockID);
					par3World.setBlock(par4, par5 + 2, par6, Block.dirt.blockID);
					par3World.setBlock(par4 + 1, par5 + 2, par6, Block.dirt.blockID);
					par3World.setBlock(par4 - 1, par5 + 2, par6, Block.dirt.blockID);
					par3World.setBlock(par4 + 1, par5, par6, Block.dirt.blockID);
					par3World.setBlock(par4 - 1, par5, par6, Block.dirt.blockID);*/ 
				}
			} else
				if (par3World.isRemote)
					par2EntityPlayer.sendChatToPlayer(ConvertUtil.toChatComponent("You need to deconstruct before reconstructing"));
			break;
		case 1 :			
			par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "FingerClick", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
			trans += 1;
			EntityPlayerMP player = (EntityPlayerMP) par2EntityPlayer;
			par3World.setBlock(par4, par5 - 1, par6, 0);
			par3World.setBlock(par4 + 1, par5 - 1, par6, 0);
			par3World.setBlock(par4 - 1, par5 - 1, par6, 0);
			par3World.setBlock(par4, par5 - 2, par6, 0);
			par3World.setBlock(par4 + 1, par5 - 2, par6, 0);
			par3World.setBlock(par4 - 1, par5 - 2, par6, 0);
			par3World.setBlock(par4, par5 - 3, par6, 0);
			par3World.setBlock(par4 + 1, par5 - 3, par6, 0);
			par3World.setBlock(par4 - 1, par5 - 3, par6, 0);
			break;
		}
		return true;
	}
}
