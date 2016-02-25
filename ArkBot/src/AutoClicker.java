import java.awt.Robot;
import java.awt.event.InputEvent;

public class AutoClicker {
	Robot bot;
	public boolean clicking;
	public float pause;
	public AutoClicker() {
		this.bot = ArkBot.bot;
		clicking = false;
	}
	
	public void Clickin() {
		if (clicking) {
			Click();
		}
	}
	
	public void Click() {
		while (clicking) {
			leftClick();
			bot.delay((int)(pause * 1000));
		}
	}
	private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	  }
}
