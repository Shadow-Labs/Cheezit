import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

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
		bot.keyPress(29); // Left Control
		while (split && splitting && splitcount <= 20) {
			MousePressDragRelease();
			splitcount++;
		}
		bot.keyRelease(29); // Left Control
		splitting = false;
		ArkBotGUI.GUIText("[SPLITTER] Finished Stack Split");
	}
	
	private void MousePressDragRelease() {
		Point start = MouseInfo.getPointerInfo().getLocation();
        
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        for(int i = 0; i <= 10;  i++) {
        	bot.mouseMove((start.x + i), (start.y + i));
        }
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        
        bot.mouseMove(start.x, start.y);
	}

}
