package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class def extends Settings {
    public def() {
        super("def");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.cps = 13;
		HL32K.color=1;
		HL32K.a32kcore = 0;
		HL32K.cpt = 1;
		HL32K.disdelay = 50;
		HL32K.dispensermode = false;
		HL32K.reach = 8.00F;
		HL32K.madmode = false;
		HL32K.chatdisable = true;
		HL32K.multi = true;
		HL32K.lookingplace = false;	
		HL32K.saveInformation();
		HL32K.loadInformation();
	}
	if (!state){
		HL32K.cps = 13;
		HL32K.color=1;
		HL32K.a32kcore = 0;
		HL32K.cpt = 1;
		HL32K.disdelay = 50;
		HL32K.dispensermode = false;
		HL32K.reach = 8.00F;
		HL32K.madmode = false;
		HL32K.chatdisable = true;
		HL32K.multi = true;
		HL32K.lookingplace = false;	
		HL32K.saveInformation();
		HL32K.loadInformation();
	}
	}
}
