package skully.fma.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInfuser extends BlockFMA {
    
    @SideOnly(Side.CLIENT)
    private Icon field_94461_a;
    @SideOnly(Side.CLIENT)
    private Icon field_94460_b;
    
    public BlockInfuser(int id) {
		super(id, Material.iron);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
	}
    
    @SideOnly(Side.CLIENT)
    
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 0 ? this.field_94460_b : (par1 == 1 ? this.field_94461_a : this.blockIcon);
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("fullmetalalchemy:ISide");
        this.field_94461_a = par1IconRegister.registerIcon("fullmetalalchemy:ITop");
        this.field_94460_b = par1IconRegister.registerIcon("fullmetalalchemy:IBottom");
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
