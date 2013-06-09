package mods.fullmetalalchemy.block;

import mods.fullmetalalchemy.entities.EntityKunaiFire;
import mods.fullmetalalchemy.item.FMAItems;
import mods.fullmetalalchemy.item.ItemKunai;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockCTest extends BlockFMA {

	public BlockCTest(int par1) {
		super(par1, Material.snow);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}
	public boolean onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity, EntityPlayer player)
	{
	if (player.getCurrentEquippedItem().getItem() == FMAItems.pStone)
	{
		player.sendChatToPlayer("Immortality has been granted upon you");
		player.setEntityHealth(900);
		player.attackEntityFrom(DamageSource.magic, 899);
	}
	return true;
	}
	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return 0;
	}
}
