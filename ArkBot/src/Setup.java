import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Setup {
	public static Process ArkProcess;
	private Robot bot;
	private ArkBotLog log;
	private Point p;
	private int PAUSE;
	public Setup()  throws AWTException {
		this.bot = ArkBot.bot;
		this.log = ArkBot.log;
		this.p = ArkBot.p;
		this.PAUSE = ArkBot.global.PAUSE;
	}
	
	//-----------------ADMINISTRATIVE FUNCTIONALITY-----------------
	public void Begin() throws AWTException //Resolution must be set to 1440x900
	{
//		File userSettings = new File("ShooterGame/Saved/Config/WindowsNoEditor/GameUserSettings.ini");
//		Path FROM = Paths.get(userSettings.getAbsolutePath());
//		File oldUserSettings = new File("ShooterGame/Saved/Config/WindowsNoEditor/OldGameUserSettings.ini");
//		Path TO = Paths.get(oldUserSettings.getAbsolutePath());
//		File arkexe = new File ("ShooterGame/Binaries/Win64/ShooterGame.exe");
//		
//		if (userSettings.exists()) {
//			ArkBotGUI.GUIText("Succesfully found GameUserSettings.ini", log);
//			try {
//				Files.copy(FROM,TO);
//				ArkBotGUI.GUIText("Succesfully copied GameUserSettings.ini to OldGameUsersSettings.ini.", log);
//			} catch (IOException e) {
//				StringWriter error = new StringWriter();
//				e.printStackTrace(new PrintWriter(error));
//				ArkBotGUI.GUIText("ERROR: Unable to Copy GameUserSettings.ini.", log);
//				ArkBotGUI.GUIText(error.toString(), log);
//				e.printStackTrace();
//			}
//		} else {
//			ArkBot.ERROR = "Setup Error";
//			ArkBotGUI.GUIText("ERROR: Could not find GameUserSettings.ini", log);
//			ArkBotGUI.GUIText("ERROR: Ensure ArkBot.jar is located in steamapps/common/ARK", log);
//		}
//		
//		if (arkexe.exists()) {
//			ArkBotGUI.GUIText("Succesfully found ShooterGame.exe", log);
//			ArkBotGUI.GUIText("Launching Ark Survival Evolved.", log);
//			try {
//				Process ArkProcess = new ProcessBuilder("ShooterGame/Binaries/Win64/ShooterGame.exe").start();
//			} catch (IOException e) {
//				ArkBot.ERROR = "Setup Error";
//				StringWriter error = new StringWriter();
//				e.printStackTrace(new PrintWriter(error));
//				ArkBotGUI.GUIText("ERROR: Unable to launch Ark Survival Evolved.", log);
//				ArkBotGUI.GUIText(error.toString(), log);
//				e.printStackTrace();
//			}
//			
//		} else {
//			ArkBot.ERROR = "Setup Error";
//			ArkBotGUI.GUIText("ERROR: Could not find ShooterGame.exe", log);
//			ArkBotGUI.GUIText("ERROR: Ensure ShooterGame.exe is located in steamapps/common/ARK/ShooterGame/Binaries/Win64", log);
//		}
		
		MouseDrag drag = new MouseDrag(p);
		
		// Focus ARK
		bot.delay(PAUSE);
		bot.mouseMove(ArkBot.global.FOCUS.x, ArkBot.global.FOCUS.y);
		bot.delay(PAUSE);
		leftClick();
		
		// Click Options
		bot.delay(PAUSE);
		drag.move(ArkBot.global.OPTIONS);
		bot.delay(PAUSE);
		leftClick();
		
		// Click Camera FOV
		bot.delay(PAUSE);
		drag.move(ArkBot.global.CAMERA_FOV);
		bot.delay(PAUSE);
		leftClick();
		
		// Set Horizontal Sensitivity
		bot.delay(PAUSE);
		drag.move(ArkBot.global.H_SENSITIVITY);
		bot.delay(PAUSE);
		leftClick();
		
		// Set Vertical Sensitivity
		bot.delay(PAUSE);
		drag.move(ArkBot.global.V_SENSITIVITY);
		bot.delay(PAUSE);
		leftClick();
		
		// Click Apply
		bot.delay(PAUSE);
		drag.move(ArkBot.global.APPLY);
		bot.delay(500);
		leftClick();
		
		// Click Save
		bot.delay(PAUSE);
		drag.move(ArkBot.global.SAVE);
		bot.delay(PAUSE);
		leftClick();
		
		// Press ESC
		bot.delay(500);
		type(KeyEvent.VK_ESCAPE);
		
		// Click Save
		bot.delay(PAUSE);
		drag.move(ArkBot.global.MENU_INVENTORY);
		bot.delay(PAUSE);
		leftClick();
		
		//Set Inventory Mode
		bot.delay(500);
		System.out.println("Blue: " + bot.getPixelColor(83, 266).getBlue());
		System.out.println("Green: " + bot.getPixelColor(83, 266).getGreen());
		System.out.println("Red: " + bot.getPixelColor(83, 266).getRed());
		if (bot.getPixelColor(83, 266).getBlue() > 130
				&& bot.getPixelColor(83, 266).getGreen() > 130
				&& bot.getPixelColor(83, 266).getRed() > 130) {
			bot.keyPress(KeyEvent.VK_Q);
			bot.delay(Global.PAUSE);
			bot.keyRelease(KeyEvent.VK_Q);
		}
		
		// Press ESC
		bot.delay(500);
		type(KeyEvent.VK_ESCAPE);
		
		// Press ESC
		bot.delay(500);
		type(KeyEvent.VK_ESCAPE);
		
	}
	public void FocusARK() throws AWTException
	{
		MouseDrag drag = new MouseDrag(p);
		
		bot.delay(PAUSE);
		bot.mouseMove(720, 160);
		
		bot.delay(PAUSE);
	}
	
	public void FocusView()
	{
		bot.delay(PAUSE);
		bot.mouseMove(1, 30);
		
		bot.delay(PAUSE);
		leftClick();
		
		bot.delay(PAUSE);
		bot.mouseMove(720, 320);
		
		bot.delay(PAUSE);
		leftClick();
	}
	
	  private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	  }
	  
	  private void type(int i)
	  {
	    bot.delay(40);
	    bot.keyPress(i);
	    bot.keyRelease(i);
	  }
}
