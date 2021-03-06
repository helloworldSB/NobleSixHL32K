package net.noblesix.hl32k.commands;

import net.noblesix.hl32k.commands.api.Command;

public class Say extends Command {
    public Say() {
        super("say", "send input through the client");
    }

    @Override
    public boolean DoCommand(String[] commandArgs) {
        String userMessage = "";
        for (int i = 1; i < commandArgs.length; i++) {
            userMessage += commandArgs[i] + " ";
        }

        if (!userMessage.equals("")) {
            minecraft.player.sendChatMessage(userMessage);
        }
        return true;
    }
}
