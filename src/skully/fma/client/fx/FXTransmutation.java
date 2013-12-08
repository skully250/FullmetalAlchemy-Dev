package skully.fma.client.fx;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import skully.fma.core.util.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FXTransmutation extends FmaFx {
	float particleScaleOverTime;

	private int maxAge;
	private final int speed;
	public boolean fade;
	public boolean dieOnTravelCompletion;

	public double travelX;
	public double travelY;
	public double travelZ;

	public FXTransmutation(World par1World, double f, double f2, double f4, double par8, double par10, double par12, int par14, int par15)
	{
		super(par1World, f, f2, f4, 0.0, 0.0, 0.0);
		this.blendMode = 771;

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		this.travelX = par8;
		this.travelY = par10;
		this.travelZ = par12;

		this.particleScaleOverTime = this.particleScale = 2.5f;
		this.particleRed = this.particleGreen = this.particleBlue = 0.25F;
		this.particleMaxAge = maxAge = par14;
		this.speed = par15;
		this.fade = false;

		double dx = par8 - this.posX;
		double dy = par10 - this.posY;
		double dz = par12 - this.posZ;

		this.motionX = (dx / speed);
		this.motionY = (dy / speed);
		this.motionZ = (dz / speed);

		this.noClip = true;
		this.dieOnTravelCompletion = true;
		this.setAlphaF(0.75f);
		this.setParticleTextureIndex(32);
	}

	public FXTransmutation(World world, double f, double f2, double f3, double d, double d2, double d3, int par4)
	{
		this(world, f, f2, f3, d, d2, d3, par4, par4);
	}

	@Override
	public void renderParticle(Tessellator tessellator, float f, float f1,
			float f2, float f3, float f4, float f5) {
		if(this.fade)
		{
			this.setAlphaF((1.0f - ((this.particleAge / 8.0f) / 10.0f)));
		}

		super.drawParticle(Resources.PARTICLE_SHEET, tessellator, f, f1, f2, f3, f4, f5);
	}

	@Override
	public int getBrightnessForRender(float par1)
	{
		return 100;
	}

	/**
	 * Gets how bright this entity is.
	 */
	 @Override
	 public float getBrightness(float par1)
	{
		return 1.0f;
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

		this.motionY -= 0.04D * this.particleGravity;

		moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.particleAge++ >= this.particleMaxAge)
		{
			this.setDead();
		}

		if(this.dieOnTravelCompletion)
		{
			if(this.posX == this.travelX && this.posY == this.travelY && this.posZ == this.travelZ)
			{
				this.setDead();
			}
		}
	}
}
