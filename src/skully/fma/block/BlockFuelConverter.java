package skully.fma.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import skully.fma.core.FullmetalAlchemy;
import skully.fma.core.lib.GuiIDs;
import skully.fma.item.FMAItems;

public class BlockFuelConverter extends BlockFMA {

	public static int Energy = 0;
	
	public BlockFuelConverter(int par1) {
		super(par1, Material.iron);
	}
		
	@Override
	public Icon getIcon(int par1, int par2) {
		return blockIcon;
		
	}
	
	@Override
	public void registerIcons(IconRegister Register) {
		
	}
	
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            player.openGui(FullmetalAlchemy.instance, GuiIDs.INFUSER, world, 
            		x, y, z);
            return true;
        }
    }
    
    public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity, EntityPlayer player) 
    {
    	if (player.inventory.hasItem(FMAItems.pStone.itemID)) {
    		
    	}
    }

}
