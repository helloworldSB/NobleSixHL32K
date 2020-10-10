package net.noblesix.hl32k.modules.api;

import net.noblesix.hl32k.HL32K;
import net.noblesix.hl32k.helpers.MultiColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Module {
    public String name;
    public KeyBinding module;
    public boolean state;

    private MultiColor multiColor;
    private float scaleFactor = 1.2f;
    protected Minecraft minecraft = Minecraft.getMinecraft();

    public Module(String name, int keyCode) {
        module = new KeyBinding("HL32k "+name, keyCode, "NobleSix's HyperLethal Auto32K");
        ClientRegistry.registerKeyBinding(module);

        this.name = name;
        getModuleName();
    }

    public void onGui(int offset) {
		if(HL32K.allowHUD){
        multiColor = MultiColor.getRainbow();
        HL32K.client.uiManager.uiPosition.positionText(HL32K.client.uiManager.position_on_screen, getModuleName(), 2, 8, 1.2f);
        HL32K.client.uiManager.uiPosition.GLScale(scaleFactor);
        minecraft.fontRenderer.drawStringWithShadow(getModuleName(), HL32K.client.uiManager.uiPosition.x_position, (HL32K.client.uiManager.uiPosition.y_position + 10) + (offset * 10), multiColor.getRGB());
        HL32K.client.uiManager.uiPosition.GLScale( 1 / scaleFactor);
		}
    }

    public void onToggle(boolean state){}
    public void onUpdate(){}
    public void onRender(){}
    public void onKey(){
        if (module.isKeyDown()) {
            Toggle();
        }
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state){
        this.state = state;
    }

    public void Toggle() {
        setState(!getState());
        onToggle(getState());
    }

    private String getModuleName() {
        return name + " " + "[" + Keyboard.getKeyName(module.getKeyCode()) + "]";
    }
}
