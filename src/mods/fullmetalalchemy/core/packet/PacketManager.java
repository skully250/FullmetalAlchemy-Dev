package mods.fullmetalalchemy.core.packet;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;


public class PacketManager implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {

        if(packet.channel == "FMA") {
            
            if(packet instanceof PacketTransmute) {
                
                ((PacketTransmute)packet).execute();
            }
        }
    }
}
