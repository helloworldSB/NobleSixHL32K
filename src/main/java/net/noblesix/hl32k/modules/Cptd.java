package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class Cptd extends Settings {
    public Cptd() {
        super("Cptd");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.cpt--;
		HL32K.saveInformation();
	}
	if (!state){
		HL32K.cpt--;
		HL32K.saveInformation();
	}
	}
}
