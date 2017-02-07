import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class AutoFisher {
	Robot bot;
	public boolean fish;
	public boolean fishing;
	
	public AutoFisher() {
		bot = ArkBot.bot;
		fish = false;
		fishing = false;
	}
	
	public void Fishin() {
		if (fish && fishing) {
			Fish();
		}
	}
	
	public void Fish() {
        while (fish && fishing) {
        	// Cast
        	leftClick();
        }
        fishing = false;
	}
	
	private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(10);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(10);
	  }
}
