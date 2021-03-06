import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ArkBotLog {
	File log;
	PrintWriter writer;
	public ArkBotLog() {
		String time = new SimpleDateFormat("ddmmyyHHmmss").format(Calendar.getInstance().getTime());
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		
		//Create /Logs directory if not exists
		File logsDir = new File("ArkBotFiles/Logs");
		if (!logsDir.exists()) {
			System.out.println("Creating Logs Directory.");
			logsDir.mkdir();
		}
		
		
		try {
			File logfile = new File("ArkBotFiles/Logs/", "ArkBotLog_" + ArkBot.version + "_" + time + ".txt");
			System.out.println("ArkBotFiles/Logs/ArkBotLog_" + ArkBot.version + "_" + time + ".txt");
			if (logfile.createNewFile()){
				//Uhhh
				}else{
				//Duhhh
			  }
		} catch (IOException e1) {
			StringWriter error = new StringWriter();
			e1.printStackTrace(new PrintWriter(error));
			System.out.println("ERROR: Unable to create file.");
			System.out.println(error.toString());
			e1.printStackTrace();
		}
		
		try {
			writer = new PrintWriter ("ArkBotFiles\\Logs\\ArkBotLog_" + ArkBot.version + "_" + time + ".txt", "UTF-8");
		} catch (FileNotFoundException e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			WriteLog("ERROR: Log File was not created.");
			WriteLog(error.toString());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			StringWriter error = new StringWriter();
			e.printStackTrace(new PrintWriter(error));
			WriteLog("ERROR: Unsupported Log Format.");
			WriteLog(error.toString());
			e.printStackTrace();
		}
		
		// Log Header
		writer.println("ArkBotLog");
		writer.println("ArkBot Version: " + ArkBot.version);
		writer.println("Begin Timestamp: " + timeStamp);
		writer.println("==================================================================================");
	}
	
	public void WriteLog(String text) {
		if (text == null) {
		} else {
			writer.println(text);
			writer.flush();
		}
	}
	
	public void CloseLog() {
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		long elapsed = (System.currentTimeMillis() - ArkBotGUI.start);
        String display = String.format("%02d:%02d:%02d", (elapsed / (1000 * 60 * 60)) % 24, (elapsed / (1000 * 60)) % 60, (elapsed / 1000) % 60);
        
		// Log Footer
		writer.println("==================================================================================");
		writer.println("End Timestamp: " + timeStamp);
		writer.println("Runtime: " + display);
		
		writer.close();
	}
	
	
	
}
