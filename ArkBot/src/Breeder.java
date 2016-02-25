import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Breeder {
	Robot bot;
	CharacterActions act;
	private Point p;
	MouseDrag drag;
	RobotType robtype;
	ArrayList breedSetup;
	boolean breeding;
	int time;
	int foodWait;
	public Breeder() throws AWTException {
		bot = ArkBot.bot;
		act = new CharacterActions();
		p = ArkBot.p;
		drag = new MouseDrag(ArkBot.p);
		robtype = new RobotType(bot);
		breedSetup = new ArrayList();
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
			while (!act.PixelRange(MouseInfo.getPointerInfo().getLocation(), 10)) {
				act.LookLeft(5);
			}
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
