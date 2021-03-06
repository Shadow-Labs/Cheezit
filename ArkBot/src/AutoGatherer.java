import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class AutoGatherer {
	Robot bot;
	public String [] materials = {"Metal", "Stone", "Flint", "Obsidian", "Crystal", "Oil", "Wood", "Thatch", "Fiber",
			"Hide", "Amarberry", "Azulberry", "Tintoberry", "Mejoberry", "Narcoberry", "Stimberry", "Seed", 
			"Mushroom", "Flower", "Raw Meat", "Prime Meat", "Spoiled Meat"};
	public ArrayList<String> dropMats = new ArrayList<String>();
	public boolean gathering;
	public boolean SPACE;
	public boolean clicking;
	public boolean dropping;
	public boolean drop;
	public float pause;
	
	public AutoGatherer() {
		this.bot = ArkBot.bot;
		gathering = false;
		SPACE = false;
		clicking = false;
		dropping = false;
		drop = false;
	}
	
	public void Gatherin() {
		if (gathering) {
			Gather();
		}
	}
	
	public void Gather() {
		while (gathering) {
			// Neccessary to run..smh
			System.out.print("");
			while (clicking) {
				// Neccessary to run..smh
				System.out.print("\nClick");
				press();
				bot.delay((int)(pause * 1000));
				
				// Check for drop
				if (drop && dropping) {
					Drop();
				}
			}
			
			// Check for drop
			if (drop && dropping) {
				Drop();
			}
		}
	}
	
	public void StartStop() {
		if (clicking) {
			ArkBotGUI.GUIText("[ACTION] Paused AutoClicking");
			clicking = false;
		} else {
			ArkBotGUI.GUIText("[ACTION] Resumed AutoClicking");
			clicking = true;
		}
	}
	
	public void Drop() {
		ArkBotGUI.GUIText("[ACTION] Dropping unwanted items.");
		
		// Open Ext Inv
		bot.keyPress(KeyEvent.VK_F);
		bot.delay(300);
		bot.keyRelease(KeyEvent.VK_F);
		bot.delay(Global.PAUSE);		
		
		int i = 0;
		while (i < dropMats.size() && gathering && dropping) {
			String mat = dropMats.get(i);
			if (dropMats.contains(mat)) {
				// Drop All
				ArkBot.state.act.ExtInvSearch(mat);
				ArkBot.state.act.DropAll();
			}
			i++;
		}
		
		// Exit Ext Inv
		bot.keyPress(KeyEvent.VK_ESCAPE);
		bot.delay(Global.PAUSE);
		bot.keyRelease(KeyEvent.VK_ESCAPE);
		bot.delay(Global.PAUSE);
		
		ArkBot.state.gatherer.dropping = false;
		ArkBotGUI.GUIText("[ACTION] Finished dropping.");
	}
	
	private void press()
	  {
		if (!SPACE) {
		    bot.mousePress(InputEvent.BUTTON1_MASK);
		    bot.delay(10);
		    bot.mouseRelease(InputEvent.BUTTON1_MASK);
		    bot.delay(10);
		} else {
			bot.keyPress(KeyEvent.VK_SPACE);
		    bot.delay(10);
			bot.keyRelease(KeyEvent.VK_SPACE);
		    bot.delay(10);
		}
	  }
}
