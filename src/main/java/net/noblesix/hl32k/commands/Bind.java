package net.noblesix.hl32k.commands;

import net.noblesix.hl32k.HL32K;
import net.noblesix.hl32k.commands.api.Command;
import net.noblesix.hl32k.modules.api.Module;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.Field;

public class Bind extends Command {
    public Bind() {
        super("bind", "bind a module to a different key");
    }

    @Override
    public boolean DoCommand(String[] commandArgs) {
        if (commandArgs.length > 1) {
            Module module = HL32K.client.moduleManager.getModule(commandArgs[1]);
            if (module != null && commandArgs.length > 2) {
                Class<Keyboard> keys = Keyboard.class;
                Field key;

                try {
                    key = keys.getField("KEY_" + commandArgs[2].toUpperCase());
                    module.module.setKeyCode(key.getInt(null));
                    KeyBinding.resetKeyBindingArrayAndHash();
                    clientResponse(responseBuilder(module.name + " has been binded to " + Keyboard.getKeyName(module.module.getKeyCode())));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    clientResponse(responseBuilder("Key not found."));
                }
            }
        }

        return true;
    }
}
