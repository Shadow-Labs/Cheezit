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
	String version;
	PrintWriter writer;
	public ArkBotLog(String version) {
		this.version = version;
		String time = new SimpleDateFormat("ddmmyyHHmmss").format(Calendar.getInstance().getTime());
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		
		try {
			File logfile = new File("ArkBotFiles/Logs/ArkBotLog_" + version + "_" + time + ".txt");
			if (logfile.createNewFile()){
			    System.out.println("File is created!");
			  }else{
			    System.out.println("File already exists.");
			  }
		} catch (IOException e1) {
			StringWriter error = new StringWriter();
			e1.printStackTrace(new PrintWriter(error));
			WriteLog("ERROR: Unable to create file.");
			WriteLog(error.toString());
			e1.printStackTrace();
		}
		
		try {
			writer = new PrintWriter ("ArkBotFiles\\Logs\\ArkBotLog_" + version + "_" + time + ".txt", "UTF-8");
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
		writer.println("ArkBot Version: " + version);
		writer.println("Timestamp: " + timeStamp);
		writer.println("=======================");
	}
	
	public void WriteLog(String text) {
		if (text == null) {
		} else {
			writer.println(text);
		}
	}
	
	public void CloseLog() {
		writer.close();
	}
	
	
	
}
