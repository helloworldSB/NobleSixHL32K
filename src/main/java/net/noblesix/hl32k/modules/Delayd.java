package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class Delayd extends Settings {
    public Delayd() {
        super("Delayd");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.disdelay --;
		HL32K.saveInformation();
	}
	if (!state){
		HL32K.disdelay --;
		HL32K.saveInformation();
	}
	}
}
