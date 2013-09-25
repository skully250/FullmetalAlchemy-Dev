package skully.fma.gui.research;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;


public class GuiResearchProgress extends Gui {

    private static Minecraft mc;
    public Icon icon;

    public GuiResearchProgress(Minecraft mc) {
        super();
        GuiResearchProgress.mc = mc;
    }

    @ForgeSubscribe(priority = EventPriority.NORMAL)
    public void renderOverlay(RenderGameOverlayEvent event) {

        if(event.isCancelable() || event.type != ElementType.HOTBAR || mc.gameSettings.showDebugInfo) {
            return;
        }
    }

    public void renderThis(Minecraft mc, EntityPlayer player) {
        mc.fontRenderer.drawStringWithShadow("Testing", 120, 115, 0xFFFFFF);
    }

    public void renderReconstructionResearch(Minecraft mc, EntityPlayer player) {

    }

    public void renderIceResearch(Minecraft mc, EntityPlayer player) {

    }

    public void renderFireResearch(Minecraft mc, EntityPlayer player) {

    }

}
