package skully.fma.block.circle;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import skully.fma.core.util.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockFMACircles extends BlockContainer {

    public BlockFMACircles(int par1) {
    	super(par1, Material.snow);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Resources.MOD_ID.toLowerCase() + 
        		":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
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
