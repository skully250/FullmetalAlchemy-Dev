package skully.fma.gui.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.mco.McoClient;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import skully.fma.item.FMAItems;
import skully.fma.item.alchemical.ItemPStone;
import skully.fma.tileEntity.TileEntityInfuser;

public class GuiOverlayInfusionInternal extends Gui {
	
	public final Minecraft mc;
	public Icon icon;
	
	public GuiOverlayInfusionInternal(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
    public void renderOverlay(RenderGameOverlayEvent event) {
        if(event.isCancelable() || event.type != ElementType.HOTBAR || mc.gameSettings.showDebugInfo) {
            return;
        }
        
        if (mc.thePlayer.getCurrentEquippedItem() != null)
        	if (mc.thePlayer.getCurrentEquippedItem().getItem() == FMAItems.pStone) {
        		if (ItemPStone.isGettingCharged) {
        			mc.fontRenderer.drawStringWithShadow(TileEntityInfuser.decay + " ", 10, 10, 10);
        		}
        	}
    }
	
}