package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Module;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class NoFOV extends Module {
    public NoFOV() {
        super("NoFOV", Keyboard.KEY_NONE);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onFOV(FOVUpdateEvent event) {
        if(getState()) {
            if (event.getEntity().isSprinting()) {
                event.setNewfov(1.15f);
            }
            else {
                event.setNewfov(1f);
            }
        }
    }
}
