package skully.fma.core;

import java.io.File;
import java.util.List;
import java.util.jar.JarFile;
import java.util.logging.Logger;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import skully.fma.block.FMABlocks;
import skully.fma.core.client.ClientTickHandler;
import skully.fma.core.command.FMACommands;
import skully.fma.core.config.ConfigSettings;
import skully.fma.core.config.CoreConfiguration;
import skully.fma.core.handler.GuiHandler;
import skully.fma.core.handler.SoundHandler;
import skully.fma.core.packet.PacketManager;
import skully.fma.core.platform.Platform;
import skully.fma.core.server.ServerTickHandler;
import skully.fma.core.util.FMAIcons;
import skully.fma.core.util.RenderUtil;
import skully.fma.core.util.Resources;
import skully.fma.core.util.registers.FMALanguageRegister;
import skully.fma.crafting.FMARecipes;
import skully.fma.energy.FMAPower;
import skully.fma.item.FMAItems;
import skully.fma.world.FMAOreGen;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
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

	@EventHandler
	public void preInitialize(FMLPreInitializationEvent evt) {

		getConfig().initialize(new File(System.getProperty("user.dir"), "FullmetalAlchemy/properties.ini"));

		MinecraftForge.EVENT_BUS.register(this);
		
		FMALanguageRegister.loadLanguageLocalizations();

		platform.registerHandlers();
		}

	@EventHandler
	public void initialize(FMLInitializationEvent evt) {
		
		NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());
		
		FMAItems.initialize();
		FMABlocks.initialize();
		FMARecipes.initialize();
		FMAPower power = new FMAPower();

		GameRegistry.registerWorldGenerator(new FMAOreGen());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {


		Platform.instance().registerRenderThings();
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
		RenderUtil.instance();
		RenderUtil.loadRenderingUtils();

		platform.registerKeyBinds();
	}

	@EventHandler
	public void addCommands(FMLServerStartingEvent evt) {

		evt.registerServerCommand(FMACommands.commandFma);
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
