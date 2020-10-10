package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class Slotf extends Settings {
    public Slotf() {
        super("Slotf");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.saveInformation();
		HL32K.slot = 6;
		HL32K.loadInformation();
	}
	if (!state){
		HL32K.saveInformation();
		HL32K.slot = 6;
		HL32K.loadInformation();
	}
	}
}
