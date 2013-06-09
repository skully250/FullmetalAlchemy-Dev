package mods.fullmetalalchemy.core.server;

import java.util.EnumSet;

import mods.fullmetalalchemy.core.tick.ServerTick;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

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