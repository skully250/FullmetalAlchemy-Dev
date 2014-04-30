package skully.mod.fma;

import java.io.File;
import java.io.IOException;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import skully.mod.fma.client.gui.FMAGuiHandler;
import skully.mod.fma.common.crafting.RecipesFMA;
import skully.mod.fma.common.items.FMAItems;
import skully.mod.fma.common.proxy.CommonProxy;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = FullmetalAlchemy.constants.MOD_ID, name = FullmetalAlchemy.constants.MOD_NAME, version = FullmetalAlchemy.constants.MOD_VERSION)
public class FullmetalAlchemy {
		
		@Instance(constants.MOD_ID)
		public static FullmetalAlchemy instance;
		
		@SidedProxy(clientSide = constants.CLIENT_SIDE_PROXY, serverSide = constants.SERVER_SIDE_PROXY)
		public static CommonProxy proxy;
		
		public static final Logger LOGGER = LogManager.getLogger("FMA");
		
		public static CreativeTabs tab = new CTabFMA();
		
		public static Configuration cfg;
		
		public static final int ChalkGuiID = 0;
		
		
		public static class constants {
			public static final String SERVER_SIDE_PROXY = "skully.mod.fma.common.proxy.CommonProxy";
			public static final String CLIENT_SIDE_PROXY = "skully.mod.fma.client.proxy.ClientProxy";
			public static final String MOD_ID = "FMA";
			public static final String MOD_NAME = "Fullmetal Alchemy";
			public static final String MOD_VERSION = "Dev Version";
		}
		
		public static class CTabFMA extends CreativeTabs {
			public CTabFMA() {
				super("FMA");
			}

			@Override
			public Item getTabIconItem() {
				// TODO Auto-generated method stub
				return FMAItems.philStone;
			}
			
			@Override
			public String getBackgroundImageName() {
				return super.getBackgroundImageName();
			}
		}
		
		@EventHandler
		public void preInit(FMLPreInitializationEvent evt) {
			LOGGER.info("Starting FMA Loading sequence");
			updateMeta(evt.getModMetadata());
			
			try {
				LOGGER.info("Attempting Configuration Loading");
				cfg = new Configuration(new File(evt.getModConfigurationDirectory(), "FMA/config.ini"));
				proxy.sideSpecificSettings(cfg);
				LOGGER.info("Completed Configuration Loading");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			FMAItems.init();
			LOGGER.info("Items Initialised!");
		}
		
		@EventHandler
		public void init() {
			RecipesFMA.registerRecipes();
		}
		
		@EventHandler
		public void postInit() {
			NetworkRegistry.INSTANCE.registerGuiHandler(this, new FMAGuiHandler());
		}
		
		public void updateMeta(ModMetadata meta) {
			meta.authorList = Lists.newArrayList("Skully250", "ShadowChild");
			meta.autogenerated = false;
			meta.credits = "ShadowChild for helping with code, Everybody else for the great community";
			meta.description = "Become the ultimate Alchemist as you Deconstruct, Reconstruct and Transmute your way to glory";
		}
	
}