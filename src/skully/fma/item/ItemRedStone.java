package skully.fma.item;

import net.minecraft.client.renderer.texture.IconRegister;
import skully.fma.core.enums.EnumState;
import skully.fma.core.implement.IKeyBound;
import skully.fma.core.implement.IStatedItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemRedStone extends ItemFMA implements IStatedItem, IKeyBound {

    public ItemRedStone(int par1, EnumState defaultState) {

        super(par1);
        
        setState(defaultState);
    }

    @Override
    public void keyBindActions() {

        
    }

    @Override
    public String getState() {

        return null;
    }

    @Override
    public void setState(EnumState state) {


    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconregister) {
        
        
    }
}