package skully.mod.fma.common.items;

import net.minecraft.item.Item;
import skully.mod.fma.FullmetalAlchemy;

public class FMAItem extends Item {
	
	public FMAItem(String name) {
		super();
		setUnlocalizedName(name);
		setTextureName(FullmetalAlchemy.constants.MOD_ID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(FullmetalAlchemy.tab);
	}
}
