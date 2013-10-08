package skully.fma.core.network.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.world.World;
import skully.fma.core.network.PacketFMA;
import cpw.mods.fml.common.network.Player;


/**
 * @author viper283
 */
public class PacketTransmute extends PacketFMA {

    public int x, y, z, blockID, meta;
    public EntityPlayer player;
    public World world;

    public PacketTransmute(int x, int y, int z, int blockID, int meta, EntityPlayer player, World world) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockID = blockID;
        this.meta = meta;
        this.player = player;
        this.world = world;
    }

	@Override
	public void execute() {
		world.setBlock(x, y, z, blockID);
	}
}
