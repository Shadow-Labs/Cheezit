import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Tamer {
	Robot bot;
	RobotStuff robtype;
	boolean taming;
	float time;
	float foodWait;
	float waterWait;
	public Tamer() throws AWTException {
		bot = ArkBot.bot;
		robtype = new RobotStuff(bot);
		taming = false;
		time = 0;
		foodWait = 0;
		waterWait = 0;
	}
	public void Tamin() {
		if (taming) {
			Tame();
		}
	}
	private void Tame() {
		ArkBotGUI.GUIText("Taming...");
		long foodStart = System.currentTimeMillis();
		long waterStart = System.currentTimeMillis();
		long narcStart = System.currentTimeMillis();
		long inactivityTime = System.currentTimeMillis();
		int waterSlot = 1;
		int waterRep = 0;
		int val;
		while (taming) {
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
			
			// Water
			if (System.currentTimeMillis() - waterStart > (waterWait * 60000) && waterWait != 0) {
				if (waterRep == 3) {
					waterSlot++;
					waterRep = 0;
				}
				
				switch (waterSlot) {
					case 1:	val = KeyEvent.VK_1;
							break;
					case 2: val = KeyEvent.VK_2;
							break;
					case 3: val = KeyEvent.VK_3;
							break;
					case 4: val = KeyEvent.VK_4;
							break;
					case 5: val = KeyEvent.VK_5;
							break;
					case 6: val = KeyEvent.VK_6;
							break;
					case 7: val = KeyEvent.VK_7;
							break;
					case 8: val = KeyEvent.VK_8;
							break;
					case 9: val = KeyEvent.VK_9;
							break;
					default: val = KeyEvent.VK_1;
				}
				ArkBot.bot.keyPress(val);
				
				waterRep++;
				waterStart = System.currentTimeMillis();
			}
			
			// Inactivity
			if (System.currentTimeMillis() - inactivityTime > 15 * 1000) {
				// Exit Inventory
				bot.keyPress(KeyEvent.VK_F);
				bot.delay(500);
				bot.keyRelease(KeyEvent.VK_F);
				bot.delay(1000);
				
				// Jump!
				bot.keyPress(KeyEvent.VK_SPACE);
				bot.delay(500);
				bot.keyRelease(KeyEvent.VK_SPACE);
				bot.delay(Global.PAUSE);
				
				// Open Inventory
				bot.keyPress(KeyEvent.VK_F);
				bot.delay(500);
				bot.keyRelease(KeyEvent.VK_F);
				bot.delay(1000);
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
