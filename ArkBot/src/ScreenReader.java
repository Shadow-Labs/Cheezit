import java.awt.Rectangle;
import java.io.File;
import net.sourceforge.tess4j.*;

public class ScreenReader {
	ImageGrab g;
	public ScreenReader() {
		g = new ImageGrab();
	}
	
	public String Grab(int loc) {
		String result = "";
		
		switch (loc) {
		// ChatBox
		case 0: g.Grab(Global.CHATBOX);
				//g.WhiteConvert();
				result = Decode();
				break;
		// Stats
		case 1: g.Grab(Global.STATS);
				g.WhiteConvert();
				result = Decode();
				break;
		
		// Tribe Log
		case 2: g.Grab(Global.TRIBELOG);
		
		// GPS
		case 3: g.Grab(Global.GPS);
				g.GPSConvert();
				//result = Decode();
				break;
		
		}
		return result;
	}
	
	private String Decode() {
		String result = "";
		//File image = new File("ArkBotFiles\\OCRImages\\ImageGrab\\grabConvert.png");
		File image = new File("ArkBotFiles\\OCRImages\\ImageGrab\\GPSConvertExample.png");
		ITesseract instance = new Tesseract();
		instance.setTessVariable("tessedit_char_whitelist", "0123456789");
		
		try {
			result = instance.doOCR(image);
			ArkBotGUI.GUIText("GPS: " + result);
			System.out.println(result);
			//image.delete();
			instance.dispose();
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
		
		
		return result;
	}

}
