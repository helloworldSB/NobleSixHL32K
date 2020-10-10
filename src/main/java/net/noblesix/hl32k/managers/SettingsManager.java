package net.noblesix.hl32k.managers;

import net.noblesix.hl32k.modules.*;
import net.noblesix.hl32k.modules.api.Settings;

import java.util.ArrayList;

public class SettingsManager {
    public ArrayList<Settings> moduleList = new ArrayList<Settings>();

    public SettingsManager() {
        moduleList.add(new Cpsp());
		moduleList.add(new Cpsd());
		moduleList.add(new Cptp());
		moduleList.add(new Cptd());
		moduleList.add(new Reachp());
		moduleList.add(new Reachd());
		moduleList.add(new LastColor());
		moduleList.add(new NextColor());
		moduleList.add(new MadMode());
		moduleList.add(new Delayp());
		moduleList.add(new Delayd());
		moduleList.add(new AuraMode());
		moduleList.add(new PlaceMode());
		moduleList.add(new ToggleChat());
		moduleList.add(new Slota());
		moduleList.add(new Slotb());
		moduleList.add(new Slotc());
		moduleList.add(new Slotd());
		moduleList.add(new Slote());
		moduleList.add(new Slotf());
		moduleList.add(new def());
		moduleList.add(new newver());
    }

    public ArrayList<Settings> getEnabledModules() {
        ArrayList<Settings> enabledModules = new ArrayList<Settings>();
        for (Settings module : moduleList) {
            if (module.getState()) {
                enabledModules.add(module);
            }
        }

        return enabledModules;
    }

    public Settings getModule(String name) {
        Settings the_module = null;
        for (Settings module : moduleList) {
            if (module.name.equalsIgnoreCase(name)) {
                the_module = module;
                break;
            }
        }

        return the_module;
    }
}
