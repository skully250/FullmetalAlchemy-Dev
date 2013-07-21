package skully.fma.core.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;


public class FMACommands {

    public static CommandBase commandFma = new FMACommand();
    
    public static class FMACommand extends CommandBase {

		@Override
		public String getCommandName() {
			
			return "fma";
		}

		@Override
		public void processCommand(ICommandSender sender, String[] args) {

			
		}

		@Override
		public String getCommandUsage(ICommandSender icommandsender) {

			return "/fma <params>";
		}
    }
}