package skully.fma.item.energy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import skully.fma.energy.FMAPower;
import skully.fma.item.ItemFMA;
import skully.fma.tileEntity.TileEntityCircle;

import java.util.List;


public class ItemEnergyMeasurer extends ItemFMA {

    public ItemEnergyMeasurer(int par1) {
        super(par1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        //if (player.isSneaking()){
        list.add("Trans Energy: " + FMAPower.transEnergy);
        list.add("Trans Decay: " + FMAPower.transDecay);
        list.add(FMAPower.decayDisplay());
        //} else {
        //list.add("Hold shift for more info");
        //}
    }

    public void onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int x, int y, int z) {
        TileEntityCircle tile = (TileEntityCircle)par2World.getBlockTileEntity(x, y, z);
        int l = TileEntityCircle.energy;
        System.out.println(l);
        par3EntityPlayer.addChatMessage("Energy: " + l);
    }
}
