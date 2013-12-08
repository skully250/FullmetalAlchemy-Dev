package skully.fma.client.fx;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/*
 * Original author of this code :
 * JCM2606 AKA Jakemichie97
 */

@SideOnly(Side.CLIENT)
public class FXFissure extends FmaFx
{
    public float scale;
    
    /**
     * 255 is max.
     * 
     * 200 is default.
     */
    public int brightness;
    
    public boolean ignoreAge;
    
    /**
     * Hexadecimal colour code
     */
    public int innerColour;
    
    /**
     * Hexadecimal colour code
     */
    public int outterColour;
    
    public boolean spawnFissureOnDeath;
    public boolean infiniteSpawning;
    
    public FXFissure(World par1World, double par2, double par4, double par6, int par7)
    {
        super(par1World, 0, 0, 0);
        
        this.prevPosX = posX = par2;
        this.prevPosY = posY = par4;
        this.prevPosZ = posZ = par6;
        
        this.motionX = this.motionY = this.motionZ = 0;
        this.noClip = true;
        this.particleMaxAge = par7;
        this.scale = 0.05f;
        this.brightness = 200;
        this.ignoreAge = false;
        this.innerColour = 16777215;
        this.outterColour = 16711935;
        this.spawnFissureOnDeath = false;
    }
    
    @Override
    public void renderParticle(Tessellator tessellator, float fl, float fl1, float fl2, float fl3, float fl4, float fl5)
    {
        tessellator.draw();
        
        float f11 = (float) (this.prevPosX + (this.posX - this.prevPosX) * fl - interpPosX);
        float f12 = (float) (this.prevPosY + (this.posY - this.prevPosY) * fl - interpPosY);
        float f13 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * fl - interpPosZ);
        
        GL11.glPushMatrix();
        GL11.glTranslatef(f11, f12, f13);
        
        float f1 = (this.particleAge + fl) / this.particleMaxAge;
        float fa = f1;
        float f2 = 0.0F;
        
        if (ignoreAge)
        {
            fa = 0.9f;
        }
        
        if (!ignoreAge && f1 > 0.8F)
        {
            f2 = (f1 - 0.8F) / 0.2F;
        }
        
        Random random = new Random(432L);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
        
        for (int i = 0; i < (fa + fa * fa) / 2.0F * 60.0F; ++i)
        {
            GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 90.0F, 0.0F, 0.0F, 1.0F);
            tessellator.startDrawing(6);
            float f3 = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
            float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
            tessellator.setColorRGBA_I(innerColour, (int) (255.0F * (1.0F - f2)));
            tessellator.setBrightness(brightness);
            tessellator.addVertex(0.0D, 0.0D, 0.0D);
            tessellator.setColorRGBA_I(outterColour, 0);
            tessellator.addVertex(-0.866D * f4, f3, -0.5F * f4);
            tessellator.addVertex(0.866D * f4, f3, -0.5F * f4);
            tessellator.addVertex(0.0D, f3, 1.0F * f4);
            tessellator.addVertex(-0.866D * f4, f3, -0.5F * f4);
            tessellator.draw();
        }
        
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        
        GL11.glPopMatrix();
        
        tessellator.startDrawingQuads();
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
        
        if (this.particleAge++ >= this.particleMaxAge)
        {
            if (this.spawnFissureOnDeath)
            {
                FXFissure fx = new FXFissure(this.worldObj, this.posX, this.posY, this.posZ, this.particleMaxAge);
                if (this.infiniteSpawning)
                {
                    this.spawnFissureOnDeath = true;
                }
            }
            this.setDead();
        }
    }
}