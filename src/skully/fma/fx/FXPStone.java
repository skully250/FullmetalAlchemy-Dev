package skully.fma.fx;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import skully.fma.core.util.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FXPStone extends FmaFx {

	float particleScaleOverTime;
    public int maxAge = 80;
    
    public FXPStone(World par1World, double f, double f2, double f4, double par8, double par10, double par12)
    {
        super(par1World, f, f2, f4, par8, par10, par12);
        this.motionX = this.motionX * 0.009999999776482582D + par8;
        this.motionY = this.motionY * 0.009999999776482582D + par10 + 0.005f;
        this.motionZ = this.motionZ * 0.009999999776482582D + par12;
        double var10000 = f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        var10000 = f2 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        var10000 = f4 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
        this.particleScaleOverTime = this.particleScale = (this.rand.nextFloat() * 0.5F + 0.5F) * 2f;
        this.particleRed = 1.0F;
        this.particleGreen = (float) (0.75 + Math.random());
        this.particleBlue = (float) (0.75 + Math.random());
        this.particleMaxAge = maxAge;
        this.noClip = true;
        this.blendMode = 1;
        this.setAlphaF(0.8f);
        this.setParticleTextureIndex(33);
    }
    
    @Override
    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setAlphaF((1.0f - ((this.particleAge / 2.0f) / 10.0f)));
        super.drawParticle(Resources.PARTICLE_SHEET, tessellator, f, f1, f2, f3, f4, f5);
    }
    
    @Override
    public int getBrightnessForRender(float par1)
    {
        return 1;
    }
    
    /**
     * Gets how bright this entity is.
     */
    @Override
    public float getBrightness(float par1)
    {
        float var2 = (this.particleAge + par1) / maxAge;
        
        if (var2 < 0.0F)
        {
            var2 = 0.0F;
        }
        
        if (var2 > 1.0F)
        {
            var2 = 1.0F;
        }
        
        float var3 = super.getBrightness(par1);
        return var3 * var2 + (1.0F - var2);
    }
    
    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        
        if (this.particleAge++ >= maxAge)
        {
            this.setDead();
        }
        
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;
        
        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }

}
