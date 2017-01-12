import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Autopilot {
	Robot bot;
	public boolean pilot;
	public boolean piloting;
	
	public Autopilot() {
		bot = ArkBot.bot;
		pilot = false;
		piloting = false;
	}
	
	public void Pilotin() {
		if (pilot && piloting) {
			Pilot();
		}
	}
	
	public void Pilot() {
        bot.keyPress(KeyEvent.VK_SHIFT);
		while (pilot && piloting) {
			bot.keyPress(KeyEvent.VK_W);
			bot.delay(20); //More realistic keyboard refresh rate
		}
		bot.keyRelease(KeyEvent.VK_W);
		bot.keyRelease(KeyEvent.VK_SHIFT);
		piloting = false;
	}
}
