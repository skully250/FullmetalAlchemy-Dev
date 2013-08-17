package skully.fma.core.util;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import skully.fma.gui.overlay.GuiOverlayEnergy;
import skully.fma.gui.overlay.GuiOverlayReconstructions;
import skully.fma.gui.research.GuiResearchProgress;

public class FMAEventRegister {
	
	public static void registerOverlays() {
		MinecraftForge.EVENT_BUS.register(new GuiOverlayEnergy(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new GuiOverlayReconstructions(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new GuiResearchProgress(Minecraft.getMinecraft()));
	}

}
