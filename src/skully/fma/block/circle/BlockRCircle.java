package skully.fma.block.circle;

import java.util.concurrent.TimeUnit;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import skully.fma.core.FMAParticle;
import skully.fma.core.util.ConvertUtil;
import skully.fma.core.util.Resources;
import skully.fma.crafting.FMAResearchRecipes;
import skully.fma.item.FMAItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockRCircle extends BlockFMACircles {

	public Minecraft mc = Minecraft.getMinecraft();
	public static boolean First = true;
	public static boolean Second = true;
	public static boolean Third = true;
	public static boolean Fourth = true;

	public BlockRCircle(int par1) {
		super(par1);
		this.setBlockBounds(-1.0F, 0.0F, -1.0F, 2.0F, 0.125F, 2.0F);

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
			FMAParticle.spawnResearchFX(posX, posY - 0.2, posZ, mx, 0, mz, 75, false, true, true);
		}
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
		FMAResearchRecipes recipes;

		if(world.isRemote) {
			return true;
		} else {
			int Chalk = FMAItems.ChalkStick.itemID;
			if(player.getCurrentEquippedItem() != null) {
				if(player.getCurrentEquippedItem().getItem() == FMAItems.ChalkStick && player.inventory.hasItem(FMAItems.AlchNotebook.itemID) && player.inventory.hasItem(Chalk) && First == true) {
					//					Random rand = new Random();
					int i1 = world.getBlockMetadata(par2, par3, (par4 + 1));
					int k1 = 8 - (i1 & 8);
					world.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "Writing", 0.3F, k1 > 0 ? 0.6F : 0.5F);
					player.sendChatToPlayer(ConvertUtil.toChatComponent("My research has yielded new Items and Transmutation possibilities!"));
					long time = mc.theWorld.getWorldTime();
					mc.theWorld.setWorldTime(18000L);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch(InterruptedException e) {
						System.out.println("Time didnt change properly :/");
					}
					mc.theWorld.setWorldTime(time);

					FMAResearchRecipes.initializeAlchemy();
					player.inventory.consumeInventoryItem(Chalk);
					player.inventoryContainer.detectAndSendChanges();
					First = false;
				} else if(player.getCurrentEquippedItem().getItem() == FMAItems.FireCircle && player.inventory.hasItem(FMAItems.AlchNotebook.itemID) && player.inventory.hasItem(Chalk) && Second == true) {
					int i1 = world.getBlockMetadata(par2, par3, (par4 + 1));
					int k1 = 8 - (i1 & 8);
					world.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "Writing", 0.3F, k1 > 0 ? 0.6F : 0.5F);
					player.sendChatToPlayer(ConvertUtil.toChatComponent("New alchemical fire abilities have been revealed to me!"));

					FMAResearchRecipes.initializeFire();
					player.inventory.consumeInventoryItem(Chalk);
					player.inventoryContainer.detectAndSendChanges();
					Second = false;
				} else if(player.getCurrentEquippedItem().getItem() == FMAItems.ChalkCircle && player.inventory.hasItem(FMAItems.AlchNotebook.itemID) && player.inventory.hasItem(Chalk) && Third == true) {
					int i1 = world.getBlockMetadata(par2, par3, (par4 + 1));
					int k1 = 8 - (i1 & 8);
					world.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "Writing", 0.3F, k1 > 0 ? 0.6F : 0.5F);
					player.sendChatToPlayer(ConvertUtil.toChatComponent("New alchemical ice abilities have been revealed to me!"));

					FMAResearchRecipes.initializeIce();
					player.inventory.consumeInventoryItem(Chalk);
					player.inventoryContainer.detectAndSendChanges();
					Third = false;
				} else if(player.getCurrentEquippedItem().getItem() == FMAItems.ChalkPyramid && player.inventory.hasItem(FMAItems.AlchNotebook.itemID) && player.inventory.hasItem(Chalk) && Fourth == true) {
					int i1 = world.getBlockMetadata(par2, par3, (par4 + 1));
					int k1 = 8 - (i1 & 8);
					world.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "Writing", 0.3F, k1 > 0 ? 0.6F : 0.5F);
					player.sendChatToPlayer(ConvertUtil.toChatComponent("Reconstruction and Deconstruction has been revealed to me!"));

					FMAResearchRecipes.initializeRec();
					player.inventory.consumeInventoryItem(Chalk);
					player.inventoryContainer.detectAndSendChanges();
					Fourth = false;
				} else if(player.getCurrentEquippedItem().getItem() == FMAItems.ChalkStick) {
					player.sendChatToPlayer(ConvertUtil.toChatComponent("Writing notes on this Circle may yield new alchemical opportunities."));
					transmuteParticles(world, par2, par3, par4, player);
				}
				if(First == false) {
					FMAResearchRecipes.initializeAlchemy();
				}
				if(Second == false) {
					FMAResearchRecipes.initializeFire();
				}
				if(Third == false) {
					FMAResearchRecipes.initializeIce();
				}
				if(Fourth == false) {
					FMAResearchRecipes.initializeRec();
				}
			}
		}
		return true;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType() {
		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
}