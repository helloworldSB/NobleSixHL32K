package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class LastColor extends Settings {
    public LastColor() {
        super("LastColor");
    }
	public void onToggle(boolean state){
	if (state){
		if (HL32K.color >= 26) {
				HL32K.color = 0;
				HL32K.saveInformation();
			}if (HL32K.color < 0) {
				HL32K.color = 25;
				HL32K.saveInformation();
			} else {
				HL32K.color -= 1;
				HL32K.saveInformation();
			}
	}
	if (!state){
		if (HL32K.color >= 26) {
				HL32K.color = 0;
				HL32K.saveInformation();
			}if (HL32K.color < 0) {
				HL32K.color = 25;
				HL32K.saveInformation();
			} else {
				HL32K.color -= 1;
				HL32K.saveInformation();
			}
	}
	}
}