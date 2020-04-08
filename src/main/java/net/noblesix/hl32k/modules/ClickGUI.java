package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.managers.GUIManager;
import net.noblesix.hl32k.modules.api.Module;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGui", Keyboard.KEY_N);
    }

    @Override
    public void onGui(int offset) {}

    @Override
    public void onToggle(boolean state) {
        minecraft.displayGuiScreen(new GUIManager());
    }
}
