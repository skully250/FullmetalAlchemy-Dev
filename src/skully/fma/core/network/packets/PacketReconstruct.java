package skully.fma.core.network.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.world.World;
import skully.fma.core.network.PacketFMA;
import cpw.mods.fml.common.network.Player;


public class PacketReconstruct extends PacketFMA {

    public int x, y, z;
    public EntityPlayer player;
    public World world;

    public PacketReconstruct(int x, int y, int z, EntityPlayer player, World world) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.player = player;
        this.world = world;
    }

	@Override
	public void execute() {
		world.setBlock(x, y, z, Block.dirt.blockID);
        world.setBlock(x + 1, y, z, Block.dirt.blockID);
        world.setBlock(x - 1, y, z, Block.dirt.blockID);
        world.setBlock(x, y + 1, z, Block.dirt.blockID);
        world.setBlock(x + 1, y + 1, z, Block.dirt.blockID);
        world.setBlock(x - 1, y + 1, z, Block.dirt.blockID);
        world.setBlock(x, y + 2, z, Block.dirt.blockID);
        world.setBlock(x + 1, y + 2, z, Block.dirt.blockID);
        world.setBlock(x - 1, y + 2, z, Block.dirt.blockID);
        world.setBlock(x + 1, y, z, Block.dirt.blockID);
        world.setBlock(x - 1, y, z, Block.dirt.blockID);
	}
}
