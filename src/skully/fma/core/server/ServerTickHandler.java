package skully.fma.core.server;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import skully.fma.core.tick.ServerTick;

import java.util.EnumSet;


public class ServerTickHandler implements ITickHandler {

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

    }

    @SuppressWarnings("static-access")
    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        // Tick Data should go here
        if(type.equals(TickType.SERVER)) {
            ServerTick.onTick(Minecraft.getMinecraft().getIntegratedServer()
                    .getServer());
        }
    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel() {

        return "FMA_SERVER_TICK";
    }
}