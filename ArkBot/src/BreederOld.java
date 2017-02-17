import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class BreederOld {
	Robot bot;
	CharacterActions act;
	RobotStuff robtype;
	ArrayList breedSetup;
	int herbs;
	int carns;
	boolean breeding;
	int foodWait;
	public BreederOld() throws AWTException {
		bot = ArkBot.bot;
		act = new CharacterActions();
		robtype = new RobotStuff(bot);
		breedSetup = new ArrayList();
		herbs = 0;
		carns = 0;
		breeding = false;
		foodWait = 15;
	}
	public void Breedin() {
		if (breeding) {
			Breed();
		}
	}
	private void Breed() {
		int i = 0;
		long foodStart = System.currentTimeMillis();
	
		ArkBot.drag.move(new Point(1330,60));
		leftClick();
		// Setup Locations (Fridge, Carnos, Herbis)
		
		// Initial Fridge routine
		Refill();
		i++;
		// Exit Inventory and look left
		bot.keyPress(KeyEvent.VK_F);
		bot.delay(Global.PAUSE);
		bot.keyRelease(KeyEvent.VK_F);
		bot.delay(250);

		act.LookLeft(120/breedSetup.size());
		
		
		
		while (breeding) {
			// Look Left Till Inventory - NEED TO TEST
			while (!act.CyanBreeder(6) && breeding) {
				act.LookLeft(2);
			}
			
			if (!breeding) {
				break;
			}
			
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
//				drag.move(ArkBot.global.CHAR_INV_FIRSTSLOT);
//				leftClick();
//				
//				drag.move(ArkBot.global.CHAR_INV_USEITEM);
//				leftClick();
				
				bot.keyPress(KeyEvent.VK_5);
				bot.delay(Global.PAUSE);
				bot.keyRelease(KeyEvent.VK_5);
				
				foodStart = System.currentTimeMillis();
			}
			
			// Exit Inventory and look left
			bot.keyPress(KeyEvent.VK_F);
			bot.delay(Global.PAUSE);
			bot.keyRelease(KeyEvent.VK_F);
			bot.delay(250);

			act.LookLeft(120/breedSetup.size());
		}
	}
	private void Refill() {
		
		//Search Berries
		act.ExtInvSearch("berry");
		
		// Transfer Stack
		ArkBot.drag.move(Global.EXT_INV_FIRSTSLOT);
		leftClick();
		act.Transfer(herbs);
		
		// Carnivores
		if (breedSetup.contains(1)) {
			act.ExtInvSearch("Raw Meat");
			ArkBot.drag.move(Global.EXT_INV_FIRSTSLOT);
			leftClick();
			act.Transfer(carns * 3);
		}
		
	}
	
	// REMOVE SPOILED MEAT
	private void Feed(int type) {
		
		if (type == 1) { // Carnivore
			// Search Raw Meat
			act.CharInvSearch("Raw Meat");
			
			// Transfer Stacks
			ArkBot.drag.move(Global.CHAR_INV_FIRSTSLOT);
			leftClick();
			act.Transfer(3);
		} else {  // Herbivore
			// Search Raw Meat
			act.CharInvSearch("berry");
			
			// Transfer Stacks
			ArkBot.drag.move(Global.CHAR_INV_FIRSTSLOT);
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
