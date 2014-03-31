package skully.mod.fma.common.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatStyle;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPhilosophersStone extends FMAItem {
	
	public ItemPhilosophersStone() {
		super("PStone");
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List lore, boolean par) {
		if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode())) {
			lore.add("The most powerful item in existence");
			lore.add("Is used for powerful crafting and transmutations");
		} else {
			lore.add("Hold shift for more info");
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {

		Block ID = world.getBlock(x, y, z);
		Block meta = world.getBlock(x, y, z);
		TileEntity te = world.getTileEntity(x, y, z);
		
		//TODO: Get a random block
		//Just to continue the theme i guess

		if (te != null)
			return false;
		else {
			player.swingItem();
			world.setBlock(x, y, z, Blocks.bedrock);
			return true;
		}
	}
}
