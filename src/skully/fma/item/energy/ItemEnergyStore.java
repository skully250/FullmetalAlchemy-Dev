package skully.fma.item.energy;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import skully.fma.core.helper.NBThelper;
import skully.fma.item.FMAItems;
import skully.fma.item.ItemFMA;
import skully.fma.item.alchemical.ItemPStone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemEnergyStore extends ItemFMA {

	public ItemEnergyStore(int par1) {
		super(par1);
		this.setNoRepair();
		this.setMaxStackSize(1);
		this.setMaxDamage(1000);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return getEnergy(stack) != 1000 && getEnergy(stack) == 0;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean isEquipped) {
		stack.setItemDamage(ItemEnergyStore.getEnergy(stack));
	}

	public static void setEnergy(ItemStack stack, int energy) {
		NBThelper.setInteger(stack, "Alchemical Energy", energy);
	}

	public static int getEnergy(ItemStack stack) {
		return NBThelper.getInt(stack, "Alchemical Energy");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode)) {
			list.add("Stored Energy: " + (1000 - ItemEnergyStore.getEnergy(stack)));
		} else {
			list.add("Hold Shift for more info");
		}
	}
	
	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
    {
        return 0xFFFFFF;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int itemID, CreativeTabs tab, List list) {
		ItemStack stack = new ItemStack(FMAItems.EnergyStore, 1, 0);
		ItemEnergyStore.setEnergy(stack, 0);
		list.add(stack);

		ItemStack stack2 = new ItemStack(FMAItems.EnergyStore, 1, 1000);
		ItemEnergyStore.setEnergy(stack2, 1000);
		list.add(stack2);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, 
			int x, int y, int z, int par7, float par8, float par9, float par10) 
	{
		if (player.inventory.hasItem(FMAItems.pStone.itemID)) {
			if (getEnergy(itemStack) != 1000) {
				ItemPStone.decayEnergy += 10;
				setEnergy(itemStack, getEnergy(itemStack) + 10);
			}
			return true;
		}
		return false;
	}
}
