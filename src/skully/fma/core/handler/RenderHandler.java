package skully.fma.core.handler;

import skully.fma.core.util.RenderUtil;
import skully.fma.core.util.Resources;

public class RenderHandler {

    public static void bindTexture(String texture)
    {
        RenderUtil.instance().bindTexture(Resources.MOD_ID, texture);
    }
}
