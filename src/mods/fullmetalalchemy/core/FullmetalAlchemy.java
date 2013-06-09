package mods.fullmetalalchemy.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import mods.fullmetalalchemy.block.FMABlocks;
import mods.fullmetalalchemy.core.client.ClientTickHandler;
import mods.fullmetalalchemy.core.client.Reference;
import mods.fullmetalalchemy.core.command.FMACommands;
import mods.fullmetalalchemy.core.config.ConfigSettings;
import mods.fullmetalalchemy.core.config.CoreConfiguration;
import mods.fullmetalalchemy.core.module.ModuleFinder;
import mods.fullmetalalchemy.core.module.ModuleLoader;
import mods.fullmetalalchemy.core.packet.PacketManager;
import mods.fullmetalalchemy.core.platform.Platform;
import mods.fullmetalalchemy.core.server.ServerTickHandler;
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
import apexapi.common.commandlib.ApexCommandFunctionPool;
import apexapi.common.utils.ApexConditionalLogger;
import cpw.mods.fml.common.FMLCommonHandler;
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
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */


@Mod(modid = Resources.MOD_ID, name = Resources.MOD_NAME, version = Resources.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"FMA"}, packetHandler = PacketManager.class)
@SuppressWarnings("static-access")
public class FullmetalAlchemy {

	@SidedProxy(clientSide = Resources.CLIENT_PLATFORM_LOCATION, serverSide = Resources.PLATFORM_LOCATION)
	public static Platform platform;

	@Instance(Resources.MOD_ID)
	public static FullmetalAlchemy instance;

	public static boolean debugMode;

	public static ApexConditionalLogger logger;

	public static List<JarFile> modules;
	public static List<Class<?>> classesToLoad;
	public static List<Class<?>> loadedClasses;

	public static ApexCommandFunctionPool pool;

	@PreInit
	public void preInitialize(FMLPreInitializationEvent evt) {

		getConfig()
		.initialize(new File(platform.getFMADir(), "properties.ini"));

		logger = new ApexConditionalLogger("FMA", debugMode);

		MinecraftForge.EVENT_BUS.register(this);

		LanguageRegistry.instance().loadLocalization("/mods/fullmetalalchemy/lang/en_US.properties", "en_US", false);
		LanguageRegistry.instance().loadLocalization("/mods/fullmetalalchemy/lang/en_GB.properties", "en_GB", false);

		platform.makeModules();
		MinecraftForge.EVENT_BUS.register(new Sounds());

		checkOFStatus();

	}
	@SideOnly(Side.CLIENT)
	private static void checkOFStatus() {

		try {

			Class.forName("IWrUpdateListener");

			System.out.println("[" + Reference.updaterName + "]" + "OptiFine Found, Using High Res Capes!");
			Reference.optiFineInstalled = true;
		} catch(Exception e) {

			System.out.println("[" + Reference.updaterName + "]" + " OptiFine Not Installed, Using Low Res Capes!");
			Reference.optiFineInstalled = false;
		}
	}

	@Init
	public void initialize(FMLInitializationEvent evt) {


		loadModules();

		FMAUtils.addIcons();

		loadCommandLib();


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

			Resources.tagCompound = platform.loadData((WorldServer) evt.world);
		}
	}

	@ForgeSubscribe
	public void onWorldSave(WorldEvent.Save evt) {

		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && evt.world.provider.dimensionId == 0) {

			platform.saveData((WorldServer) evt.world, Resources.nbtHelper.getNBT());
		}
	}

	/**
	 * 
	 * Will be replaced with better code soon
	 */
	 @Deprecated
	 public void loadModules() {

		 modules = new ArrayList<JarFile>();
		 classesToLoad = new ArrayList<Class<?>>();
		 loadedClasses = new ArrayList<Class<?>>();

		 try {

			 ModuleFinder.findModules(platform.getModuleDir());
		 } catch(IOException e) {

			 throw new RuntimeException(e);
		 }

		 try {

			 ModuleLoader.loadModules();
		 } catch(Exception e) {

			 throw new RuntimeException(e);
		 }
	 }

	 private static void loadCommandLib() {

		 pool = new ApexCommandFunctionPool(FMACommands.class, "FMA");
		 pool.scanContainmentClass();
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
