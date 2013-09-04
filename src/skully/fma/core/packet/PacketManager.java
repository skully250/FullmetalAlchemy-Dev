package skully.fma.core.packet;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import skully.fma.item.FMAItems;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;


public class PacketManager implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {

        if(packet.channel == "FMA") {

            if(packet instanceof PacketTransmute) {

                ((PacketTransmute)packet).execute();
            }
        }
    }

    public static void sendDeconstructPacket(ItemStack stack) {
        if(stack == new ItemStack(FMAItems.ReconstructionCircle)) {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream dataStream = new DataOutputStream(byteStream);
        }
    }
}
