package skully.fma.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import skully.fma.core.FMAParticle;
import skully.fma.item.FMAItems;


/**
 * @author viper283
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockCircles extends BlockFMA {

    public BlockCircles(int par1) {
        super(par1, Material.snow);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
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
			
			world.spawnEntityInWorld(new EntityLightningBolt(world, par2, par3 + 1, par4));
			FMAParticle.spawnTransmutationFX(posX, posY + 0.2, posZ, mx, -0.1, mz, 9, false, true, true);
		}
	}

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if(world.isRemote) {
            return true;
        } else {
            if(player.getCurrentEquippedItem() != null) {
                if(player.getCurrentEquippedItem().getItem() == Item.enderPearl 
                		&& player.inventory.hasItem(FMAItems.Kunai.itemID)) {
                	
                    player.inventory.consumeInventoryItem(Item.enderPearl.itemID);
                    player.inventory.consumeInventoryItem(FMAItems.Kunai.itemID);
                    transmuteParticles(world, par2, par3, par4, player);
                    player.inventoryContainer.detectAndSendChanges();
                    player.inventory.addItemStackToInventory(new ItemStack(FMAItems.KunaiEnder));
                    
                } else if(player.getCurrentEquippedItem().getItem() == Item.blazePowder 
                		&& player.inventory.hasItem(FMAItems.Kunai.itemID)) {
                	
                    player.inventory.consumeInventoryItem(Item.blazePowder.itemID);
                    player.inventory.consumeInventoryItem(FMAItems.Kunai.itemID);
                    transmuteParticles(world, par2, par3, par4, player);
                    player.inventoryContainer.detectAndSendChanges();
                    player.inventory.addItemStackToInventory(new ItemStack(FMAItems.KunaiFire));
                    
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
}
