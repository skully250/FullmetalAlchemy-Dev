package mods.fullmetalalchemy.core.client;

import java.util.EnumSet;

import mods.fullmetalalchemy.core.implement.IKeyBound;
import mods.fullmetalalchemy.core.util.Resources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class ClientKeybindHandler extends KeyHandler {

    private static KeyBinding debugKeybind = new KeyBinding("FMA Debug Mode",
            Keyboard.KEY_COMMA);
    private static KeyBinding item = new KeyBinding(
            "Special Item Abilty", Keyboard.KEY_G);

    private static EntityPlayer player;

    private static boolean[] flags = {
        false, false
    };

    private static KeyBinding[] keybinds = {
        debugKeybind, item
    };

    public ClientKeybindHandler() {

        super(keybinds, flags);
    }

    @Override
    public String getLabel() {

        return "FMA_CLIENT_KEYBIND";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb,
            boolean tickEnd, boolean isRepeat) {

        player = Minecraft.getMinecraft().thePlayer;

        if(kb.equals(debugKeybind)) {

            player.sendChatToPlayer("[FMA] Debug");

            toggleDebug();
        } else if(kb.equals(item)) {

            if(player != null) {

                if(!tickEnd) {

                    ItemStack stack = player.getCurrentEquippedItem();
                    Item item;

                    if(stack != null) {

                        item = stack.getItem();

                        IKeyBound keyBoundItem;

                        if(item instanceof IKeyBound) {

                            keyBoundItem = (IKeyBound)item;

                            keyBoundItem.keyBindActions();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {

    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.CLIENT);
    }

    public void toggleDebug() {

        Resources.debugMode = !Resources.debugMode;
    }
}