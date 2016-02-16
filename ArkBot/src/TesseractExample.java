import java.io.File;
import net.sourceforge.tess4j.*;

public class TesseractExample {
	public TesseractExample() {
		File image = new File("ArkBotFiles\\OCRImages\\ChatImageGrab\\grabInvert.png");
		ITesseract instance = new Tesseract();
		
		try {
			String result = instance.doOCR(image);
			System.out.println(result);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
	}
}
