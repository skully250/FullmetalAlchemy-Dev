package skully.fma.fx;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import skully.fma.core.util.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FXResearch extends FmaFx {

	float particleScaleOverTime;
	public int maxAge = 500;

	public FXResearch(World world, double par2, double par4, double par6, double par8, double par10, double par12, int par14) {
		super(world, par2, par4, par6);
		this.motionX = this.motionX * /*0.009999999776482582D*/ + par8;
		this.motionY = this.motionY * 0.009999999776482582D + par10 + 0.005f;
		this.motionZ = this.motionZ * /*0.009999999776482582D*/ + par12;
		double var10000 = par2 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
		var10000 = par4 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
		var10000 = par6 + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F;
		this.particleScaleOverTime = this.particleScale = (this.rand.nextFloat() * 4.0F + 4.0F) * 2f;
		//this.particleRed = 1.0F;
		//this.particleGreen = (float) (0.75 + Math.random());
		this.particleBlue = (float) (0.75 + Math.random());
		this.particleMaxAge = maxAge;
		this.noClip = true;
		this.blendMode = 1;
		this.setAlphaF(1.0f);
		this.setParticleTextureIndex(33);
		this.setParticleTextureIndex(32);
	}

	public FXResearch(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setAlphaF((1.0f - ((this.particleAge / 2.0f) / 10.0f)));
		super.drawParticle(Resources.PARTICLE_SHEET, tessellator, f, f1, f2, f3, f4, f5);
	}

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
