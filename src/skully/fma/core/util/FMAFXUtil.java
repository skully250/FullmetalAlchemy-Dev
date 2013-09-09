package skully.fma.core.util;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;


public class FMAFXUtil extends EntityFX {
	
	/*
	 * All of this possible because of Jakemichie, thanks :P
	 */
	
    public int blendMode = 771;
    public boolean shrink = false;
    public String modid;
    
    public FMAFXUtil(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
    }
    
    protected FMAFXUtil(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    
    /**
     * Draw the particle with the textures being provided by the given spritesheet.
     * 
     * @param spriteSheet
     * @param tessellator
     * @param f
     * @param f1
     * @param f2
     * @param f3
     * @param f4
     * @param f5
     */
    public void drawParticle(String spriteSheet, Tessellator tessellator, float f, float f1,
            float f2, float f3, float f4, float f5) {
        boolean wasDrawing = tessellator.isDrawing;

        if (wasDrawing) {
            tessellator.draw();
        }
        
        GL11.glPushMatrix();
        
        RenderUtil.instance().bindTexture(modid, spriteSheet);
        
        GL11.glEnable(32826);
        GL11.glEnable(3042);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, blendMode);
        
        GL11.glPushMatrix();
        
        GL11.glDepthMask(false);

        float f6 = this.particleTextureIndexX / 16.0F;
        float f7 = f6 + 0.0624375F;
        float f8 = this.particleTextureIndexY / 16.0F;
        float f9 = f8 + 0.0624375F;
        float f10 = 0.1F * this.particleScale;
        if (this.shrink) {
            f10 *= (this.particleMaxAge - this.particleAge + 1) / this.particleMaxAge;
        }

        if (this.particleIcon != null)
        {
            f6 = this.particleIcon.getMinU();
            f7 = this.particleIcon.getMaxU();
            f8 = this.particleIcon.getMinV();
            f9 = this.particleIcon.getMaxV();
        }

        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * f - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * f - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * f - interpPosZ);
        float f14 = 1.0F;
        tessellator.startDrawingQuads();
        tessellator.setBrightness(300);
        tessellator.setColorRGBA_F(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha);
        tessellator.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, f7, f9);
        tessellator.addVertexWithUV(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10, f7, f8);
        tessellator.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, f6, f8);
        tessellator.addVertexWithUV(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10, f6, f9);
        
        tessellator.draw();
        
        GL11.glDepthMask(true);
        
        GL11.glPopMatrix();

        GL11.glDisable(32826);
        GL11.glDisable(3042);
        GL11.glDisable(3042);
        
        RenderUtil.instance().bindTexture("minecraft", "textures/particle/particles.png");
        
        GL11.glPopMatrix();
        
        if (wasDrawing) {
            tessellator.startDrawingQuads();
        }
    }
}