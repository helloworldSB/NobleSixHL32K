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
		drawRect(160, 2, 238, 134, HL32K.fontcolor);
		drawRect(240, 2, 275, 134, HL32K.fontcolor);
		drawRect(160, 136, 275, 150, HL32K.fontcolor);
		mc.fontRenderer.drawStringWithShadow("Auto32k Info", 176, (4 + mc.fontRenderer.FONT_HEIGHT) / 2,  new Color(255, 255, 255).getRGB());
		drawRect(242, 22, 273, 34, new Color(164, 164, 164).getRGB());
		mc.fontRenderer.drawStringWithShadow("Slot " + String.valueOf(HL32K.slot), 244, 23,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("Config", 247, (4 + mc.fontRenderer.FONT_HEIGHT) / 2,  new Color(255, 255, 255).getRGB());
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
		drawRect(195, 137, 236, 149, new Color(164, 164, 164).getRGB());
		mc.fontRenderer.drawStringWithShadow("Core", 164, 138,  new Color(255, 255, 255).getRGB());
		mc.fontRenderer.drawStringWithShadow("Chat", 164, 121,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("[D]Delay", 164, 107,  new Color(255, 64, 64).getRGB());
		if(HL32K.a32kcore != 3){
		mc.fontRenderer.drawStringWithShadow("MadMode", 164, 93,  new Color(255, 64, 64).getRGB());
		} else mc.fontRenderer.drawStringWithShadow("Multi", 164, 93,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("Aura", 164, 79,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("Place", 164, 65,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("Reach", 164, 51,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("CPT", 164, 37,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("CPS", 164, 23,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow(String.valueOf(HL32K.cps), 197, 23,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow(String.valueOf(HL32K.cpt), 197, 37,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow(String.valueOf(HL32K.reach), 197, 51,  new Color(255, 64, 64).getRGB());
		if(HL32K.a32kcore != 2){
		mc.fontRenderer.drawStringWithShadow((HL32K.lookingplace ? "Looking" : "Auto"), 197, 65,  new Color(255, 64, 64).getRGB());
		} else mc.fontRenderer.drawStringWithShadow("Unsupport", 197, 65,  new Color(255, 64, 64).getRGB());
		if(HL32K.a32kcore != 2){
		mc.fontRenderer.drawStringWithShadow((HL32K.auramode ? "CPS" : "CPT"), 197, 79,  new Color(255, 64, 64).getRGB());
		} else mc.fontRenderer.drawStringWithShadow("Unsupport", 197, 79,  new Color(255, 64, 64).getRGB());
		if(HL32K.a32kcore != 3 && HL32K.a32kcore != 2){
		mc.fontRenderer.drawStringWithShadow((HL32K.madmode ? "Enabled" : "Disabled"), 197, 93,  new Color(255, 64, 64).getRGB());
		} else if (HL32K.a32kcore == 3) mc.fontRenderer.drawStringWithShadow("Enabled", 197, 93,  new Color(255, 64, 64).getRGB());
		else mc.fontRenderer.drawStringWithShadow("Unsupport", 197, 93,  new Color(255, 64, 64).getRGB());
		if(HL32K.a32kcore != 1 && HL32K.a32kcore != 2){
		mc.fontRenderer.drawStringWithShadow(String.valueOf(HL32K.disdelay), 197, 107,  new Color(255, 64, 64).getRGB());
		} else mc.fontRenderer.drawStringWithShadow("Unsupport", 197, 107,  new Color(255, 64, 64).getRGB());
		if(HL32K.a32kcore != 2){
		mc.fontRenderer.drawStringWithShadow((HL32K.chatdisable ? "No Number" : "All"), 197, 121,  new Color(255, 64, 64).getRGB());
		} else mc.fontRenderer.drawStringWithShadow("Unsupport", 197, 121,  new Color(255, 64, 64).getRGB());
		if(HL32K.a32kcore == 0){
			mc.fontRenderer.drawStringWithShadow("Noble6HL", 197, 138,  new Color(255, 64, 64).getRGB());
		}
		if(HL32K.a32kcore == 1){
			mc.fontRenderer.drawStringWithShadow("BadAuto32K", 197, 138,  new Color(255, 64, 64).getRGB());
		}
		if(HL32K.a32kcore == 2){
			mc.fontRenderer.drawStringWithShadow("WWEAuto32K", 197, 138,  new Color(255, 64, 64).getRGB());
		}
		if(HL32K.a32kcore == 3){
			mc.fontRenderer.drawStringWithShadow("Noble6GOD", 197, 138,  new Color(255, 64, 64).getRGB());
		}
		mc.fontRenderer.drawStringWithShadow("NobleSix's HyperLethal Auto32K V" + String.valueOf(HL32KClient.CLIENT_VERSION) , 2, 140,  new Color(255, 64, 64).getRGB());
		mc.fontRenderer.drawStringWithShadow("Made by xX_NobleSix_Xx Enjoy Your Multi Mode" , 2, 150,  new Color(255, 64, 64).getRGB());
		GlStateManager.scale(1 / scaleFactor, 1 / scaleFactor, 1 / scaleFactor);
		for (int i = 0; i < getModuleList().size();i++) {
            if (i != getModuleList().size() -1) {
				if (getModuleList().get(i).state){
				mc.fontRenderer.drawStringWithShadow("Enabled", 90, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * i),  new Color(64, 255, 64).getRGB());
				}
				else{
				mc.fontRenderer.drawStringWithShadow("Disabled", 90, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * i),  new Color(255, 64, 64).getRGB());
				}
				if (i == 3) {
					if(getModuleList().get(i).state)
					{
						allowguimove = true;
					}
					if(!getModuleList().get(i).state)
					{
						allowguimove = false;
					}
				}
				if (i == 7) {
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
            if (i != getModuleList().size() -1) {
				buttonList.add(new GuiButton(i, 2, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * i), 86, mc.fontRenderer.FONT_HEIGHT * 2, getModuleList().get(i).name));
            }
        }
		for (int i = getModuleList().size(); i < getSettingsList().size()+getModuleList().size();i++){
			if (i == getModuleList().size()) {
				buttonList.add(new GuiButton(i, 181, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 0), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Cps +"));
			}
			if (i == getModuleList().size() + 1) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 0), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Cps -"));
			}
			if (i == getModuleList().size() + 2) {
				buttonList.add(new GuiButton(i, 181, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 1), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Cpt +"));
			}
			if (i == getModuleList().size() + 3) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 1), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Cpt -"));
			}
			if (i == getModuleList().size() + 4) {
				buttonList.add(new GuiButton(i, 181, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 2), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Reach +"));
			}
			if (i == getModuleList().size() + 5) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 2), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Reach -"));
			}
			if (i == getModuleList().size() + 6) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 3), 58, mc.fontRenderer.FONT_HEIGHT * 2, "LastColor"));
			}
			if (i == getModuleList().size() + 7) {
				buttonList.add(new GuiButton(i, 181, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 3), 58, mc.fontRenderer.FONT_HEIGHT * 2, "NextColor"));
			}
			if (i == getModuleList().size() + 8) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 5), 119, mc.fontRenderer.FONT_HEIGHT * 2, "MadMode"));
			}
			if (i == getModuleList().size() + 9) {
				buttonList.add(new GuiButton(i, 181, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 4), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Disdelay +"));
			}
			if (i == getModuleList().size() + 10) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 4), 58, mc.fontRenderer.FONT_HEIGHT * 2, "Disdelay -"));
			}
			if (i == getModuleList().size() + 11) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 6), 119, mc.fontRenderer.FONT_HEIGHT * 2, "AuraMode"));
			}
			if (i == getModuleList().size() + 12) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 7), 119, mc.fontRenderer.FONT_HEIGHT * 2, "PlaceMode"));
			}
			if (i == getModuleList().size() + 13) {
				buttonList.add(new GuiButton(i, 121, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 8), 119, mc.fontRenderer.FONT_HEIGHT * 2, "ToggleChat"));
			}
			if (i == getModuleList().size() + 14) {
				buttonList.add(new GuiButton(i, 363, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 1) +4, 46, mc.fontRenderer.FONT_HEIGHT * 2, "Slot 1"));
			}
			if (i == getModuleList().size() + 15) {
				buttonList.add(new GuiButton(i, 363, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 2) +5, 46, mc.fontRenderer.FONT_HEIGHT * 2, "Slot 2"));
			}
			if (i == getModuleList().size() + 16) {
				buttonList.add(new GuiButton(i, 363, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 3) +6, 46, mc.fontRenderer.FONT_HEIGHT * 2, "Slot 3"));
			}
			if (i == getModuleList().size() + 17) {
				buttonList.add(new GuiButton(i, 363, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 4) +7, 46, mc.fontRenderer.FONT_HEIGHT * 2, "Slot 4"));
			}
			if (i == getModuleList().size() + 18) {
				buttonList.add(new GuiButton(i, 363, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 5) +8, 46, mc.fontRenderer.FONT_HEIGHT * 2, "Slot 5"));
			}
			if (i == getModuleList().size() + 19) {
				buttonList.add(new GuiButton(i, 363, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 6) +9, 46, mc.fontRenderer.FONT_HEIGHT * 2, "Slot 6"));
			}
			if (i == getModuleList().size() + 20) {
				buttonList.add(new GuiButton(i, 363, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 7) +10, 46, mc.fontRenderer.FONT_HEIGHT * 2, "Default"));
			}
			if (i == getModuleList().size() + 21) {
				buttonList.add(new GuiButton(i, 363, (12 + mc.fontRenderer.FONT_HEIGHT * 2) + (20 * 8) +16, 46, mc.fontRenderer.FONT_HEIGHT * 2, "Change"));
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
