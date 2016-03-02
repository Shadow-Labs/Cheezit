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
	int foodWait;
	public Breeder() throws AWTException {
		bot = ArkBot.bot;
		act = new CharacterActions();
		p = ArkBot.p;
		drag = new MouseDrag(ArkBot.p);
		robtype = new RobotType(bot);
		breedSetup = new ArrayList();
		breeding = false;
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
	
		drag.move(new Point(1330,60));
		leftClick();
		// Setup Locations (Fridge, Carnos, Herbis)
		
		// Todo: Setup fix for reopening inv
		
		
		
		while (breeding) {
			// Look Left Till Inventory - NEED TO TEST
			while (!act.CyanCenter(6)) {
				act.LookLeft(2);
			}
			ArkBotGUI.GUIText("Cyan Center!");
			
			// Open Inv
			bot.keyPress(KeyEvent.VK_F);
			bot.delay(ArkBot.global.PAUSE);
			bot.keyRelease(KeyEvent.VK_F);
			bot.delay(250);
			
			// Switch Fridge - 0, Carno - 1, Herbi - 2 Inv Search & Feed/refill
			switch ((int)breedSetup.get(i)) {
			case 0: // Fridge
				Refill();
				break;
			case 1:	// Carnivore
				Feed(1);
				break;
			case 2: // Herbivore
				Feed(2);
				break;
			}
			
			// Set i
			i++;
			if (i == breedSetup.size()) {
				i = 0;
			}
			
			// AutoFeed
			if (foodWait != 0 && System.currentTimeMillis() - foodStart > (foodWait * 1000)) {
				drag.move(ArkBot.global.CHAR_INV_FIRSTSLOT);
				leftClick();
				
				drag.move(ArkBot.global.CHAR_INV_USEITEM);
				leftClick();
				
				foodStart = System.currentTimeMillis();
			}
			
			// Exit Inventory and look left
			bot.keyPress(KeyEvent.VK_F);
			bot.delay(Global.PAUSE);
			bot.keyRelease(KeyEvent.VK_F);
			bot.delay(250);
			
			ArkBotGUI.GUIText("Got Here!");

			act.LookLeft(20);
		}
	}
	private void Refill() {
		
		//Search Berries
		act.ExtInvSearch("berry");
		
		// Transfer Stack
		drag.move(Global.EXT_INV_FIRSTSLOT);
		leftClick();
		act.Transfer(1);
		
		// Carnivores
		if (breedSetup.contains(1)) {
			act.ExtInvSearch("Raw Meat");
			drag.move(Global.EXT_INV_FIRSTSLOT);
			leftClick();
			act.Transfer(5);
		}
		
	}
	
	// REMOVE SPOILED MEAT
	private void Feed(int type) {
		
		if (type == 1) { // Carnivore
			// Search Raw Meat
			act.CharInvSearch("Raw Meat");
			
			// Transfer Stacks
			drag.move(Global.CHAR_INV_FIRSTSLOT);
			leftClick();
			act.Transfer(3);
		} else {  // Herbivore
			// Search Raw Meat
			act.CharInvSearch("berry");
			
			// Transfer Stacks
			drag.move(Global.CHAR_INV_FIRSTSLOT);
			leftClick();
			act.Transfer(1);
		}
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
