package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Module;
import org.lwjgl.input.Keyboard;

public class Fullbright extends Module {
    private float previousGamma;
    public Fullbright() {
        super("Fullbright", Keyboard.KEY_NONE);
    }

    @Override
    public void onToggle(boolean state) {
        super.onToggle(state);

        if (state) previousGamma = minecraft.gameSettings.gammaSetting;
        minecraft.gameSettings.gammaSetting = (state ? 100f : previousGamma);
    }
}
