package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class MadMode extends Settings {
    public MadMode() {
        super("MadMode");
    }
	public void onToggle(boolean state){
	if (state)
	HL32K.madmode = !HL32K.madmode;
HL32K.saveInformation();
	if (!state)
	HL32K.madmode = !HL32K.madmode;
HL32K.saveInformation();
	}
}
