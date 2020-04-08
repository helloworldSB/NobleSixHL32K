package net.noblesix.hl32k.modules.api;

import net.noblesix.hl32k.HL32K;
import net.noblesix.hl32k.helpers.MultiColor;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Settings {
    public String name;
    private boolean state;

    private MultiColor multiColor;
    private float scaleFactor = 1.2f;
    protected Minecraft minecraft = Minecraft.getMinecraft();

    public Settings(String name) {
        this.name = name;
        getModuleName();
    }

    public void onGui(int offset) {
    }

    public void onToggle(boolean state){}
    public void onUpdate(){}
    public void onRender(){}


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
        return name;
    }
}
