import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Taming {
	Robot bot;
	private Point p;
	MouseDrag drag;
	RobotType robtype;
	boolean taming;
	int time;
	int foodWait;
	public Taming() throws AWTException {
		bot = ArkBot.bot;
		p = ArkBot.p;
		drag = new MouseDrag(ArkBot.p);
		robtype = new RobotType(bot);
		taming = false;
		time = 5;
		foodWait = 15;
	}
	public void Tamin() {
		if (taming) {
			Tame();
		}
	}
	private void Tame() {
		ArkBotGUI.GUIText("Taming...");
		long start = System.currentTimeMillis();
		while (taming) {
			//Click First Stack
			drag.move(ArkBot.global.EXT_INV_FIRSTSLOT);
			leftClick();
			
			// Click RemoteUse
			drag.move(ArkBot.global.EXT_INV_REMOTEUSE);
			leftClick();
			
			// Torpor Wait
			bot.delay(time * 1000);
			
			// Food
			if (System.currentTimeMillis() - start > (foodWait * 1000) && foodWait != 0) {
				drag.move(ArkBot.global.EXT_PERSON_INV_FIRSTSLOT);
				leftClick();
				
				drag.move(ArkBot.global.EXT_PERSON_INV_USEITEM);
				leftClick();
				
				start = System.currentTimeMillis();
			}
		}
	}
	private void InvSearch(String type) {
		ArkBotGUI.GUIText("[ACTION]: Searching inventory");
		p = MouseInfo.getPointerInfo().getLocation();
		drag.move(ArkBot.global.EXT_INV_SEARCHBAR);
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
