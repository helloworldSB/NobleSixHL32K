package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class Delayp extends Settings {
    public Delayp() {
        super("Delayp");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.disdelay ++;
		HL32K.saveInformation();
	}
	if (!state){
		HL32K.disdelay ++;
		HL32K.saveInformation();
	}
	}
}
