package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class Reachp extends Settings {
    public Reachp() {
        super("Reachp");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.reach += 0.1F;
		HL32K.saveInformation();
	}
	if (!state){
		HL32K.reach += 0.1F;
		HL32K.saveInformation();
	}
	}
}
