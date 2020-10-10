package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class Cpsp extends Settings {
    public Cpsp() {
        super("Cpsp");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.cps++;
		HL32K.saveInformation();
	}
	if (!state){
		HL32K.cps++;
		HL32K.saveInformation();
	}
	}
}
