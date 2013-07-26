package skully.fma.core.config;

import java.io.File;

import skully.fma.core.FullmetalAlchemy;
import net.minecraftforge.common.Configuration;

/**
 * @author viper283
 * 
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CoreConfiguration {

	private static final String block = Configuration.CATEGORY_BLOCK;
	private static final String item = Configuration.CATEGORY_ITEM;
	private static final String general = Configuration.CATEGORY_GENERAL;

	public void initialize(File file) {

		Configuration cfg = new Configuration(file);
		try {
			cfg.load();

			// Blocks
			ConfigSettings.chalkOreID = cfg.getTerrainBlock(block,
					"block.chalkOre.id", 211, "Chalk Ore ID").getInt();
			ConfigSettings.circle = cfg.getTerrainBlock(block, 
					"block.circle.id", 212, "Circle Block ID").getInt();
			ConfigSettings.RCircle = cfg.getTerrainBlock(block,
					"block.RCircle.id", 213, "RCircle Block ID").getInt();
			ConfigSettings.infuser = cfg.getTerrainBlock(block,
					"block.infuser.id", 214, "Infuser Block ID").getInt();
			ConfigSettings.Test = cfg.getTerrainBlock(block,
					"block.Test.id", 215, "Test Block ID").getInt();

			// Items
			ConfigSettings.metaID = cfg.getItem(item, "item.meta.id", 3840,
					"Items with metadata ID").getInt();
			ConfigSettings.pStoneID = cfg.getItem(item, "item.pStone.id", 3841,
					"Philosophers' Stone ID").getInt();
			ConfigSettings.alchemicCrystal = cfg.getItem(item, "item.achemicCrystal.id",
					3842, "Alchemic Crystal ID").getInt();
			ConfigSettings.redStone = cfg.getItem(item, "item.redStone.id", 3843, "Red-Stone Item ID").getInt();
			ConfigSettings.Stone = cfg.getItem(item, "item.Stone.id", 3844, "Stone Item ID").getInt();
			ConfigSettings.stone0 = cfg.getItem(item, "item.stone0.id", 3845, "Stone0 Item ID").getInt();
			ConfigSettings.stone1 = cfg.getItem(item, "item.stone1.id", 3846, "Stone1 Item ID").getInt();
			ConfigSettings.stone2 = cfg.getItem(item, "item.stone2.id", 3847, "Stone2 Item ID").getInt();
			ConfigSettings.ash = cfg.getItem(item, "item.ash.id", 3848, "Ash Item ID").getInt();
			ConfigSettings.eash = cfg.getItem(item, "item.eash.id", 3849, "Eash Item ID").getInt();
			ConfigSettings.gash = cfg.getItem(item, "item.gash.id", 3850, "Gash Item ID").getInt();
			ConfigSettings.AlchNotebook = cfg.getItem(item, "item.AlchNotebook.id", 3851, "AlchNotebook Item ID").getInt();
			ConfigSettings.ChalkStick = cfg.getItem(item, "Item.ChalkStick.id", 3852, "ChalkStick Item ID").getInt();
			ConfigSettings.FireGlove = cfg.getItem(item, "Item.FireGlove.id", 3853, "FireGlove ItemID").getInt();
			ConfigSettings.AlchSilk = cfg.getItem(item, "Item.AlchSilk.id", 3854, "AlchSilk ItemID").getInt();
			ConfigSettings.FireCircle = cfg.getItem(item, "Item.FireCircle.id", 3855, "FireCircle ItemID").getInt();
			ConfigSettings.ChalkCircle = cfg.getItem(item, "Item.ChalkCircle.id", 3856, "ChalkCircle ItemID").getInt();
			ConfigSettings.ChalkPyramid = cfg.getItem(item, "Item.ChalkPyramid.ID", 3857, "ChalkPyramid ItemID").getInt();
			ConfigSettings.IceGauntlet = cfg.getItem(item, "Item.IceGauntlet.ID", 3858, "IceGauntlet ItemID").getInt();
			ConfigSettings.ReconstructionCircle = cfg.getItem(item, "Item.ReconstructionCircle.ID", 3859, "ReconstructionsCircle ItemID").getInt();
			ConfigSettings.AlchNotes = cfg.getItem(item, "Item.AlchNote.id", 3860, "AlchNotes ItemID").getInt();
			ConfigSettings.Kunai = cfg.getItem(item, "Item.Kunai.id", 3861, "Kunai ItemID").getInt();
			ConfigSettings.KunaiFire = cfg.getItem(item, "Item.KunaiFire.id", 3863, "FireKunai ItemID").getInt();
			ConfigSettings.KunaiEnder = cfg.getItem(item, "Item.KunaiEnder.id", 3864, "EnderKunai ItemID").getInt();
			ConfigSettings.ChalkBag = cfg.getItem(item, "Item.ChalkBag.id", 3865, "ChalkBag ItemID").getInt();
			ConfigSettings.energyMeasurer = cfg.getItem(item, "Item.energyMeasure.id", 3866, "Energy Measurere ItemID").getInt();


			// Booleans
			FullmetalAlchemy.debugMode = cfg.get(general, "debugMode.enabled",
					false, "This may spam your log!").getBoolean(false);
			ConfigSettings.moduleLoadingSpammyMode = cfg
					.get(general, "moduleLoadingSpammyMode.enabled", false,
							"This will spam your log more than the debug mode! You are warned!")
							.getBoolean(false);
			ConfigSettings.firstRun = true;
			
		} catch(Exception e) {

			throw new RuntimeException(e);
		} finally {
			cfg.save();
		}
	}
}