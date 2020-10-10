package net.noblesix.hl32k.modules;

import net.noblesix.hl32k.HL32K;
import net.noblesix.hl32k.util.ColourHolder;
import net.noblesix.hl32k.modules.api.Module;
import org.lwjgl.input.Keyboard;



public class DisableHUD extends Module {
    public DisableHUD() {
        super("DisableHUD", Keyboard.KEY_NONE);
    }
	
	
	public void onToggle(boolean state){
	if (state){
		HL32K.allowHUD =false;
		}
	if (!state){
		HL32K.allowHUD =true;
	}
	
	}
}
