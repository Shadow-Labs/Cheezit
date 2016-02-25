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
		int i = 0;
		long foodStart = System.currentTimeMillis();
		long narcStart = System.currentTimeMillis();
		
		// Setup Locations (Fridge, Carnos, Herbis)
		
		// Todo: Setup fix for reopening inv
		
		
		
		while (breeding) {
			// Look Left Till Inventory
			while (!act.PixelRange(MouseInfo.getPointerInfo().getLocation(), 10)) {
				act.LookLeft(5);
			}
			// Open Inv
			bot.keyPress(KeyEvent.VK_F);
			bot.delay(ArkBot.global.PAUSE);
			bot.keyRelease(KeyEvent.VK_F);
			
			// Switch Fridge - 0, Carno - 1, Herbi - 2 Inv Search & Feed/refill
			switch ((int)breedSetup.get(i)) {
			case 1:
			}
			
			
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
