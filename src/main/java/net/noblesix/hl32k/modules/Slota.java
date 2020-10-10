package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class Slota extends Settings {
    public Slota() {
        super("Slota");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.saveInformation();
		HL32K.slot = 1;
		HL32K.loadInformation();
	}
	if (!state){
		HL32K.saveInformation();
		HL32K.slot = 1;
		HL32K.loadInformation();
	}
	}
}
