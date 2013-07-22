package skully.fma.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import skully.fma.inventory.ContainerInfuser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiInfuser extends GuiContainer {

    public GuiInfuser(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5)
    {
        super(new ContainerInfuser(par1InventoryPlayer, par2World, par3, par4, par5));
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString(I18n.func_135053_a("container.crafting"), 28, 6, 4210752);
        this.fontRenderer.drawString(I18n.func_135053_a("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        this.mc.func_110434_K().func_110577_a(field_110408_a);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

}
