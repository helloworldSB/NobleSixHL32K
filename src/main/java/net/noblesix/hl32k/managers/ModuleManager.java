package net.noblesix.hl32k.managers;

import net.noblesix.hl32k.modules.*;
import net.noblesix.hl32k.hyperlethal32k.Auto32kBypass;
import net.noblesix.hl32k.modules.api.Module;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public class ModuleManager {
    public ArrayList<Module> moduleList = new ArrayList<Module>();

    public ModuleManager() {
		moduleList.add(new AutoTotem());
		moduleList.add(new Fullbright());
		moduleList.add(new DoubleTotem());
		moduleList.add(new InventoryWalk());
		moduleList.add(new NoFOV());
        moduleList.add(new Sprint());
		moduleList.add(new DisableHUD());
        moduleList.add(new Rainbow());
		moduleList.add(new Auto32kBypass());
		moduleList.add(new ClickGUI());
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> enabledModules = new ArrayList<Module>();
        for (Module module : moduleList) {
            if (module.getState()) {
                enabledModules.add(module);
            }
        }

        return enabledModules;
    }

    public Module getModule(String name) {
        Module the_module = null;
        for (Module module : moduleList) {
            if (module.name.equalsIgnoreCase(name)) {
                the_module = module;
                break;
            }
        }

        return the_module;
    }
}
