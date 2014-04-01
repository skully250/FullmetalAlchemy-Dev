package skully.mod.fma.common.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import skully.mod.fma.FullmetalAlchemy;
import skully.mod.fma.common.container.ContainerChalkCircle;

public class GuiChalkCircle extends GuiContainer {
	
	ResourceLocation texture = new ResourceLocation("textures/gui/container/crafting_table.png" /*FullmetalAlchemy.constants.MOD_ID.toLowerCase(), "/textures/guis/Chalkgui.png"*/);

	public GuiChalkCircle(InventoryPlayer playerInv, World world, int x, int y, int z) {
		super(new ContainerChalkCircle(playerInv, world, x, y, z));
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

}
