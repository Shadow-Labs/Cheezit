import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Tamer {
	Robot bot;
	private Point p;
	MouseDrag drag;
	RobotType robtype;
	boolean taming;
	int time;
	int foodWait;
	public Tamer() throws AWTException {
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
		long foodStart = System.currentTimeMillis();
		long narcStart = System.currentTimeMillis();
		while (taming) {
			// Narc
			if (System.currentTimeMillis() - narcStart > (time * 1000)) {
				//Click First Stack
				drag.move(ArkBot.global.EXT_INV_FIRSTSLOT);
				leftClick();
				
				// Click RemoteUse
				drag.move(ArkBot.global.EXT_INV_REMOTEUSE);
				leftClick();
				
				narcStart = System.currentTimeMillis();
			}
			
			// Food
			if (System.currentTimeMillis() - foodStart > (foodWait * 1000) && foodWait != 0) {
				drag.move(ArkBot.global.EXT_PERSON_INV_FIRSTSLOT);
				leftClick();
				
				drag.move(ArkBot.global.EXT_PERSON_INV_USEITEM);
				leftClick();
				
				foodStart = System.currentTimeMillis();
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
