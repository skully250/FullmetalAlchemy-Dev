package skully.fma.core.network.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.world.World;
import skully.fma.core.network.PacketFMA;
import cpw.mods.fml.common.network.Player;


public class PacketDeconstruct extends PacketFMA {

    public int x, y, z;
    public EntityPlayer player;
    public World world;

    public PacketDeconstruct(int x, int y, int z, EntityPlayer player, World world) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.player = player;
        this.world = world;
    }
    
	@Override
	public void execute() {
		world.setBlock(x, y, z, 0);
        world.setBlock(x + 1, y, z, 0);
        world.setBlock(x - 1, y, z, 0);
        world.setBlock(x, y - 1, z, 0);
        world.setBlock(x + 1, y - 1, z, 0);
        world.setBlock(x - 1, y - 1, z, 0);
        world.setBlock(x, y + 2, z, 0);
        world.setBlock(x + 1, y - 2, z, 0);
        world.setBlock(x - 1, y - 2, z, 0);
        world.setBlock(x + 1, y, z, 0);
        world.setBlock(x - 1, y, z, 0);
	}

}
