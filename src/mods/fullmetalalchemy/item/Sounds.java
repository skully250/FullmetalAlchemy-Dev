package mods.fullmetalalchemy.item;

import mods.fullmetalalchemy.core.FullmetalAlchemy;
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
            event.manager.soundPoolSounds.addSound("FingerClick.ogg", FullmetalAlchemy.class.getResource("/mods/fullmetalalchemy/sounds/FingerClick.ogg"));            
            event.manager.soundPoolSounds.addSound("ChalkDraw.ogg", FullmetalAlchemy.class.getResource("/mods/fullmetalalchemy/sounds/ChalkDraw.ogg"));
            event.manager.soundPoolSounds.addSound("EarthMoving.ogg", FullmetalAlchemy.class.getResource("/mods/fullmetalalchemy/sounds/EarthMoving.ogg"));
            event.manager.soundPoolSounds.addSound("Transmute.wav", FullmetalAlchemy.class.getResource("/mods/fullmetalalchemy/sounds/Transmute.wav"));
            event.manager.soundPoolSounds.addSound("Writing.wav", FullmetalAlchemy.class.getResource("/mods/fullmetalalchemy/sounds/Writing.wav"));
        } 
        catch (Exception e)
        {
            System.err.println("Failed to register one or more sounds.");
        }
    }
}
