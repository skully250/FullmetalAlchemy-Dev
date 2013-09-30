package skully.fma.gui.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
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
import skully.fma.item.alchemical.ItemPStone;


public class GuiOverlayEnergy extends Gui {

	public final Minecraft mc;
	public Icon icon;

	public GuiOverlayEnergy(Minecraft mc) {
		super();

		this.mc = mc;
	}

	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void renderOverlay(RenderGameOverlayEvent event) {
		if(event.isCancelable() || event.type != ElementType.HOTBAR || mc.gameSettings.showDebugInfo) {
			return;
		}

		if(mc.thePlayer.getCurrentEquippedItem() != null) {
			if(mc.thePlayer.getCurrentEquippedItem().getItem() == FMAItems.pStone) {
				renderEnergyOverlay(mc, mc.thePlayer);
			}
		}

		if(mc.thePlayer.inventory.getCurrentItem() == new ItemStack(FMAItems.pStone)) {
			if(mc.thePlayer.getCurrentEquippedItem() != null) {
				if(mc.thePlayer.getCurrentEquippedItem().getItem() == FMAItems.pStone) {
					return;
				}
			}
		}

		if(mc.thePlayer.inventory.getCurrentItem() == new ItemStack(FMAItems.EnergyStore)) {
			if(mc.thePlayer.getCurrentEquippedItem() != null) {
				if(mc.thePlayer.getCurrentEquippedItem().getItem() == FMAItems.pStone) {
					return;
				}
			}
			this.renderEnergyOverlay(mc, mc.thePlayer);
		}
	}

	public void renderEnergyOverlay(Minecraft minecraft, EntityPlayer player) {

		int currentEnergy = ItemPStone.PStoneEnergy;
		int maxEnergy = ItemPStone.decayEnergy;

		RenderUtil.instance().bindTexture(Resources.MOD_ID, "textures/items/ChalkCircle.png");
		GL11.glPushMatrix();
		GL11.glScalef(0.2f, 0.2f, 0.2f);
		RenderUtil.instance().drawTextureRect(1, 1, 0, 0, 256, 256, 1);
		GL11.glPopMatrix();
		minecraft.fontRenderer.drawStringWithShadow("\2477" + currentEnergy, 13, 12, 0xffffff);
		minecraft.fontRenderer.drawStringWithShadow("_____", 11, 20, 0xffffff);
		minecraft.fontRenderer.drawStringWithShadow("\2477" + maxEnergy, 13, 35, 0xffffff);
		//minecraft.standardGalacticFontRenderer.drawStringWithShadow("JakeMichie helped with this", 16, 3 + 15, 0xffffff);
	}
}
