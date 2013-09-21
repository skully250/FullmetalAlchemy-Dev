package skully.fma.core.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import skully.fma.fx.FXPStone;
import skully.fma.fx.FXResearch;
import skully.fma.fx.FXTransmutation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TransmutationParticle {
	private static Minecraft mc = Minecraft.getMinecraft();

	public static EntityFX spawnTransmutationFX(double x, double y, double z, double motX, double motY, double motZ, int age, boolean noClip,
			boolean fade, boolean dieOnTravelCompletion, boolean handleParticleSettings) {

		FXTransmutation fx = new FXTransmutation(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ, age);
		fx.noClip = noClip;
		fx.fade = fade;
		fx.dieOnTravelCompletion = dieOnTravelCompletion;

		spawnParticle(fx, handleParticleSettings);
		return fx;
	}

	public static EntityFX spawnTransmutationFX(double x, double y, double z, double motX, double motY, double motZ, int age, boolean noClip,
			boolean fade, boolean handleParticleSettings)
	{
		FXTransmutation fx = new FXTransmutation(Minecraft.getMinecraft().theWorld, x, y, z, 0, 0, 0, age);

		fx.motionX = motX;
		fx.motionY = motY;
		fx.motionZ = motZ;
		fx.noClip = noClip;
		fx.fade = fade;
		fx.dieOnTravelCompletion = false;

		spawnParticle(fx, handleParticleSettings);
		return fx;
	}
	
	public static EntityFX spawnPStoneFX(double x, double y, double z, double motX, double motY, double motZ, int age, boolean noClip,
			boolean fade, boolean dieOnTravelCompletion, boolean handleParticleSettings) {
		
		FXPStone fx = new FXPStone(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ, age);
		
		fx.noClip = noClip;
		fx.fade = fade;
		fx.dieOnTravelCompletion = dieOnTravelCompletion;
		
		spawnParticle(fx, handleParticleSettings);
		return fx;
	}

	public static EntityFX spawnPStoneFX(double x, double y, double z, double motX, double motY, double motZ, int age, boolean noClip,
			boolean fade, boolean handleParticleSettings) {
		FXPStone fx = new FXPStone(Minecraft.getMinecraft().theWorld, x, y, z, 0, 0, 0, age);

		fx.motionX = motX;
		fx.motionY = motY;
		fx.motionZ = motZ;
		fx.noClip = noClip;
		fx.fade = fade;
		fx.dieOnTravelCompletion = false;

		spawnParticle(fx, handleParticleSettings);
		return fx;
	}
	
	public static EntityFX spawnResearchFX(double x, double y, double z, double motX, double motY, double motZ, int age, boolean noClip,
			boolean fade, boolean dieOnTravelCompletion, boolean handleParticleSettings) {
		
		FXResearch fx = new FXResearch(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ, age);
		
		fx.noClip = noClip;
		
		spawnParticle(fx, handleParticleSettings);
		return fx;
	}
	
	public static EntityFX spawnResearchFX(double x, double y, double z, double motX, double motY, double motZ, int age, boolean noClip,
			boolean fade, boolean handleParticleSettings) {
		
		FXResearch fx = new FXResearch(Minecraft.getMinecraft().theWorld, x, y, z, motX, motY, motZ, age);
		
		fx.noClip = noClip;
		
		spawnParticle(fx, handleParticleSettings);
		return fx;
	}

	private static void spawnParticle(EntityFX fx, boolean handleParticleSettings)
	{
		mc.effectRenderer.addEffect(fx);
	}
}