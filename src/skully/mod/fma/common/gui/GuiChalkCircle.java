package skully.mod.fma.common.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import skully.mod.fma.FullmetalAlchemy;
import skully.mod.fma.common.container.ContainerChalkCircle;

public class GuiChalkCircle extends GuiContainer {
	
	ResourceLocation texture = new ResourceLocation(FullmetalAlchemy.constants.MOD_ID.toLowerCase(), "/textures/guis/Chalkgui.png");
	boolean[] chalkButtons;
	World world;
	int[] pos;
	
	public GuiChalkCircle(InventoryPlayer playerInv, ItemStack stack, World world, int x, int y, int z) {
		super(new ContainerChalkCircle(playerInv, stack, world, x, y, z));
		this.world = world;
		this.pos = new int[] { x, y, z };
		this.xSize = 176;
		this.ySize = 186;
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}
	
	@Override
	public void initGui() {
		super.initGui();
		chalkButtons = new boolean[25];
		
		buttonList.clear();
		for(int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				buttonList.add(new GuiChalkButton(x+(y*5), guiLeft+(x*16)+10, guiTop + (y*16)+10, 16, 16, this.world.getBlock(pos[0], pos[1], pos[2])));
				if (chalkButtons[(y*5) + x]) {
					resetButton((y*5) + x);
				}
			}
		}
	}
	
	public void resetButton(int id) {
		chalkButtons[id] = true;
		((GuiChalkButton) this.buttonList.get(id)).enabled = false;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

}
