package skully.fma.core.handler;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHandler {
	private static LocalizationHandler instance;
	
	public static LocalizationHandler instance() {
		return instance;
	}

	public void initialize() {
		LanguageRegistry.instance().loadLocalization("/assets/fullmetalalchemy/lang/en_US.properties", "en_US", false);
		LanguageRegistry.instance().loadLocalization("/assets/fullmetalalchemy/lang/en_GB.properties", "en_GB", false);
		LanguageRegistry.instance().addStringLocalization("death.attack." + "humanTransmutation", "You were killed for attempting Human Transmutation");
		LanguageRegistry.instance().addStringLocalization("death.attack." + "crimsonAlchemy", "Your dabbles have taken you too far");
	}
}
