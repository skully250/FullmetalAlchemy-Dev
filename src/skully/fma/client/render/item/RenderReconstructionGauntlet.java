package skully.fma.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import skully.fma.core.handler.RenderHandler;
import skully.fma.core.util.render.ItemRendererBase;
import skully.fma.item.models.ReconstructionGauntlet;

public class RenderReconstructionGauntlet extends ItemRendererBase {

	private final ReconstructionGauntlet gauntlet = new ReconstructionGauntlet();
	private final ReconstructionGauntlet gauntlet2 = new ReconstructionGauntlet();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	@Override
	public void renderEquipped(float posX, float posY, float posZ, ItemStack stack)
	{
		GL11.glPushMatrix();

		RenderHandler.bindTexture("textures/models/deconstructMod.png");

		GL11.glScalef(0.2f, 0.2f, 0.25f);

		if (stack.getItemUseAction() == EnumAction.bow)
		{
			if (Minecraft.getMinecraft().thePlayer.isUsingItem())
			{
				GL11.glTranslatef(4f, 1f, 2.75f);

				GL11.glRotatef(-135f, 1, 0, 0);
			} else
			{
				GL11.glTranslatef(4f, 0.3f, 2f);

				GL11.glRotatef(-135f, 1, 0, 0);
			}
		} else
		{
			GL11.glTranslatef(4f, 0.3f, 3f);

			GL11.glRotatef(-135f, 1, 0, 0);
		}

		gauntlet.render((Entity)Minecraft.getMinecraft().thePlayer, 0F, 0F, 0F, 0F, 0F, 0.625F);

		GL11.glPopMatrix();
	}

	@Override
	public void renderEquippedFirstPerson(float posX, float posY, float posZ, ItemStack stack)
	{

		GL11.glPushMatrix();

		RenderHandler.bindTexture("textures/models/deconstructMod.png");

		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		GL11.glScalef(0.05f, 0.2f, 0.2f);
		GL11.glTranslatef(0.4f, -0.5f, 0.4f);

		GL11.glRotatef(-50f, 1, 1, 1);
		//GL11.glRotatef(2f, 1, 0, 1);

		if (stack.getItemUseAction() == EnumAction.bow)
		{
			if (Minecraft.getMinecraft().thePlayer.isUsingItem())
			{
				GL11.glRotatef(30f, 1, 0, 0);
				GL11.glRotatef(-15f, 1, 0, 2);
			}
		}

		gauntlet.render((Entity)Minecraft.getMinecraft().thePlayer, 0F, 0F, 0F, 0F, 0F, 0.625F);

		GL11.glPushMatrix();
		
		GL11.glTranslatef(-15F, -8F, -0.4F);
		gauntlet2.render((Entity)Minecraft.getMinecraft().thePlayer, 0F, 0F, 0F, 0F, 0F, 0.625F);
		
		GL11.glPopMatrix();

		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

	@Override
	public void renderEntity(float posX, float posY, float posZ, ItemStack stack)
	{
	}

	@Override
	public void renderInventory(float posX, float posY, float posZ, ItemStack stack)
	{
	}

}
