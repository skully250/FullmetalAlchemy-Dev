package mods.fullmetalalchemy.core.command;

import net.minecraft.client.Minecraft;
import apexapi.common.commandlib.ApexCommand;
import apexapi.core.annotations.ApexUniversalCommand;
import apexapi.core.annotations.CommandFunction;

public class FMACommands {

    @ApexUniversalCommand()
    public static ApexCommand commandFma = new ApexCommand("version");

    @CommandFunction("version")
    public static void version() {

        Minecraft.getMinecraft().thePlayer.sendChatToPlayer("Version: ");
    }
}