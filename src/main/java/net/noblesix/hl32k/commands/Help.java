package net.noblesix.hl32k.commands;

import net.noblesix.hl32k.HL32K;
import net.noblesix.hl32k.commands.api.Command;

public class Help extends Command {
    public Help() {
        super("help", "lists all possible commands");
    }

    @Override
    public boolean DoCommand(String[] commandArgs) {
        for (Command command : HL32K.commands.commandManager.commandList) {
            clientResponse(responseBuilder(command.name + ": " + command.description));
        }

        return true;
    }
}
