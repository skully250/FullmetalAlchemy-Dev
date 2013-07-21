/**
 * 
 */
package skully.fma.core.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


/**
 * @author viper283
 *
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
        
        //FullmetalAlchemy.platform.transmuteBlock(x, y, z, blockID, meta, player, world);
        
        world.setBlock(x, y, z, blockID);
    }
}
