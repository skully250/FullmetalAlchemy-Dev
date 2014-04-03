package skully.mod.fma.common.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderHelper {
	/*
	 * Future render helpers will probably go here aswell
	 */
	public static void bindTexture(ResourceLocation texture) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
	}
}
