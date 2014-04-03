package skully.mod.fma.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import skully.mod.fma.client.gui.GuiChalkCircle;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemChalkStick extends FMAItem {

	public ItemChalkStick() {
		super("ChalkStick");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		//player.openGui(FullmetalAlchemy.instance, FullmetalAlchemy.ChalkGuiID, world, x, y, z);
		FMLClientHandler.instance().displayGuiScreen(player, new GuiChalkCircle(player.inventory, player.getCurrentEquippedItem(), world, x, y, z));
		world.setBlock(x, y, z, Blocks.bookshelf);
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

}
