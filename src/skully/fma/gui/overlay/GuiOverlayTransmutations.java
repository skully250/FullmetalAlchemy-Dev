package skully.fma.gui.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import skully.fma.core.util.FMARenderUtil;
import skully.fma.core.util.Resources;
import skully.fma.item.FMAItems;
import skully.fma.item.alchemical.ItemEnergyStore;
import skully.fma.item.alchemical.ItemPStone;
import skully.fma.item.alchemical.ItemReconstructionCircle;

public class GuiOverlayTransmutations extends Gui {
	public final Minecraft mc;

	public GuiOverlayTransmutations(Minecraft mc) {
		super();

		this.mc = mc;
	}

	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void renderOverlay(RenderGameOverlayEvent event) 
	{
		if(event.isCancelable() || event.type != ElementType.HOTBAR || mc.gameSettings.showDebugInfo)
		{
			return;
		}

        if(mc.thePlayer.getCurrentEquippedItem() != null)
        {
            if(mc.thePlayer.getCurrentEquippedItem().getItem() == FMAItems.ReconstructionCircle)
            {
                renderPStoneOverlay(mc, mc.thePlayer, mc.thePlayer.getCurrentEquippedItem());
            }
        }
        
        if(mc.thePlayer.inventory.getCurrentItem() == new ItemStack(FMAItems.pStone))
        {
            if(mc.thePlayer.getCurrentEquippedItem() != null)
            {
                if(mc.thePlayer.getCurrentEquippedItem().getItem() == FMAItems.ReconstructionCircle)
                {
                    return;
                }
            }
            
            this.renderReconstructionOverlay(mc, mc.thePlayer);
        }
	}

	public void renderReconstructionOverlay(Minecraft mc, EntityPlayer player) {
		FMARenderUtil.instance().bindTexture(Resources.MOD_ID, "FullmetalAlchemy: /textures/items/ChalkCircle");
		FMARenderUtil.instance().drawTextureRect(1, 1 + 0, 0, 0, 4, 256, 0.05f, 0.05f, 0.05f, 1.0, 1.0, 1.0);
		mc.fontRenderer.drawStringWithShadow("Reconstructions: " + (ItemReconstructionCircle.Trans / 2), 16, 3 + 0, 0xffffff);
	}
	
	public void renderPStoneOverlay(Minecraft minecraft, EntityPlayer player, ItemStack stack) {
        for(int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack2 = player.inventory.mainInventory[i];
        }
        
        this.renderReconstructionOverlay(minecraft, player);
	}
}
