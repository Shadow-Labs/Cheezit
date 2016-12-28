import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class ArkBotSettings {
	public static File settings;
	public static File shortcuts;
	private static Dictionary<String, String> setdic;
	private static Dictionary<String, Integer> shortdic;
	public ArkBotSettings() {
		settings = new File("ArkBotFiles/Settings/ArkBotSettings.txt");
		shortcuts = new File("ArkBotFiles/Settings/ArkBotShortcuts.txt");
		InitializeDics();
	}
	
	public static void ResetSettings() {
		setdic.put("Username", "default");
		setdic.put("SetupPrompt", "true");
		setdic.put("ResX", "1440");
		setdic.put("ResY", "900");
		setdic.put("FullScreen", "false");
		setdic.put("AndrewIsAwesome", "true");
		WriteSettings(true);
	}
	
	public static void ResetShortcuts() {
		shortdic.put("AGatherStartStop", 74); 	// Keypad Minus
		shortdic.put("AGatherDrop", 55); 		// Keypad Multiply
		shortdic.put("AHealIncOne", 74); 		// Keypad Minus
		shortdic.put("AHealIncTen", 55); 		// Keypad Multiply
		shortdic.put("AHealAbort", 3637);		// Keypad Divide
		shortdic.put("BreederStartStop", 74);	// Keypad Minus
		shortdic.put("MSplit", 78);				// Keypad Plus
		WriteShortcuts(true);
	}
	
	private static void InitializeDics() {
		// Settings
		setdic = new Hashtable<String, String>();
		if (settings.exists() && settings.length() > 0) {
			fileToDic(1);
		} else {
			ResetSettings();
		}
		
		// Shortcuts
		shortdic = new Hashtable<String, Integer>();
		if (shortcuts.exists() && shortcuts.length() > 0) {
			fileToDic(2);
		} else {
			ResetShortcuts();
		}
	}
	
	private static void WriteSettings(boolean overwrite) {
		
		// Determine if file is empty or not
		if (settings.length() == 0 || overwrite) {
			try {
				PrintWriter writer = new PrintWriter("ArkBotFiles/Settings/ArkBotSettings.txt");
				Enumeration<String> key = setdic.keys();
				Enumeration<String> element = setdic.elements();
				while(key.hasMoreElements()) {
					String name = key.nextElement().toString();
					writer.println(name + " = " + setdic.get(name));
					element.nextElement();
				}
				writer.close();
			} catch (FileNotFoundException e) {
				ArkBot.ERROR = "Setup Error";
				StringWriter error = new StringWriter();
				e.printStackTrace(new PrintWriter(error));
				ArkBotGUI.GUIText("ERROR: Could not write to ArkBotSettings.txt");
				ArkBotGUI.GUIText(error.toString());
				e.printStackTrace();
			}
		} else {
			fileToDic(1);
		}
	}
	
	private static void WriteShortcuts(boolean overwrite) {
		
		// Determine if file is empty or not
		if (shortcuts.length() == 0 || overwrite) {
			try {
				PrintWriter writer = new PrintWriter("ArkBotFiles/Settings/ArkBotShortcuts.txt");
				Enumeration<String> key = shortdic.keys();
				Enumeration<Integer> element = shortdic.elements();
				while(key.hasMoreElements()) {
					String name = key.nextElement().toString();
					writer.println(name + " = " + shortdic.get(name));
					element.nextElement();
				}
				writer.close();
			} catch (FileNotFoundException e) {
				ArkBot.ERROR = "Setup Error";
				StringWriter error = new StringWriter();
				e.printStackTrace(new PrintWriter(error));
				ArkBotGUI.GUIText("ERROR: Could not write to ArkBotShortcuts.txt");
				ArkBotGUI.GUIText(error.toString());
				e.printStackTrace();
			}
		} else {
			fileToDic(1);
		}
	}
	
	// Returns Setting value
	public static String GetSetting(String name) {
		return setdic.get(name);
	}
	
	// Returns Setting value
	public static Integer GetShortcut(String name) {
		return shortdic.get(name);
	}
	
	public static void UpdateSetting(String name, String value) {
		setdic.remove(name);
		setdic.put(name, value);
		ArkBotGUI.GUIText("Updated ArkBotSettings.txt " + name + " = " + value);
		WriteSettings(true);
	}
	
	public static void UpdateShortcut(String name, Integer value) {
		shortdic.remove(name);
		shortdic.put(name, value);
		ArkBotGUI.GUIText("Updated ArkBotShortcuts.txt " + name + " = " + value);
		WriteShortcuts(true);
	}
	
	private static void fileToDic(int dic) {
		BufferedReader br;
		// Settings
		if (dic == 1) {
			try {
				br = new BufferedReader(new FileReader("ArkBotFiles/Settings/ArkBotSettings.txt"));
			    String line = br.readLine();
			    
			    while (line != null) {
				    String name = "";
				    String value = "";
			        char text[] = line.toCharArray();
			        // read name
			        int i = 0;
			        while (text[i] != ' ') {
			        	name = name + text[i];
			        	i++;
			        }
			        // move to value
			        while (text[i] == ' ' || text[i] == '=') {
			        	i++;
			        }
			        // read value
			        while(i!= text.length) {
			        	value = value + text[i];
			        	i++;
			        }
			        setdic.put(name, value);
			        line = br.readLine();
			    }
			    br.close();
			} catch (IOException e) {
				ArkBot.ERROR = "Setup Error";
				StringWriter error = new StringWriter();
				e.printStackTrace(new PrintWriter(error));
				ArkBotGUI.GUIText("ERROR: Could not read from ArkBotSettings.txt");
				ArkBotGUI.GUIText(error.toString());
				e.printStackTrace();
			}
		// Shortcuts
		} else if (dic == 2) {
			try {
				br = new BufferedReader(new FileReader("ArkBotFiles/Settings/ArkBotShortcuts.txt"));
			    String line = br.readLine();
			    
			    while (line != null) {
				    String name = "";
				    String value = "";
			        char text[] = line.toCharArray();
			        // read name
			        int i = 0;
			        while (text[i] != ' ') {
			        	name = name + text[i];
			        	i++;
			        }
			        // move to value
			        while (text[i] == ' ' || text[i] == '=') {
			        	i++;
			        }
			        // read value
			        while(i!= text.length) {
			        	value = value + text[i];
			        	i++;
			        }
			        shortdic.put(name, Integer.parseInt(value));
			        line = br.readLine();
			    }
			    br.close();
			} catch (IOException e) {
				ArkBot.ERROR = "Setup Error";
				StringWriter error = new StringWriter();
				e.printStackTrace(new PrintWriter(error));
				ArkBotGUI.GUIText("ERROR: Could not read from ArkBotShortcuts.txt");
				ArkBotGUI.GUIText(error.toString());
				e.printStackTrace();
			}
		}
		
	}
	
	public static void printDic(int dic) {
		// Settings
		if (dic == 1) {
			Enumeration<String> key = setdic.keys();
			Enumeration<String> element = setdic.elements();
			while(key.hasMoreElements()) {
				String name = key.nextElement().toString();
				System.out.println(name + " = " + setdic.get(name));
				element.nextElement();
			}
		// Shortcuts
		} else if (dic == 2) {
			Enumeration<String> key = shortdic.keys();
			Enumeration<Integer> element = shortdic.elements();
			while(key.hasMoreElements()) {
				String name = key.nextElement().toString();
				System.out.println(name + " = " + setdic.get(name));
				element.nextElement();
			}
		}
		
	}
	
}
