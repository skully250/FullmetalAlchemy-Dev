package skully.fma.core.util;

import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import skully.fma.item.FMAItems;


public class FMAIcons {

    public static Icon[] metaItemIcons = new Icon[10];

    @ForgeSubscribe
    public void addIcons(TextureStitchEvent.Post evt) {

        if(evt.map.textureType == 1) {

            for(int i = 0; i < FMAItems.meta_names.length; i++) {

                metaItemIcons[i] = evt.map.registerIcon("fullmetalalchemy:" + FMAIcons.metaItemIcons[i]);
            }
        } else if(evt.map.textureType == 0) {

        }
        //		ApexIconIndexer index = new ApexIconIndexer("fullmetalalchemy", evt);
        //
        //		for(int i = 0; i < meta_names.length; i++) {
        //
        //			metaItemIcons[i] = index.getIcon(meta_names[i], false);
        //		}
    }
}
