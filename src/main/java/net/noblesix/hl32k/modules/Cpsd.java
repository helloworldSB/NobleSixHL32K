package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class Cpsd extends Settings {
    public Cpsd() {
        super("Cpsd");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.cps--;
		HL32K.saveInformation();
	}
	if (!state){
		HL32K.cps--;
		HL32K.saveInformation();
	}
	}
}
