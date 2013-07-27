package skully.fma.core.util;

import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ResourceLocation;

public class ConvertUtil {

	public static ResourceLocation getResourceLocation(String modId, String location) {
		return new ResourceLocation(modId, location);
	}
	
	public static ChatMessageComponent toChatComponent(String message) {
		
		return ChatMessageComponent.func_111077_e(message);
	}
}
