import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class AutoHealer {
	Robot bot;
	public boolean heal;
	public boolean healing;
	public boolean abort;
	public int healcount;
	
	public AutoHealer() {
		bot = ArkBot.bot;
		heal = false;
		healing = false;
		abort = false;
		healcount = 0;
	}
	
	public void Healin() {
		if (healing) {
			Heal();
		}
	}
	
	public void Heal() {
		if (healcount != 0) {
			// Open Inventory
			bot.keyPress(KeyEvent.VK_F);
			bot.delay(500);
			bot.keyRelease(KeyEvent.VK_F);
			bot.delay(Global.PAUSE);
			
			// Search for Raw Meat
			ArkBot.state.act.ExtInvSearch("Raw Meat");
			
			// Heal!
			while (healing && !abort && healcount > 0) {
				ArkBot.drag.set(ArkBot.global.EXT_INV_FIRSTSLOT);
				leftClick();
				ArkBot.drag.set(ArkBot.global.EXT_INV_REMOTEUSE);
				//Use Stack (20)
				for (int c = 0; c < 20; c++) {
					leftClick();
					if (abort) {
						break;
					}
				}
				healcount--;
			}
			// Escape Inventory
			bot.keyPress(KeyEvent.VK_F);
			bot.delay(500);
			bot.keyRelease(KeyEvent.VK_F);
			healcount = 0;
			healing = false;
			abort = false;
		}
	}
	
	private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(10);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(10);
	  }
}
