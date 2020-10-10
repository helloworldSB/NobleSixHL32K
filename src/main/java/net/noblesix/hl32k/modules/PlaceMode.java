package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class PlaceMode extends Settings {
    public PlaceMode() {
        super("PlaceMode");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.lookingplace = !HL32K.lookingplace;
		HL32K.saveInformation();
	}
	if (!state){
		HL32K.lookingplace = !HL32K.lookingplace;
		HL32K.saveInformation();
	}
	}
}
