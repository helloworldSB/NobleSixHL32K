package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class newver extends Settings {
    public newver() {
        super("newver");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.a32kcore++;
		if(HL32K.a32kcore == 4){
			HL32K.a32kcore = 0;
			HL32K.saveInformation();
		}
	}
	if (!state){
		HL32K.a32kcore++;
		if(HL32K.a32kcore == 4){
			HL32K.a32kcore = 0;
			HL32K.saveInformation();
		}
	}
	}
}
