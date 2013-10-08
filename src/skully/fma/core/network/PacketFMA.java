package skully.fma.core.network;

import net.minecraft.network.packet.Packet250CustomPayload;


public class PacketFMA extends Packet250CustomPayload {

    public PacketFMA() {
        super("FMA", new byte[] {0});
    }

    public void execute() {}
}
