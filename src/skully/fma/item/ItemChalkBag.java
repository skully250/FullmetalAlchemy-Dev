package skully.fma.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import skully.fma.core.enums.BagEnumState;
import skully.fma.core.enums.EnumState;
import skully.fma.core.implement.IKeyBound;
import skully.fma.core.implement.IStatedItem;
import skully.fma.core.util.Resources;

import java.util.List;


public class ItemChalkBag extends ItemFMA implements IStatedItem, IKeyBound {

    //NBTTagCompound compound = new NBTTagCompound();
    //Chalk = compound.getInteger("Chalk Sticks");
    private int bagState;
    public int PChalk;

    public ItemChalkBag(int par1, BagEnumState defaultState) {
        super(par1);
        setMaxStackSize(1);
        //loadNBT();

        if(getState() != null && !(getState().equals(""))) {

            getState();
        } else {

            //			setState(defaultState);
        }
    }

	/*	private void loadNBT() {
        if (compound.getInteger("Chalk Sticks") < 0) {
			compound.setInteger("Chalk Sticks", 0);
		} else {
			compound.getInteger("Chalk Sticks");
		}
	}*/

    private Icon icons[] = new Icon[256];

    /**
     * 0 - Holding 1 - Taking
     */
    private int stateFlag;

    @Override
    public String getState() {

        //String state = FMAUtils.nbtHelper.getString(null, "ChalkBagState");
        String bState = null;
        if(bagState == 0) {
            return "Taking Chalk";
        } else if(bagState == 1) {
            return "Giving Chalk";
        }

        return bState;
    }

    public void setState(BagEnumState defaultState) {

        //FMAUtils.nbtHelper.setString(null, "ChalkBagState", defaultState.getName());
    }

    @Override
    public void setState(EnumState state) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        this.icons[0] = iconRegister.registerIcon(Resources.MOD_ID
                .toLowerCase() + ":" + "ChalkBagOff");
        this.icons[1] = iconRegister.registerIcon(Resources.MOD_ID
                .toLowerCase() + ":" + "ChalkBagOn");
    }

    @Override
    public Icon getIconFromDamage(int par1) {

        return icons[stateFlag];
    }

    @SuppressWarnings({
            "unchecked", "rawtypes"
    })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode)) {
            switch(bagState) {
                case 0:
                    list.add("Taking Chalk");
                    list.add("Will store your chalk sticks for later use");
                    //list.add("Chalk Sticks: " + compound.getInteger("Chalk Sticks"));
                    break;
                case 1:
                    list.add("Giving Chalk");
                    list.add("Will store your chalk sticks for later use");
                    //list.add("Chalk Sticks: " + compound.getInteger("Chalk Sticks"));
                    break;
            }
        } else {
            list.add("Hold Shift for more info");
        }
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {

        boolean updateTextures = getState() != null ? true : false;

        if(updateTextures) {

            if(bagState == 0) {

                stateFlag = 0;
            } else if(bagState == 1) {

                stateFlag = 1;
            } else {

                stateFlag = 0;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack) {

        return true;
    }

    @Override
    public void keyBindActions() {
        if(bagState == 0) {
            bagState = 1;
        } else if(bagState == 1) {
            bagState = 0;
        } else {
            bagState = 0;
        }
    }


    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player) {
        if(par2World.isRemote) {
            if(bagState == 0 && player.inventory.hasItem(FMAItems.ChalkStick.itemID)) {
                player.inventory.consumeInventoryItem(FMAItems.ChalkStick.itemID);
                player.inventoryContainer.detectAndSendChanges();
                return par1ItemStack;

            } else if(bagState == 1) {
                player.inventory.addItemStackToInventory(new ItemStack(FMAItems.ChalkStick, 1));
                player.inventoryContainer.detectAndSendChanges();
                return par1ItemStack;
            }
        }
        return par1ItemStack;
    }
}
