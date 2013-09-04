package skully.fma.item.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import skully.fma.core.helper.NBThelper;
import skully.fma.item.FMAItems;
import skully.fma.item.ItemFMA;

import java.util.List;


public class ItemEnergyStore extends ItemFMA {


    public ItemEnergyStore(int par1) {
        super(par1);
        this.setNoRepair();
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return getEnergy(stack) != 1000;
    }

    public void onUpdate(ItemStack stack, World world, EntityPlayer player) {
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

    public ItemStack onItemRightClick(EntityPlayer player, World world, ItemStack stack, List list) {
        ItemEnergyStore.setEnergy(stack, 1000);
        list.add(this);
        return stack;
    }
}
