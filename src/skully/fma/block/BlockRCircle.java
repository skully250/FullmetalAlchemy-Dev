package skully.fma.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import skully.fma.core.util.ConvertUtil;
import skully.fma.crafting.FMAResearchRecipes;
import skully.fma.item.FMAItems;


public class BlockRCircle extends BlockFMA {

    public static boolean First = true;
    public static boolean Second = true;
    public static boolean Third = true;
    public static boolean Fourth = true;

    public BlockRCircle(int par1) {
        super(par1, Material.snow);
        this.setBlockBounds(-1.0F, 0.0F, -1.0F, 2.0F, 0.125F, 2.0F);

    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
        FMAResearchRecipes recipes = new FMAResearchRecipes();
        if(world.isRemote) {
            return true;
        } else {
            int Chalk = FMAItems.ChalkStick.itemID;
            if(player.getCurrentEquippedItem() != null) {
                if(player.getCurrentEquippedItem().getItem() == FMAItems.ChalkStick && player.inventory.hasItem(FMAItems.AlchNotebook.itemID) && player.inventory.hasItem(Chalk) && First == true) {
                    //					Random rand = new Random();
                    int i1 = world.getBlockMetadata(par2, par3, par4);
                    int k1 = 8 - (i1 & 8);
                    world.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "Writing", 0.3F, k1 > 0 ? 0.6F : 0.5F);
                    player.sendChatToPlayer(ConvertUtil.toChatComponent("My research has yielded new Items and Transmutation possibilities!"));

                    FMAResearchRecipes.initializeAlchemy();
                    player.inventory.consumeInventoryItem(Chalk);
                    player.inventoryContainer.detectAndSendChanges();
                    First = false;
                } else if(player.getCurrentEquippedItem().getItem() == FMAItems.FireCircle && player.inventory.hasItem(FMAItems.AlchNotebook.itemID) && player.inventory.hasItem(Chalk) && Second == true) {
                    int i1 = world.getBlockMetadata(par2, par3, par4);
                    int k1 = 8 - (i1 & 8);
                    world.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "Writing", 0.3F, k1 > 0 ? 0.6F : 0.5F);
                    player.sendChatToPlayer(ConvertUtil.toChatComponent("New alchemical fire abilities have been revealed to me!"));

                    FMAResearchRecipes.initializeFire();
                    player.inventory.consumeInventoryItem(Chalk);
                    player.inventoryContainer.detectAndSendChanges();
                    Second = false;
                } else if(player.getCurrentEquippedItem().getItem() == FMAItems.ChalkCircle && player.inventory.hasItem(FMAItems.AlchNotebook.itemID) && player.inventory.hasItem(Chalk) && Third == true) {
                    int i1 = world.getBlockMetadata(par2, par3, par4);
                    int k1 = 8 - (i1 & 8);
                    world.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "Writing", 0.3F, k1 > 0 ? 0.6F : 0.5F);
                    player.sendChatToPlayer(ConvertUtil.toChatComponent("New alchemical ice abilities have been revealed to me!"));

                    FMAResearchRecipes.initializeIce();
                    player.inventory.consumeInventoryItem(Chalk);
                    player.inventoryContainer.detectAndSendChanges();
                    Third = false;
                } else if(player.getCurrentEquippedItem().getItem() == FMAItems.ChalkPyramid && player.inventory.hasItem(FMAItems.AlchNotebook.itemID) && player.inventory.hasItem(Chalk) && Fourth == true) {
                    int i1 = world.getBlockMetadata(par2, par3, par4);
                    int k1 = 8 - (i1 & 8);
                    world.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "Writing", 0.3F, k1 > 0 ? 0.6F : 0.5F);
                    player.sendChatToPlayer(ConvertUtil.toChatComponent("Reconstruction and Deconstruction has been revealed to me!"));

                    FMAResearchRecipes.initializeRec();
                    player.inventory.consumeInventoryItem(Chalk);
                    player.inventoryContainer.detectAndSendChanges();
                    Fourth = false;
                } else if(player.getCurrentEquippedItem().getItem() == FMAItems.ChalkStick) {
                    player.sendChatToPlayer(ConvertUtil.toChatComponent("Writing notes on this Circle may yield new alchemical opportunities."));
                } else if(First == false) {
                    FMAResearchRecipes.initializeAlchemy();
                } else if(Second == false) {
                    FMAResearchRecipes.initializeFire();
                } else if(Third == false) {
                    FMAResearchRecipes.initializeIce();
                } else if(Fourth == false) {
                    FMAResearchRecipes.initializeRec();
                }
            }
        }
        return true;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return 0;
    }
}