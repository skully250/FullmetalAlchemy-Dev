package skully.fma.core.handler;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SoundHandler 
{
    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void onSound(SoundLoadEvent event)
    {
        try 
        {
            event.manager.soundPoolSounds.addSound("fullmetalalchemy:FingerClick.ogg");            
            event.manager.soundPoolSounds.addSound("fullmetalalchemy:ChalkDraw.ogg");
            event.manager.soundPoolSounds.addSound("fullmetalalchemy:EarthMoving.ogg");
            event.manager.soundPoolSounds.addSound("fullmetalalchemy:Transmute.wav");
            event.manager.soundPoolSounds.addSound("fullmetalalchemy:Writing.wav");
        } 
        catch (Exception e)
        {
            System.err.println("Failed to register one or more sounds.");
        }
    }
}
