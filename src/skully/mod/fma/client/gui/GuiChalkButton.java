package skully.mod.fma.client.gui;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import skully.mod.fma.FullmetalAlchemy;
import skully.mod.fma.common.util.RenderHelper;

public class GuiChalkButton extends GuiButton {

	Block block;

	public GuiChalkButton(int index, int xPos, int yPos, int width, int height, Block block) {
		super(index, xPos, yPos, width, height, "");
		this.block = block;
	}

	@Override
	public void drawButton(Minecraft mc, int par2, int par3) {
		IIcon icon = block.getBlockTextureFromSide(0);
		ResourceLocation texture = new ResourceLocation(FullmetalAlchemy.constants.MOD_ID + ":" + "textures/blocks/chalkOre.png");
		RenderHelper.bindTexture(texture);
		if (this.visible) {
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			this.field_146123_n = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
			int k = this.getHoverState(this.field_146123_n);
			this.drawTexturedModelRectFromIcon(this.xPosition, this.yPosition, icon, this.width, this.height);
			this.mouseDragged(mc, par2, par3);
			int l = 14737632;

			if (!this.enabled) {
				l = -6250336;
			} else if (this.field_146123_n) {
				l = 16777120;
			}
		} else if (!this.visible) {
			texture = new ResourceLocation(FullmetalAlchemy.constants.MOD_ID + ":" + "textures/blocks/IBottom");
			RenderHelper.bindTexture(texture);
			GL11.glColor4f(0F, 0F, 0F, 1F);
			this.drawTexturedModelRectFromIcon(this.xPosition, this.yPosition, icon, this.width, this.height);
		}
	}
}
