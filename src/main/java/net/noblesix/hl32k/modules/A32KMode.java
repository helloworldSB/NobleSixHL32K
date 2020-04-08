package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class A32KMode extends Settings {
    public A32KMode() {
        super("A32KMode");
    }
	public void onToggle(boolean state){
	if (state)
	HL32K.dispensermode = !HL32K.dispensermode;
	HL32K.saveInformation();
	if (!state)
	HL32K.dispensermode = !HL32K.dispensermode;
	HL32K.saveInformation();
	}
}
