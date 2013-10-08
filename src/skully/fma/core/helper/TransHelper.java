package skully.fma.core.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import skully.fma.core.FullmetalAlchemy;
import skully.fma.core.network.packets.PacketTransmute;
import skully.fma.transmute.value.TransValues;
import cpw.mods.fml.common.network.PacketDispatcher;


public class TransHelper {

    public static boolean transmuteRandomBlock(int x, int y, int z, int blockID, int metadata, World world, EntityPlayer player) {

        int newID = TransValues.getRandomID();
        
        FullmetalAlchemy.logger.info(String.format("Transmuting Block with id: %d, meta: %d, x: %d, y: %d, z: %d", newID, metadata, x, y, z));

        // TODO fix server crash/disconnect
        PacketDispatcher.sendPacketToServer(new PacketTransmute(x, y, z, newID, metadata, player, world));

        return false;
    }
    
    public static boolean transmuteCorruption(int x, int y, int z, int blockID, int metadata, World world, EntityPlayer player) {
    	
    	int newID = TransValues.getCorruptionID();
    	
    	FullmetalAlchemy.logger.info(String.format("Transmuting Block with id: %d, meta: %d, x: %d, y: %d, z: %d", newID, metadata, x, y, z));
    	
    	PacketDispatcher.sendPacketToServer(new PacketTransmute(x, y, z, newID, metadata, player, world));
    	
    	return false;
    }
}