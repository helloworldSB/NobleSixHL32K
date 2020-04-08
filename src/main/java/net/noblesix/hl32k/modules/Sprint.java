package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Module;
import org.lwjgl.input.Keyboard;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Keyboard.KEY_NONE);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (canSprint()) {
            minecraft.player.setSprinting(true);
        }
    }

    private boolean canSprint() {
        return (minecraft.player.moveForward > 0 && !minecraft.player.isActiveItemStackBlocking() && !minecraft.player.isOnLadder() && !minecraft.player.collidedHorizontally && minecraft.player.getFoodStats().getFoodLevel() > 6);
    }
}
