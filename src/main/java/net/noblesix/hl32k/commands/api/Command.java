package net.noblesix.hl32k.commands.api;

import net.noblesix.hl32k.HL32K;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class Command {
    public String name;
    public String description;

    protected Minecraft minecraft = Minecraft.getMinecraft();

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean DoCommand(String[] commandArgs) { return false; }

    protected void clientResponse(ITextComponent response) {
        minecraft.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(HL32K.commands.commandManager.responsePrefix + response.getFormattedText()));
    }

    protected ITextComponent responseBuilder(String message) {
        return new TextComponentString(message).setStyle(HL32K.commands.commandManager.chatFormat);
    }
}
