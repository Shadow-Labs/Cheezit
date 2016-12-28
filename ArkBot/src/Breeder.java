import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Breeder {
	Robot bot;
	public boolean breed;
	public boolean breeding;
	public boolean abort;
	
	public Breeder() {
		bot = ArkBot.bot;
		breed = false;
		breeding = false;
		abort = false;
	}
	
	public void Breedin() {
		if (breeding) {
			Breed();
		}
	}
	
	public void Breed() {
		// Breed!
		while (breeding && !abort) {
			// Transfer Food Attempts
			bot.keyPress(KeyEvent.VK_T);
			bot.delay(500);
			bot.keyRelease(KeyEvent.VK_T);
			bot.delay(1000);
		}
		abort = false;
	}
	
	private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(10);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(10);
	  }
}
