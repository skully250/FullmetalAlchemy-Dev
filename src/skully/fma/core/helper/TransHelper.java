package skully.fma.core.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import skully.fma.core.packet.PacketTransmute;
import skully.fma.transmute.value.TransValues;
import cpw.mods.fml.common.network.PacketDispatcher;


public class TransHelper {

    public static boolean transmuteRandomBlock(int x, int y, int z, int blockID, int metadata, World world, EntityPlayer player) {

        //FullmetalAlchemy.logger.info(String.format("Transmuting Block with id: %d, meta: %d, x: %d, y: %d, z: %d", blockID, metadata, x, y, z));

        int newID = TransValues.getRandomID();

        // TODO fix server crash/disconnect
        PacketDispatcher.sendPacketToAllPlayers(new PacketTransmute(x, y, z, newID, metadata, player, world));

        return false;
    }
    
    public static boolean transmuteCorruption(int x, int y, int z, int blockID, int metadata, World world, EntityPlayer player) {
    	
    	int newID = TransValues.getCorruptionID();
    	
    	PacketDispatcher.sendPacketToAllPlayers(new PacketTransmute(x, y, z, newID, metadata, player, world));
    	
    	return false;
    }
}