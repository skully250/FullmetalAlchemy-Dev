package skully.fma.core.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import skully.fma.client.render.RenderPlayerFMA;
import skully.fma.client.render.item.RenderReconstructionGauntlet;
import skully.fma.core.FMAParticle;
import skully.fma.core.FullmetalAlchemy;
import skully.fma.core.client.ClientKeybindHandler;
import skully.fma.core.client.ClientTickHandler;
import skully.fma.core.handler.SoundHandler;
import skully.fma.core.util.RenderUtil;
import skully.fma.core.util.registers.FMAEventRegister;
import skully.fma.item.FMAItems;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**
 * @author viper283
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public static CommonProxy instance() {

        return FullmetalAlchemy.platform;
    }
    
    public static FMAParticle particleManager = new FMAParticle();

    @Override
    public void registerTickHandlers() {
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
    }

    @Override
    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }

    @Override
    public void registerRenderThings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderPlayerFMA());
        MinecraftForgeClient.registerItemRenderer(FMAItems.ReconstructionCircle.itemID, new RenderReconstructionGauntlet());
        FMAEventRegister.registerOverlays();
        RenderUtil.instance();
        RenderUtil.loadRenderingUtils();
        //RenderingRegistry.registerEntityRenderingHandler(EntityKunaiEnder.class, new RenderKunaiEnder(FMAItems.KunaiEnder));
    }

    @Override
    public void registerKeyBinds() {
        KeyBindingRegistry.registerKeyBinding(new ClientKeybindHandler());
    }

    @Override
    public void transmuteBlock(int x, int y, int z, int blockID, int meta, EntityPlayer player, World world) {
        world.setBlock(x, y, z, blockID);
    }
}
