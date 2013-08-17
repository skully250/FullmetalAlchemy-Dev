package skully.fma.core.util;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class FMALanguageRegister {

	public static void loadLanguageLocalizations() {
		LanguageRegistry.instance().loadLocalization("/assets/fullmetalalchemy/lang/en_US.properties", "en_US", false);
		LanguageRegistry.instance().loadLocalization("/assets/fullmetalalchemy/lang/en_GB.properties", "en_GB", false);
	}

	public static void loadDeathLocalizations() {
		LanguageRegistry.instance().addStringLocalization("death.attack." + "humanTransmutation", "You were killed for attempting Human Transmutation");
		LanguageRegistry.instance().addStringLocalization("death.attack." + "crimsonAlchemy", "Your dabbles have taken you too far");
	}
}
