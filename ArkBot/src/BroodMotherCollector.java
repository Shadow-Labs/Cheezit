import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class BroodMotherCollector {
	Robot bot;
	RobotStuff robtype;
	boolean brooding;
	int time;
	int foodWait;
	public BroodMotherCollector() throws AWTException {
		bot = ArkBot.bot;
		robtype = new RobotStuff(bot);
		brooding = false;
		time = 5;
		foodWait = 15;
	}
	public void Broodin() {
		if (brooding) {
			Brood();
		}
	}
	private void Brood() {
		ArkBotGUI.GUIText("Brooding...");
		long foodStart = System.currentTimeMillis();
		long narcStart = System.currentTimeMillis();
		while (brooding) {
			// Narc
			if (System.currentTimeMillis() - narcStart > (time * 1000)) {
				//Click First Stack
				ArkBot.drag.move(ArkBot.global.EXT_INV_FIRSTSLOT);
				leftClick();
				
				// Click RemoteUse
				ArkBot.drag.move(ArkBot.global.EXT_INV_REMOTEUSE);
				leftClick();
				
				narcStart = System.currentTimeMillis();
			}
			
			// Food
			if (System.currentTimeMillis() - foodStart > (foodWait * 1000) && foodWait != 0) {
				ArkBot.drag.move(ArkBot.global.CHAR_INV_FIRSTSLOT);
				leftClick();
				
				ArkBot.drag.move(ArkBot.global.CHAR_INV_USEITEM);
				leftClick();
				
				foodStart = System.currentTimeMillis();
			}
		}
	}
	private void InvSearch(String type) {
		ArkBotGUI.GUIText("[ACTION]: Searching inventory");
		ArkBot.drag.move(ArkBot.global.EXT_INV_SEARCHBAR);
		leftClick();
		bot.delay(Global.PAUSE);
		leftClick();
		robtype.type(type);
	}
	
	  private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	  }
}
