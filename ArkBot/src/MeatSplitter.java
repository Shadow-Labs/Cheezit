import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MeatSplitter {
	Robot bot;
	public boolean split;
	public boolean splitting;
	
	public MeatSplitter() {
		bot = ArkBot.bot;
		split = false;
		splitting = false;
	}
	
	public void Splittin() {
		if (split && splitting) {
			Split();
		}
	}
	
	public void Split() {
		int splitcount = 0;
        bot.keyPress(KeyEvent.VK_CONTROL); // Left Control
		while (split && splitting && splitcount <= 21) {
			MousePressDragRelease();
			splitcount++;
		}
		bot.keyRelease(KeyEvent.VK_CONTROL); // Left Control
		splitting = false;
		ArkBotGUI.GUIText("[SPLITTER] Finished Stack Split");
	}
	
	private void MousePressDragRelease() {
		Point start = MouseInfo.getPointerInfo().getLocation();
		bot.keyPress(KeyEvent.VK_CONTROL); // Left Control
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.keyPress(KeyEvent.VK_CONTROL); // Left Control
        for(int i = 0; i <= 5;  i++) {
        	bot.keyPress(KeyEvent.VK_CONTROL); // Left Control
        	bot.mouseMove((start.x + (4*i)), (start.y + (4*i)));
        	bot.keyPress(KeyEvent.VK_CONTROL); // Left Control
        }
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        bot.delay(155); // Prevent Double Clicking?
        bot.mouseMove(start.x, start.y);
	}

}
