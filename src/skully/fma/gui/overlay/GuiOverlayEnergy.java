package skully.fma.gui.overlay;

import java.util.Collection;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import skully.fma.block.BlockFuelConverter;
import skully.fma.core.util.RenderUtil;
import skully.fma.item.FMAItems;
import skully.fma.item.alchemical.ItemPStone;
import skully.fma.item.energy.ItemEnergyStore;

public class GuiOverlayEnergy extends Gui {
	public final Minecraft mc;
	public Icon icon;

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
		
		if(mc.thePlayer.inventory.getCurrentItem() == new ItemStack(FMAItems.pStone)) {
			if(mc.thePlayer.getCurrentEquippedItem() != null) {
				if(mc.thePlayer.getCurrentEquippedItem().getItem() == FMAItems.pStone) {
					return;
				}
			}
			this.renderEnergyInFuelConverter(mc, mc.thePlayer, mc.thePlayer.getCurrentEquippedItem());
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

	public void renderEnergyOverlay(Minecraft minecraft, EntityPlayer player)
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
					currentEnergy = currentEnergy + (1000 - ItemEnergyStore.getEnergy(stack2));             
					cellsInInv++;

					if(!(cellsInInv > 1))
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
			int currentCellEnergy = (1000 - ItemEnergyStore.getEnergy(stack));

			if(ItemEnergyStore.getEnergy(stack) < 1000)
			{
				fullCellsInInv++;

				int scale = currentCellEnergy / 4;
			}
			
			Icon icon = FMAItems.pStone.getIconIndex(new ItemStack(FMAItems.pStone));
			this.drawTexturedModelRectFromIcon(0, 0, icon, 16, 16);
			RenderUtil.instance().drawTextureRect(0, 0, 32, 32, 30, 7, 5);
			minecraft.fontRenderer.drawStringWithShadow("\2477" + currentEnergy + "/" + maxEnergy, 16, 3 + offsetText, 0xffffff);
			minecraft.standardGalacticFontRenderer.drawStringWithShadow("JakeMichie helped with this", 16, 3 + 15, 0xffffff);

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

					currentEnergy = currentEnergy + (1000 - ItemEnergyStore.getEnergy(stack2));
				}
			}
		}

		ItemPStone stone = (ItemPStone) stack.getItem();

		if(player.inventory.hasItem(FMAItems.EnergyStore.itemID))
				this.renderEnergyOverlay(minecraft, player);
	}
	
	public void renderEnergyInFuelConverter(Minecraft minecraft, EntityPlayer player, ItemStack stack) {
		int Energy = BlockFuelConverter.Energy;
		
		minecraft.fontRenderer.drawStringWithShadow("Transmutation Energy: " + Energy, 100, 100, 0xFFFFFF);
	}
}
