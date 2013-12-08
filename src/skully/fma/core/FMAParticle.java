package skully.fma.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import skully.fma.client.fx.FXFissure;
import skully.fma.client.fx.FXResearch;
import skully.fma.client.fx.FXTransmutation;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FMAParticle {
	private static Minecraft mc = FMLClientHandler.instance().getClient();

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
	
	public static EntityFX spawnFissureFX(double x, double y, double z, int age) {
		FXFissure fx = new FXFissure(Minecraft.getMinecraft().theWorld, x, y + 1, z, age);
		spawnParticle(fx, false);
		return fx;
	}

	private static void spawnParticle(EntityFX fx, boolean handleParticleSettings)
	{
		mc.effectRenderer.addEffect(fx);
	}
}