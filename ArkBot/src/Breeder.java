import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Breeder {
	Robot bot;
	private Point p;
	MouseDrag drag;
	RobotType robtype;
	boolean breeding;
	int time;
	int foodWait;
	public Breeder() throws AWTException {
		bot = ArkBot.bot;
		p = ArkBot.p;
		drag = new MouseDrag(ArkBot.p);
		robtype = new RobotType(bot);
		breeding = false;
		time = 5;
		foodWait = 15;
	}
	public void Breedin() {
		if (breeding) {
			Breed();
		}
	}
	private void Breed() {
		ArkBotGUI.GUIText("Breeding...");
		long foodStart = System.currentTimeMillis();
		long narcStart = System.currentTimeMillis();
		
		// Setup Locations (Fridge, Carnos, Herbis)
		while (breeding) {
			// Look Left Till Inventory
			
			// Switch Fridge, Carno, Herbi Inv Search & Feed/refill
			
			// Autofeed
		}
	}
	private void InvSearch(String type) {
		ArkBotGUI.GUIText("[ACTION]: Searching inventory");
		p = MouseInfo.getPointerInfo().getLocation();
		drag.move(ArkBot.global.EXT_INV_SEARCHBAR);
		leftClick();
		bot.delay(Global.PAUSE);
		leftClick();
		robtype.type(type);
	}
	
	  private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	  }
	  
	  private void leftHold() {
		  bot.mousePress(InputEvent.BUTTON1_MASK);
		  bot.delay(200);
	  }
	  
	  private void leftRelease() {
		  bot.mouseRelease(InputEvent.BUTTON1_MASK);
		  bot.delay(200);
	  }
}
