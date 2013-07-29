package skully.fma.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import skully.fma.core.util.FMADamageSource;
import skully.fma.item.FMAItems;
import skully.fma.tileEntity.TileEntityCircle;

public class CircleTransmutation extends BlockFMA {

	public CircleTransmutation(int par1) {
		super(par1, Material.snow);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}
	public boolean onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity, EntityPlayer player)
	{
	if (player.getCurrentEquippedItem().getItem() == FMAItems.pStone)
	{
		player.attackEntityFrom(FMADamageSource.crimsonAlchemy, 9001);
	}
	return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityCircle();
	}
	
	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return 0;
	}
}
