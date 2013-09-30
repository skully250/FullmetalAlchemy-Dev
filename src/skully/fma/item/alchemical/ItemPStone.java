package skully.fma.item.alchemical;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import skully.fma.api.energy.IAlchEnergyRequester;
import skully.fma.core.FMAParticle;
import skully.fma.core.enums.EnumState;
import skully.fma.core.helper.TransHelper;
import skully.fma.core.implement.IKeyBound;
import skully.fma.core.implement.IStatedItem;
import skully.fma.fx.FXPStone;
import skully.fma.item.ItemFMA;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPStone extends ItemFMA implements IAlchEnergyRequester, IStatedItem, IKeyBound {

	public static String power = "Transmutational Power";
	public static int PStoneEnergy;
	public static int decayEnergy;
	public static boolean isGettingCharged = false;

	private int stateFlag;
	private int state;

	public ItemPStone(int par1, EnumState defaultState) {
		super(par1);

		if (getState() != null && !(getState().equals("")))
			getState();
		else
			setState(defaultState);
	}

	@Override
	public String getState() {
		String oState = null;
		if (state == 0)
			oState = "Off";
		else if (state == 1)
			oState = "On";
		else if (state == 2)
			oState = "Corruption";
		return oState;
	}

	@Override
	public void setState(EnumState state) {

	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode)) {
			list.add("\u00a78" + getState());
			list.add("Will randomly transmute, so long");
			list.add("as power is provided");
			list.add("Power: " + PStoneEnergy);
			list.add("Dark Energy: " + decayEnergy);
		} else {
			list.add("Hold shift for more info");
		}
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		if (PStoneEnergy == 0)
			return 0xFFFFFF;
		if (decayEnergy >= PStoneEnergy * 1.5)
			return 0x54003C;
		else if (decayEnergy >= PStoneEnergy * 2)
			return 0x615D5E;
		else if (decayEnergy >= PStoneEnergy * 3)
			return 0x2E2B2C;
		else if (decayEnergy >= PStoneEnergy * 4)
			return 000000;
		else
			return 0xFFFFFF;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public int requestEnergy(int amount, boolean decayIncrease) {
		return 0;
	}

	@Override
	public int disperseEnergy(int amount) {
		return 0;
	}

	@Override
	public int useEnergy(int amount, boolean usesDecay) {
		if (!usesDecay)
		PStoneEnergy -= amount;
		if (usesDecay)
			decayEnergy -= amount;
		return amount;
	}

	@Override
	public void keyBindActions() {
		state = state + 1;
		if (state == 3)
			state = 0;
	}

	private void transmuteParticles(World world, double x, double y, double z, EntityPlayer player) {
		for(int i = 0; i < 1000 / 5; i++)
		{
			float r1 = player.worldObj.rand.nextFloat() * 360.0F;
			float mx = -MathHelper.sin(r1 / 180.0F * 3.141593F) / 5.0F;
			float mz = MathHelper.cos(r1 / 180.0F * 3.141593F) / 5.0F;

			double adjAngle = 35.0D;
			double dist = 0.4D;

			EntityPlayer center = player;

			double posX = x + 0.57; // - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
			double posY = y + 0.2; // - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
			double posZ = z + 0.5; // + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;

			FMAParticle.spawnTransmutationFX(posX, posY + 0.9, posZ, mx, 0, mz, 500, false, true, true).setParticleTextureIndex(16);
		}
	}

	private void renderParticle(ItemStack stack, World world, Entity entity, int par4) {
		double adjAngle = 25.0D;
		double dist = 0.4D;

		EntityPlayer center = Minecraft.getMinecraft().thePlayer;

		double posX = center.posX + 0.12 - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
		double posY = center.posY - 0.2 - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
		double posZ = center.posZ - 0.1 + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;

		Random rand = new Random();
		float speed = 0.02F;

		FXPStone fx = new FXPStone(Minecraft.getMinecraft().theWorld, posX, posY, posZ, (rand.nextFloat() - rand.nextFloat()) * speed,
				(rand.nextFloat() - rand.nextFloat()) * speed, (rand.nextFloat() - rand.nextFloat()) * speed);
		fx.maxAge = 40;

		Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}

	private void renderCorruption(ItemStack stack, World world, Entity entity, int par4) {
		double adjAngle = 25.0D;
		double dist = 0.4D;

		EntityPlayer center = Minecraft.getMinecraft().thePlayer;

		double posX = center.posX + 0.12 - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
		double posY = center.posY - 0.2 - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
		double posZ = center.posZ - 0.1 + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;

		Random rand = new Random();
		float speed = 0.02F;

		FXPStone fx = new FXPStone(Minecraft.getMinecraft().theWorld, posX, posY, posZ, (rand.nextFloat() - rand.nextFloat()) * speed,
				(rand.nextFloat() - rand.nextFloat()) * speed, (rand.nextFloat() - rand.nextFloat()) * speed);
		fx.setParticleTextureIndex(32);
		fx.maxAge = 40;

		Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean isEquipped) {
		if (state == 0)
			stateFlag = 0;
		else if (state == 1) {
			if (isEquipped)
				renderParticle(stack, world, entity, par4);
			stateFlag = 1;
		} else if (state == 2) {
			if (isEquipped)
				renderCorruption(stack, world, entity, par4);
			stateFlag = 2;
		}
		stateFlag = 0;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {

		int ID = world.getBlockId(x, y, z);
		int meta = world.getBlockId(x, y, z);
		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te != null)
			return false;
		else {
			if (state == 1) {
				if (!world.isRemote)
					if(PStoneEnergy >= 30) {
						transmuteParticles(world, x, y, z, player);
						TransHelper.transmuteRandomBlock(x, y, z, ID, meta, world, player);
						player.swingItem();
						useEnergy(30, false);
					}
				return true;
			} else if (state == 2) {
				if (!world.isRemote)
					if (decayEnergy >= 30) {
						transmuteParticles(world, x, y, z, player);
						TransHelper.transmuteCorruption(x, y, z, ID, meta, world, player);
						player.swingItem();
						useEnergy(30, true);
					}
				return true;
			}
			return false;
		}
	}
}