package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Module;
import org.lwjgl.input.Keyboard;

public class InventoryWalk extends Module {
    public InventoryWalk() {
        super("InventoryWalk", Keyboard.KEY_NONE);
    }
}
