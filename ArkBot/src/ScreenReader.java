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
				g.WhiteConvert();
				result = Decode();
				break;
		// Stats
		case 1: g.Grab(Global.STATS);
				g.WhiteConvert();
				result = Decode();
				break;
		
		// Tribe Log
		case 2: g.Grab(Global.TRIBELOG);
		
		}
		return result;
	}
	
	private String Decode() {
		String result = "";
		File image = new File("ArkBotFiles\\OCRImages\\ImageGrab\\grabConvert.png");
		ITesseract instance = new Tesseract();
		
		try {
			result = instance.doOCR(image);
			ArkBotGUI.GUIText("\n\n" + result);
			System.out.println(result);
			image.delete();
			instance.dispose();
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
		
		return result;
	}

}
