package skully.fma.core.util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import skully.fma.core.client.ClientTickHandler;

public class FMARenderUtil extends ClientTickHandler {
    private static final FMARenderUtil INSTANCE = new FMARenderUtil();

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

    public static FMARenderUtil instance()
    {
        return INSTANCE;
    }

    public void bindTexture(String modid, String texture)
    {
        renderEngine.func_110577_a(ConvertUtil.getResourceLocation(modid, texture));
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

    public void drawFullScreenOverlay(int x, int y, String texturePath)
    {
        drawFullScreenOverlay(x, y, texturePath, 1.0f, 1.0f, 1.0f);
    }

    public void drawFullScreenOverlay(int x, int y, String texturePath, float colourR, float colourG, float colourB)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(colourR, colourG, colourB, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        FMARenderUtil.instance().bindTexture(Resources.MOD_ID, texturePath);
        Tessellator var3 = Tessellator.instance;
        var3.startDrawingQuads();
        var3.addVertexWithUV(0.0D, y, -90.0D, 0.0D, 1.0D);
        var3.addVertexWithUV(x, y, -90.0D, 1.0D, 1.0D);
        var3.addVertexWithUV(x, 0.0D, -90.0D, 1.0D, 0.0D);
        var3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        var3.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB)
    {
        Tessellator var2 = Tessellator.instance;
        var2.startDrawing(3);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(3);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(1);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.draw();
    }

    public void renderVignette(float par1, int par2, int par3)
    {
        par1 = 1.0F - par1;

        if (par1 < 0.0F) {
            par1 = 0.0F;
        }

        if (par1 > 1.0F) {
            par1 = 1.0F;
        }

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
        GL11.glColor4f(par1, par1, par1, 1.0F);
        FMARenderUtil.instance().bindTexture("minecraft", "%blur%/misc/vignette.png");
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, par3, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(par2, par3, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(par2, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void renderLabel(String par2Str, double par3, double par5, double par7)
    {
        FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par3 + 0.0F, (float) par5, (float) par7);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(-f1, -f1, f1);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator tessellator = Tessellator.instance;
        byte b0 = 0;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        tessellator.startDrawingQuads();
        int j = fontrenderer.getStringWidth(par2Str) / 2;
        tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
        tessellator.addVertex(-j - 1, -1 + b0, 0.0D);
        tessellator.addVertex(-j - 1, 8 + b0, 0.0D);
        tessellator.addVertex(j + 1, 8 + b0, 0.0D);
        tessellator.addVertex(j + 1, -1 + b0, 0.0D);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, b0, 553648127);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, b0, -1);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    // Tick Handling Code
    // Only used to set the Scaled Resolution sr variable and width and height
    // variables.
    @Override
    public void onClientWorldLoad(Minecraft mc, World world)
    {
    }

    @Override
    public void onClientWorldUnload(Minecraft mc, World world)
    {
    }

    @Override
    public void onClientWorldTick(Minecraft mc, World world)
    {
    }

    @Override
    public void onHUDTick(Minecraft mc)
    {
        this.sr = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth,
                Minecraft.getMinecraft().displayHeight);

        width = sr.getScaledWidth();
        height = sr.getScaledHeight();
    }

    @Override
    public void onGUITick(Minecraft mc, GuiScreen guiscreen)
    {
    }
    
    public void renderAllSides(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, Icon tex, boolean allsides)
    {
      if ((allsides) || (block.shouldSideBeRendered(world, x + 1, y, z, 6))) {
        renderer.renderFaceZPos(block, x, y, z, tex);
    }
      if ((allsides) || (block.shouldSideBeRendered(world, x - 1, y, z, 6))) {
        renderer.renderFaceZNeg(block, x, y, z, tex);
    }
      if ((allsides) || (block.shouldSideBeRendered(world, x, y, z + 1, 6))) {
        renderer.renderFaceXNeg(block, x, y, z, tex);
    }
      if ((allsides) || (block.shouldSideBeRendered(world, x, y, z - 1, 6))) {
        renderer.renderFaceXPos(block, x, y, z, tex);
    }
      if ((allsides) || (block.shouldSideBeRendered(world, x, y + 1, z, 6))) {
        renderer.renderFaceYPos(block, x, y, z, tex);
    }
      if ((allsides) || (block.shouldSideBeRendered(world, x, y - 1, z, 6))) {
        renderer.renderFaceYNeg(block, x, y, z, tex);
    }
    }

    public void renderAllSidesInverted(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, Icon tex, boolean allsides)
    {
      if ((allsides) || (!block.shouldSideBeRendered(world, x - 1, y, z, 6))) {
        renderer.renderFaceZPos(block, x - 1, y, z, tex);
    }
      if ((allsides) || (!block.shouldSideBeRendered(world, x + 1, y, z, 6))) {
        renderer.renderFaceZNeg(block, x + 1, y, z, tex);
    }
      if ((allsides) || (!block.shouldSideBeRendered(world, x, y, z - 1, 6))) {
        renderer.renderFaceXNeg(block, x, y, z - 1, tex);
    }
      if ((allsides) || (!block.shouldSideBeRendered(world, x, y, z + 1, 6))) {
        renderer.renderFaceXPos(block, x, y, z + 1, tex);
    }
      if ((allsides) || (!block.shouldSideBeRendered(world, x, y - 1, z, 6))) {
        renderer.renderFaceYPos(block, x, y - 1, z, tex);
    }
      if ((allsides) || (!block.shouldSideBeRendered(world, x, y + 1, z, 6))) {
        renderer.renderFaceYNeg(block, x, y + 1, z, tex);
    }
    }

    public void renderAllSides(int x, int y, int z, Block block, RenderBlocks renderer, Icon tex)
    {
        renderer.renderFaceZPos(block, x - 1, y, z, tex);
        renderer.renderFaceZNeg(block, x + 1, y, z, tex);
        renderer.renderFaceXNeg(block, x, y, z - 1, tex);
        renderer.renderFaceXPos(block, x, y, z + 1, tex);
        renderer.renderFaceYPos(block, x, y - 1, z, tex);
        renderer.renderFaceYNeg(block, x, y + 1, z, tex);
    }
    
    public static void drawFaces(RenderBlocks renderblocks, Block block, Icon icon, boolean st)
    {
      drawFaces(renderblocks, block, icon, icon, icon, icon, icon, icon, st);
    }

    public static void drawFaces(RenderBlocks renderblocks, Block block, Icon i1, Icon i2, Icon i3, Icon i4, Icon i5, Icon i6, boolean solidtop)
    {
      Tessellator tessellator = Tessellator.instance;
      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, -1.0F, 0.0F);
      renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, i1);
      tessellator.draw();
      if (solidtop) {
        GL11.glDisable(3008);
    }
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 1.0F, 0.0F);
      renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, i2);
      tessellator.draw();
      if (solidtop) {
        GL11.glEnable(3008);
    }
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, 1.0F);
      renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, i3);
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, -1.0F);
      renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, i4);
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setNormal(1.0F, 0.0F, 0.0F);
      renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, i5);
      tessellator.draw();
      tessellator.startDrawingQuads();
      tessellator.setNormal(-1.0F, 0.0F, 0.0F);
      renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, i6);
      tessellator.draw();
      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
    
    public int setBrightness(IBlockAccess blockAccess, int i, int j, int k, Block block) {
        Tessellator tessellator = Tessellator.instance;
        int mb = block.getMixedBrightnessForBlock(blockAccess, i, j, k);
        tessellator.setBrightness(mb);

        float f = 1.0F;

        int l = block.colorMultiplier(blockAccess, i, j, k);
        float f1 = (l >> 16 & 0xFF) / 255.0F;
        float f2 = (l >> 8 & 0xFF) / 255.0F;
        float f3 = (l & 0xFF) / 255.0F;
        if (EntityRenderer.anaglyphEnable)
        {
          float f6 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
          float f4 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
          float f7 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
          f1 = f6;
          f2 = f4;
          f3 = f7;
        }
        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        return mb;
      }
    
    /**
     * Utility function to draw crossed swuares
     */
    public void drawCrossedSquares(Icon icon, double par3, double par5, double par7, float par9)
    {
        Tessellator tessellator = Tessellator.instance;

        double d3 = icon.getMinU();
        double d4 = icon.getMinV();
        double d5 = icon.getMaxU();
        double d6 = icon.getMaxV();
        double d7 = 0.45D * par9;
        double d8 = par3 + 0.5D - d7;
        double d9 = par3 + 0.5D + d7;
        double d10 = par7 + 0.5D - d7;
        double d11 = par7 + 0.5D + d7;
        tessellator.addVertexWithUV(d8, par5 + par9, d10, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + par9, d11, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + par9, d11, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + par9, d10, d5, d4);
        tessellator.addVertexWithUV(d8, par5 + par9, d11, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + par9, d10, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + par9, d10, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + par9, d11, d5, d4);
    }
}
