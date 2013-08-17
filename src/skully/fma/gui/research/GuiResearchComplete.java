package skully.fma.gui.research;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

public class GuiResearchComplete extends Gui {
	public Minecraft mc;
	public Icon icon;
	
	public GuiResearchComplete(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void renderOverlay(RenderGameOverlayEvent event) {
		if(event.isCancelable() || event.type != ElementType.HOTBAR || mc.gameSettings.showDebugInfo)
		{
			return;
		}
	}
	
	public void renderReconstructionComplete() {
		
	}
	
	public void renderIceComplete() {
		
	}
	
	public void renderFireComplete() {
		
	}
}
