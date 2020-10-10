package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.modules.api.Settings;
import net.noblesix.hl32k.HL32K;

public class ToggleChat extends Settings {
    public ToggleChat() {
        super("ToggleChat");
    }
	public void onToggle(boolean state){
	if (state){
		HL32K.chatdisable = !HL32K.chatdisable;
		HL32K.saveInformation();
	}
	if (!state){
		HL32K.chatdisable = !HL32K.chatdisable;
		HL32K.saveInformation();
	}
	}
}
