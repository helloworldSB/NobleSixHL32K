package net.noblesix.hl32k.managers;

import net.noblesix.hl32k.HL32K;
import net.noblesix.hl32k.client.HL32KClient;
import net.noblesix.hl32k.modules.api.Module;
import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.hyperlethal32k.Auto32kModule;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.util.ArrayList;

public class GUIManager extends GuiScreen {
    private final float scaleFactor = 1.5f;
    private final int x_pos = 2;
	public static boolean allowguimove = false;
	public static boolean allowrainbow = false;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        GlStateManager.scale(scaleFactor, scaleFactor, scaleFactor);
        drawRect(x_pos, 2, 79, mc.fontRenderer.FONT_HEIGHT * 2, new Color(128, 128, 128).getRGB());
        mc.fontRenderer.drawString("Modules", 28, (4 + mc.fontRenderer.FONT_HEIGHT) / 2, new Color(255, 255, 255).getRGB());
		drawRect(x_pos+79, 2, 159, mc.fontRenderer.FONT_HEIGHT * 2, new Color(128, 128, 128).getRGB());
		mc.fontRenderer.drawString("Settings", 105, (4 + mc.fontRenderer.FONT_HEIGHT) / 2, new Color(255, 255, 255).getRGB());
		drawRect(160, 2, 238, 134, Auto32kModule.fontcolor);
		mc.fontRenderer.drawStringWithShadow("Auto32k Info", 176, (4 + mc.fontRenderer.FONT_HEIGHT) / 2,  new Color(255, 255, 255).getRGB());
		drawRect(162, 22, 193, 34, new Color(164, 164, 164).getRGB());
		drawRect(195, 22, 236, 34, new Color(164, 164, 164).getRGB());
		drawRect(162, 36, 193, 48, new Color(164, 164, 164).getRGB());
		drawRect(195, 36, 236, 48, new Color(164, 164, 164).getRGB());
		drawRect(162, 50, 193, 62, new Color(164, 164, 164).getRGB());
		drawRect(195, 50, 236, 62, new Color(164, 164, 164).getRGB());
		drawRect(162, 64, 193, 76, new Color(164, 164, 164).getRGB());
		drawRect(195, 64, 236, 76, new Color(164, 164, 164).getRGB());
		drawRect(162, 78, 193, 90, new Color(164, 164, 164).getRGB());
		drawRect(195, 78, 236, 90, new Color(164, 164, 164).getRGB());
		drawRect(162, 92, 193, 104, new Color(164, 164, 164).getRGB());
		drawRect(195, 92, 236, 104, new Color(164, 164, 164).getRGB());
		drawRect(162, 106, 193, 118, new Color(164, 164, 164).getRGB());
		drawRect(195, 106, 236, 118, new Color(164, 164, 164).getRGB());
		drawRect(162, 120, 193, 132, new Color(164, 164, 164).getRGB());
		drawRect(195, 120, 236, 132, new Color(164, 164, 164).getRGB());
		mc.fontRenderer.drawStringWithShadow("Chat", 164, 121,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("32KMode", 164, 107,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("MadMode", 164, 93,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("Aura", 164, 79,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("Place", 164, 65,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("Reach", 164, 51,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("CPT", 164, 37,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("CPS", 164, 23,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow(String.valueOf(HL32K.cps), 197, 23,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow(String.valueOf(HL32K.cpt), 197, 37,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow(String.valueOf(HL32K.reach), 197, 51,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow((HL32K.lookingplace ? "Yawlook" : "Auto"), 197, 65,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow((HL32K.auramode ? "CPS" : "CPT"), 197, 79,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow((HL32K.madmode ? "Enabled" : "Disabled"), 197, 93,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow((HL32K.dispensermode ? "Dispenser" : "Hopper"), 197, 107,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow((HL32K.chatdisable ? "No Number" : "All"), 197, 121,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("NobleSix's HyperLethal Auto32K V" + String.valueOf(HL32KClient.CLIENT_VERSION) , 2, 140,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("Made by xX_NobleSix_Xx" , 2, 150,  new Color(255, 64, 64).getRGB());
		GlStateManager.scale(1 / scaleFactor, 1 / scaleFactor, 1 / scaleFactor);
		for (int i = 0; i < getModuleList().size();i++) {
            if (getModuleList().get(i).name != "ClickGui") {
				if (getModuleList().get(i).state){
				mc.fontRenderer.drawStringWithShadow("Enabled", 90, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * i),  new Color(64, 255, 64).getRGB());
				}
				else{
				mc.fontRenderer.drawStringWithShadow("Disabled", 90, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * i),  new Color(255, 64, 64).getRGB());
				}
				if (getModuleList().get(i).name == "InventoryWalk") {
					if(getModuleList().get(i).state)
					{
						allowguimove = true;
					}
					if(!getModuleList().get(i).state)
					{
						allowguimove = false;
					}
				}
				if (getModuleList().get(i).name == "Rainbow") {
					if(getModuleList().get(i).state)
					{
						allowrainbow = true;
					}
					if(!getModuleList().get(i).state)
					{
						allowrainbow = false;
					}
				}
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        for (int i = 0; i < getModuleList().size();i++) {
            if (getModuleList().get(i).name != "ClickGui") {
				buttonList.add(new GuiButton(i, 2, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * i), 86, mc.fontRenderer.FONT_HEIGHT * 2, getModuleList().get(i).name));
            }
        }
		for (int i = getModuleList().size(); i < getSettingsList().size()+getModuleList().size();i++){
			if (i == getModuleList().size()) {
				buttonList.add(new GuiButton(i, 180, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 0), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Cps +"));
			}
			if (i == getModuleList().size() + 1) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 0), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Cps -"));
			}
			if (i == getModuleList().size() + 2) {
				buttonList.add(new GuiButton(i, 180, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 1), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Cpt +"));
			}
			if (i == getModuleList().size() + 3) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 1), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Cpt -"));
			}
			if (i == getModuleList().size() + 4) {
				buttonList.add(new GuiButton(i, 180, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 2), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Reach +"));
			}
			if (i == getModuleList().size() + 5) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 2), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Reach -"));
			}
			if (i == getModuleList().size() + 6) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 3), 58, mc.fontRenderer.FONT_HEIGHT * 2, "LastColor"));
			}
			if (i == getModuleList().size() + 7) {
				buttonList.add(new GuiButton(i, 180, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 3), 58, mc.fontRenderer.FONT_HEIGHT * 2, "NextColor"));
			}
			if (i == getModuleList().size() + 8) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 4), 119, mc.fontRenderer.FONT_HEIGHT * 2, "MadMode"));
			}
			if (i == getModuleList().size() + 9) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 5), 119, mc.fontRenderer.FONT_HEIGHT * 2, "32KMode"));
			}
			if (i == getModuleList().size() + 10) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 6), 119, mc.fontRenderer.FONT_HEIGHT * 2, "AuraMode"));
			}
			if (i == getModuleList().size() + 11) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 7), 119, mc.fontRenderer.FONT_HEIGHT * 2, "PlaceMode"));
			}
			if (i == getModuleList().size() + 12) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 8), 119, mc.fontRenderer.FONT_HEIGHT * 2, "ToggleChat"));
			}
		}
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        for (int i = 0; i < getModuleList().size(); i++) {
            if (button.id == i) {
                getModuleList().get(i).Toggle();
            }
        }
		for (int i = getModuleList().size(); i < getSettingsList().size()+getModuleList().size(); i++) {
            if (button.id == i) {
                getSettingsList().get(i-getModuleList().size()).Toggle();
            }
        }
    }

    public ArrayList<Module> getModuleList() {
        return HL32K.client.moduleManager.moduleList;
    }
	private ArrayList<Settings> getSettingsList() {
        return HL32K.client.settingsManager.moduleList;
    }
}
