package skully.fma.core.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;

import org.lwjgl.opengl.GL11;

import skully.fma.core.client.ClientTickHandler;

public class RenderUtil extends ClientTickHandler {
    private static final RenderUtil INSTANCE = new RenderUtil();

    public ScaledResolution sr;

    public static int width;
    public static int height;

    public static RenderItem renderItem = new RenderItem();
    public static RenderBlocks renderBlocks;
    public static TextureManager renderEngine;
    public static RenderManager renderManager = RenderManager.instance;
    public static RenderGlobal renderGlobal;
    
    public static void loadRenderingUtils()
    {
        renderGlobal = Minecraft.getMinecraft().renderGlobal;
        renderBlocks = renderGlobal.globalRenderBlocks;
        renderEngine = Minecraft.getMinecraft().renderEngine;
    }

    public static RenderUtil instance()
    {
        return INSTANCE;
    }
    
    public void bindTexture(String modid, String texture)
    {
        renderEngine.bindTexture(ConvertUtil.getResourceLocation(modid, texture));
    }
	
    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v,
     * width, height, xscale, yscale, zscale
     */
    public void drawTextureRect(int par1, int par2, int par3, int par4, int par5, int par6, float xscale, float yscale, float zscale)
    {
        drawTextureRect(par1, par2, par3, par4, par5, par6, xscale, yscale, zscale, 1.0, 1.0, 1.0);
    }

    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v,
     * width, height, scale
     */
    public void drawTextureRect(int par1, int par2, int par3, int par4, int par5, int par6, float scale)
    {
        drawTextureRect(par1, par2, par3, par4, par5, par6, scale, scale, scale);
    }

    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v,
     * width, height, xscale, yscale, zscale, colourR, colourG, colourB
     */
    public void drawTextureRect(int par1, int par2, int par3, int par4, int par5, int par6, float xscale, float yscale, float zscale, double colourR,
            double colourG, double colourB)
    {
        GL11.glPushMatrix();
        GL11.glScalef(xscale, yscale, zscale);
        GL11.glDepthMask(false);
        GL11.glColor4d(colourR, colourG, colourB, 1.0);
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV(par1 + 0, par2 + par6, 1.0f, (par3 + 0) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + par6, 1.0f, (par3 + par5) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + 0, 1.0f, (par3 + par5) * var7, (par4 + 0) * var8);
        var9.addVertexWithUV(par1 + 0, par2 + 0, 1.0f, (par3 + 0) * var7, (par4 + 0) * var8);
        var9.draw();
        GL11.glDepthMask(true);
        GL11.glScalef(1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
}
