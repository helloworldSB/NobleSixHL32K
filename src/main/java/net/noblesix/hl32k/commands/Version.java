package net.noblesix.hl32k.commands;

import net.noblesix.hl32k.HL32K;
import net.noblesix.hl32k.commands.api.Command;

public class Version extends Command {
    public Version() {
        super("version", "gets the version of the current client");
    }

    @Override
    public boolean DoCommand(String[] commandArgs) {
        clientResponse(responseBuilder("You are running " + HL32K.client.CLIENT_NAME + " " + HL32K.client.CLIENT_VERSION));

        return true;
    }
}
