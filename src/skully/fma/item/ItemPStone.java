package skully.fma.item;

import java.util.List;

import skully.fma.core.enums.EnumState;
import skully.fma.core.implement.IKeyBound;
import skully.fma.core.implement.IStatedItem;
import skully.fma.core.util.FMAUtils;
import skully.fma.core.util.Resources;
import skully.fma.core.util.TransHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemPStone extends ItemFMA implements IStatedItem, IKeyBound {

    private Icon icons[] = new Icon[256];

    /**
     * 0 - inactive 1 - on
     */
    private int stateFlag;

    public ItemPStone(int par1, EnumState defaultState) {

        super(par1);
        setMaxStackSize(1);

        if(getState() != null && !(getState().equals(""))) {

            getState();
        } else {

            setState(defaultState);
        }
    }

    @Override
    public String getState() {

        String state = FMAUtils.nbtHelper.getString(new ItemStack(FMAItems.pStone), "PStoneState");

        return state;
    }

    @Override
    public void setState(EnumState state) {

        FMAUtils.nbtHelper.setString(new ItemStack(FMAItems.pStone), "PStoneState", state.getName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        this.icons[0] = iconRegister.registerIcon(Resources.MOD_ID.toLowerCase() + ":" + "pStoneOff");
        this.icons[1] = iconRegister.registerIcon(Resources.MOD_ID.toLowerCase() + ":" + "pStoneOn");
    }

    @Override
    public Icon getIconFromDamage(int par1) {

        return icons[stateFlag];
    }

    @SuppressWarnings({
            "unchecked", "rawtypes"
    })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        list.add("\u00a78" + getState());
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {

        boolean updateTextures = getState() != null ? true : false;

        if(updateTextures) {

            if(getState().equalsIgnoreCase("inactive")) {

                stateFlag = 0;
            } else if(getState().equalsIgnoreCase("active")) {

                stateFlag = 1;
            } else {

                stateFlag = 0;
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack) {
        
        return true;
    }

    @Override
    public void keyBindActions() {

        if(getState().equals("active")) {

            setState(EnumState.OFF);
        } else if(getState().equals("inactive")) {

            setState(EnumState.ON);
        } else {

            setState(EnumState.OFF);
        }
    }
    
    
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        
        int ID = par3World.getBlockId(par4, par5, par6);
        int meta = par3World.getBlockMetadata(par4, par5, par6);
        TileEntity te = par3World.getBlockTileEntity(par4, par5, par6);
        
        if(!par3World.isRemote) {
            
            if(te != null) {
                
                // We dont want to do anything if the block has a tile entity
                
                return false;
            } else {
                
                if(getState() == "active") {
                
                    addTransCost(ID);
                    TransHelper.transmuteRandomBlock(par4, par5, par6, ID, meta, (WorldServer)par3World, player);
                    player.swingItem();
                }
                return true;
            }
        } else {
            
            return true;
        }
    }
    
    public void addTransCost(int blockID) {
        
        
    }
}