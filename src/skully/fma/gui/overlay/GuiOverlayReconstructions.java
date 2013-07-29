package skully.fma.gui.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import skully.fma.core.util.RenderUtil;
import skully.fma.core.util.Resources;
import skully.fma.item.FMAItems;
import skully.fma.item.alchemical.ItemReconstructionCircle;

public class GuiOverlayReconstructions extends Gui {
	public final Minecraft mc;
	private Icon icon;
	private RenderItem itemRender;

	public GuiOverlayReconstructions(Minecraft mc) {
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
		RenderUtil.instance().bindTexture(Resources.MOD_ID, "textures/items/pStoneOff.png");
		GL11.glPushMatrix();
		GL11.glScalef(0.2f, 0.2f, 0.2f);
		RenderUtil.instance().drawTextureRect(1, 1, 0, 0, 256, 256, 1);
		GL11.glPopMatrix();
		if (ItemReconstructionCircle.trans >= 20) {
			mc.fontRenderer.drawStringWithShadow(ItemReconstructionCircle.oState, 3, 8, 0xffffff);
			mc.fontRenderer.drawStringWithShadow("" + (ItemReconstructionCircle.trans / 2), 18, 22, 0xffffff);
		} else {
			mc.fontRenderer.drawStringWithShadow(ItemReconstructionCircle.oState, 3, 8, 0xffffff);
			mc.fontRenderer.drawStringWithShadow("" + (ItemReconstructionCircle.trans / 2), 22, 22, 0xffffff);
		}
		//mc.standardGalacticFontRenderer.drawStringWithShadow("ShadowChild is Awesome", 16, 3 + 15, 0xffffff);
	}

	public void renderPStoneOverlay(Minecraft minecraft, EntityPlayer player, ItemStack stack) {
		for(int i = 0; i < player.inventory.mainInventory.length; i++)
		{
			ItemStack stack2 = player.inventory.mainInventory[i];
		}

		this.renderReconstructionOverlay(minecraft, player);
	}
}
