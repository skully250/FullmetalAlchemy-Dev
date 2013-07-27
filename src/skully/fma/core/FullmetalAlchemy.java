package skully.fma.core;

import java.io.File;
import java.util.List;
import java.util.jar.JarFile;
import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import skully.fma.block.FMABlocks;
import skully.fma.core.client.ClientTickHandler;
import skully.fma.core.command.FMACommands;
import skully.fma.core.config.ConfigSettings;
import skully.fma.core.config.CoreConfiguration;
import skully.fma.core.handler.GuiHandler;
import skully.fma.core.packet.PacketManager;
import skully.fma.core.platform.Platform;
import skully.fma.core.server.ServerTickHandler;
import skully.fma.core.util.FMAIcons;
import skully.fma.core.util.Resources;
import skully.fma.crafting.FMARecipes;
import skully.fma.energy.FMAPower;
import skully.fma.gui.overlay.GuiOverlayEnergy;
import skully.fma.item.FMAItems;
import skully.fma.item.Sounds;
import skully.fma.world.FMAOreGen;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@Mod(modid = Resources.MOD_ID, name = Resources.MOD_NAME, version = Resources.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"FMA"}, packetHandler = PacketManager.class)
public class FullmetalAlchemy {

	@SidedProxy(clientSide = Resources.CLIENT_PLATFORM_LOCATION, serverSide = Resources.PLATFORM_LOCATION)
	public static Platform platform;

	@Mod.Instance(Resources.MOD_ID)
	public static FullmetalAlchemy instance;

	public static boolean debugMode;

	public static Logger logger = Logger.getLogger("FMA");

	public static List<JarFile> modules;
	public static List<Class<?>> classesToLoad;
	public static List<Class<?>> loadedClasses;
	NBTTagCompound compound;
	
	static {
		
		logger.setParent(FMLLog.getLogger());
	}

	@Mod.EventHandler
	public void preInitialize(FMLPreInitializationEvent evt) {

		getConfig().initialize(new File(System.getProperty("user.dir"), "FullmetalAlchemy/properties.ini"));

		MinecraftForge.EVENT_BUS.register(this);

		LanguageRegistry.instance().loadLocalization("/assets/fullmetalalchemy/lang/en_US.properties", "en_US", false);
		LanguageRegistry.instance().loadLocalization("/assets/fullmetalalchemy/lang/en_GB.properties", "en_GB", false);
		LanguageRegistry.instance().addStringLocalization("death.attack." + "humanTransmutation", "You were killed for attempting Human Transmutation");
		LanguageRegistry.instance().addStringLocalization("death.attack." + "crimsonAlchemy", "Your dabbles have taken you too far");

		//platform.makeModules();
		MinecraftForge.EVENT_BUS.register(new Sounds());
		MinecraftForge.EVENT_BUS.register(new FMAIcons());
		MinecraftForge.EVENT_BUS.register(new GuiOverlayEnergy(Minecraft.getMinecraft()));
	}

	@Mod.EventHandler
	public void initialize(FMLInitializationEvent evt) {
		
		NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());

		//Class Initializations
		FMAItems.initialize();
		FMABlocks.initialize();
		FMARecipes.initialize();
		FMAPower power = new FMAPower();

		GameRegistry.registerWorldGenerator(new FMAOreGen());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {


		Platform.instance().registerRenderThings();
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);

		platform.registerKeyBinds();
	}

	@Mod.EventHandler
	public void addCommands(FMLServerStartingEvent evt) {

		evt.registerServerCommand(FMACommands.commandFma);
	}

	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load evt) {

//		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && evt.world.provider.dimensionId == 0) {
//
//			FMAUtils.tagCompound = platform.loadData((WorldServer) evt.world);
//		}
	}

	@ForgeSubscribe
	public void onWorldSave(WorldEvent.Save evt) {

//		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && evt.world.provider.dimensionId == 0) {
//
//			platform.saveData((WorldServer) evt.world, FMAUtils.nbtHelper.getNBT());
//		}
	}

	 public static FullmetalAlchemy instance() {

		 return instance;
	 }

	 public static CoreConfiguration getConfig() {

		 return new CoreConfiguration();
	 }

	 public static boolean isModuleSpamAllowed() {

		 return ConfigSettings.moduleLoadingSpammyMode;
	 }
}
