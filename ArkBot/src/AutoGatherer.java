import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class AutoGatherer {
	Robot bot;
	public String [] materials = {"Metal", "Stone", "Flint", "Wood", "Thatch", "Fiber",
			"Berries", "Seed", "Mushroom", "Flower", "Oil", "Raw Meat", "Spoiled Meat", "Hide"};
	public ArrayList<String> keepMats = new ArrayList<String>();
	public boolean gathering;
	public boolean clicking;
	public float pause;
	
	public AutoGatherer() {
		this.bot = ArkBot.bot;
		gathering = false;
		clicking = false;
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
				System.out.print("");
				leftClick();
				bot.delay((int)(pause * 1000));
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
		bot.delay(Global.PAUSE);
		bot.keyRelease(KeyEvent.VK_F);
		bot.delay(Global.PAUSE);
		
		
		int i = 0;
		while (i < materials.length) {
			String mat = materials[i];
			if (keepMats.contains(mat)) {
				// Do Nothing
			} else {
				// Drop All
				ArkBot.act.ExtInvSearch(mat);
				ArkBot.act.Drop(0);
			}
			i++;
		}
		
		// Exit Ext Inv
		bot.keyPress(KeyEvent.VK_ESCAPE);
		bot.delay(Global.PAUSE);
		bot.keyRelease(KeyEvent.VK_ESCAPE);
		bot.delay(Global.PAUSE);
		
		
		ArkBotGUI.GUIText("[ACTION] Finished dropping.");
	}
	
	private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    //bot.delay(10);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    //bot.delay(10);
	  }
}
