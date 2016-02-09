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

public class ArkBotSettings {
	public static File settings;
	private static Dictionary<String, String> setdic;
	public ArkBotSettings() {
		settings = new File("ArkBotFiles/Settings/ArkBotSettings.txt");
		InitializeDic();
	}
	
	public static void ResetSettings() {
		setdic.put("SetupPrompt", "true");
		setdic.put("AndrewIsAwesome", "true");
		WriteSettings(true);
	}
	
	private static void InitializeDic() {
		setdic = new Hashtable<String, String>();
		if (settings.exists() && settings.length() > 0) {
			fileToDic();
		} else {
			ResetSettings();
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
					if (setdic.get(name).equals("true")) {
						writer.println(name + " = true");
						element.nextElement();
					} else {
						writer.println(name + " = false");
						element.nextElement();
					}
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
			fileToDic();
		}
	}
	
	public static boolean CheckSetting(String name) {
		return (setdic.get(name).equals("true"));
	}
	
	public static void UpdateSetting(String name, boolean value) {
		setdic.remove(name);
		if (value) {
			ArkBotGUI.GUIText("Updated ArkBotSettings.txt " + name + " true");
			setdic.put(name, "true");
		} else {
			ArkBotGUI.GUIText("Updated ArkBotSettings.txt " + name + " false");	
			setdic.put(name, "false");
		}
		WriteSettings(true);
	}
	
	private static void fileToDic() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("ArkBotFiles/Settings/ArkBotSettings.txt"));
		    String line = br.readLine();
		    String name = "";
		    String value = "";
		    
		    while (line != null) {
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
		        // add to dic
				if (value.equals("true")) {
					setdic.put(name, "true");
				} else {
					setdic.put(name, "false");
				}
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
	}
	
}
