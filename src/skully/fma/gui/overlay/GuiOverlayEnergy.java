package skully.fma.gui.overlay;

import java.util.Collection;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import skully.fma.core.util.FMARenderUtil;
import skully.fma.item.FMAItems;
import skully.fma.item.alchemical.ItemEnergyStore;
import skully.fma.item.alchemical.ItemPStone;

public class GuiOverlayEnergy extends Gui {
	public final Minecraft mc;
	
	public GuiOverlayEnergy(Minecraft mc) {
		super();
		
		this.mc = mc;
	}
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void renderOverlay(RenderGameOverlayEvent event) 
	{
        if(event.isCancelable() || event.type != ElementType.HOTBAR || mc.gameSettings.showDebugInfo)
        {
            return;
        }
        
        if(mc.thePlayer.getCurrentEquippedItem() != null)
        {
            if(mc.thePlayer.getCurrentEquippedItem().getItem() == FMAItems.pStone)
            {
                renderPStoneOverlay(mc, mc.thePlayer, mc.thePlayer.getCurrentEquippedItem());
            }
        }
        
        if(mc.thePlayer.inventory.getCurrentItem() == new ItemStack(FMAItems.EnergyStore))
        {
            if(mc.thePlayer.getCurrentEquippedItem() != null)
            {
                if(mc.thePlayer.getCurrentEquippedItem().getItem() == FMAItems.pStone)
                {
                    return;
                }
            }
            
            this.renderEnergyOverlay(mc, mc.thePlayer);
        }
	}
	
    private void renderEnergyOverlay(Minecraft minecraft, EntityPlayer player)
    {
        int maxEnergy = 0;
        int currentEnergy = 0;
        int cellsInInv = 0;
        int fullCellsInInv = 0;
        
        int offsetX = 0;
        int offsetText = 0;
        
        HashMap<Integer, ItemStack> stackList = new HashMap<Integer, ItemStack>();
        
        for(int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack2 = player.inventory.mainInventory[i];
            
            if(stack2 != null)
            {
                if(stack2.getItem() == FMAItems.EnergyStore)
                {
                    ItemEnergyStore cell = (ItemEnergyStore) stack2.getItem();
                    
                    maxEnergy = maxEnergy + 1000;
                    currentEnergy = currentEnergy + (1000 - cell.getEnergy(stack2));
                    cellsInInv++;
                    
                    if(!(cellsInInv > 4))
                    {
                        stackList.put(i, stack2);
                    }
                }
            }
        }
        
        Collection<ItemStack> stackCollection = stackList.values();
        
        for(ItemStack stack : stackCollection)
        {
            ItemEnergyStore cell = (ItemEnergyStore) stack.getItem();
            int currentCellEnergy = 1000 - cell.getEnergy(stack);
            
            //RenderHelperSC.bindTexture("FullmetalAlchemy:textures/gui/astral_energy_cell_hud.png");
            
            if(cell.getEnergy(stack) < 1000)
            {
                fullCellsInInv++;
                
                int scale = currentCellEnergy / 4;
                
                //RenderHelperSC.bindTexture("FullmetalAlchemy:textures/gui/astral_energy_cell_hud.png");
                FMARenderUtil.instance().drawTextureRect(1, 1 + offsetX, 0, 0, scale, 256, 0.05f, 0.05f, 0.05f, 1.0, 1.0, 1.0);
            }
            
            minecraft.fontRenderer.drawStringWithShadow("\2477" + currentCellEnergy + "/" + maxEnergy, 16, 3 + offsetText, 0xffffff);
            
            offsetX += 256 + 48;
            offsetText += 15;
        }
    }
    
    private void renderPStoneOverlay(Minecraft minecraft, EntityPlayer player, ItemStack stack)
    {
        int currentEnergy = 0;
        
        for(int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack2 = player.inventory.mainInventory[i];
            
            if(stack2 != null)
            {
                if(stack2.getItem() == FMAItems.EnergyStore)
                {
                    ItemEnergyStore cell = (ItemEnergyStore) stack2.getItem();
                    
                    currentEnergy = currentEnergy + (1000 - cell.getEnergy(stack2));
                }
            }
        }
        
        ItemPStone stone = (ItemPStone) stack.getItem();
        
        if(player.inventory.hasItem(FMAItems.EnergyStore.itemID))
        /*{
            minecraft.fontRenderer.drawStringWithShadow("\2477" + AstralManager.getMode(stone.getMode(stack)).name, FMARenderUtil.width - minecraft.fontRenderer.getStringWidth(AstralManager.getMode(stone.getMode(stack)).name) - 2, 2, 0xffffff);
        } else
            if(!AstralManager.getMode(stone.getMode(stack)).hasErroredName)
            {
                minecraft.fontRenderer.drawStringWithShadow("\2477" + AstralManager.getMode(stone.getMode(stack)).name, FMARenderUtil.width - minecraft.fontRenderer.getStringWidth(AstralManager.getMode(stone.getMode(stack)).name) - 2, 2, 0xffffff);
            } else*/
                {
                    minecraft.fontRenderer.drawStringWithShadow("This is a test", FMARenderUtil.width - minecraft.fontRenderer.getStringWidth("This is a test") - 2, 2, 0xffffff);
                }
        
        /*stoneMode mode = AstralManager.getMode(stone.getMode(stack));
        
        HashMap<EnumUseType, Integer> energyRequirementMap = new HashMap<EnumUseType, Integer>();
        
        for(EnumUseType type : EnumUseType.values())
        {
            energyRequirementMap.put(type, mode.energyRequired(type, player));
        }
        
        Collection<Integer> energyRequirementCollection = energyRequirementMap.values();
        
        int offsetText = 15;
        
        for(EnumUseType type : EnumUseType.values())
        {
            if(energyRequirementMap.get(type) <= 0)
            {
                continue;
            }
            
            if(currentEnergy < energyRequirementMap.get(type))
            {
                minecraft.fontRenderer.drawStringWithShadow("\247c\247o" + energyRequirementMap.get(type) + " [" + type.suffix + "]", FMARenderUtil.width - minecraft.fontRenderer.getStringWidth(energyRequirementMap.get(type) + " [" + type.suffix + "]") - 2, offsetText, 0xffffff);
            } else {
                minecraft.fontRenderer.drawStringWithShadow("\2477\247o" + energyRequirementMap.get(type) + " [" + type.suffix + "]", FMARenderUtil.width - minecraft.fontRenderer.getStringWidth(energyRequirementMap.get(type) + " [" + type.suffix + "]") - 2, offsetText, 0xffffff);
            }
            
            offsetText += 10;
        }*/
        
        this.renderEnergyOverlay(minecraft, player);
    }
}
