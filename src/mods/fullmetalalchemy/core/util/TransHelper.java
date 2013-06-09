package mods.fullmetalalchemy.core.util;

import mods.fullmetalalchemy.core.packet.PacketTransmute;
import mods.fullmetalalchemy.transmute.value.TransValues;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;


public class TransHelper {
    
    public static boolean transmuteRandomBlock(int x, int y, int z, int blockID, int metadata, World world, EntityPlayer player) {
        
        //FullmetalAlchemy.logger.info(String.format("Transmuting Block with id: %d, meta: %d, x: %d, y: %d, z: %d", blockID, metadata, x, y, z));
        
        int newID = TransValues.getRandomID();
        
        // TODO fix server crash/disconnect
        PacketDispatcher.sendPacketToAllPlayers(new PacketTransmute(x, y, z, newID, metadata, player, world));
        
        return false;
    }
}