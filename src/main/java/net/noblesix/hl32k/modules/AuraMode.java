package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class AuraMode extends Settings {
    public AuraMode() {
        super("AuraMode");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.auramode = !HL32K.auramode;
		HL32K.saveInformation();
	}
	if (!state){
		HL32K.auramode = !HL32K.auramode;
		HL32K.saveInformation();
	}
	}
}
