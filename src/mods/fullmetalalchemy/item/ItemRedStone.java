package mods.fullmetalalchemy.item;

import mods.fullmetalalchemy.core.enums.EnumState;
import mods.fullmetalalchemy.core.implement.IKeyBound;
import mods.fullmetalalchemy.core.implement.IStatedItem;
import net.minecraft.client.renderer.texture.IconRegister;
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