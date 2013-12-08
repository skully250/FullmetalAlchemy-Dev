package skully.fma.core.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import skully.fma.core.network.packets.PacketDeconstruct;
import skully.fma.core.network.packets.PacketReconstruct;
import skully.fma.core.network.packets.PacketTransmute;
import skully.fma.item.FMAItems;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;


public class PacketManager implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {

		if(packet.channel == "FMA") {

			if(packet instanceof PacketFMA) {
				((PacketFMA)packet).execute();
			}
		}
	}
}