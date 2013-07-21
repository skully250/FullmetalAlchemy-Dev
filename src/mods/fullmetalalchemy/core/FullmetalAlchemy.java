package mods.fullmetalalchemy.core;

import java.io.File;
import java.util.List;
import java.util.jar.JarFile;
import java.util.logging.Logger;

import mods.fullmetalalchemy.block.FMABlocks;
import mods.fullmetalalchemy.core.client.ClientTickHandler;
import mods.fullmetalalchemy.core.command.FMACommands;
import mods.fullmetalalchemy.core.config.ConfigSettings;
import mods.fullmetalalchemy.core.config.CoreConfiguration;
import mods.fullmetalalchemy.core.packet.PacketManager;
import mods.fullmetalalchemy.core.platform.Platform;
import mods.fullmetalalchemy.core.server.ServerTickHandler;
import mods.fullmetalalchemy.core.util.FMAIcons;
import mods.fullmetalalchemy.core.util.FMAUtils;
import mods.fullmetalalchemy.core.util.Resources;
import mods.fullmetalalchemy.crafting.FMARecipes;
import mods.fullmetalalchemy.item.FMAItems;
import mods.fullmetalalchemy.item.Sounds;
import mods.fullmetalalchemy.world.FMAOreGen;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
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

	@Instance(Resources.MOD_ID)
	public static FullmetalAlchemy instance;

	public static boolean debugMode;

	public static Logger logger = Logger.getLogger("FMA");

	public static List<JarFile> modules;
	public static List<Class<?>> classesToLoad;
	public static List<Class<?>> loadedClasses;
	
	static {
		
		logger.setParent(FMLLog.getLogger());
	}

	@PreInit
	public void preInitialize(FMLPreInitializationEvent evt) {

		getConfig().initialize(new File(System.getProperty("user.dir"), "FullmetalAlchemy/properties.ini"));

		MinecraftForge.EVENT_BUS.register(this);

		LanguageRegistry.instance().loadLocalization("/mods/fullmetalalchemy/lang/en_US.properties", "en_US", false);
		LanguageRegistry.instance().loadLocalization("/mods/fullmetalalchemy/lang/en_GB.properties", "en_GB", false);

		//platform.makeModules();
		MinecraftForge.EVENT_BUS.register(new Sounds());
		MinecraftForge.EVENT_BUS.register(new FMAIcons());
	}

	@Init
	public void initialize(FMLInitializationEvent evt) {

		FMAItems.initialize();
		FMABlocks.initialize();
		FMARecipes.initialize();

		GameRegistry.registerWorldGenerator(new FMAOreGen());
	}

	@PostInit()
	public void postInit(FMLPostInitializationEvent event) {


		Platform.instance().registerRenderThings();
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);

		platform.registerKeyBinds();
	}

	@ServerStarting
	public void addCommands(FMLServerStartingEvent evt) {

		evt.registerServerCommand(FMACommands.commandFma);
	}

	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load evt) {

		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && evt.world.provider.dimensionId == 0) {

			FMAUtils.tagCompound = platform.loadData((WorldServer) evt.world);
		}
	}

	@ForgeSubscribe
	public void onWorldSave(WorldEvent.Save evt) {

		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && evt.world.provider.dimensionId == 0) {

			//platform.saveData((WorldServer) evt.world, FMAUtils.nbtHelper.getNBT());
		}
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
