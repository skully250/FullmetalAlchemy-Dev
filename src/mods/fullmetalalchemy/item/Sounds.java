package mods.fullmetalalchemy.item;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Sounds 
{
	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
	public void onSound(SoundLoadEvent event)
	{
		try 
		{
			event.manager.soundPoolSounds.addSound("FingerClick.ogg");            
			event.manager.soundPoolSounds.addSound("ChalkDraw.ogg"); 
			event.manager.soundPoolSounds.addSound("EarthMoving.ogg");
			event.manager.soundPoolSounds.addSound("Transmute.wav");
			event.manager.soundPoolSounds.addSound("Writing.wav");
		} 
		catch (Exception e)
		{
			System.err.println("Failed to register one or more sounds.");
		}
	}
}
