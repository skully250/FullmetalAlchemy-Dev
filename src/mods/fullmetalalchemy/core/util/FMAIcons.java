package mods.fullmetalalchemy.core.util;

import apex.util.ApexIconIndexer;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;

import static mods.fullmetalalchemy.item.FMAItems.meta_names;

public class FMAIcons {
	
	public static Icon[] metaItemIcons = new Icon[10];
	
	@ForgeSubscribe
	public void addIcons(TextureStitchEvent.Pre evt) {
		
		ApexIconIndexer index = new ApexIconIndexer("fullmetalalchemy", evt);
		
		for(int i = 0; i < meta_names.length; i++) {
			
			metaItemIcons[i] = index.getIcon(meta_names[i], false);
		}
	}
}
