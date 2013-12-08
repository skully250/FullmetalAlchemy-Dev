package skully.fma.client.render.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import skully.fma.core.handler.RenderHandler;
import skully.fma.tileEntity.TileEntityInfuser;

public class RenderInfuserTop extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) {
		
	}
	
	private void renderCircle(String texture, Tessellator tessellator, TileEntityInfuser tile, double x, double y, double z, boolean doSpin,
			float spinAngle, float bobAmount, int blendMode, int blendModePowered) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
		GL11.glPushMatrix();
		
		GL11.glDisable(GL11.GL_LIGHTING);
		
		if(doSpin) {
			GL11.glRotatef(spinAngle, 0.0F, 1.0F, 0.0F);
		}
		
		GL11.glTranslatef(-0.45F, 0.95F + bobAmount, -0.45F);
		
		GL11.glDepthMask(false);
		GL11.glEnable(3042);
		
		/*
		 * if (tile.hasSource()) {
		 * 	GL11.glBlendFunc(770, blendModePowered);
		 * } else {
		 * 	GL11.glBlendFunc(770, blendMode);
		 * }
		 */
		
		RenderHandler.bindTexture("textures/fx/" + texture + ".png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		tessellator.startDrawingQuads();
		
		/*
		 * if (tile.hasSource()) {
		 * 	tessellator.setBrightness(255);
		 * }
		 */
		
		tessellator.addVertexWithUV(0.0D, 0.0D, 0.8999999761581421D, 0.0D, 1.0D);
		tessellator.addVertexWithUV(0.8999999761581421D, 0.0D, 0.8999999761581421D, 1.0D, 1.0D);
		tessellator.addVertexWithUV(0.8999999761581421D, 0.0D, 0.0D, 1.0D, 0.0D);
		tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
		
		tessellator.draw();
		
		GL11.glDisable(3042);
		GL11.glDepthMask(true);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
