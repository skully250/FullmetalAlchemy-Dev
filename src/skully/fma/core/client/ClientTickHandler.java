package skully.fma.core.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.World;
import skully.fma.core.tick.GameTick;
import skully.fma.core.tick.RenderTick;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {
	private Minecraft mc = Minecraft.getMinecraft();

	public void onClientWorldLoad(Minecraft mc, World world) {
		
	}

	public void onClientWorldUnload(Minecraft mc, World world) {
		
	}

	public void onClientWorldTick(Minecraft mc, World world) {
		
	}

	public void onHUDTick(Minecraft mc) {
		
	}

	public void onGUITick(Minecraft mc, GuiScreen guiscreen) {
		
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

		// But it can go here
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

		if(type.equals(TickType.CLIENT)) {

			GameTick.onTick(Minecraft.getMinecraft());
		} else if(type.equals(TickType.RENDER)) {

			RenderTick.onTick(Minecraft.getMinecraft());
		}

		// Still working out how capes work now

		//		// TODO: Use Better Code
		//		if(mc.theWorld != null && mc.theWorld.playerEntities.size() > 0) {
		//
		//			List<?> players = mc.theWorld.playerEntities;
		//
		//			for(int counter = 0; counter < players.size(); counter++) {
		//
		//				if(players.get(counter) != null) {
		//
		//					EntityPlayer thePlayer = (EntityPlayer)players.get(counter);
		//					String oldCloak = thePlayer.cloakUrl;
		//
		//					for(String staff : Reference.staff) {
		//
		//						if(thePlayer.username.equalsIgnoreCase(staff)) {
		//
		//							String newCloakUrl;
		//
		//							if(Reference.optiFineInstalled) {
		//
		//								newCloakUrl = "https://dl.dropboxusercontent.com/s/i1mdviw5bm5t6lo/32x%20FMA%20Cape.png?token_hash=AAGNHFlhkxn8mZ8bpAhLRT94dPEyXoTYDR-OCj8l6MLOGA&dl=1";
		//							} else {
		//
		//								newCloakUrl = "https://dl.dropboxusercontent.com/s/82ainzjt1kozr8b/16x%20FMA%20Cape.png?token_hash=AAEbTfvzlK5FVOqQ8u4qwbMo0rpr8ClptINlPzlCMgdRnQ&dl=1";
		//							}
		//
		//							thePlayer.cloakUrl = newCloakUrl;
		//							break;
		//						} else {
		//
		//
		//						}
		//					}
		//
		//					if (thePlayer.cloakUrl != oldCloak) {
		//
		//						mc.renderEngine.obtainImageData(thePlayer.cloakUrl, new ImageBufferDownload());
		//					}
		//				}
		//			}
		//		}
	}

	@Override
	public EnumSet<TickType> ticks() {

		return EnumSet.of(TickType.RENDER, TickType.CLIENT);
	}

	@Override
	public String getLabel() {

		return "FMA_CLIENT_TICK";
	}
}
