import java.awt.Robot;
import java.awt.event.InputEvent;

public class AutoGatherer {
	Robot bot;
	public String [] materials = {"Metal", "Stone", "Flint", "Berries", "Seeds"};
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
				leftClick();
				//bot.delay((int)(pause * 1000));
			}
		}
	}
	
	public void StartStop() {
		if (clicking) {
			ArkBotGUI.GUIText("Paused AutoClicking");
			clicking = false;
		} else {
			ArkBotGUI.GUIText("Resumed AutoClicking");
			clicking = true;
		}
	}
	
	public void Drop() {
		ArkBotGUI.GUIText("Dropping unwanted items.");
	}
	
	private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    //bot.delay(10);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    //bot.delay(10);
	  }
}
