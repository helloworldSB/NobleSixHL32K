package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Module;
import org.lwjgl.input.Keyboard;
import net.noblesix.hl32k.HL32K;

public class Rainbow extends Module {
    public Rainbow() {
        super("Rainbow", Keyboard.KEY_NONE);
    }
	int previous;
    @Override
    public void onGui(int offset) {}
}
