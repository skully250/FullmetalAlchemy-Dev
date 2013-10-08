package skully.fma.tileEntity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import skully.fma.core.FMAParticle;
import skully.fma.item.FMAItems;

/*
 * Pretty much using this as a custom crafting handler, because why not :P
 */
public class TileEntityCircle extends TileEntity {

	List<Item> itemList = new ArrayList<Item>();

	public static int craftTime;
	private boolean craftingFire;
	private boolean craftingEnder;

	public TileEntityCircle() {
		craftingFire = false;
		craftingEnder = false;
	}

	public static void transmuteParticles(World world, double par2, double par3, double par4) {
		for(int i = 0; i < 1000 / 5; i++)
		{
			//float r1 = player.worldObj.rand.nextFloat() * 360.0F;
			//float mx = -MathHelper.sin(r1 / 180.0F * 3.141593F) / 5.0F;
			//float mz = MathHelper.cos(r1 / 180.0F * 3.141593F) / 5.0F;

			double adjAngle = 35.0D;
			double dist = 0.4D;

			//EntityPlayer center = player;

			double posX = par2 + 0.57; //- Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
			double posY = par3 + 0.2; //- Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
			double posZ = par4 + 0.5; //+ Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;

			FMAParticle.spawnFissureFX(posX, posY - 0.2, posZ, 20);
		}
	}

	public void completeCraft(Item craftItem, Item component1, Item component2) {
		if (!worldObj.isRemote)
			this.worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord, zCoord, 
					new ItemStack(craftItem)));
		
		itemList.remove(component1);
		itemList.remove(component2);
	}
	
	public void clearList() {
		itemList.clear();
	}

	@Override
	public void updateEntity() {
		if (craftTime >= 0)
			craftTime--;
		if (craftTime < 0)
			craftTime = 0;

		if (craftTime > 0)
			System.out.println(craftTime);

		List list = worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(
				this.xCoord - 1.5, this.yCoord, this.zCoord - 1.5,
				this.xCoord + 1.5, this.yCoord + 1, this.zCoord + 1.5));


		if(list != null && !list.isEmpty()) {
			for (Object obj : list)  {
				EntityItem item = (EntityItem)obj;

				if (item.getEntityItem().getItem() == FMAItems.Kunai)
					itemList.add(FMAItems.Kunai);
				if (item.getEntityItem().getItem() == Item.blazePowder)
					itemList.add(Item.blazePowder);
				if (item.getEntityItem().getItem() == Item.enderPearl)
					itemList.add(Item.enderPearl);

				if (itemList.contains(FMAItems.Kunai) && itemList.contains(Item.blazePowder)) {
					if (!craftingFire) {
						craftingFire = true;
						craftTime = 20;
						transmuteParticles(worldObj, xCoord, yCoord + 0.2, zCoord);
						itemList.clear();
					}
					else if (itemList.contains(FMAItems.Kunai) && itemList.contains(Item.enderPearl)) {
						if (!craftingEnder) {
							craftingEnder = true;
							craftTime = 20;
							transmuteParticles(worldObj, xCoord, yCoord + 0.2, zCoord);
							itemList.clear();
						}
					}
					if (craftingFire && craftTime == 1) {
						completeCraft(FMAItems.KunaiFire, FMAItems.Kunai, Item.blazePowder);
						for(Object object : list) {
							EntityItem desitem = (EntityItem)object;
							desitem.setDead();
						}
						craftingFire = false;
					}
					if (craftingEnder && craftTime == 1) {
						completeCraft(FMAItems.KunaiEnder, FMAItems.Kunai, Item.enderPearl);
						for(Object object : list) {
							EntityItem fireItem = (EntityItem)object;
							fireItem.setDead();
						}
						craftingEnder = false;
					}
				}
			}
		}
	}

}