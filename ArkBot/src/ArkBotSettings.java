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
		setdic.put("Username", "default");
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
			fileToDic();
		}
	}
	
	// Returns Setting value
	public static String GetSetting(String name) {
		return setdic.get(name);
	}
	
	public static void UpdateSetting(String name, String value) {
		setdic.remove(name);
		setdic.put(name, value);
		ArkBotGUI.GUIText("Updated ArkBotSettings.txt " + name + " = " + value);
		WriteSettings(true);
	}
	
	private static void fileToDic() {
		BufferedReader br;
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
	}
	
	public static void printDic() {
		Enumeration<String> key = setdic.keys();
		Enumeration<String> element = setdic.elements();
		while(key.hasMoreElements()) {
			String name = key.nextElement().toString();
			System.out.println(name + " = " + setdic.get(name));
			element.nextElement();
		}
	}
	
}
