package skully.fma.item.alchemical;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import skully.fma.core.enums.EnumState;
import skully.fma.core.helper.NBThelper;
import skully.fma.core.helper.TransHelper;
import skully.fma.core.implement.IKeyBound;
import skully.fma.core.implement.IStatedItem;
import skully.fma.core.particle.TransmutationParticle;
import skully.fma.core.util.Resources;
import skully.fma.energy.IAlchEnergyRequester;
import skully.fma.fx.FXResearch;
import skully.fma.item.FMAItems;
import skully.fma.item.ItemFMA;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**
 * @author viper283
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemPStone extends ItemFMA implements IStatedItem, IKeyBound, IAlchEnergyRequester {

	private Icon icons[] = new Icon[256];
	public static String power = "Transmutational Power";
	public static int power2;

	/**
	 * 0 - inactive 1 - on
	 */
	private int stateFlag;
	private int state;

	public ItemPStone(int par1, EnumState defaultState) {

		super(par1);
		setMaxStackSize(1);

		if(getState() != null && !(getState().equals(""))) {

			getState();
		} else {

			setState(defaultState);
		}
	}

	@Override
	public String getState() {

		//String state = FMAUtils.nbtHelper.getString(new ItemStack(FMAItems.pStone), "PStoneState");
		String oState = null;
		if(state == 0) {
			oState = "Off";
		} else if(state == 1) {
			oState = "On";
		}

		return oState;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target) {
		return false;
	}

	@Override
	public void setState(EnumState state) {

		//FMAUtils.nbtHelper.setString(new ItemStack(FMAItems.pStone), "PStoneState", state.getName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		this.icons[0] = iconRegister.registerIcon(Resources.MOD_ID.toLowerCase() + ":" + "pStoneOff");
		this.icons[1] = iconRegister.registerIcon(Resources.MOD_ID.toLowerCase() + ":" + "pStoneOn");
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
		if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode)) {
			list.add("\u00a78" + getState());
			list.add("Will randomly transmute, so long");
			list.add("as power is provided");
			list.add("Power: " + power2);
		} else {
			list.add("Hold Shift for more info");
		}
	}

	public void renderParticle(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4) {
		double adjAngle = 25.0D;
		double dist = 0.1D;

		EntityPlayer center = Minecraft.getMinecraft().thePlayer;

		double posX = center.posX - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
		double posY = center.posY - 0.2 - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
		double posZ = center.posZ + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;

		Random rand = new Random();
		float speed = 0.02F;

		FXResearch fx = new FXResearch(Minecraft.getMinecraft().theWorld, posX, posY, posZ, (rand.nextFloat() - rand.nextFloat()) * speed,
				(rand.nextFloat() - rand.nextFloat()) * speed, (rand.nextFloat() - rand.nextFloat()) * speed, 500);
		fx.maxAge = 40;

		for (int i = 0; i < 2; i++)
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {

		par1ItemStack.setItemDamage(NBThelper.getInt(new ItemStack(this), power));

		boolean updateTextures = getState() != null ? true : false;

		if(updateTextures) {

			if(state == 0) {

				stateFlag = 0;
			} else if(state == 1) {
				renderParticle(par1ItemStack, par2World, par3Entity, par4);
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

	public void chargePStone() {
		if(Minecraft.getMinecraft().thePlayer.inventory.hasItem(FMAItems.EnergyStore.itemID)) {
			power2 += 1;
		}
	}

	@Override
	public void keyBindActions() {
		if(state == 0) {
			state = 1;
		} else if(state == 1) {
			state = 0;
		} else {
			state = 0;
		}
	}

	public static void transmuteParticles(World world, double par2, double par3, double par4, EntityPlayer player) {
		for(int i = 0; i < 1000 / 5; i++)
		{
			float r1 = player.worldObj.rand.nextFloat() * 360.0F;
			float mx = -MathHelper.sin(r1 / 180.0F * 3.141593F) / 5.0F;
			float mz = MathHelper.cos(r1 / 180.0F * 3.141593F) / 5.0F;

			double adjAngle = 35.0D;
			double dist = 0.4D;

			EntityPlayer center = player;

			double posX = par2 + 0.57; //- Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
			double posY = par3 + 0.2; //- Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
			double posZ = par4 + 0.5; //+ Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;

			//world.spawnEntityInWorld(new EntityLightningBolt(world, par2, par3 + 1, par4));
			TransmutationParticle.spawnPStoneFX(posX, posY + 0.9, posZ, mx, 0, mz, 20, false, true, true);
		}
	}


	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {

		int ID = par3World.getBlockId(par4, par5, par6);
		int meta = par3World.getBlockMetadata(par4, par5, par6);
		TileEntity te = par3World.getBlockTileEntity(par4, par5, par6);

		float r1 = player.worldObj.rand.nextFloat() * 360.0F;
		float mx = -MathHelper.sin(r1 / 180.0F * 3.141593F) / 5.0F;
		float mz = MathHelper.cos(r1 / 180.0F * 3.141593F) / 5.0F;

		double adjAngle = 35.0D;
		double dist = 0.4D;

		EntityPlayer center = player;

		double posX = center.posX - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
		double posY = center.posY - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
		double posZ = center.posZ + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;

		if(player.isSneaking()) {
			NBThelper.setInteger(new ItemStack(this), power, 10);
		}
		if(!par3World.isRemote) {

			if(te != null) {
				// We dont want to do anything if the block has a tile entity
				return false;
			} else {
				if (state == 1) {
					if (power2 > 30) {
						addTransCost(ID);
						transmuteParticles(par3World, par4, par5, par6, player);
						TransHelper.transmuteRandomBlock(par4, par5, par6, ID, meta, par3World, player);
						player.swingItem();
						power2 -= 30;
					}
				}
				return true;
			}
		} else {

			return true;
		}
	}

	public void addTransCost(int blockID) {

	}

	@Override
	public void requestEnergy(int amount, boolean decayIncrease) {

	}

	@Override
	public void disperseEnergy(int amount) {

	}

	@Override
	public void useEnergy(int amount, boolean usesDecay) {

	}
}