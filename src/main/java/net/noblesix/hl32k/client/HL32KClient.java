package net.noblesix.hl32k.client;

import net.noblesix.hl32k.managers.ModuleManager;
import net.noblesix.hl32k.managers.SettingsManager;
import net.noblesix.hl32k.managers.UIManager;
import net.noblesix.hl32k.modules.api.Module;
import net.noblesix.hl32k.modules.api.Settings;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.Display;

public class HL32KClient {
    public final String CLIENT_NAME = "HL32K";
    public final static double CLIENT_VERSION = 6.0;

    public ModuleManager moduleManager;
	public SettingsManager settingsManager;
    public UIManager uiManager;

    public HL32KClient() {
        moduleManager = new ModuleManager();
		settingsManager = new SettingsManager();
        uiManager = new UIManager();

        Display.setTitle("NobleSix's HyperLethal Auto 32K V" + CLIENT_VERSION);
    }

    @SubscribeEvent
    public void onGui(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            uiManager.Draw();
            for (int i = 0; i < moduleManager.getEnabledModules().size(); i++) {
                moduleManager.getEnabledModules().get(i).onGui(i);
            }
			for (int i = 0; i < settingsManager.getEnabledModules().size(); i++) {
                settingsManager.getEnabledModules().get(i).onGui(i);
            }
        }
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.PlayerTickEvent event) {
        if (event.type == TickEvent.Type.PLAYER) {
            for (Module module : moduleManager.getEnabledModules()) {
                module.onUpdate();
            }
			for (Settings settings : settingsManager.getEnabledModules()) {
                settings.onUpdate();
            }
        }
    }

    @SubscribeEvent
    public void onRender(RenderHandEvent event) {
        for (Module module : moduleManager.getEnabledModules()) {
            module.onRender();
        }
		for (Settings settings : settingsManager.getEnabledModules()) {
            settings.onRender();
        }
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        for (Module module : moduleManager.moduleList) {
            module.onKey();
        }
    }
}
