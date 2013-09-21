package skully.fma.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import skully.fma.core.FullmetalAlchemy;
import skully.fma.core.lib.GuiIDs;
import skully.fma.energy.IAlchEnergyProvider;
import skully.fma.tileEntity.TileEntityInfuser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockInfuser extends BlockContainer implements IAlchEnergyProvider {

    @SideOnly(Side.CLIENT)
    private Icon field_94461_a;
    @SideOnly(Side.CLIENT)
    private Icon field_94460_b;

    public BlockInfuser(int id) {
        super(id, Material.iron);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)

    public Icon getIcon(int par1, int par2) {
        return par1 == 0 ? this.field_94460_b : (par1 == 1 ? this.field_94461_a : this.blockIcon);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("fullmetalalchemy:ISide");
        this.field_94461_a = par1IconRegister.registerIcon("fullmetalalchemy:ITop");
        this.field_94460_b = par1IconRegister.registerIcon("fullmetalalchemy:IBottom");
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if(player.isSneaking()) {
            return false;
        } else {
            player.openGui(FullmetalAlchemy.instance, GuiIDs.INFUSER, world,
                    x, y, z);
            return true;
        }
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
		return new TileEntityInfuser();
	}

	@Override
	public int provideEnergy(int amount) {
		return amount;
	}

	@Override
	public int increaseDecay(int amount) {
		return amount;
	}
}
