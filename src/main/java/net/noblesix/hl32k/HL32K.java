package net.noblesix.hl32k;

import net.noblesix.hl32k.modules.api.Module;
import net.noblesix.hl32k.managers.GUIManager;
import net.noblesix.hl32k.client.HL32KClient;
import net.noblesix.hl32k.client.HL32KCommands;
import net.noblesix.hl32k.hyperlethal32k.Auto32kModule;
import net.noblesix.hl32k.hyperlethal32k.Auto32kModuleBad;
import net.noblesix.hl32k.hyperlethal32k.Auto32kModuleWWE;
import net.noblesix.hl32k.hyperlethal32k.Auto32kModuleGOD;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.client.settings.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.*;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.*;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStreamReader;

@Mod(
        modid = "hyperlethal32k",
        version = "6.0",
        acceptedMinecraftVersions = "[1.12.2]"
)
public class HL32K
{
    public static HL32KClient client;
    public static HL32KCommands commands;
	public static boolean guimoveon = false ;


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(client = new HL32KClient());
        MinecraftForge.EVENT_BUS.register(commands = new HL32KCommands());
    }
	public static Minecraft mc = Minecraft.getMinecraft();

	public static int fontcolor;
	public static boolean allowHUD = true;
	public static int disdelay = 50;
	public static int slot = 1;
	public static boolean is32kEnabled;
	public static boolean wweis32kEnabled;
	public static int a32kcore = 0;
	public static boolean auramode;
	public static int cps = 13;
	public static boolean isKillauraOptionEnabled = true;
	public static boolean dispensermode = false;
	public static int red = 256;
	public static int blue = 0;
	public static int green = 0;
	public static boolean RGB = false;
	public static String font;
	public static int color = 0;
	public static KeyBinding auto32kKeybind= new KeyBinding("Auto32K toggle", Keyboard.KEY_NONE, "Auto 32K Settings");
	public static KeyBinding auto32kCpsIncrementKeybind= new KeyBinding("cps +", Keyboard.KEY_NONE, "Auto 32K Settings");
	public static KeyBinding auto32kCpsdecrementKeybind= new KeyBinding("cps -", Keyboard.KEY_NONE, "Auto 32K Settings");
	public static KeyBinding changecolor= new KeyBinding("Change HUD",43,"Auto 32K Settings");
	public static KeyBinding auto32kToggleKillauraKeybind= new KeyBinding("Kill Aura", Keyboard.KEY_NONE, "Auto 32K Settings");
	public static KeyBinding reachplus= new KeyBinding("Reach +",Keyboard.KEY_NONE, "Auto 32K Settings");
	public static KeyBinding reachminus= new KeyBinding("Reach -",Keyboard.KEY_NONE,"Auto 32K Settings");
	public static KeyBinding auraswitch= new KeyBinding("Switch Aura Mode",43,"Auto 32K Settings");
	public static KeyBinding cptplus= new KeyBinding("cpt +",0,"Auto 32K Settings");
	public static KeyBinding cptminus= new KeyBinding("cpt -",0,"Auto 32K Settings");
	public static KeyBinding switch32k= new KeyBinding("Hopper / Dispenser",0,"Auto 32K Settings");
	public static KeyBinding switchmad= new KeyBinding("Mad Mode",0,"Auto 32K Settings");
	public static KeyBinding switchplace= new KeyBinding("PlaceMode",0,"Auto 32K Settings");
	public static float reach = 8.00F;
	public static boolean testmode = false;
	public static boolean madmode = false;
	public static int cpt = 1;
	public static  List<String> friends = getFriends();
	public static boolean readfriends = false;
	public static boolean lookingplace = false;	
	public static boolean chatdisable = true;
	public static boolean multi = true;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ClientRegistry.registerKeyBinding(auto32kKeybind);
		ClientRegistry.registerKeyBinding(auto32kCpsIncrementKeybind);
		ClientRegistry.registerKeyBinding(auto32kCpsdecrementKeybind);
		ClientRegistry.registerKeyBinding(auto32kToggleKillauraKeybind);
		ClientRegistry.registerKeyBinding(changecolor);
		ClientRegistry.registerKeyBinding(reachplus);
		ClientRegistry.registerKeyBinding(reachminus);
		ClientRegistry.registerKeyBinding(auraswitch);
		ClientRegistry.registerKeyBinding(cptplus);
		ClientRegistry.registerKeyBinding(cptminus);
		ClientRegistry.registerKeyBinding(switch32k);
		ClientRegistry.registerKeyBinding(switchmad);
		ClientRegistry.registerKeyBinding(switchplace);
		mc.gameSettings.loadOptions();
	}

	
	@EventHandler
	public void post(FMLPostInitializationEvent event){
		loadInformation();
		getFriends();
		MinecraftForge.EVENT_BUS.register(new Auto32kModule());
		MinecraftForge.EVENT_BUS.register(new Auto32kModuleBad());
		MinecraftForge.EVENT_BUS.register(new Auto32kModuleWWE());
		MinecraftForge.EVENT_BUS.register(new Auto32kModuleGOD());
	}
	public static void saveInformation() {
		try {
			File file = new File("./", "Auto 32k Config" + String.valueOf(slot) +".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write("cps:" + cps + "\r\n");
			bufferedWriter.write("a32kcore:" + a32kcore + "\r\n");
			bufferedWriter.write("disdelay:" + disdelay + "\r\n");
			bufferedWriter.write("killaura:" + isKillauraOptionEnabled + "\r\n");
			bufferedWriter.write("font:" + font + "\r\n");
			bufferedWriter.write("color:" + color + "\r\n");
			bufferedWriter.write("reach:" + reach + "\r\n");
			bufferedWriter.write("killauramode:" + auramode + "\r\n");
			bufferedWriter.write("cpt:" + cpt  + "\r\n");
			bufferedWriter.write("32kmode:" + dispensermode  + "\r\n");
			bufferedWriter.write("madmode:"+madmode+"\r\n");
			bufferedWriter.write("togglechat:"+chatdisable+"\r\n");
			bufferedWriter.write("multi:"+multi+"\r\n");
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadInformation() {
		try {
			File file = new File("./", "Auto 32k Config" + String.valueOf(slot) +".txt");
			if (!file.exists()) {
				saveInformation();
				return;
			}
			FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if(!line.contains(":")) {
					continue;
				}
				String key = line.split(":")[0];
				String value = line.split(":")[1];
				if(key.equalsIgnoreCase("cps")) {
					cps = Integer.parseInt(value);
				}else if(key.equalsIgnoreCase("disdelay")) {
					disdelay = Integer.parseInt(value);
				}else if(key.equalsIgnoreCase("killaura")) {
					isKillauraOptionEnabled = Boolean.parseBoolean(value);
				}else if(key.equalsIgnoreCase("a32kcore")) {
					a32kcore = Integer.parseInt(value);
				}else if(key.equalsIgnoreCase("font")) {
					font = value;
				}else if(key.equalsIgnoreCase("color")){
					color = Integer.parseInt(value);
				}else if(key.equalsIgnoreCase("reach")){
					reach = Float.parseFloat(value);
				}else if(key.equalsIgnoreCase("killauramode")){
					auramode = Boolean.parseBoolean(value);
				}else if(key.equalsIgnoreCase("cpt")){
					cpt = Integer.parseInt(value);
				}else if(key.equalsIgnoreCase("32kmode")) {
					dispensermode = Boolean.parseBoolean(value);
				}else if(key.equalsIgnoreCase("madmode")) {
					madmode = Boolean.parseBoolean(value);
				}else if(key.equalsIgnoreCase("togglechat")) {
					chatdisable = Boolean.parseBoolean(value);
				}else if(key.equalsIgnoreCase("lookingplace")) {
					lookingplace = Boolean.parseBoolean(value);
				}else if(key.equalsIgnoreCase("multi")) {
					multi= Boolean.parseBoolean(value);
				}
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			saveInformation();
		}
	}
	
	public static List<String> getFriends()
	{
		try {
			List<String> friends = Files.readAllLines(Paths.get(HL32K.mc.mcDataDir.getPath(), "auto32k friends.txt")).stream().map(String::toLowerCase).collect(Collectors.toList());
			readfriends = true;
			return friends;
		} catch (IOException var2) {
			System.out.println("Error Reading Friends");
			readfriends = false;
			return new ArrayList<String>();
		}
	}
	

}
