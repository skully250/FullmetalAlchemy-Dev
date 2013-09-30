package skully.fma.fx;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import skully.fma.core.handler.RenderHandler;
import skully.fma.core.util.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FXChargingBeam extends FmaFx {
	private float rotYaw = 0.0F;
    private float rotPitch = 0.0F;
    private float prevYaw = 0.0F;
    private float prevPitch = 0.0F;
    private double targetX = 0;
    private double targetY = 0;
    private double targetZ = 0;
    public boolean pulse;
    private final float prevSize = 0.0F;
    private float length = 0.0f;
    private final int rotationSpeed = 10;
    public boolean slide;
    
    public FXChargingBeam(World par1World, double f, double f2, double f4, double par8, double par10, double par12, int par14, int par15)
    {
        super(par1World, f, f2, f4, 0.0, 0.0, 0.0);
        this.blendMode = 1;
        
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        
        this.particleScale = 2.5f;
        this.particleRed = this.particleGreen = this.particleBlue = 0.25F;
        this.particleMaxAge = par15;
        
        this.targetX = par8;
        this.targetY = par10;
        this.targetZ = par12;
        
        double dx = posX - par8;
        double dy = posY - par10;
        double dz = posZ - par12;
        
        double var7 = MathHelper.sqrt_double(dx * dx + dz * dz);
        
        this.rotYaw = ((float) (Math.atan2(dx, dz) * 180.0D / 3.141592653589793D));
        this.rotPitch = ((float) (Math.atan2(dy, var7) * 180.0D / 3.141592653589793D));
        this.prevYaw = this.rotYaw;
        this.prevPitch = this.rotPitch;
        
        this.noClip = true;
        this.pulse = false;
        this.slide = true;
    }
    
    public FXChargingBeam(World world, double f, double f2, double f3, double d, double d2, double d3, int par4)
    {
        this(world, f, f2, f3, d, d2, d3, par4, par4);
    }
    
    @Override
    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
    {
        tessellator.draw();
        
        GL11.glPushMatrix();
        float var9 = 1.0F;
        float slide = this.worldObj.getTotalWorldTime();
        float rot = this.worldObj.provider.getWorldTime() % (360 / this.rotationSpeed) * this.rotationSpeed + this.rotationSpeed * f;
        
        float size = 1.0F;
        
        float op = 0.25F;
        
        RenderHandler.bindTexture("textures/items/pStoneOn.png");
        
        GL11.glTexParameterf(3553, 10242, 10497.0F);
        GL11.glTexParameterf(3553, 10243, 10497.0F);
        
        GL11.glDisable(2884);
        
        float var11 = slide + f;
        
        if (!this.slide)
        {
            var11 = 0;
        }
        
        float var12 = -var11 * 0.2F - MathHelper.floor_float(-var11 * 0.1F);
        
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, blendMode);
        GL11.glDepthMask(false);
        
        float xx = (float) (this.prevPosX + (this.posX - this.prevPosX) * f - interpPosX);
        float yy = (float) (this.prevPosY + (this.posY - this.prevPosY) * f - interpPosY);
        float zz = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * f - interpPosZ);
        GL11.glTranslated(xx, yy, zz);
        
        float ry = this.prevYaw + (this.rotYaw - this.prevYaw) * f;
        float rp = this.prevPitch + (this.rotPitch - this.prevPitch) * f;
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(180.0F + ry, 0.0F, 0.0F, -1.0F);
        GL11.glRotatef(rp, 1.0F, 0.0F, 0.0F);
        
        double var44 = -0.15D * size;
        double var17 = 0.15D * size;
        double var44b = -0.15D * size * 1.0f;
        double var17b = 0.15D * size * 1.0f;
        
        GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
        for (int t = 0; t < 3; t++)
        {
            double var29 = length * size * var9;
            double var31 = 0.0D;
            double var33 = 1.0D;
            double var35 = -1.0F + var12 + t / 3.0F;
            double var37 = length * size * var9 + var35;
            
            GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
            tessellator.startDrawingQuads();
            tessellator.setBrightness(500);
            tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, op);
            tessellator.addVertexWithUV(var44b, var29, 0.0D, var33, var37);
            tessellator.addVertexWithUV(var44, 0.0D, 0.0D, var33, var35);
            tessellator.addVertexWithUV(var17, 0.0D, 0.0D, var31, var35);
            tessellator.addVertexWithUV(var17b, var29, 0.0D, var31, var37);
            tessellator.draw();
        }
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2884);
        
        GL11.glPopMatrix();
        
        tessellator.startDrawingQuads();
        
        RenderUtil.instance().bindTexture("minecraft", "textures/particle/particles.png");
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
        
        this.prevYaw = this.rotYaw;
        this.prevPitch = this.rotPitch;
        
        float xd = (float) (this.posX - this.targetX);
        float yd = (float) (this.posY - this.targetY);
        float zd = (float) (this.posZ - this.targetZ);
        
        this.length = MathHelper.sqrt_float(xd * xd + yd * yd + zd * zd);
        
        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
    }
}
