package skully.fma.client.fx;

import net.minecraft.world.World;
import skully.fma.core.util.FMAFXUtil;

public class FmaFx extends FMAFXUtil {
	
	/*
	 * All of this possible because of Jakemichie, thanks :P
	 */
	
    public FmaFx(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
        this.modid = "fullmetalalchemy";
    }
    
    public FmaFx(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.modid = "fullmetalalchemy";
    }
}