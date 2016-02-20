import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MeatSplitter {
	private Robot bot;
	private Point p;
	private int PAUSE;
	MouseDrag drag;
	CharacterActions action;
	public MeatSplitter(Robot bot, Point p, int PAUSE)  throws AWTException {
		this.bot = bot;
		this.p = p;
		this.PAUSE = PAUSE;
		drag = new MouseDrag(p);
		action = new CharacterActions(p, Global.PAUSE);
	}
	public void CharInvSearch() {
		
	}
	
	public void SplitAll() {
		int stacks = action.InvSearch("Fiber");
		if (stacks >= 20) {
			drag.move(Global.INV_SCROLL_BOT);
			leftClick();
			
			
		}
	}
	
	public void SplitStack(Point q, int stacks) {
		if (stacks == 0) {
			ArkBotGUI.GUIText("[ACTION]: No stacks to split");
		} else {
			ArkBotGUI.GUIText("[ACTION]: Splitting stacks");
			
			Point k = new Point(q.x - (int) ((Math.random() * 5) + 5), q.y - (int) ((Math.random() * 10) + 20));
			// WHILE CYAN split,
			drag.move(q);
			bot.keyPress(KeyEvent.VK_CONTROL);
			System.out.println(action.PixelRange(q) + " Split " + stacks);
			while (action.PixelRange(q) && stacks > 0) {
				System.out.println(action.PixelRange(q) + " Split " + stacks);
				drag.move(q);
				bot.delay(PAUSE);
				bot.mousePress(InputEvent.BUTTON1_MASK);
				drag.move(k);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				k = new Point(q.x - (int) ((Math.random() * 5) + 5), q.y - (int) ((Math.random() * 10) + 20));
			}
			bot.keyRelease(KeyEvent.VK_CONTROL);
		}
	}
	
	  private void leftClick()
	  {
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    bot.delay(200);
	  }

}
