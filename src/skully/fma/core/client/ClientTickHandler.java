package skully.fma.core.client;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.World;
import skully.fma.core.tick.GameTick;
import skully.fma.core.tick.RenderTick;

import java.util.EnumSet;


public class ClientTickHandler implements ITickHandler {

    private Minecraft mc = Minecraft.getMinecraft();

    public void onClientWorldTick(Minecraft mc, World world) {

    }

    public void onHUDTick(Minecraft mc) {

    }

    public void onGUITick(Minecraft mc, GuiScreen guiscreen) {

    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        // But it can go here
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        if(type.equals(TickType.CLIENT)) {

            GameTick.onTick(Minecraft.getMinecraft());
        } else if(type.equals(TickType.RENDER)) {

            RenderTick.onTick(Minecraft.getMinecraft());
        }
    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.RENDER, TickType.CLIENT);
    }

    @Override
    public String getLabel() {

        return "FMA_CLIENT_TICK";
    }
}
