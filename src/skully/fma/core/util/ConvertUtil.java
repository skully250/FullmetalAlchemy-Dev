package skully.fma.core.util;

import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ResourceLocation;


public class ConvertUtil {

    public static ResourceLocation getResourceLocation(String modid, String location) {
        return new ResourceLocation(modid, location);
    }

    public static ChatMessageComponent toChatComponent(String message) {

        return ChatMessageComponent.createFromText(message);
    }
}
